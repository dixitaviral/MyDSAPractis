/*
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.

 

Example 1:

Input: nums = [2,2,1]

Output: 1

Example 2:

Input: nums = [4,1,2,1,2]

Output: 4

Example 3:

Input: nums = [1]

Output: 1

Intution:
1. Bhai for loop chala kar ans variable lelo and usko array ke 1 number se assign kar do.
2. Then loop chalao from 1 and ans xor nums[i] krte jao.
3. Jo number do bar aa re hoge vo cancel ho jage and and jo ek bar aaega vo reh jaega.

like 1,1,2,3,3 ka xor karoge to duplicates cancel ho jaege bachega 2 sirf.

*/

class Solution {
    public int singleNumber(int[] nums) {
        int ans = nums[0];

        for(int i = 1; i < nums.length; i++){
            ans = ans ^ nums[i];
        }

        return ans;
    }
}