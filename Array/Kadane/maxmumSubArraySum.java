/*
Given an integer array nums, find the subarray with the largest sum, and return its sum.

 

Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.
Example 2:

Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.
Example 3:

Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.

Intution:
1. Bhai ye ques simple hai kadane algo se lagega.
2. Kadance algo simple kehti hai ki:
    1. sum add krte jao.
    2. maxSum store krte jao.
    3. sum jese negative ho usko 0 kar do.
3. Bas itna hi hai.

4. For printing maxSubarray 3 index bana lo, start, ansStart, end.
5. Logic hai ye ki humko pata hai ki hum sum ko 0 kar dete hai jab bhi sum negative aata hai
6. Or tab tak 0 ni krte, jab tak sum negative ni ho jata.
7. To bas start me check lagao ki sum == 0 tab start = i kar do.
8. Jab maxSum ko set kar rahe ho us time ans Start = start and end = i kar do.
9. Ab tumhre man me do ques aaege ki:
    a. end = i bhi kar rahe ho and start = i bhi kar rahe ho.
    b. bhai vo islye kyuki maxSum jab set krte hai jab sum > maxSum hota hai and start = i tab hi set karege
        jab sum == 0 hoga. To ansStart ko i set set krna and end ko i se set krna means 1 ho index ko do bar set 
        kar rahe ho.
    c. Isliye jab sum == 0 hoga tab se lekr jab tak sum vapas 0 ni hota start = i rhega. And jab maxSum < sum hoga
        tab ansStart = start kar dete hai and end = i jo ki current index hai.
*/

class Solution {
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;

        for(int n : nums){
            sum += n; 
            maxSum = Math.max(sum, maxSum);
            if(sum < 0){
                sum = 0;
            }
        }

        return maxSum;
    }
}

// printing maxSubarray

class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;

        int start = 0;
        int ansStart = 0;
        int end = 0;
        int sum = 0;

        for(int i = 0; i < nums.length; i++){
            if(sum == 0) start = i;
            sum += nums[i];

            if(sum > maxSum){
                maxSum = sum;
                end = i;
                ansStart = start;
            }

            if(sum < 0){
                sum = 0;
            }
        }

        System.out.println(ansStart + " "+end);

        return maxSum;
    }
}