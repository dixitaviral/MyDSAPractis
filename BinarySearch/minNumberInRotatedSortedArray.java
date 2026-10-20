/*
Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.

 

Example 1:

Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.
Example 2:

Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
Example 3:

Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times. 

Intution:

1. Bhai ye ques me tumko rotated sorted array diya hai jisme tumko min number find karna hai.
2. Abhi isme tumko binary search laga kar solve krna hai.
3. Iss ques ko tumne bhai khud se kiya hai so congratulations.
4. Aao intution dekhe:
    a. Intution simple hai binary search lagao theek. mid element nikalo and usko ans variable me
        store krte jao by comparing prev and current and min out of these.
    b. Abhi isme scene ye hai ki mid element nikalne ke bad, tumhra target ye hoga chahiye
        ki agar mid se bhi koi chot value hai usko ans banao.
    c. Ab socho mid se min kidhar milega but but array rotated sorted hai, to mid se j compare karo
        agar mid chota hai j se that means right side me tumko bigger values milegi.
    d. Iska mtlb tumko j shrink krna hai, else i shrink karna hai

    bass bhai akhir me ans return kar do.
*/

class Solution {
    public int findMin(int[] nums) {
        int i = 0;
        int j = nums.length-1;
        int ans = Integer.MAX_VALUE;
        
        while(i <= j){
            int mid = (i+j)/2;

            ans = Math.min(ans, nums[mid]);

            if(nums[mid] < nums[j]){
                j = mid-1;
            }else{
                i = mid+1;
            }
        }

        return ans;
    }
}