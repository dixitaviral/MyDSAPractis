/** Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.

Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
Return k.
Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int val = ...; // Value to remove
int[] expectedNums = [...]; // The expected answer with correct length.
                            // It is sorted with no values equaling val.

int k = removeElement(nums, val); // Calls your implementation

assert k == expectedNums.length;
sort(nums, 0, k); // Sort the first k elements of nums
for (int i = 0; i < actualLength; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.

 

Example 1:

Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
Note that the five elements can be returned in any order.
It does not matter what you leave beyond the returned k (hence they are underscores).


Intution:

1. Two pointer's approach:
    a. slow pointer i ko use krna hai for insertion, jo vals hai left me unko non-val's se
        replace karna, jab j ko non-val mile.
    b. fast pointer j ko use karna hai for scanning non-val elements, jese hi non-val mile 
        turnt replace kar do.
        
Bonus point jaha confusion hogi:
1. Tum sochoge ki yrr hum to j par val check kar rahe hai to apne aap i ke pas val hoga,
    ye kese assume kar re hai?
Ans. Bhai hum i = 0 and j = 0, dono val se start kar re hai, to agar 0th index par val ni hota 
    hai, to hum i++, j++ kar dege, jisse pointer move hoga, and usse pehle khud ko hi replace kar dega.
    And agar 0th index par val hota hai then hum i ko move hi ni kar re, sirf j ko move kar re hai.

**/

class Solution {
    public int removeElement(int[] nums, int val) {
        int i = 0;

        int j = 0;


        while(j < nums.length){
            if(nums[j] != val){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j++;
            }else{
                j++;
            }
        }
        

        return i;
    }
}