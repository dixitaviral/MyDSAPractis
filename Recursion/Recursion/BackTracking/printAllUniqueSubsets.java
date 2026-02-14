/*

Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

 

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]


Intution:
    1. Bhai same backtracking ka question hai jisme humne pehle bhi 3 cheeze dekho thi:
        a. result hamesha base condition ke ander banta hai.
        b. Ya to loop lelo ya ek or recursion call lelo, depends upon question.
        c. Jo current iteration ya recursive call me answer me change kiya usko remove kar do after first call.
    2. Bas ye 3 condition aa gyi fir kya badhiya hai sab. Abhi is ques ki intutuion dekhte hai:
        a. Isme keh ra hai ki ek array hai uske sare unique subset/power set/subsequence nikalo.
        b. humne kaha theek bhai, to isme kya scene hai ki jab bhi subset/power set/subsequence nikalne 
            ki baat ho, tab do choices vala idea dimaag me lana hai.
            i. Jese array hai [1,2,3], abhi har index par tumhre pas do choices hai, ith index ko answer
                me include karna hai ya ni kahi karna hai.
            ii. Bas do recursion call isi par based hogi.
            iii. But ek dhyan dene vali baat jaha glti ho skti hai:
                a. Tumko lagega ek recursion call me i badha lo to include case ho jaega and
                    ek me mat badhao to exclude case ho jaega, but aisa ni hai.
                b. Tumko dono case i increment karna hai, but jo ans banaoge usme tumko current element
                    add karna hai ya remove krna hai.
                c. Isko hi yaha par include/Exclude case kahege.
    3. Bas fir kya easy hai, do list banegi ek ans store karegi dusri current answer store karegi
        jisko hum base condition hit hote hi main answer list me add kar dege.
    4. Dhyaan rahe ki main answer list me current answer list ko direct na add karke new object 
        bana kr add karna hai.
    5. Kyuki list and array by reference pass hote hai agar direct ans me add krke kiya to list
        me later jo change hoga vo main answer list me bhi reflect hoga.
*/





class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList();

        create(0, res, new ArrayList() ,nums);

        return res;
    }


    public void create(int i, List<List<Integer>> res, List<Integer> li, int nums[]){
        if(i >= nums.length){
            res.add(new ArrayList(li));
            return;
        }
        
        //add case
        li.add(nums[i]);

        create(i+1, res, li, nums);

        //remove case
        li.remove(li.size()-1);

        create(i+1, res, li, nums);
    }
}