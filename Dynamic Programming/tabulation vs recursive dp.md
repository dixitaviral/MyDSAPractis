## Bottom-Up DP vs Recursive DP - Unique Paths (Hinglish)

DP ko samajhne ka ek simple tareeka hai: **same problem ko chhote subproblems me todna aur unke answers reuse karna**.

Is note me hum ek hi problem ko do styles me solve karenge:

1. **Bottom-up DP / Tabulation**: start point se aage badho aur answers table me store karte jao.
2. **Recursive DP / Top-down with memoization**: destination ki taraf jao, phir return hote waqt answers store karo.

> Important: Sirf recursion me answers store nahi hote. Jab recursion ke saath memo array/table use karte hain, tab usse **top-down DP** ya **memoization** kehte hain.

---

## Problem: Unique Paths

Hume ek grid di gayi hai. Start cell `(0, 0)` se destination cell `(2, 2)` tak jaana hai.

Allowed moves:

- Right: `R`
- Down: `D`

Left ya up move allowed nahi hai.

```text
			 col 0   col 1   col 2
		   +-------+-------+-------+
row 0      | Start |       |       |
		   +-------+-------+-------+
row 1      |       |       |       |
		   +-------+-------+-------+
row 2      |       |       |  End  |
		   +-------+-------+-------+
```

Hume count karna hai ki `(0, 0)` se `(2, 2)` tak kitne different paths ban sakte hain.

For example, ek path hai:

```text
(0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2)
  R       R       D       D
```

Total answer `6` hoga.

---

## Core DP Formula

Kisi bhi cell `(row, col)` par hum do directions se aa sakte hain:

```text
1. Upar wale cell se       -> (row - 1, col)
2. Left wale cell se       -> (row, col - 1)
```

Isliye:

```text
ways(row, col) = ways(row - 1, col) + ways(row, col - 1)
```

Yaani current cell tak pahunchne ke paths = upar se aane wale paths + left se aane wale paths.

### Base case

Start cell `(0, 0)` tak pahunchne ka ek hi tareeka hai: wahi se start karna.

```text
ways(0, 0) = 1
```

---

## 1. Bottom-Up DP / Tabulation

### Bottom-up ka idea

Bottom-up me hum **smallest known answer se start** karte hain aur table ko fill karte hue destination ki taraf badhte hain.

Yaha direction natural hai:

```text
Start (0,0) ---------------> Destination (2,2)
			 table fill karo
```

Hum har cell me store karenge:

> Is cell tak pahunchne ke total kitne ways hain?

### Step 1: Start cell fill karo

Start cell tak pahunchne ka answer `1` hai.

```text
			 0       1       2
		   +-------+-------+-------+
row 0      |   1   |   ?   |   ?   |
		   +-------+-------+-------+
row 1      |   ?   |   ?   |   ?   |
		   +-------+-------+-------+
row 2      |   ?   |   ?   |   ?   |
		   +-------+-------+-------+
```

### Step 2: First row fill karo

First row ke cells tak sirf right move karke pahunch sakte hain. Isliye har cell ka answer `1` hai.

```text
			 0       1       2
		   +-------+-------+-------+
row 0      |   1   |   1   |   1   |
		   +-------+-------+-------+
row 1      |   ?   |   ?   |   ?   |
		   +-------+-------+-------+
row 2      |   ?   |   ?   |   ?   |
		   +-------+-------+-------+
```

### Step 3: First column fill karo

First column ke cells tak sirf down move karke pahunch sakte hain. Isliye yaha bhi har answer `1` hai.

```text
			 0       1       2
		   +-------+-------+-------+
row 0      |   1   |   1   |   1   |
		   +-------+-------+-------+
row 1      |   1   |   ?   |   ?   |
		   +-------+-------+-------+
row 2      |   1   |   ?   |   ?   |
		   +-------+-------+-------+
```

### Step 4: Baaki cells formula se fill karo

Cell `(1,1)`:

```text
ways(1,1) = ways(0,1) + ways(1,0)
		  = 1 + 1
		  = 2
```

Cell `(1,2)`:

```text
ways(1,2) = ways(0,2) + ways(1,1)
		  = 1 + 2
		  = 3
```

Cell `(2,1)`:

```text
ways(2,1) = ways(1,1) + ways(2,0)
		  = 2 + 1
		  = 3
```

Cell `(2,2)`:

```text
ways(2,2) = ways(1,2) + ways(2,1)
		  = 3 + 3
		  = 6
```

Final table:

```text
			 0       1       2
		   +-------+-------+-------+
row 0      |   1   |   1   |   1   |
		   +-------+-------+-------+
row 1      |   1   |   2   |   3   |
		   +-------+-------+-------+
row 2      |   1   |   3   |   6   |
		   +-------+-------+-------+
```

### Bottom-up me answer kahan milta hai?

Is example me answer **destination cell `(2,2)`** par milta hai, kyunki humne definition rakhi hai:

> `dp[row][col]` = start se current cell tak pahunchne ke ways.

Isliye final answer:

```text
dp[2][2] = 6
```

Technically ise sirf "last cell" kehna zaroori nahi hai. Answer us cell par milega jo problem ke hisaab se **final destination/state** represent karta hai. Agar DP ki definition different hoti, to answer kisi aur cell ya variable me bhi ho sakta hai. Lekin unique-paths ke is standard version me destination hi final cell hai.

### Bottom-up ka flow yaad rakho

```text
Known small answers
		|
		v
Next cells ke answers calculate aur store
		|
		v
Destination cell par final answer
```

### Java code

```java
class Solution {
	public int uniquePaths(int rows, int cols) {
		int[][] dp = new int[rows][cols];

		for (int row = 0; row < rows; row++) {
			dp[row][0] = 1;
		}

		for (int col = 0; col < cols; col++) {
			dp[0][col] = 1;
		}

		for (int row = 1; row < rows; row++) {
			for (int col = 1; col < cols; col++) {
				dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
			}
		}

		return dp[rows - 1][cols - 1];
	}
}
```

Time complexity: `O(rows * cols)`  
Space complexity: `O(rows * cols)`

---

## 2. Recursive DP / Top-Down with Memoization

### Recursive ka idea

Recursive approach me hum start cell se destination ki taraf sochte hain:

```text
"Mujhe current cell se destination tak kitne ways milenge?"
```

Current cell se hum do choices try karte hain:

```text
1. Right jao
2. Down jao
```

Hum sabse pehle calls ke through destination tak pahunchte hain. Jab destination mil jaata hai, tab recursion return hona start hota hai. **Return hote waqt har call apna answer calculate karke memo table me store karti hai.**

### Recursive definition

```text
ways(row, col) = current cell se destination tak ke ways
```

Base case:

```text
if (row == lastRow && col == lastCol) {
	return 1;
}
```

Grid se bahar chale gaye to koi valid path nahi:

```text
if (row >= rows || col >= cols) {
	return 0;
}
```

Recurrence:

```text
ways(row, col) = ways(row + 1, col) + ways(row, col + 1)
```

---

## Recursive Call Flow: Pehle Destination, Phir Return

Same `3 x 3` grid ke liye starting call:

```text
ways(0, 0)
```

Conceptually call tree kuch aisa dikhega:

```text
ways(0,0)
├── ways(1,0)
│   ├── ways(2,0)
│   │   └── ways(2,1) ...
│   └── ways(1,1)
│       ├── ways(2,1) ...
│       └── ways(1,2) ...
└── ways(0,1)
	├── ways(1,1) ...
	└── ways(0,2) ...
```

Actual execution me recursion ek branch ko depth tak follow karegi. Example:

```text
ways(0,0)
  -> ways(1,0)
	  -> ways(2,0)
		  -> ways(3,0)  // grid ke bahar, return 0
		  -> ways(2,1)
			  -> ways(3,1)  // bahar, return 0
			  -> ways(2,2)  // destination, return 1
```

Ab return hote waqt answers bante hain:

```text
ways(2,2) = 1                         // destination
ways(2,1) = 0 + 1 = 1
ways(2,0) = 0 + 1 = 1
```

Phir doosri branches calculate hoti hain:

```text
ways(1,2) = ways(2,2) + ways(1,3)
           = 1 + 0
           = 1

ways(1,1) = ways(2,1) + ways(1,2)
		   = 1 + 1
		   = 2

ways(0,1) = ways(1,1) + ways(0,2)
		   = 2 + 1
		   = 3
```

Finally start cell par:

```text
ways(0,0) = ways(1,0) + ways(0,1)
		   = 3 + 3
		   = 6
```

Yaha important point:

```text
Destination par base result milta hai: 1
Start point par final answer milta hai: 6
```

Bottom-up me hum **start se destination** tak table fill kar rahe the. Recursive top-down me hum **start se destination tak calls** ja rahe hain, lekin final combined answer recursion ke return hote-hote **start call** par milta hai.

---

## Memoization Table Kaise Fill Hoti Hai?

Recursive version me `memo[row][col]` ka meaning hai:

> Current cell se destination tak ke ways.

Return ke time table store hoti hai:

```text
			 0       1       2
		   +-------+-------+-------+
row 0      |   ?   |   ?   |   ?   |
		   +-------+-------+-------+
row 1      |   ?   |   ?   |   ?   |
		   +-------+-------+-------+
row 2      |   ?   |   ?   |   1   |  <- destination base case
		   +-------+-------+-------+
```

Return hote-hote values store hote hain:

```text
			 0       1       2
		   +-------+-------+-------+
row 0      |   ?   |   ?   |   1   |
		   +-------+-------+-------+
row 1      |   ?   |   2   |   1   |
		   +-------+-------+-------+
row 2      |   1   |   1   |   1   |
		   +-------+-------+-------+
```

End me:

```text
			 0       1       2
		   +-------+-------+-------+
row 0      |   6   |   3   |   1   |
		   +-------+-------+-------+
row 1      |   3   |   2   |   1   |
		   +-------+-------+-------+
row 2      |   1   |   1   |   1   |
		   +-------+-------+-------+
```

Note karo: Ye table bottom-up wali table se different values dikha rahi hai, kyunki dono DP definitions different hain.

- Bottom-up: `dp[row][col]` = start se current cell tak ways
- Top-down: `memo[row][col]` = current cell se destination tak ways

### Java code

```java
import java.util.Arrays;

class Solution {
	public int uniquePaths(int rows, int cols) {
		int[][] memo = new int[rows][cols];

		for (int[] row : memo) {
			Arrays.fill(row, -1);
		}

		return countPaths(0, 0, rows, cols, memo);
	}

	private int countPaths(int row, int col, int rows, int cols, int[][] memo) {
		if (row >= rows || col >= cols) {
			return 0;
		}

		if (row == rows - 1 && col == cols - 1) {
			return 1;
		}

		if (memo[row][col] != -1) {
			return memo[row][col];
		}

		int downPaths = countPaths(row + 1, col, rows, cols, memo);
		int rightPaths = countPaths(row, col + 1, rows, cols, memo);

		memo[row][col] = downPaths + rightPaths;
		return memo[row][col];
	}
}
```

Time complexity with memoization: `O(rows * cols)`  
Space complexity: `O(rows * cols)` for memo + `O(rows + cols)` recursion stack

---

## Bottom-Up vs Recursive DP: Quick Comparison

| Point | Bottom-Up / Tabulation | Recursive / Top-Down Memoization |
|---|---|---|
| Direction | Start se destination | Start se calls, destination taker |
| Calculation | Aage badhte hue | Return hote hue |
| Storage | Pehle known cells, phir next cells | Subproblem ka answer return ke time |
| Final answer | Usually destination/final state par | Usually initial function call par |
| Base case | First row/column ya initial state | Destination ya invalid state |
| Risk | Table order galat hua to issue | Memo na ho to repeated calls |
| Extra memory | DP table | Memo table + recursion stack |

## One-Line Memory Trick

```text
Bottom-up: start se chalo, answer destination par pakdo.
Top-down: destination tak jao, answers return me banao, final answer start par pakdo.
```

Bas ek subtle point yaad rakhna: answer ki location hamesha DP state ki definition par depend karti hai. Is unique-path problem me bottom-up table start-to-cell hai, aur recursive memo current-cell-to-destination hai. Isi wajah se dono approaches me table values aur answer read karne ki jagah alag dikhti hai.
