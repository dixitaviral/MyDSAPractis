# BFS: Distance vs Path — common confusion

Ye confusion bahut common hai. BFS ke baare mein teen alag cheezein hoti hain:

1. Shortest distance
2. Shortest path
3. BFS traversal ka output

Aur in teeno ko aksar log mix kar dete hain.

---

## 1) Shortest distance ✅

BFS source se har node tak minimum number of edges batata hai.

Example:

```
A -- B -- D
 \      
  C -----
```

Source = A

Then BFS will say:

- dist[A] = 0
- dist[B] = 1
- dist[C] = 1
- dist[D] = 2

So D tak minimum distance = 2 edges.

---

## 2) Shortest path ✅

Agar BFS ke time parent[] maintain karte ho, to actual shortest route bhi reconstruct kar sakte ho.

Example:

```
parent[B] = A
parent[C] = A
parent[D] = B
```

To D ka path:

```
D -> B -> A
```

Reverse kar do:

```
A -> B -> D
```

Ye shortest path hai.

---

## 3) Kya BFS khud path store karta hai? ❌

Nahi.

Normal BFS sirf traversal karta hai. Queue me nodes jaate hain, aur queue unhe visit karne ke liye rakhti hai. Queue ko sirf nodes ka frontier samjho, path ka history nahi.

Agar aap sirf is tarah likhoge:

```java
while (!queue.isEmpty()) {
    String curr = queue.poll();
    path.add(curr);

    for (String next : neighbors(curr)) {
        queue.add(next);
    }
}
```

To jo list banti hai, wo sirf dequeue order hoti hai — ye valid path nahi hoti.

---

## Why this fails

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

Shortest paths are:

```
hit -> hot -> dot -> dog -> cog
hit -> hot -> lot -> log -> cog
```

Agar aap queue se nodes ko bas append karte ja rahe ho, to aapko ye sequence mil sakti hai:

```
hit, hot, dot, lot, dog, log, cog
```

Ye sequence BFS ke dequeue order ka representation hai, na ki ek valid path.

Kyun? Kyunki `dot` aur `lot` sibling hain, aur unka ek dusre se edge nahi hai.

---

## Correct way to think

- Distance chahiye? -> use dist[]
- Actual path chahiye? -> use parent[] (or store path state)

So these two statements can both be true:

- ✅ BFS gives shortest distance in an unweighted graph
- ✅ BFS can also give shortest path if we store parent information

---

## Important correction

Ye statement galat lag sakta hai:

> "BFS se shortest path nahi banta"

Ye sirf tab sahi hota hai jab aap simple traversal ke output ko path samajh rahe ho.

Lekin agar aap parent array ya path tracking ke saath BFS use karte ho, to BFS shortest path bhi nikal sakta hai.

---

## Final shortcut

- Shortest distance = dist[]
- Shortest path = parent[] + dist[]
- Queue alone = nodes to visit, not the full path

---

## Weighted graph ka note

Unweighted graph me BFS kaam karta hai.

Weighted graph me shortest path ke liye Dijkstra ya Bellman-Ford use karte hain.
