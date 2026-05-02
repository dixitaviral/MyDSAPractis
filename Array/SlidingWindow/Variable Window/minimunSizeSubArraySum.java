/*
    Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.

 

Example 1:

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:

Input: target = 4, nums = [1,4,4]
Output: 1
Example 3:

Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0

Intution:

1. Bhai simple hai do pointers lene hai i and start.
2. Then temp variable me nums[i] add krte jao, jese hi temp >= target ho
3. Turnt count update kar do:
    a. count update karo ki agar tumko koi min window mil gayi hai.
    b. then abhi just target se equal ya greater element mila hai, iske aage jaege to window invalid
        ho jaegi ya agar greater mila tha to kya pata equal ho jae and window size kam ho jae etc.
    c. uske liye temp -= nums[start] and start++, window shrink, jab tak temp < target na jae.
    d. so that agar current se choti window exist krti hai koi to vo mil jae.
4. Then after while loop check if count == Integer.MAX_VALUE ? 0 : count;
*/


class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0;//5

        int start = 0;//4

        int temp = 0;

        int count = Integer.MAX_VALUE;

        while(i < nums.length){
            temp += nums[i];// 5
            
            while(start <= i && temp >= target){
                count = Math.min(i-start+1, count);
                temp -= nums[start];
                start++;
            }

            //1

            i++;//1
        }


        return count == Integer.MAX_VALUE ? 0 : count;
    }
}