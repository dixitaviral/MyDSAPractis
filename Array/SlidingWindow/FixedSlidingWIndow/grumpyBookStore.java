/*
There is a bookstore owner that has a store open for n minutes. You are given an integer array customers of length n where customers[i] is the number of the customers that enter the store at the start of the ith minute and all those customers leave after the end of that minute.

During certain minutes, the bookstore owner is grumpy. You are given a binary array grumpy where grumpy[i] is 1 if the bookstore owner is grumpy during the ith minute, and is 0 otherwise.

When the bookstore owner is grumpy, the customers entering during that minute are not satisfied. Otherwise, they are satisfied.

The bookstore owner knows a secret technique to remain not grumpy for minutes consecutive minutes, but this technique can only be used once.

Return the maximum number of customers that can be satisfied throughout the day.

 

Example 1:

Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3

Output: 16

Explanation:

The bookstore owner keeps themselves not grumpy for the last 3 minutes.

The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.

Example 2:

Input: customers = [1], grumpy = [0], minutes = 1

Output: 1

Bhai 👍
Main **tumhari hi language + structure** me, **easy + readable** intuition likh raha hoon.
Medium size, notes-friendly, no over-explanation.

---

## **Intuition (Grumpy Bookstore Owner)**

1. **Solution ke do parts hote hain:**

   **a. Hamesha satisfied customers (base case):**

   * Jab owner grumpy **nahi** hota (`grumpy[i] == 0`),
     tab jo customers aate hain wo hamesha satisfied hote hain.
   * Isliye hum seedha:

     ```
     resTwo += customers[i]
     ```

     kar dete hain.

   **b. Extra customers jo hum ek window se gain kar sakte hain:**

   * Question me diya hai ki **`minutes` ke liye lagatar** owner grumpy nahi hoga.
   * Isko hum **fixed sliding window** ki tarah treat karte hain.
   * Window ke andar ka **maximum extra benefit** nikalna hai.

---

2. **Sliding window ka kaam (extra benefit ke liye):**

   * Har index par:

     * pehle `temp` me value add karte hain
     * jab window size `minutes` ho jaati hai (`i - start + 1 == minutes`)

       * `resOne = max(resOne, temp)`
       * phir window slide karne ke liye
         `temp` me se `customers[start]` minus karke `start++`

---

3. **Main catch (double counting ka issue):**

   * Jab `grumpy[i] == 0` hota hai,
     to wo customers hum pehle hi `resTwo` me add kar chuke hote hain.
   * Agar unko sliding window (`temp`) me bhi add kar diya,
     to **same customers do baar count ho jaayenge** ❌

---

4. **Is problem ka smart trick:**

   * `temp` me **sirf wahi customers add karo**
     jahan `grumpy[i] == 1` ho.
   * Kyunki:

     * ye wahi customers hain jo normally unsatisfied hote
     * aur `minutes` wali window me satisfied ban sakte hain

---

5. **Final answer:**

   * `resTwo` = hamesha satisfied customers
   * `resOne` = window se mila max extra benefit
   * **Total = `resTwo + resOne`**

---

### 🔒 One-line yaad rakhne ke liye

```
Base satisfaction + window se extra gain
```


*/

class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int i = 0;
        int start = 0;

        int resOne = 0;
        int temp = 0;

        int resTwo = 0;

        while(i < customers.length){
            resTwo += grumpy[i] == 0 ? customers[i] : 0;
            temp += grumpy[i] != 0 ? customers[i] : 0;
            

            if(i - start + 1 == minutes){
                resOne = Math.max(resOne, temp);
                temp -= grumpy[start] != 0 ? customers[start] : 0;
                start++;
            }

            i++;
        }

        return resOne+resTwo;

    }
}