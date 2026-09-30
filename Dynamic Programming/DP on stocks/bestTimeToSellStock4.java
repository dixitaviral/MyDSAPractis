/*
You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

Example 1:

Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.

Intution:

1. Bhai ye ques same hai best time to sell stock 3 jese, usme bola tha 2 times transaction ho 
    skte hai, abhi isme atmost k transactions ho skte hai means, 2*k total buy and sell hoge.
2. Refer the solution from that page
*/

class Solution {
    public int maxProfit(int k, int[] prices) {
        int dp[][] = new int[prices.length][(k*2)+1];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }
        
        return helper(prices, 0, 0, dp, k);
    }

    public int helper(int[] prices, int i, int flag, int dp[][], int k){
        if(flag > k*2 || i == prices.length){
            return 0;
        }

        if(dp[i][flag] != -1) return dp[i][flag];

        if(flag % 2 == 0){
            int buy = -prices[i] + helper(prices, i+1, flag+1, dp, k);
            int notBuy = helper(prices, i+1, flag, dp, k);

            return dp[i][flag] =  Math.max(buy, notBuy);
        }else{
            int sell = prices[i] + helper(prices, i+1, flag+1, dp, k);
            int notSell = helper(prices, i+1, flag, dp, k);

            return dp[i][flag] = Math.max(sell, notSell);
        }
    }
}