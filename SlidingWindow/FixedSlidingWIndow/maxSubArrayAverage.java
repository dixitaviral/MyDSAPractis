/*
You are given an integer array nums consisting of n elements, and an integer k.

Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.

 

Example 1:

Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
Example 2:

Input: nums = [5], k = 1
Output: 5.00000

Intution:

1. Fixed sliding window me sirf ek pointer use hota hai.
2. Isme window slide krni hai tumko for example arr = [1,2,3,4,5] and k = 3;
    a. First window will be = [1,2,3], second = [2,3,4]
    b. Abhi isme sum nikalna padta hai, so first window ka sum hoga 6(1+2+3) 
    c. Now if we will shift window to second = [2,3,4] by incrementing i = i+1. Then humko puri window ka
        dobara sum krne ki zrurt ni hai.
    d. kyuki hamare pas pehli window ka sum already hai, that 6. Now humko dekhna hai ki next window me
        extra 4 add ho ra hai and 1 minus ho ra hai, so we will do the same.
    e. 6(1+2+3 window sum)-nums[start] and sum me nums[i] jo ki next digit add ho ri hai window me 
        vo loop ke sath sath add ho ra hai. Neeche code dekho. so 6-1+4 = 9, so second window ka sum hua
        9(2+3+4);
3. Abhi zruri cheez, hume pata hai jab k size ki window ban jaegi tbhi sum krna hai, uske liye hum ek "start"
    variable lege, jo tb increase hoga jab window size = k ho jaega.
4. Humko window size maintain krna hai, uske liye check lagega "i-start == k", jo ensure karega ki sum 
    window ke ander hi ho ra hai.
*/


class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double res = Double.NEGATIVE_INFINITY;

        int i = 0;
        int start = 0;

        double temp = 0;

        while(i < nums.length){
            temp += nums[i];
            i++;
            
            if(i - start == k){
                res = Math.max(temp/k,res);
                temp -= nums[start];
                start++;
            }
        }

        return res;
    }
}