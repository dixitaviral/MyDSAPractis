# Tarjan’s Algorithm: Bridges se Articulation Point tak

Is file ka maksat hai Tarjan algorithm ko simple bhaasha mein samjhana, bilkul waise jaisa `criticalConnections.java` aur `articulationPoint.java` mein likha gaya hai.

## 1. Ek hi idea, do problems

`criticalConnections.java` aur `articulationPoint.java` dono same DFS-based logic use karte hain.

- `criticalConnections.java` -> bridges nikalta hai
- `articulationPoint.java` -> articulation points nikalta hai

Donon mein base line yahi hai:

- `time[node]` = discovery time
- `low[node]` = smallest discovery time reachable from node ke subtree se

Ye arrays Tarjan ka heart hain.

### Bridges aur Articulation Point kya hote hain?

- Bridge: ek aisi edge jo hataane se graph do alag connected components mein bat jaata hai.
  - Agar edge pe depend karke koi subgraph sirf usi edge se connected hai, to vah edge bridge hai.
- Articulation Point: ek aisi vertex jisko hataane se graph ka connectivity toot jaata hai, yani connected component ka count badh jaata hai.
  - Agar vertex hatate hi koi neighbour group root se alag ho jaaye, to vah vertex articulation point hai.

Ye dono problems same DFS se solve hoti hain, bas check alag hai.

## 2. Graph aur arrays set up

Tarjan mein pehle adjacency list banate hain, phir:

- `time[]` ko `-1` se fill karo (not visited)
- `low[]` ko `Integer.MAX_VALUE` se fill karo

Example code style:

```java
int time[] = new int[n];
int low[] = new int[n];
Arrays.fill(time, -1);
Arrays.fill(low, Integer.MAX_VALUE);
```

`criticalConnections.java` mein graph edges ko undirected adjacency list mein store kiya gaya hai.

## 3. DFS ka base step

Har node pe jab aate ho:

```java
counter++;
time[cur] = counter;
low[cur] = counter;
```

Yeh dono values same li jaati hain pehli visit pe.

Phir for each neighbour:

- agar neighbour parent (`prev`) ho to skip karo
- agar neighbour not visited ho to DFS chalao
- agar neighbour visited ho to back-edge ka `low[cur]` update karo

## 4. `low[]` ka simple matlab

`low[cur]` bolta hai:

> "Mera subtree kis sabse chote discovery time wale ancestor tak ja sakta hai?"

Agar `low[cur]` chhota ho gaya, matlab tree se upar koi back-edge aaya.

## 5. Bridges ka rule

Bridge ko identify karne ka condition hai:

```java
if (time[cur] < low[nbr]) {
    // cur-nbr edge bridge hai
}
```

Matlab:

- `nbr` ka subtree `cur` ke aage kisi ancestor tak nahi ja sakta
- to `cur-nbr` edge agar hata diya toh graph disconnect ho jayega

Example example `criticalConnections.java` se:

Graph:

```
0 - 1 - 3
 \ /
  2
```

Aur agar `1-3` alag branch hai, toh `1-3` bridge ho sakta hai.

### Real Example

Input:

```
n = 4
connections = [[0,1],[1,2],[2,0],[1,3]]
```

Output:

```
[[1,3]]
```

Kyuki `1-3` edge agar hatao toh node 3 disconnected ho jaata hai.

## 6. Articulation point ka rule

Articulation point ke liye check thoda different hai.

### Non-root ke liye:

```java
if (low[nbr] >= time[cur]) {
    // cur articulation point hai
}
```

Yeh matlab hai:

- `nbr` ke subtree se koi back-edge aisa nahi jo `cur` se pehle ka ancestor dekhaye
- agar `cur` hata diya, toh `nbr` wali subtree alag ho jayegi

### Root ke liye special case:

Agar root ke 2 ya zyada direct children hain, toh root articulation point ban sakta hai.

```java
if (cur == root ) {
    if(rootChildCount >= 2)
    // root articulation point hai
}
```

Ye `articulationPoint.java` mein root count ke through implement hua hai.

## 6a. Bridge finding se Articulation Point finding ka transition

Ab dekhte hain ki bridge wala Tarjan algorithm kaise articulation point algorithm mein badalta hai.

1. Same base arrays use ho rahi hain: `time[]` aur `low[]`.
2. Same DFS traversal aur same parent skip logic hai.
3. Difference sirf yeh hai ki:
   - bridge problem mein hum edge `(cur, nbr)` ko judge karte hain
   - articulation point problem mein hum vertex `cur` ko judge karte hain
4. Bridge condition:

```java
if (time[cur] < low[nbr]) {
    // edge bridge hai
}
```

5. Articulation condition:

```java
if (low[nbr] >= time[cur]) {
    // cur vertex articulation point hai
}
```

6. Yeh dono conditions similar dikhte hain, lekin logic alag hai:
   - bridge case mein `nbr` subtree ka `low` value check karte hain, agar vo `cur` ke time se bada hai to edge ka koi alternate route nahi
   - AP case mein `nbr` subtree ka `low` value check karte hain, agar vo `cur` ke time se bada ya barabar hai to `cur` ko remove karne par subtree upar nahi ja sakta
7. Root node case:
   - bridge mein root special nahin hota
   - AP mein root ko 2+ children hone chahiye, tab root articulation point banega

Isi tarah, Tarjan bridge algorithm ko thoda modify kar ke articulation point algorithm ban jaata hai.

## 7. Bridge vs Articulation point: farq samjho

Same DFS, same arrays, bas condition change hoti hai.

- Bridge check karta hai edge `(cur, nbr)` ko
- Articulation point check karta hai vertex `cur` ko

### Agar graph me cycle ho

Cycle wale components me:

- bridge nahin milta
- articulation point bhi nahin milta (unless koi root special case ho)

Kyuki cycle me subtree wapas ancestor tak back-edge se ja sakta hai.

## 8. Example se step-by-step transition

Let us take simple graph:

```
0 - 1 - 3
 \ /
  2
```

Traversal ka order ho sakta hai:

- `0` pe aate hi: `time[0]=1`, `low[0]=1`
- `1` pe: `time[1]=2`, `low[1]=2`
- `2` pe: `time[2]=3`, `low[2]=3`
- `2` ka neighbour `0` visited hai, to `low[2] = min(3, time[0]=1) = 1`
- wapas `1` pe: `low[1] = min(2, low[2]=1) = 1`

Ab check:

- Bridge ke liye `time[1] < low[2]` ? => `2 < 1` false → koi bridge nahi
- AP ke liye `low[2] >= time[1]` ? => `1 >= 2` false → `1` AP nahi

Yeh dikhata hai ki
- cycle wali edge bridge nahi hoti
- same vertex AP bhi nahi hota

Agar graph me `3` sirf `1` se connected ho aur `3` ka koi back-edge na ho, tab:

- `1-3` bridge ban jayega
- `1` shayad articulation point ban sakta hai

## 9. Root special case samjho

Agar root node ke 2 ya zyada separate DFS children hain, root AP hota hai.

Example:

```
  0
 / \
1   2
```

Yahaan 0 root hai, aur uske do children hain.
Agar 0 remove ho jaye, graph 2 parts me divide ho jayega.

Isliye root ke liye condition alag hai.

## 10. Simple teaching points

- `time[]` = discovery order
- `low[]` = subtree se agar koi ancestor reachable ho sakta hai toh sabse choti discovery time
- `bridge` = edge jiske baad `nbr` subtree upar nahi ja sakta
- `AP` = vertex jiske baad `nbr` subtree us vertex ke pehle ancestor tak wapas nahi ja sakta
- `root` = agar 2+ child, toh AP ho sakta hai

## 11. Kaise yaad rakhe?

Bhai, Tarjan ko do line mein yaad rakho:

- `time[cur] < low[nbr]` → edge bridge
- `low[nbr] >= time[cur]` → vertex `cur` cut vertex

Aur root ke liye `child count >= 2`.

## 12. Summary

1. Dono problems me same base DFS hai
2. `criticalConnections.java` sirf edge bridge identify karta hai
3. `articulationPoint.java` vertex ko check karta hai
4. difference sirf condition hai, logic same hi rehta hai

Agar tum chaaho toh main yahan ek aur example graph ka full DFS trace likh sakta hoon jisme `time[]`, `low[]`, `bridge`, aur `AP` dono dikhaye jaayen.
