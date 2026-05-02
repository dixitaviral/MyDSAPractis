/*
    Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

0 <= a, b, c, d < n
a, b, c, and d are distinct.
nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

 

Example 1:

Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
Example 2:

Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]
 
Intution:

1. Refer threeSum and twoSum for the intution.
*/

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> li = new ArrayList();

        Arrays.sort(nums);
        int i = 0;
        int j = nums.length-1;

        for(int a = 0; a < nums.length-3; a++){
            if(a > 0 && nums[a] == nums[a-1]) continue;
            for(int b = a+1; b < nums.length-2; b++){
                i = b+1;
                j = nums.length-1;

                if(b > a+1 && nums[b] == nums[b-1]) continue;

                while(i < j){
                    long sum = (long)nums[a]+nums[b]+nums[i]+nums[j];

                    if(sum == target){
                        li.add(List.of(nums[a],nums[b],nums[i],nums[j]));

                        i++;
                        j--;

                        while(i < j && nums[i] == nums[i-1]) i++;
                        while(i < j && nums[j] == nums[j+1]) j--;
                    } else if(sum > target){
                        j--;
                    }else{
                        i++;
                    }
                }
            }
        }

        return li;


    }


}