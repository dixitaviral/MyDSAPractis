/*
You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.

 

Example 1:

Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
Example 2:

Input: nums = [1], target = 1
Output: 1

Intution:
1. ye ques keh raha tha ki jitne number diye hai unki ek expression banao combinations banao by 
    putting either + or - and check karo it that expression or combinations add up to target.
2. Theek to abhi isko humko recursion le lagana chahiye, to aao recursion dekhe pehle:
    a. Sabse pehle bat aati hai base condition ki:
        1. Dekho bhai pehli base condition yehi hogi ki i == nums.length hoga to return 0.
        2. Abhi humko ye bhi make sure krna hai ki expression me number sare hone chahiye
            jitne array me present hai. unko + - krke tumne target banaya hai.
        3. To scene ye hai ki ye bhi check tumko isi condition ke ander karna pdega ki jab
            i == nums.length hoga, uss time par hi check karoge ki abhi tak jo + - vali
            expression bani hai kya uska add up target hai ya ni.
        4. Agar hai to 1 return kar do, else i == nums.length par 0 return kar do.
        5. Ab tum sochoge bhai hum sum > target || sum < 0 vali condition bhi laga skte hai
            , but ni laga skte kyuki sum negative bhi ho skta hai target bhi negative ho skta hai
            similarly abhi sum bada hai target se aage minus vali expression bani to kam bhi ho skta hai.
            to ye condition ni lagegei.
    b. Ab bat krte hai recursion ki:
        1. Dekho bhai hamare pas do choices hai ya to hum + karege ya to - karege.
        2. To do calls banani padegi ek sum+nums[i] and ek sum-nums ke liye.
        3. Abhi agar invalid hua kuch to 0 return hoga us case me result count me kuch mat add karo and if 
            1 return hua to count me + add karo 
        4. And same yahi return kar do plus vala + minus vala.

3. Ab scene hai memoization ka:
    a. dekho bhai memo simple hai i and sum change hore ye do state ki banai hai apne ko.
    b. But sum negative me bhi ho skta hai isliye hum kya karege ki memo me 2 dimension ki length
        rakhege 2*totalSum+1, abhi koi bhi sum aae positive ya negative tum sum ko aise store karoge
        memo[i][sum+totalSum] isse tmhra negative number ka overflow manage ho jaega.
    c. baki tumko pata hi hai ki last me memo me save karo and start me memo se return kar do. 
*/


class Solution {
    int total = 0;
    public int findTargetSumWays(int[] nums, int target) {
        for(int num : nums){
            total += num;
        }

        int memo[][] = new int[nums.length][(2*total)+1];

        for(int arr[] : memo){
            Arrays.fill(arr, -1);
        }

        return helper(nums, target, 0, 0, memo);
    }

    public int helper(int nums[], int target, int i, int sum, int memo[][]){
        if(i == nums.length){
            if(sum == target){
                return 1;
            }
            return 0;
        }

        if(memo[i][sum+total] != -1) return memo[i][sum+total];

        int count = 0;

        int plus = helper(nums, target, i+1, sum+nums[i], memo);

        if(plus != 0) count += plus;

        int min = helper(nums, target, i+1, sum-nums[i], memo);

        if(min != 0) count += min;

        memo[i][sum+total] = count;

        return count;
    }
}