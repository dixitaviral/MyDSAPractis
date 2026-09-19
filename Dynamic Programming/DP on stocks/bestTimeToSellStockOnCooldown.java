/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

Example 1:

Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
Example 2:

Input: prices = [1]
Output: 0

Intution:
1. Bhai ye ques ke liye bhi tum buy and sell stock 3 par refer kar lo solution, agar ni smjh aara.
2. Uske bad solution me bas itna tweak hai ki ques bol ra hai ki jab tum stock sell karoge, us
    time par tumko uske agle din buy ni krna hai, jiske liye humne simple sell vali recursion
    call ko i+2 badhaya hai, so that next day skip ho jae.

Bas baki pura solution is identical to buy and sell stock 3.
*/

class Solution {
    public int maxProfit(int[] prices) {
        int dp[][] = new int[prices.length][2];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }
        return helper(prices, 0, 0, dp);
    }

    public int helper(int prices[], int i, int flag, int dp[][]){
        if(i >= prices.length) return 0;

        if(dp[i][flag] != -1) return dp[i][flag];

        if(flag == 0){
            int buy = -prices[i] + helper(prices, i+1, 1, dp);
            int notBuy = helper(prices, i+1, 0, dp);

            return dp[i][flag] = Math.max(buy, notBuy);
        }else{

            // increase i by 2 as ques says after selling you can't buy next
            // day.
            int sell = prices[i] + helper(prices, i+2, 0, dp);
            int notSell = helper(prices, i+1, 1, dp);

            return dp[i][flag] = Math.max(sell, notSell);
        }
    }
}