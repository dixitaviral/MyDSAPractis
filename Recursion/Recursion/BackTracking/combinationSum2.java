/*
    Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

 

Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5
Output: 
[
[1,2,2],
[5]
]

Intution:

1. Aao bhai baat krte hai combination sum ki, jo bss subset 2 problem jesa hi hai.
2. Backtracking ka ques hai to to vahi 3 rule: make ans, recurse with include, then recurse with
    exclude.
3. But since ye combination ka ques hai plus duplicates ko take care krna hai then we will do it
    with loop + recursion inside loop.
4. Basically loop humko batata horizontally chalne ko and recursion batata hai,
    dept me jane ko.
5. Or rule yhi hai ki horizontal jane time, prev element == curr element to skip.
       ------> horizontal choose 
loop = [1,2,3]
recursion inside loop : [1]|
                        [2]|
                        [3]|
                           \/

6. Abhi intution par aate hai:
    a. First lagegi base case par usse pehle ek cheez dekh lete hai.
        i. Ki bhai base case to ans == target hota, jisme ans ek variable lete usme add krte jate
            and jab ans == target hota then result banate.
        ii. Aise hi kiya tha but usse TLE aa ri thi, jiska mtlb agar hum uski jagah, target ko hi
            increment decrement kare to kam time me kaam ho jaega.
    b. So base case targer == 0;
    c. ab baat aati hai loop ki, loop start hoga i = start se. Start variable humko batata hai
        ki horizontal loop walk me konse element se branch start kr re hai.
    d. then ander simple check: if (i > start and nums[i] == nums[i-1]) continue;
    e. Abhi kahoge ki i > start kyu kiya, kyuki jesa mene bataya start batata hai current horizontal
        direction me konsa start element hai, and i > start ka mtlb i start se aage hai and 
        start ka comparison start ke aage vale element se ho ra hai
        if equal then skip.
    f. then backtracking vala hi hai, ki target -= nums[i].
    g. then li.add(nums[i])
    h. then recurse. abhi recursion me i+1, ka mtlb hai start ko aage badhao, and start+1 isliye
        ni kiya kyuki khatarnak hai kyuki aage chal kar loop ko reset kar dega.
    i. then vapas undo. bas ho gaya.
*/


class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList();

        Arrays.sort(candidates);
        
        create(result, candidates, new ArrayList(), target, 0);

        return result;
    }

    public void create(List<List<Integer>> result, int[] nums, List<Integer> li, int target, int start){
        
        if(0 == target){
            result.add(new ArrayList(li));
            return;
        }

        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]){
                continue;
            }

            target -= nums[i];

            li.add(nums[i]);

            if(target >= 0)
                create(result, nums, li, target, i+1);

            target += li.get(li.size()-1);

            li.remove(li.size()-1);
        }

    }
}