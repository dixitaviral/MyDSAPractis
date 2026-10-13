# Recursion — Golden Rules

## Easy explanation

Recursion means solving a big problem by breaking it into a smaller version of the same problem.

Think of it like this:

- First, you solve a smaller problem.
- Then you use that answer to solve the bigger one.

This is why recursion looks like a function calling itself.

---

## The 3 golden rules of recursion

### 1. Base case
This is the stopping point.

If there is no base case, the function keeps calling itself forever and eventually causes a stack overflow.

Example:
- For factorial, when `n` becomes `1`, stop.

### 2. Smaller problem
Each call must move closer to the base case.

Example:
- `n` becomes `n - 1`
- or `n` becomes `n / 2`

If the problem is not getting smaller, recursion will never finish.

### 3. Combine the result
After the smaller problem is solved, use its result to solve the original problem.

Example:
- `factorial(5) = 5 * factorial(4)`

---

## Simple example: factorial

```java
int factorial(int n) {
    if (n == 1) return 1;   // base case
    return n * factorial(n - 1);  // smaller problem + combine
}
```

### How it works
- `factorial(5)` calls `factorial(4)`
- `factorial(4)` calls `factorial(3)`
- and so on until `factorial(1)`
- then all results come back and multiply

---

## Recursion in one line

> Recursion = stop at the base case, go to a smaller problem, and combine the answer on the way back.

---

## Common pattern

When writing recursion, always ask:

1. What is the base case?
2. How does the problem become smaller?
3. How do I combine the result?

---

## Important note

Recursion is very useful for:
- tree problems
- backtracking
- DFS
- divide and conquer
- dynamic programming

If you understand these 3 rules, recursion becomes much easier.
