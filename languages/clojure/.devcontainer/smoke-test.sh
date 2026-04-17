#!/usr/bin/env bash
# Smoke test for the Clojure student devcontainer.
# Run from inside the container:  bash .devcontainer/smoke-test.sh
# Exits 0 if everything works, non-zero on first failure.

set -euo pipefail

EXPECTED_CLJ="1.12.0.1530"
EXPECTED_KONDO="2024.11.14"
PASS=0
FAIL=0

check() {
  local label="$1"; shift
  if "$@" >/dev/null 2>&1; then
    echo "  PASS  $label"
    ((PASS++))
  else
    echo "  FAIL  $label"
    ((FAIL++))
  fi
}

check_output() {
  local label="$1" expected="$2"; shift 2
  local actual
  actual=$("$@" 2>&1)
  if echo "$actual" | grep -qF "$expected"; then
    echo "  PASS  $label"
    ((PASS++))
  else
    echo "  FAIL  $label (expected '$expected', got '$actual')"
    ((FAIL++))
  fi
}

echo "=== Clojure Student Devcontainer Smoke Test ==="
echo

echo "1. Checking Clojure CLI version..."
check_output "clojure version is $EXPECTED_CLJ" "$EXPECTED_CLJ" clojure --version

echo "2. Checking clj-kondo version..."
check_output "clj-kondo version is $EXPECTED_KONDO" "$EXPECTED_KONDO" clj-kondo --version

echo "3. Evaluating trivial Clojure expression..."
RESULT=$(clojure -e '(+ 1 2)' 2>&1)
if [ "$RESULT" = "3" ]; then
  echo "  PASS  (+ 1 2) => 3"
  ((PASS++))
else
  echo "  FAIL  (+ 1 2) => expected '3', got '$RESULT'"
  ((FAIL++))
fi

echo "4. Running first exercise test suite (against solution)..."
EXERCISE_DIR="01-functional-foundations/exercises/01-hello-values"
if [ -d "$EXERCISE_DIR" ]; then
  # Copy solution into the src tree so the test has something to find
  SOLUTION="$EXERCISE_DIR/.solutions/solution.clj"
  TARGET="src/clojure_course/functional_foundations/ex_01_hello_values.clj"
  if [ -f "$SOLUTION" ]; then
    cp "$SOLUTION" "$TARGET"
    if clojure -M:test --focus 'clojure-course.functional-foundations.ex-01-hello-values-test' 2>&1 | tail -1 | grep -q "0 failures"; then
      echo "  PASS  exercise 01 tests pass against solution"
      ((PASS++))
    else
      echo "  FAIL  exercise 01 tests did not pass"
      ((FAIL++))
    fi
    # Restore the starter file
    git checkout -- "$TARGET" 2>/dev/null || true
  else
    echo "  SKIP  solution file not found at $SOLUTION"
  fi
else
  echo "  SKIP  exercise directory not found at $EXERCISE_DIR"
fi

echo
echo "=== Results: $PASS passed, $FAIL failed ==="

if [ "$FAIL" -gt 0 ]; then
  exit 1
fi
