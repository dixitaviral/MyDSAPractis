
# Grid BFS Cheat Sheet (DSA)

Ye document grid-based BFS problems ke **common patterns** ko simple Hinglish me explain karta hai.

Examples:
- Rotting Oranges
- 01 Matrix
- Shortest Path in Binary Matrix
- As Far From Land As Possible

---

# 1. Grid ka meaning samjho

Most grid problems me values ka meaning hota hai:

```
0 → open / water / empty
1 → wall / land / blocked
```

Har problem me **values ka role pehle samajhna zaroori hai**.

---

# 2. BFS kab use karte hain

BFS use hota hai jab:

- shortest distance chahiye
- step-by-step expansion hota hai
- har move ka cost same hota hai

BFS wave jaisa behave karta hai:

```
source → layer1 → layer2 → layer3
```

---

# 3. Directions ka rule, add to current index to propagate

## 4 Direction movement

```
down  (1,0)
up    (-1,0)
right (0,1)
left  (0,-1)
```

Common in:
- Number of Islands
- Rotting Oranges
- 01 Matrix

---

## 8 Direction movement

```
down       (1,0)
up         (-1,0)
right      (0,1)
left       (0,-1)
down-right (1,1)
up-right   (-1,1)
down-left  (1,-1)
up-left    (-1,-1)
```

Common in:
- Shortest Path in Binary Matrix

---

# 4. BFS ka common template

```
Queue queue

Step 1: source cells queue me daalo

while(queue not empty):

    cell = queue.poll()

    neighbours explore karo

    valid neighbour ho:
        queue me add karo
```

---

# 5. Valid cell check

Har neighbour ke liye check:

```
row >= 0
col >= 0
row < rows
col < cols
```

Aur:

```
cell blocked na ho
cell visited na ho
```

---

# 6. Distance propagate rule

Parent cell ka distance:

```
d
```

Neighbour ka distance:

```
d + 1
```

Example:

```
grid[row][col] = grid[i][j] + 1
```

---

# 7. Visited mark karna

Visited mark karne ke 3 tareeke:

### Method 1
```
boolean visited[][]
```

### Method 2
grid value change

```
grid[row][col] = -1
```

### Method 3
distance store kar do

```
grid[row][col] = distance
```

---

# 8. Multi-source BFS

Kabhi **multiple starting points** hote hain.

Example:

- Rotting Oranges
- 01 Matrix
- As Far From Land As Possible

Is case me:

```
sab sources queue me daalo
BFS ek saath spread karega
```

---

# 9. BFS complexity

```
Time Complexity  : O(m × n)
Space Complexity : O(m × n)
```

Reason:

```
har cell maximum 1 baar queue me aata hai
```

---

# 10. Grid BFS problem families

## Component exploration

Use DFS / BFS

Problems:

- Number of Islands
- Max Area of Island

---

## Distance propagation

Use **Multi-source BFS**

Problems:

- Rotting Oranges
- 01 Matrix
- As Far From Land As Possible

---

## Shortest path

Use **normal BFS**

Problems:

- Shortest Path in Binary Matrix

---

# One Golden Rule

Grid BFS me **distance always parent + 1 hota hai**.

```
next = current + 1
```

Is rule se **90% grid BFS problems solve ho jati hain.**
