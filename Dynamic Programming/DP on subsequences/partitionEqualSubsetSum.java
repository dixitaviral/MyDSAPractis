/*
Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.

 

Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.

Intution:
1. Bhai ye ques easy hai bas thora tricky hai aao smjhe.
2. Sabse pehle ques dekho keh raha hai ki array ko 2 equal subset me tod do jinke sum equal ho.
3. Abhi socho zara ek point ki agar humne array ka total sum nikal liya and then humne array subsets
    ka sum nikala jo total sum divide by 2 ke equal hai that means array 2 subset me divide ho skta hai.
4. Bas yahi logic humko lagana hai abhi aao intution dekhe:
    a. Sabse pehle base condition dekh lete hai:
        i. Dekho bhai ek to base condition hume samjh aai ki if sum == total/2 then return true.
        ii. Then dusri base condition simple agar i index array size ke barabar ho gaya to return false.
    b. Abhi aate hai subset kese banaya jae:
        i. Iske liye bhi hum jante hai simple take and not take ka concept lagana hai.
        ii. Now since hamari base condition boolean return kar ri hai humko recursive call ko if
            block ke ander rakhna padega.
        iii. To pehla if block hoga with take condition jisme tumne i badhaya and sum variable me
            curr ith number add kiya, agar recursion true return krta hai that means usko sum == total/2
            conition mil gyi hai, to if ke ander return true.
        iv. Then hoga not take condition jisme i badhana hai but sum me add ni karna hai, if ye true return kare 
            to bhi inside return true.
        v. Abhi krte hai baat last me hum simple return false kar dege.
    c. Abhi memo add krna hai to simple state hogi i and sum to isi ka memo bana do recursive call ke pehle check kar
        lo agar already visited hai to return false.
    d. And last me return false se pehle memo me visited mark kar do.
5. Abhi tumhre man me ques aaega ki memo me value store karne ki jagah visited kyu mark kar rahe hai. Vo isliye kyuki
    ques keh ra hai subset exist krna hai ye batao, to humko sum ni return krna, humko ye batana hai, ki 2 me divide
    hoga ya ni.
6. That's the reason hum path visited mark kar rahe hai jin par ni mila, and jis par mil gaya uspar se turnt return true
    ho jata hai.
    
*/

class Solution {
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for(int num : nums){
            totalSum += num;
        }

        if(totalSum % 2 != 0) return false;

        return helper(nums, 0, 0, totalSum, new int[nums.length][totalSum+1]);
    }

    public boolean helper(int[] nums, int i, int sum, int totalSum, int memo[][]){
        
        if(sum == totalSum/2) return true;
        
        if(i == nums.length) return false;

        if(memo[i][sum] == 1) return false;

        if(helper(nums, i+1, sum+nums[i], totalSum, memo)){
            return true;
        }

        if(helper(nums, i+1, sum, totalSum, memo)){
            return true;
        }

        memo[i][sum] = 1;

        return false;

    }
}