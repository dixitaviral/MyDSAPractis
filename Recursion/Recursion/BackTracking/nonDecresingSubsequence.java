/*
Given an integer array nums, return all the different possible non-decreasing subsequences of the given array with at least two elements. You may return the answer in any order.

Example 1:

Input: nums = [4,6,7,7]
Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
Example 2:

Input: nums = [4,4,3,2,1]
Output: [[4,4]]

Intution:
1. Chalo bhai intution dekh lete hai iski.
2. Sbse pehle backtracking ke basic rule:
    a. Ans banao
    b. Recursion kar
    c. Then undo kar do
3. So ques keh ra hai ki tumko ek integer array diya hai for example: [4,6,7,7] jisme se tumko:
    a. aise subsequence nikalne hai, jo increasing order me ho. Like [4,6],[4,6,7],[4,6,7,7]...
    b. Dusra rule ye hai ki subsequence length 2 ya 2 se badi honi chahiye.
    c. Teesra hidden rule hai, order maintain krna hai.
    d. Fourth hai ki, subsequence distinct hone chahiye.
4. Abhi intution par aate hai:
    a. Sabse pehle humko smjhna hoga ki isme hum loop + recursion kare ya dual recursion:
        i. So dekho, isme hum loop use karege, kyuki:
        ii. Yaha par humko multiple choices di hai, plus ye dekho ki har element ka jo tree banega
            usme choice binary ni hai zada hai or kam bhi ho skti hai.
        iii. Tumne 4 choose kiya hai, ab next tum 4 se bada chose kr skte ho, tum 6,7,7 3 choices
            me se koi ek choose krke ek branch banaoge.
        iv. Ye batata hai ki bhai humko n-ary mtlb for loop + recursion use krna hai.
    b. Chalo abhi dekhte hai ki code kese likhege iska:
        i. Code simple hai
        ii. Base condition agar ans length 2 se jada hai to add kar lo and return ni krna hai
            kyuki 2 se bade bhi ho skte hai to aage loop me jakr naye combination banege.
        iii. Then hum ek set lege not on parameter level balki recursion method ke inside.
             kyuki, man lo branch ban ri hai horizontal me ja rahe ho and same element se vapas
            branch banne lagegi to duplicates aa jaege, isliye horizontal me distinct element rakhne
            ke liye hum set use kar rahe hai. Pehle hum ye use krte the 
            if(i > start && nums[i] == nums[i-1]), but ye tab use krte hai jab array sorted ho.
        iv. Chalo, abhi hum condition dekh lete hai, ki jab list me koi element ho and
            list ka last element current element se bada hai, to continue kar lo, kyuki humko
            increasing order maintain krna hai.
        v. Abhi ek or cheez isme start use kr rahe hai for (i = start), kyuki humko loop aage
            badhana hai, agar i = 0 rakhege to hamesha vapas aakr zero par khada ho jaega.
        vi. then bs recurse and remove or undo last character.
*/

class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList();

        create(result, nums, new ArrayList(), 0);

        return result;
    }

    public void create(List<List<Integer>> result, int nums[], List<Integer> li, int start){
        if(li.size() >= 2){
            result.add(new ArrayList(li));
        }
        
        // bhai ye set hum recursion level par na use krke ander isliye use kar rahe hai..
        // kyuki humko make sure krna hai ki recursion jab depth me jae tab same element se 
        // new branch na bane.
        Set<Integer> set = new HashSet();

        for(int i = start; i < nums.length; i++){

            if(set.contains(nums[i])) continue;

            if(li.size() > 0 && li.get(li.size()-1) > nums[i]) continue;

            li.add(nums[i]);

            set.add(nums[i]);

            create(result, nums, li, i+1);

            li.remove(li.size()-1);
        }
    }
}

