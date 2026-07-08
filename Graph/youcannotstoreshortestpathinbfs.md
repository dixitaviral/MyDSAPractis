**Summary:**

This example shows why BFS alone cannot build a shortest path by just appending dequeued nodes into a list. A BFS queue remembers which nodes to visit next, not the path taken to reach them. So the order nodes are removed from the queue is not the same as a valid path.

Perfect. Let's use a dry run that exposes the flaw. The key is to remember:

> **A BFS queue stores nodes, not paths.**
Consider this graph:

```
           hit
            |
           hot
          /   \
        dot   lot
         |     |
        dog   log
          \   /
           cog
```
The shortest paths are:

```
hit → hot → dot → dog → cog
hit → hot → lot → log → cog
```
Each shortest path has 5 nodes. That is the actual path length from `hit` to `cog`.

Now suppose your BFS does:

```
while (!queue.isEmpty()) {
    String curr = queue.poll();
    shortestPath.add(curr);

    for (String next : neighbors(curr))
        queue.add(next);
}
```
Let's dry run.

---

## Initial state

```
Queue = [hit]

shortestPath = []
```

---

## Iteration 1
Poll:

```
hit
```
Add to list:

```
shortestPath = [hit]
```
Generate neighbors:

```
hot
```
Queue becomes:

```
[hot]
```

---

## Iteration 2
Poll:

```
hot
```
Add to list:

```
shortestPath = [hit, hot]
```
Neighbors:

```
dot
lot
```
Queue:

```
[dot, lot]
```
So far everything still looks like a path.

---

## Iteration 3
Poll:

```
dot
```
Add:

```
shortestPath =
[hit, hot, dot]
```
Neighbor:

```
dog
```
Queue:

```
[lot, dog]
```

---

## Iteration 4
Poll:

```
lot
```
Now look carefully.

You append it:

```
shortestPath =
[hit, hot, dot, lot]
```
Ask yourself:

**Is there an edge between `dot` and `lot`?**

No.

Your list now says

```
hit
 ↓
hot
 ↓
dot
 ↓
lot
```
But in the graph:

```
dot     lot
```
They are siblings.

You just glued two unrelated branches together.

---
Queue becomes

```
[dog, log]
```

---

## Iteration 5
Poll:

```
dog
```
Append:

```
[hit, hot, dot, lot, dog]
```
Again ask:

Is there an edge

```
lot → dog
```
No.

---
Queue:

```
[log, cog]
```

---

## Iteration 6
Poll:

```
log
```
Append:

```
[hit, hot, dot, lot, dog, log]
```
Again,

```
dog → log
```
doesn't exist.

---
Queue:

```
[cog]
```

---

## Iteration 7
Poll:

```
cog
```
Append:

```
[hit, hot, dot, lot, dog, log, cog]
```
You stop.

---

# What did you actually store?
You hoped to store a valid path with 5 nodes:

```
hit
 ↓
hot
 ↓
dot
 ↓
dog
 ↓
cog
```
Instead you stored

```
hit
 ↓
hot
 ↓
dot

lot

dog

log

cog
```
which is simply

> **the order in which BFS removed nodes from the queue.**
Not a path.

---

# Why?
Imagine a queue in BFS.

```
Queue
---------------
dot
lot
dog
log
```
When you pop `lot`, does BFS know

> "I came from hot."
No.

It only knows

```
current = lot
```
It has forgotten how it reached `lot`.

---

# This is the key insight
A queue element is only

```
String word;
```
It is **not**

```
class Node {
    String word;
    Node parent;
}
```
or

```
class State {
    String word;
    List<String> path;
}
```
So when `cog` is finally dequeued, BFS knows:

- ✅ `cog` is reachable.
- ✅ `cog` is at the shortest distance.
- ❌ It does **not** know which sequence of words led to `cog`.
That's why simply appending dequeued words to a list can never reconstruct a shortest path. The queue contains the frontier of the search, not the history of how each node was reached.
