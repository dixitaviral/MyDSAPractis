/*
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

 

Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]

Intution:

1. Bhai ye bhi backtacking ka question hai.
2. Ye ques me hum smjhege ki backtracking me humko kabhi loop use krna hai and kabhi recursion.
3. Aao dekhte hai:
    1. Jab bhi tumko subsequence/power set/subset banane ko bole tab recursion call lena.
    2. But jab bhi tumko bole all combinations like permutations, then loop lena. All combinations me
        base condtion hamesha rehti hai jab current result input ke barabar ho jae
        to usko main result me add kar do.
4. Abhi Simple hai humne upar dekh liya ki isme combination banane hai to loop lena padega.
5. Abhi dusri cheez ye ki yaha par bhi choices hai but:
    a. Choice include/exclude ki ni hai
    b. yaha par choices hai ki kisko lu, let's say [1,2] array hai, to 0th index result ke liye do choices
        hai ya to 1 lege ya 2.
    c. Abhi 1th result index ke liye 1 hi choice hai agar 0th par 1 liya hai then 2 else 1.
    d. Isko karne ke liye hum simple result list me check kr skte hai ki current element already
        present then skip it.
6. Abhi inlcude krne ke bad recursion call aa jaegi jo further choices explore kar sake.
7. Abhi iske bad simple backtracking rule ke hisaab se jo result me last me add hua hai
    usko remobe kar do.
8. Bas ho gaya.
*/ 

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList();

        create(result, new ArrayList(), nums);

        return result;
    }

    public void create(List<List<Integer>> result, List<Integer> li, int[] nums){
        if(nums.length == li.size()){
            result.add(new ArrayList(li));
            return;
        }

        for(int n : nums){
            if(li.contains(n))
                continue;
            
            li.add(n);

            create(result, li, nums);

            li.remove(li.size()-1);
        }   
    }
} 