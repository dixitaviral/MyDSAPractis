/*
Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2
 

Intution:
1. Bhai ye ques hai prefix sum pattern ka.
2. Ques smjhane ki zrurt hai ni straight forward hai.
3. Baat krte hai intution ki:
    a. Sbse pehle logic smjhte hai:
        i. Karna ye hai ki index 0 se start karo and ek sare elements ko har loop cylce me add krte raho.
        ii. Sath hi sath 3 har cycle me vo sum ko ek map me store krte jao, with that curSum frequency.
        iii. Abhi 2 second step me map me store krne se pehle ye check kar lo, ki jo curSum-k hai, 
            aisi koi entry map me hai, if yes, then vo entry kitni bar aai, usko count me add kar do.
        iv. Yhi hua prefix sum pattern.
        v. Ek important step, map ko initialize krne ke bad ek default entry krna zaruri hai,
            jo ki hai map.put(0,1).
        vi. Ye isliye ki man lo k and curSum equal ho gye, to unka diff 0 aaega, which means ki ek
            valid subarray mil gaya hai, to uska count bhi add krna hai.
    b. Abhi upr jo logic hai usi ko neeche implement kiya hai. That's it
*/

class Solution {
    public int subarraySum(int[] nums, int k) {
        int curSum = 0;
        int count = 0;

        Map<Integer, Integer> map = new HashMap();
        
        map.put(0, 1);

        for(int num : nums){
            curSum += num;
            
            if(map.containsKey(curSum - k)){
                count += map.get(curSum - k);
            }

            map.put(curSum, map.getOrDefault(curSum, 0)+1);

        } 

        return count;
    }
}