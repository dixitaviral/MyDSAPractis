/*
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.

 

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Example 3:

Input: nums = [], target = 0
Output: [-1,-1]

Intution:

1. Bhai isme tumko ek target number diya hai uski leftmost and rightmost index batana hai.
2. Abhi isko simple hum binary search se kar skte hai easily. 
3. Binary search likho and sabse pehle leftmost nikalna hai to jab mid par target mile
    tab j = mid-1 return kar do, so that mid-1 par target ho to vo bhi count ho jae.
4. Abhi same for right ke liye alag se binary search likho and jab mid = target aae tab
    i = mid + 1 kar do, take right me jitne mile uska index update hota jae.
5. Bas yahi dono values return kar do.
*/

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int arr[] =  new int[]{-1,-1};

        int i = 0;
        int j = nums.length -1;
        while(i <= j){
            int mid = (i+j)/2;

            if(nums[mid] == target){
                arr[0] = mid;
                j = mid-1;
            }else if(nums[mid] < target){
                i = mid+1;
            }else{
                j = mid-1;
            }

        }

        i = 0;
        j = nums.length -1;

        while(i <= j){
            int mid = (i+j)/2;

            if(nums[mid] == target){
                arr[1] = mid;
                i = mid+1;
            }else if(nums[mid] < target){
                i = mid+1;
            }else{
                j = mid-1;
            }

        }

        return arr;
    }
}