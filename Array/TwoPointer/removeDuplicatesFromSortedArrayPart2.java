/**

Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.

 

Example 1:

Input: nums = [1,1,1,2,2,3]
Output: 5, nums = [1,1,2,2,3,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,0,1,1,1,1,2,3,3]
Output: 7, nums = [0,0,1,1,2,3,3,_,_]
Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

✍️ Intuition: Remove Duplicates from Sorted Array II

*(At most 2 duplicates allowed)*

---

### 1. Two Pointer Approach

* **Slow pointer `i`** → next valid element **kahan likhna hai**
  (ye output ka end / size batata hai)
* **Fast pointer `j`** → pure array ko **scan** karta hai

---

### 2. Rule (Core Idea)

* Har element **max 2 baar** allowed hai
* Agar current element ko likhne se
  **output ke end me 3 same elements ban jaayenge**
  → **skip**
* Warna **write** karo

---

### 3. `nums[i-2]` se compare kyu?

* `i` = output ka size
* `i-2` = output me 2 positions pehle ka element
* Agar `nums[i-2] == nums[j]`
  → already 2 copies likhi ja chuki hain ❌
* Else → likhna safe ✅

---

### 4. Initialization

* `i = 2`, `j = 2`
* Kyunki first 2 elements hamesha allowed hote hain
* Rule 3rd element se apply hota hai

---

### 5. Bonus – Common Confusion

**“Count kyu nahi rakh rahe?”**
→ Sorted array me duplicates saath-saath aate hain
→ Output ke last 2 elements hi kaafi info de dete hain

---

### 🔒 One-Line Memory

> **Slow pointer = output ka end**
> **At most K duplicates → compare with output K positions back**

---

Isko notes me daal do bhai 👍
Ye size interview se pehle revise karne ke liye **perfect** hai.

Next bolo:

* 👉 Sliding Window start karein
* 👉 Phase-0 ka final recap
* 👉 Ek aur Two Pointer brain-opener

Aage jab bole:

“at most 3 duplicates”
  Tum turant sochoge:
  👉 `i-3`


 */



  class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 2;
        int j = 2;

        if(nums.length <= 2) return nums.length;
        // i-2 par isliye check karte hai ki man lo 1,1,1,2,2 hai input
        // to hum i,j = 2 se already start kar re hai
        // ab comparison aise hoga na ki is tarah se socho ki har 1(one) ke liye ek 2(two) hoga
        while(j < nums.length){
            if(nums[i-2] == nums[j]){
                j++;
            }else{
                nums[i] = nums[j];
                i++;
                j++;
            }
        }

        return i;
    }
}