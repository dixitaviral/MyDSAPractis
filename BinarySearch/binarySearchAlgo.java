/*
Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.

You must write an algorithm with O(log n) runtime complexity.

 

Example 1:

Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
Example 2:

Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1

Intution:

1. Bhai aati hai vese tumhe binary search par still intution bata ra hu.
2. Simple hai ekdm ki do variables lelo i = 0 and j = nums.length - 1.
3. Then while loop chala do condition i <= j.
4. Abhi mid nikal lo i+j/2 krke, check karo ki mid num equal hai kya target se, if yes return index.
5. Agar ni hai to fir dekho agar mid target se chota hai to shrink karo left pointer ko i = mid+1.
6. Agar mid se bada hai to shrink karo right pointer ko j = mid - 1.
7. Aise krte krte tumko mid par vo element mil jaega, and agar ni mila to that means element
    exist ni krta hai.
*/

class Solution {
    public int search(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;

        while(i <= j){
            int mid = (i+j)/2;

            if(nums[mid] == target) return mid;

            if(nums[mid] < target){
                 i = mid+1;
            }else{
                j = mid-1;
            }
        }

        return -1;
    }
}