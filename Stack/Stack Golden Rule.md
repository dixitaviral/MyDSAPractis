# Monotonic Stack — Final Notes (Meri Language Me)

## 0. Sabse bada realization

Stack ka question = **order maintain karna**
Max/min dhoondhna main kaam nahi hota.
**Order tootega → pop hoga.**

---

## 1. Golden rule

Har monotonic stack question me sirf 2 choice:

* Increasing stack maintain karo
* Decreasing stack maintain karo

Poora question isi decision par depend karta hai.

---

## 2. Increasing vs Decreasing ka real matlab

### Increasing stack (bottom → top increasing)

Example:

```
2, 5, 9
```

Meaning:

* nearest **smaller** element nikalne ke kaam aata hai
* global max store nahi hota
* sirf **useful order** store hota hai

Use in:

* Subarray Minimums
* Histogram
* Next Smaller Element

---

### Decreasing stack

Example:

```
9, 5, 2
```

Meaning:

* nearest **greater** element nikalne ke kaam aata hai

Use in:

* Next Greater Element
* Daily Temperatures
* Stock Span

---

## 3. Pop ka real matlab (most important)

Pop ka matlab:

**“current element ne previous ka future destroy kar diya.”**

NOT:

* bada mil gaya
* chhota mil gaya

Sirf:

* **ab useless hai → pop**

---

## 4. Subarray Minimums ka core idea

Har element secretly bolta hai:

**“main kitni subarrays ka minimum hoon?”**

Na ki:
**“global minimum kya hai?”**

---

## 5. Contribution formula (heart)

Har index `mid` ke liye:

```
answer += arr[mid] * left * right
```

Where:

* left = kitne elements left me jahan tak ye minimum reh sakta hai
* right = kitne elements right me jahan tak ye minimum reh sakta hai

Total subarrays jahan mid minimum hai:

```
left * right
```

---

## 6. Left & Right ka real meaning

* Left = **previous smaller element tak distance**
* Right = **next smaller element tak distance**

Ye dono:

* brute force se O(n²)
* stack se **O(n)**

---

## 7. Same family problems

| Problem          | Element ka role      |
| ---------------- | -------------------- |
| Histogram        | rectangle height     |
| Rain Water       | container floor      |
| Subarray Minimum | minimum contribution |

Teenon me **same monotonic stack logic**.

---

## 8. Time complexity ka secret

For loop + inner while loop hone ke baad bhi:

```
O(n)
```

Kyunki:

* har index **1 baar push**
* har index **1 baar pop**

Total operations ≤ **2n**

Isko bolte hain:

**Amortized O(n)**

---

## 9. Final one-line summary (lock)

**Monotonic stack = nearest boundary machine**
**Contribution problems = boundary × boundary math**

---

## Status

Agar ye samajh aa gaya:

**Stack chapter DONE.**
