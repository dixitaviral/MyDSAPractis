# Articulation Point — Root Rule Notes

## Sabse important observation
Non-root condition hai:

```text
tin[u] <= low[v]
```

Ab agar `u = ROOT` hai, to:

```text
tin[root] = minimum possible discovery time
```

aur `low[v]` kabhi root ke `tin` se chhota nahi ho sakta.

Isliye root ke kisi bhi DFS child `v` ke liye:

```text
tin[root] <= low[v]
```

**hamesha true hai.**

Yani:

> **Non-root rule root ko distinguish hi nahi kar sakta.**

Root ka **ek bhi DFS child** hua → non-root rule root ko AP mark kar dega.

---

# Ab graph ko 2 categories mein tod

## Category A — Root ke DFS children = 1
Is category mein:

```text
non-root rule  → root AP mark karega
actual root    → AP nahi hai
```

Kyun?

Root hataane ke baad sirf ek DFS branch bachi hai. Us branch ke andar kitne bhi nodes/cycles/back-edges ho, remaining vertices ek hi connected component mein reh sakte hain.

So:

> **1 DFS child ⇒ root is NOT AP.**

Lekin non-root rule phir bhi usko mark karega, because condition automatically true hai.

**Yahi false-positive category hai.**

---

## Category B — Root ke DFS children = 2 or more
Is category mein:

```text
non-root rule  → root AP mark karega
actual root    → root AP hai
```

Kyun?

Root hata:

```text
child-subtree 1
child-subtree 2
child-subtree 3
...
```

DFS tree ki ye branches root ke through connected thi.

Root hataane ke baad ye independent components ban jaati hain.

So:

> **≥ 2 DFS children ⇒ root is AP.**

Yahan non-root rule ka answer coincidentally correct hai.

---

# Toh tera actual question ka complete answer
Root ke DFS children

```text
Non-root rule root ko mark karega?    Root actually AP?
0                                     ❌❌
1                                     ✅❌
≥2                                    ✅✅
```

## Is table se sabse important conclusion
**Non-root rule se root ko NOT mark karne wala koi non-trivial connected graph hai hi nahi.**

Because:

```text
connected graph with >1 vertex
        ↓
DFS root has at least 1 child
        ↓
tin[root] <= low[child] always true
        ↓
root gets marked
```

So root rule ki necessity sirf is baat mein hai ki root ke 1-child aur ≥2-child cases ko distinguish kare.

---

## Aur ek subtle point jo miss nahi karna
Ye **original graph ke ordinary degree** ki baat nahi hai.

Root ke **DFS children** count karne hain.

Matlab:

> "root ke kitne neighbors hain?" ❌
> "DFS traversal mein root se kitni independent child branches nikli?" ✅

Isi liye articulation-point algorithm mein root ke liye:

```text
children >= 2
```

aur non-root ke liye:

```text
tin[u] <= low[v]
```

do alag tests hain.

### Code-style intuition
Agar hum code mein isko compactly likhna chahte hain, to ye idea aa sakti hai:

```text
if (curr == root) {
    if (rootChild < 2) continue;
}

if (tin[cur] <= low[nbr]) {
    markAP(cur);
}
```

Yahan:

- `rootChild < 2` ke case mein root ko non-root rule se treat karne se bachne ke liye loop continue ho jata hai.
- Root ka rule sirf is baat ko handle karta hai ki root ke paas 1 DFS child hai to woh AP nahi hai.
- Lekin agar root ke paas 2 ya 2 se zyada DFS children hain, to woh obvious AP hai.

Isiliye root ke liye hum sirf special case lagate hain, aur baaki logic ko non-root rule ke saath use kar sakte hain.

**Root rule kisi special graph-shape ke liye optional nahi hai.**
Non-trivial connected graph mein non-root rule root par blindly lagaya, to root **har baar mark hoga**; root rule hi decide karega ki woh mark **sahi** hai (`≥2 children`) ya **false positive** (`1 child`).
