# 🌳 Prim’s Algorithm — Bhai, friendly style mein samjho

## 💡 Prim’s kya hai?

`Prim's algorithm` ek greedy technique hai jo **MST** banane ke liye use hoti hai.

### Seedha baat:

- Graph ka ek node chun lete hain start ke liye.
- Fir har step mein us edge ko lete hain jo **sabse sasta** ho aur abhi tak tree mein nahi hai.
- Jarurat ke edges ko add karte karte ek **Minimum Spanning Tree** ban jaata hai.

> Prim’s basically ek component se shuru karta hai, aur har baar us component se bahar sabse mehenga nahi, sabse sasta edge pakad leta hai.

---

## 🧠 Prim’s ka idea

Kuch is tarah socho:

- Ek tree ban raha hai.
- Tree ke aas-paas jitne bhi edges available hain, unme se **sabse chhota edge** uthao.
- Agar wo edge tree ko ek naye vertex se jodta hai, toh use lao.
- Continue karo jab tak sab nodes tree mein na aa jaayein.

### Short rule:

- `choose minimum edge from current tree to outside vertex`
- `avoid cycles`
- `tree gradually bada hota hai`

---

## 🔥 Prim’s algorithm ka step-by-step flow

1. Kisi ek vertex se start karo.
2. Use `visited` mark karo.
3. Us vertex se connected sab edges ko priority mein daalo (min-heap / priority queue).
4. Sabse chhota edge uthao.
5. Agar us edge ka doosra node abhi tak `visited` nahi hai:
   - edge ko MST mein add karo
   - node ko `visited` mark karo
   - us node se connected naye edges queue mein daalo
6. Repeat karo jab tak sab nodes include na ho jaayein.

---

## 📘 Example with graph

Graph lete hain:

```
    1
   / \
  2   3
   \ / \
    4   5
```

Edges aur weights:

- `1 - 2` = `2`
- `1 - 3` = `3`
- `2 - 4` = `1`
- `3 - 4` = `4`
- `3 - 5` = `2`
- `4 - 5` = `3`

### Step 1: Start kahi se bhi karo

Start karte hain node `1` se.

Visited: `{1}`

Available edges:
- `1 - 2` (2)
- `1 - 3` (3)

### Step 2: Sabse chhota edge le lo

Sabse chhota edge hai `1 - 2` (2).

- `2` outside vertex hai → add karo MST mein
- mark `2` as visited

MST edges: `1 - 2`

Visited: `{1, 2}`

### Step 3: Naye edges add karo

Ab queue mein hai:
- `1 - 3` (3)
- `2 - 4` (1)

Sabse chhota: `2 - 4` (1)

- `4` outside hai → add karo
- `4` ko visited karo

MST edges: `1 - 2`, `2 - 4`

Visited: `{1, 2, 4}`

### Step 4: Next cheapest edge 

Queue mein ab hai:
- `1 - 3` (3)
- `3 - 4` (4)
- `4 - 5` (3)

Smallest edge hua: `1 - 3` (3)

- `3` outside hai → add karo
- `3` ko visited karo

MST edges: `1 - 2`, `2 - 4`, `1 - 3`

Visited: `{1, 2, 3, 4}`

### Step 5: Ab last node 5

Queue mein ab hai:
- `3 - 4` (4)
- `4 - 5` (3)
- `3 - 5` (2)

Sabse chhota: `3 - 5` (2)

- `5` outside hai → add karo
- `5` visited karo

MST edges: `1 - 2`, `2 - 4`, `1 - 3`, `3 - 5`

Visited: `{1, 2, 3, 4, 5}`

### Complete ho gaya!

Total edges = `V - 1 = 4`.

### Final MST edges:

- `1 - 2` (2)
- `2 - 4` (1)
- `1 - 3` (3)
- `3 - 5` (2)

**Total weight = `2 + 1 + 3 + 2 = 8`**

---

## 🎨 Friendly summary

- Prim’s ek tarah ka **greedy growing tree** algorithm hai.
- Start kisi bhi node se kar sakte ho.
- Har step mein **current tree ke bahar se sabse sasta edge** choose karo.
- Jo node already tree mein hai, usko dobara mat le.
- Jab sab nodes connect ho jaayein, tab finish.

---

## 🧩 Prim’s vs Kruskal

- `Prim’s` tree ko **ek side se grow** karta hai.
- `Kruskal` sab edges mein se **lowest edge choose** karta hai.
- Prim’s achha hai jab graph dense ho.
- Kruskal achha hai jab graph sparse ho.

---

## 🔍 Dijkstra vs Prim’s — easy language

- **Dijkstra** = Tum city me travel kar rahe ho aur ghar se har city tak **minimum travel cost** dhoond rahe ho.
- **Prim’s** = Tum sab gharon ko **electric wire** se connect karna chahte ho minimum total wire length me.

Dono me PQ hai, lekin goal alag:

- **Dijkstra** → PQ orders by **distance from source**
- **Prim’s** → PQ orders by **edge weight**

Important difference:

- **Dijkstra**: Relaxation: `dist[u] + wt < dist[v]`
- **Prim’s**: Bas cheapest edge choose karo jo tree ke bahar wale node ko connect kare

Aur yeh bhi yaad rakho:

- **Dijkstra** → Path matters
- **Prim’s** → Path doesn’t matter
- **Dijkstra** → Distance array mandatory
- **Prim’s** → Distance array optional (often `visited` + PQ is enough)

---