# 3 Pointers in Two Pointer - Dutch National Flag Problem

## Problem kya thi?
Ek array diya hota hai jisme sirf 3 values hote hain:
- 0
- 1
- 2

Humein array ko isi tarah arrange karna hota hai:
- sab 0 pehle
- phir sab 1
- phir sab 2

Example:
```java
[2, 0, 1, 2, 0, 1]
```

Sorted result:
```java
[0, 0, 1, 1, 2, 2]
```

---

## Iska naam Dutch National Flag kyun pada?
Iska naam Netherlands ke national flag se aaya hai.
Dutch flag me 3 colors hote hain:
- red
- white
- blue

Yahi concept yahan follow hota hai:
- 0 ko left side me rakha jata hai
- 1 ko middle me rakha jata hai
- 2 ko right side me rakha jata hai

Isliye is problem ko "Dutch National Flag" kaha jata hai.

---

## Simple story samjho
Socho tumhare paas ek line me 3 colors ke balls hain.
Tumhe unhe yeh tarah arrange karna hai:
- sab red pehle
- phir white
- phir blue

Aise hi array me 0, 1, 2 ko arrange karna hota hai.

---

## 3 pointers ka idea
Is problem ko solve karne ke liye hum 3 pointers use karte hain:
- `low` → left side me 0 place karne ke liye
- `mid` → current element ko check karne ke liye
- `high` → right side me 2 place karne ke liye

Ye 3 pointers hum array ko 3 parts me divide karte hain:
1. `0 to low-1` → yahan already 0 hain
2. `low to mid-1` → ye unknown part hai
3. `mid to high` → abhi check karna hai
4. `high+1 to end` → yahan already 2 hain

---

## Har case me kya karte hain?
Jab `mid` par value hoti hai:
- agar value `0` hai → `low` aur `mid` ko swap kar do
- agar value `1` hai → bas `mid` ko aage badhao
- agar value `2` hai → `mid` aur `high` ko swap kar do

Ye process tab tak chalta hai jab `mid` `high` tak pahunch jaye.

---

## Step-by-step example
Input:
```java
[2, 0, 1, 2, 0, 1]
```

### Step 1
`mid` pe `2` mila, toh use right side me bhej diya.

### Step 2
`mid` pe `0` mila, toh use left side me bhej diya.

### Step 3
`mid` pe `1` mila, toh use chhod diya aur aage badh gaye.

Final result:
```java
[0, 0, 1, 1, 2, 2]
```

---

## Java code
```java
class Solution {
    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

---

## Kyun ye approach achha hai?
- Time complexity: O(n)
- Space complexity: O(1)

Iska matlab hai:
- ek hi pass me solve ho jata hai
- extra space nahi lagta

---

## Ek line summary
Dutch National Flag problem ka simple matlab hai:
“0 ko left me, 1 ko middle me, aur 2 ko right me rakho.”

Aur 3 pointers se hum isko fast aur smart tarike se solve karte hain.
