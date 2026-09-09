/*
A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

For example, the next permutation of arr = [1,2,3] is [1,3,2].
Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
Given an array of integers nums, find the next permutation of nums.

The replacement must be in place and use only constant extra memory.

 

Example 1:

Input: nums = [1,2,3]
Output: [1,3,2]
Example 2:

Input: nums = [3,2,1]
Output: [1,2,3]
Example 3:

Input: nums = [1,1,5]
Output: [1,5,1]

Intution:

1. Bhai is ques ko karne ki trick hai
2. Aao dekhe:
    a. Sabse pehle right to left loop chalao and find the first index jisme left num is smaller
        than right number and break. Like nums[i-1] < nums[i].
    b. Then nums-1 index ko store kar lo ek index variable me.
    c. Then check karo if index variable is not changed from it's initialized value then
        iska mtlb hua number digits are already in descending order, in that case as per ques
        we need to reverse it and return that.
    d. Now if index value has got changed from it's initialized value, then again from right to
        index-1 tak loop chalao and find the number just greater from that index-1 number.
    e. Now after finding swap that number, and then break.
    f. Now, last step is to reverse the numbers from index+1 to right most.
        That's all
*/

class Solution {
    public void nextPermutation(int[] nums) {
        
        int idx = -1;

        // find the pivot element
        // pivot element is nothing, just scan the
        // array from right to left and find the
        // first pair where prev < curr like 1 comes first so and 3 after
        // so 1 < 3, so 1 index will be pivot, and break.
        for(int i = nums.length-1; i > 0; i--){
            if(nums[i] > nums[i-1]){
                idx = i-1;
                break;
            }
        }

        // check if we found pivot
        // if we haven't found pivot means number's digits are in
        // decreasing order from left to right
        // hence reverse the number and return it.
        if(idx == -1){
            reverse(0, nums.length-1, nums);
            return;
        }

        // after finding pivot element, you need to swap that pivot element 
        // from the element on it's right, scan from right to left and 
        // which is just greater from pivot
        // replace the pivot with that number and break.
        for(int i = nums.length-1; i > idx; i--){
            if(nums[idx] < nums[i]){
                nums[i] = nums[i]+nums[idx];
                nums[idx] = nums[i]-nums[idx];
                nums[i] = nums[i]-nums[idx];
                break;
            }
        }
        
        // then reverse the digits from pivot index + 1 to nums.length - 1.
        reverse(idx+1, nums.length-1, nums);
    }

    public void reverse(int start, int end, int nums[]){
        while(start < end){
            nums[start] = nums[start]+nums[end];
            nums[end] = nums[start]-nums[end];
            nums[start] = nums[start]-nums[end];

            start++;
            end--;
        }
    }
}