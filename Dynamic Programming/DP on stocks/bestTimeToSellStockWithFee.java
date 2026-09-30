/*
You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.

Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

Note:

You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
The transaction fee is only charged once for each stock purchase and sale.
 

Example 1:

Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Example 2:

Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6

Intution:

1. Bhai sabse pehle ye ques bhi same as best time to sell stock 3 jesa hai.
2. To usko refer kar lena agar solution click na kare.
3. Is ques me sabse alag ye hai ki isme ek transaction fee bhi lag ri hai jo tmhre profit hoge.
4. Abhi vo humko minus krni hogi, but kaha par ye hai asli point.
5. Abhi dekho tum shyd socho ki dono if else me return krte time - fee kar do. But vo galat hoga
    kyuki kayde se to hum buy ya sell return kar re hai.
6. Dusra ye sochoge ki buy krte time and sell krte time laga dete hai vo bhi galat hai, kyuki vo
    minus har buy and sell par lgega jo ki milakar ek transaction hai, to ye bhi galat.
7. Abhi baat aai, ki fir lagae kaha, dekho bhai sell jab hum krte hai tab ye hota hai
    ek transaction complete, to bas jaha sell kar rahe ho vaha par hi fee minus krni hai. 
Bas khatam ho gaya.
*/

class Solution {
    public int maxProfit(int[] prices, int fee) {
        int dp[][] = new int[prices.length][2];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }
        return helper(prices, 0, 0, fee, dp);
    }

    public int helper(int[] prices, int i, int flag, int fee, int dp[][]){
        if(i == prices.length) return 0;

        if(dp[i][flag] != -1) return dp[i][flag];

        if(flag == 0){
            int buy = -prices[i] + helper(prices, i+1, 1, fee, dp);
            int notBuy = helper(prices, i+1, 0, fee, dp);

            return dp[i][flag] = Math.max(buy, notBuy);
        }else{
            int sell = prices[i] + helper(prices, i+1, 0, fee, dp) - fee;
            int notSell = helper(prices, i+1, 1, fee, dp);

            return dp[i][flag] = Math.max(sell, notSell);
        }
    }
}