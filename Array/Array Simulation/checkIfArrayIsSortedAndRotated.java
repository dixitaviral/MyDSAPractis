/*
Given an array nums, return true if the array was originally sorted in non-decreasing order, then rotated some number of positions (including zero). Otherwise, return false.

There may be duplicates in the original array.

Note: An array A rotated by x positions results in an array B of the same length such that B[i] == A[(i+x) % A.length] for every valid index i.

 

Example 1:

Input: nums = [3,4,5,1,2]
Output: true
Explanation: [1,2,3,4,5] is the original sorted array.
You can rotate the array by x = 2 positions to begin on the element of value 3: [3,4,5,1,2].
Example 2:

Input: nums = [2,1,3,4]
Output: false
Explanation: There is no sorted array once rotated that can make nums.
Example 3:

Input: nums = [1,2,3]
Output: true
Explanation: [1,2,3] is the original sorted array.
You can rotate the array by x = 0 positions (i.e. no rotation) to make nums.

Intution:
1. Bhai easy ques hai, aao smjhata hu.
2. Sabse pehle to ques me puch kya raha hai ye dekhte hai.
3. Ques puch ra hai bhai ek array diya hai 3,4,5,1,2. Ye array sorted tha, and abhi bhi sorted hai.
4. bas isko rotate kar diya gaya hai.
5. Abhi rotate kidhr kiya gaya hai ye ques me diya hai ki B hai rotated array jo diya hai
6. And A tha sorted array jisko rotate krke B banaya hai.
7. Abhi B and A me relation hai B[i] =  A[(i+x)%A.length].
8. Mtlb B ka ith index par jo value hai vo A ke (i+x)%A.length index par this.
9. Abhi x yaha par value hai ki kitni bar array rotate kiya gaya hai.
10. Aao ek cheez sikhata hu ki formula se kese pata kare jo ques me diya hota hai ki array left
    shift hai ya right?
11. Koi bhi valid index rakh do formula me i ki jagah and check karo agar A ka index after calculation
    bada aaya to mtlb hua ki original array me vo number koi aage ke index par tha jo B me
    peeche aa gaya hai, to left shift else right shift.
12. Abhi intution dekho:
    a. Simple line me hai yrr ki jo sorted array hoga, uski ye condition hamesha false hogi
        nums[i] > nums[i+1].
    b. Abhi humne kiya hai array rotate, to mtlb koi bada number chote number ke aage ya peeche aaya hai.
    c. ABhi upar vali condition ek bar true ho jae to mtlb hai ki pura array sorted hai and
        jo ek jagah break hui condition uska mtlb ye hua ki vaha par array ka sabse bada number
        and chota number hai tabhi nums[i] > nums[i+1] hua.
    d. to bas humko isi ka count nikalna hai agar count > 1 aaya to false else true.
    e. Abhi tum kahoge ki == 1 kyu ni. To bhai agar man lo array ke sare numbers same hue.
    f. To condition kabhi true hi ni hogi and count kabhi badhega hi ni, jiska mtlb hoga
        sare elements same hai array me hence count = 0 is true.

        Important note:
        1. Is ques me modulo ka use bohot hua hai.
        2. To dhyaan se dekh lo isko.
*/

class Solution {
    public boolean check(int[] nums) {
        int count = 0;
        // yaha par tum keh skte ho ki i+1 kiya hai to i == nums.length par outofbound aa jaega
        // par ni aaega kyuki humne % nums.length kiya hai ki overflow ho to index 0 se start ho jae
        for(int i = 0; i < nums.length; i++){
            if(nums[(i+1)%nums.length] < nums[i%nums.length]){
                count++;
            }
        }

        return count <= 1 ? true : false;
    }
}