#!/usr/bin/env bash
# Smoke test for the Clojure student devcontainer.
# Run from inside the container:  bash .devcontainer/smoke-test.sh
# Exits 0 if every check passes, 1 otherwise. On failure, the full output of
# the failing command is printed so you can see *why* it failed.

set -uo pipefail

EXPECTED_CLJ="1.12.0.1530"
EXPECTED_KONDO="2024.11.14"
PASS=0
FAIL=0

# Print a captured multi-line output block, indented, with a dim separator
# before it so the failure stands out visually.
print_output_block() {
  local output="$1"
  if [ -z "$output" ]; then
    echo "        (command produced no output)"
    return
  fi
  echo "        ---"
  # Indent every line by 8 spaces so the block is clearly "attached" to the
  # FAIL line above it.
  printf '%s\n' "$output" | sed 's/^/        /'
  echo "        ---"
}

# Run a command; mark PASS/FAIL based on its exit code. On FAIL, dump the
# captured stdout+stderr so the student can see what broke.
run_check() {
  local label="$1"; shift
  local output
  local rc=0
  output=$("$@" 2>&1) || rc=$?
  if [ "$rc" -eq 0 ]; then
    echo "  PASS  $label"
    PASS=$((PASS + 1))
  else
    echo "  FAIL  $label (exit $rc)"
    print_output_block "$output"
    FAIL=$((FAIL + 1))
  fi
}

# Run a command and require its output to contain a specific substring.
# Dumps the full output on FAIL, not just the one line that matched.
check_output_contains() {
  local label="$1" expected="$2"; shift 2
  local output
  local rc=0
  output=$("$@" 2>&1) || rc=$?
  if [ "$rc" -ne 0 ]; then
    echo "  FAIL  $label (command exited $rc)"
    print_output_block "$output"
    FAIL=$((FAIL + 1))
    return
  fi
  if printf '%s' "$output" | grep -qF "$expected"; then
    echo "  PASS  $label"
    PASS=$((PASS + 1))
  else
    echo "  FAIL  $label (expected to find '$expected' in output)"
    print_output_block "$output"
    FAIL=$((FAIL + 1))
  fi
}

# Check that a command exists on PATH. Dedicated helper so the error message
# is obvious: "command not found" is the #1 cause of a failing smoke test.
check_command_exists() {
  local cmd="$1"
  if command -v "$cmd" >/dev/null 2>&1; then
    echo "  PASS  '$cmd' is on PATH ($(command -v "$cmd"))"
    PASS=$((PASS + 1))
  else
    echo "  FAIL  '$cmd' not found on PATH"
    echo "        PATH=$PATH"
    echo "        (are you running this inside the devcontainer?)"
    FAIL=$((FAIL + 1))
  fi
}

echo "=== Clojure Student Devcontainer Smoke Test ==="
echo

echo "1. Checking tools are on PATH..."
check_command_exists clojure
check_command_exists clj-kondo

echo
echo "2. Checking Clojure CLI version is $EXPECTED_CLJ..."
check_output_contains "clojure --version contains $EXPECTED_CLJ" "$EXPECTED_CLJ" clojure --version

echo
echo "3. Checking clj-kondo version is $EXPECTED_KONDO..."
check_output_contains "clj-kondo --version contains $EXPECTED_KONDO" "$EXPECTED_KONDO" clj-kondo --version

echo
echo "4. Evaluating a trivial Clojure expression..."
eval_rc=0
# Use -M -e (not implicit clojure.main) to avoid the deprecation warning
# that would otherwise pollute the captured output.
eval_output=$(clojure -M -e '(+ 1 2)' 2>&1) || eval_rc=$?
# The Clojure CLI may print warnings (locale, deps resolution) before the
# result; the actual value is on the last line.
eval_last=$(printf '%s' "$eval_output" | tail -n 1)
if [ "$eval_rc" -eq 0 ] && [ "$eval_last" = "3" ]; then
  echo "  PASS  (+ 1 2) => 3"
  PASS=$((PASS + 1))
else
  echo "  FAIL  (+ 1 2) => expected last line '3', got last line '$eval_last' (exit $eval_rc)"
  print_output_block "$eval_output"
  FAIL=$((FAIL + 1))
fi

echo
echo "5. Running exercise 01's test suite against its reference solution..."
EXERCISE_DIR="01-functional-foundations/exercises/01-hello-values"
SOLUTION="$EXERCISE_DIR/.solutions/solution.clj"
TARGET="src/clojure_course/functional_foundations/ex_01_hello_values.clj"

if [ ! -d "$EXERCISE_DIR" ]; then
  echo "  SKIP  exercise directory not found at $EXERCISE_DIR"
elif [ ! -f "$SOLUTION" ]; then
  echo "  SKIP  solution file not found at $SOLUTION"
elif [ ! -f "$TARGET" ]; then
  echo "  SKIP  target starter file not found at $TARGET"
else
  # Back up the student's current file to a temp location and register a
  # trap so we restore it no matter what — including on Ctrl-C or if the
  # test runner crashes. This works whether or not git is available inside
  # the container (the student devcontainer mounts only languages/clojure/,
  # so .git/ isn't visible).
  BACKUP=$(mktemp)
  cp "$TARGET" "$BACKUP"
  restore_target() {
    if [ -f "$BACKUP" ]; then
      cp "$BACKUP" "$TARGET"
      rm -f "$BACKUP"
    fi
  }
  trap restore_target EXIT INT TERM

  test_rc=0
  cp "$SOLUTION" "$TARGET"
  # cognitect.test-runner uses -n (--namespace), not --focus.
  test_output=$(clojure -M:test -n clojure-course.functional-foundations.ex-01-hello-values-test 2>&1) || test_rc=$?

  restore_target
  trap - EXIT INT TERM

  if [ "$test_rc" -eq 0 ] && printf '%s' "$test_output" | grep -qE '0 failures,? 0 errors'; then
    echo "  PASS  exercise 01 tests pass against solution"
    PASS=$((PASS + 1))
  else
    echo "  FAIL  exercise 01 tests did not pass (exit $test_rc)"
    print_output_block "$test_output"
    FAIL=$((FAIL + 1))
  fi
fi

echo
echo "=== Results: $PASS passed, $FAIL failed ==="

if [ "$FAIL" -gt 0 ]; then
  exit 1
fi
