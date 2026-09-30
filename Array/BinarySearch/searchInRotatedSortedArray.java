/*
There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.

 

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Example 3:

Input: nums = [1], target = 0
Output: -1
 

Intution: 

1. Bhai ye ques bohot simple and easy hai.
2. Isme tumko simple binary search lagani hai.
3. Binary search in a bit tricky way:
    a. Bas ek line padho smjh aa jaega 
        for example array hai nums = [4,5,6,7,0,1,2];
    b. Isme tum mid nikal lo that will be I think 3 aana chahiye.
    c. Abhi tum low = 0 to 3 check karo and 3+1= 4 to high = 6 check karo.
    d. Tum dhyan doge ki koi ek side sorted hai hamare case me 0 to 3 vali hai.
    e. And ha agar ye ni hoti to dusri side pakka sorted hoti.
    f. To bas yahi logic lagana hai humko:
        i. Sabse pehle mid nikalo.
        ii. Then check karo if left side is sorted, if yes
        iii. Check if target lies between that part, if yes adjust high to mid-1;
        iv. if no adjust low to mid+1. 
        v. Abhi ye adjust ni smjh aa ra hai tumko to binary search padh kar aao.

        vi. Similarly if right side is sorted that will be in else block.
        vii. Then check if target lies between mid+1 and high.
        viii. If yes then adjust low else adjust high.
    g. At last of while loop when it will get ended, return -1 as agar number
        mila hota to while loop ke ander hi return ho jata.
*/

class Solution {
    public int search(int[] nums, int target) {
        int high = nums.length-1;

        int low = 0;

        while(low <= high){
            int mid = (low+high)/2;

            if(nums[mid] == target) return mid;

            if(nums[low] <= nums[mid]){
                if(nums[low] <= target && target < nums[mid]){
                    high = mid-1;
                }else{
                    low = mid+1;
                }
            }else{
                if(nums[mid] < target && target <= nums[high]){
                    low = mid+1;
                }else{
                    high = mid-1;
                }
            }
        }

        return -1;
    }
}


