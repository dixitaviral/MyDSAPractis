/*

Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.

 

Example 1:

Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Example 2:

Input: nums = [1,2,3], k = 0
Output: 0

Intution:

1. Bhai ekdm seedha seedha hai, subarray window badhane ke liye temp *= nums[i]; and
    ghatane ke liye temp /= nums[start];
2. Subarray length tumko i-start+1 se mil jaegi.
3. ek or while loop lgega and almost variable window me lgta hai so that result ko valid bana sake.
4. Iss ques ke case me jab tak temp, k se kam ni ho jata tb tk start++ and temp /= nums[start].

Important catch:

1. Say nums = [5,2,6] and k = 100. 
2. 5*2*6 = 60 < 100. which is valid.
3. abhi socho ki 5,2,6 ki length hai 3, and is subarray ka product hai 60.
4. to kya iske baki subarray like [5],[2],[6], [5,2], [2,6], [5,2,6]. In sabka product 60 ya
    60 se kam rhega and less than 100 also. which means directly result me plus 3 or
    i-start+1 add kar skte hai.
    


*/


class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int i = 0;

        int start = 0;

        int temp = 1;

        int count = 0;

        while(i < nums.length){
            temp *= nums[i];

            while(start <= i && temp >= k){
                temp /= nums[start];
                start++;
            }

            if(temp < k){
                count += i-start+1;
            }

            i++;

        }

        return count;
    }
}