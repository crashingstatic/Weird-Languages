# Exercise 06: When to Use Which

## What you're learning

Multimethods and protocols solve overlapping but different problems. This
exercise asks you to convert a multimethod-based design to a protocol-based
one — and then reflect on when you'd choose each.

## The problem

The starter file contains a commented-out multimethod version of a `render`
function that dispatches on a `:type` field. Convert it to use:

1. A `Renderable` protocol with a `render` method
2. `Text`, `Image`, and `Link` records
3. Same behavior: text wraps in `<p>`, image produces `<img>`, link produces `<a>`

In a comment in your solution, explain in 2 sentences when you'd choose the
multimethod version instead.

## The interface

```clojure
(render (->Text "Hello"))         ;=> "<p>Hello</p>"
(render (->Image "pic.jpg" "x"))  ;=> "<img src=\"pic.jpg\" alt=\"x\">"
(render (->Link "http://..." "Click")) ;=> "<a href=\"http://...\">Click</a>"
```

## Run the tests

```bash
clojure -M:test -n clojure-course.polymorphism-and-protocols.ex-06-when-to-use-which-test
```

**Your working file:** [starter.clj](starter.clj) in this folder — a symlink to `src/clojure_course/polymorphism_and_protocols/ex_06_when_to_use_which.clj`.
