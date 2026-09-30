/*
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The final answer is guaranteed to fit into a signed 32-bit integer.

 

Example 1:

Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10]
Output: 1

Intution:

1. Bhai ye ques same hai as coin change bas is ques me tumko kitne maximum ways se amount achieve ho skta hai,
    vo return krna hai.
2. Simple hai ye bhi, isme base conditions hogi index array overflow vali, agar sum == amount hai to 1 and agar
    sum > amount hai to invalid ho gaya return 0.
3. Abhi same take not take vali branch bana do, and return take+notTake kar do ye total number of ways ho jaega.
4. Abhi dp ki bat krte hai state hogi i and sum because yahi change ho re hai. So vahi pehle dp check laga do
    agar value -1 ni hai to return kar do.
5. And at last dp me take + notTake value return kar do.

Khatam
*/

class Solution {
    public int change(int amount, int[] coins) {
        
        int dp[][] = new int[coins.length][amount+1];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }

        return helper(amount, coins, 0, 0, dp);
    }

    public int helper(int amount, int coins[], int i, int sum, int dp[][]){
        if(i == coins.length) return 0;

        if(sum > amount) return 0;

        if(sum == amount) return 1;

        if(dp[i][sum] != -1) return dp[i][sum];

        int take = helper(amount, coins, i, sum+coins[i], dp);

        int notTake = helper(amount, coins, i+1, sum, dp);

        return dp[i][sum] = take+notTake;
    }
}