
/*
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

 

Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
Example 2:

Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
Example 3:

Input: candidates = [2], target = 1
Output: []


Intution:

1. Bhai simple backtracking ka ques hai.
2. Case dekh le smjh jaega:
    a. Pehle har number par do choices hai isme, ki ya to current vapas se lelo.
    b. ya to next element lelo.
    c. Isse smjh aata hai do branches jaegi ek current element consider kar lo, dusra next
        element consider karo.
    d. Abhi scene ye hai ki jo current element vali branch banaoge, vo tumko tab tak hi 
        chalani hai jab tak ans ya to target se chota hai ya to barabar hai.
    e. Then agar ans target se bada ho jata hai iska mtlb, humko ab stop krke next element
        par switch krna hai.
    f. but usse pehle backtracking ka rule aaega ki jo last branch me add hua hai usko hata do.
    g. Bas base condition do hogi, ek i >= nums.length and second ans == target then store result.
*/



class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList();

        create(result, candidates, new ArrayList(), target, 0,0);

        return result;
    }

    public void create(List<List<Integer>> result, int nums[], List<Integer> li, int target, 
                        int ans, int i){
        if(ans == target){
            result.add(new ArrayList(li));
            return;
        }

        if(i >= nums.length){
            return;
        }

        ans += nums[i];

        li.add(nums[i]);

        if(ans <= target)
            create(result, nums, li, target, ans, i);

        ans -= li.get(li.size()-1);

        li.remove(li.size()-1);

        create(result, nums, li, target, ans, i+1);
    }

}