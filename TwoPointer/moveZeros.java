/**Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.

 

Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
Example 2:

Input: nums = [0]
Output: [0]

Intution:

1. Two pointer's approach:
    a. slow pointer i ko use krna hai for insertion, jo 0's hai left me unko non-zero's se
        replace karna, jab j ko non-zero mile.
    b. fast pointer j ko use karna hai for scanning non-zero elements, jese hi non-zero mile 
        turnt replace kar do.
        
Bonus point jaha confusion hogi:
1. Tum sochoge ki yrr hum to j par zero check kar rahe hai to apne aap i ke pas zero hoga,
    ye kese assume kar re hai?
Ans. Bhai hum i = 0 and j = 0, dono zero se start kar re hai, to agar 0th index par zero ni hota 
    hai, to hum i++, j++ kar dege, jisse pointer move hoga, and usse pehle khud ko hi replace kar dega.
    And agar 0th index par zero hota hai then hum i ko move hi ni kar re, sirf j ko move kar re hai.

**/


class Solution {
    public void moveZeroes(int[] nums) {
        int i = 0;
        int j = 0;

        while(j < nums.length){
           if(nums[j] != 0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j++;
           }else{
            j++;
           }
        }
    }
}