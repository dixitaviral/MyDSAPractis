/*
You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums that meet the following conditions:

The length of the subarray is k, and
All the elements of the subarray are distinct.
Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the conditions, return 0.

A subarray is a contiguous non-empty sequence of elements within an array.

 

Example 1:

Input: nums = [1,5,4,2,9,9,9], k = 3
Output: 15
Explanation: The subarrays of nums with length 3 are:
- [1,5,4] which meets the requirements and has a sum of 10.
- [5,4,2] which meets the requirements and has a sum of 11.
- [4,2,9] which meets the requirements and has a sum of 15.
- [2,9,9] which does not meet the requirements because the element 9 is repeated.
- [9,9,9] which does not meet the requirements because the element 9 is repeated.
We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions
Example 2:

Input: nums = [4,4,4], k = 3
Output: 0
Explanation: The subarrays of nums with length 3 are:
- [4,4,4] which does not meet the requirements because the element 4 is repeated.
We return 0 because no subarrays meet the conditions.


Intution:
1. For taking out max sum of a subarray, you can refer to maxSubArrayAverage.java.
2. Now comes the difficult part, ki ek window me unique elements kese ensure kare:
    a. Things to consider:
        i. Window kitni bhi badi ho skti hai.
        ii. Repeated element contiguous bhi aa skte hai and non-contiguous bhi, like 1,1,2 or 1,2,1.
        iii. Hence, we need a frequency map, jisse hum uniqueness track kar sake.
    b. Abhi logic par aate hai:
        i. jab bhi hum temp me nums[i] add karege, usi time map me entry bhi karege, if not then 1 
            or if exits then +1.
        ii. Abhi humko pata hi ki ek window tb invalid ho jaegi jab usme ek bhi repeated char aa gaya.
        iii. To simple humko us window ko skip karna hai.
        iv. Skip karne ke liye simple current ith index par while check laga dege (map.get(nums[i]) > 1).
        v. Agar nums[i] do ya zada bar aa chuka hai point iv vali condition true hogi, then simple humko window
            choti krni hai.
        vi. Window choti krne ke liye hum left se karege, temp -= nums[start] and start++.
        vii. Ab since start++ kiya hai to frequency map se start ka count bhi to km krna pdega na.
        viii. So, map.put(nums[start], nums.get(nums[start])-1); ye line if(i-start+1 == k) ke ander bhi hogi
                kyuki start++ and temp -= start vala bhi kr re hai.
        ix: That's it.

*/

class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        long res = 0;

        int i = 0;
        long temp = 0;

        int start = 0;

        Map<Integer, Integer> map = new HashMap();

        while(i < nums.length){
            temp += nums[i];
            map.put(nums[i], map.getOrDefault(nums[i],0)+1);
            
            
            while(map.get(nums[i]) > 1){
                map.put(nums[start], map.get(nums[start])-1);
                temp -= nums[start];
                start++;
            }


            if(i-start+1 == k){
                res = Math.max(res, temp);
                temp -= nums[start];
                map.put(nums[start], map.get(nums[start])-1);
                start++;
            }

            i++;
        }

        return res;
    }
}