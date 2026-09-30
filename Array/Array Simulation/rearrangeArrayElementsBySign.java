/*
You are given a 0-indexed integer array nums of even length consisting of an equal number of positive and negative integers.

You should return the array of nums such that the array follows the given conditions:

Every consecutive pair of integers have opposite signs.
For all integers with the same sign, the order in which they were present in nums is preserved.
The rearranged array begins with a positive integer.
Return the modified array after rearranging the elements to satisfy the aforementioned conditions.

 

Example 1:

Input: nums = [3,1,-2,-5,2,-4]
Output: [3,-2,1,-5,2,-4]
Explanation:
The positive integers in nums are [3,1,2]. The negative integers are [-2,-5,-4].
The only possible way to rearrange them such that they satisfy all conditions is [3,-2,1,-5,2,-4].
Other ways such as [1,-2,2,-5,3,-4], [3,1,2,-2,-5,-4], [-2,3,-5,1,-4,2] are incorrect because they do not satisfy one or more conditions.  
Example 2:

Input: nums = [-1,1]
Output: [1,-1]
Explanation:
1 is the only positive integer and -1 the only negative integer in nums.
So nums is rearranged to [1,-1].

Intution:
1. Bhai jo tum brute force soch skte ho vahi optimal solution hai.
2. Kyuki is ques me agar tum in place swap karte ho to ques keh ra hai ki order maintain karp
    swapping se order ni maintain hoga.
*/

class Solution {
    public int[] rearrangeArray(int[] nums) {
        int pos = 0;
        int neg = 0;

        int arr1[] = new int[(nums.length/2)];
        int arr2[] = new int[(nums.length/2)];

        for(int i = 0; i < nums.length; i++){
            if(nums[i] >= 0){
                arr1[pos++] = nums[i];
            }else{
                arr2[neg++] = nums[i];
            }
        }
        
        pos = 0;
        neg = 0;

        boolean flag = true;
        for(int i = 0; i < nums.length; i++){
            if(flag){
                nums[i] = arr1[pos++];
                flag = false;
            }else{
                nums[i] = arr2[neg++];
                flag = true;
            }
        }

        return nums;
    }
}