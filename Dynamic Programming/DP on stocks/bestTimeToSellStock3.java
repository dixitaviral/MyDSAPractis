/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

Intution:

1. Dekho bhai ye ques seedha seedha best time to sell stock 2 ke jesa hai. 
2. Us ques me basically two cheeze thi ya to buy karo ya to sell karo, means agar ek stock 
    khareed liya hai to next sell hi kr skte hai khareed ni skte.
3. And buy me bhi 2 condition hai ya to current buy karna hai ya ni krna hai and sell me bhi
    same ya to sell karna hai ya ni karna hai.
4. Plus buy krte time -prices[i] lete hai kyuki utne paise hamare jeb se gye hai, next sell
    vala number return hoga jisse humko profit pata chalega. And sell ke time par prices[i] 
    positive me hoga.
5. And uss ques me hum ek flag lete the 0 hai to buy kar lo and 1 hai to sell kar do.
6. Abhi scene hai ye ki iss ques me bola gaya hai ki sirf two transactions kar skte ho, like 
    buy->sell->buy->sell, to ek transaction me ye buy->sell aaega means agar flag ki value 4 hai
    to uss time par 0 return kar do and same 0 return karna hai jab i overflow jo jae.
7. Abhi flag ko do tarah se use krna hai ek to ye batana hai ki current transaction me buy
    karege ya sell and ye batana hai ki transactions total kitne hue hai.
8. Abhi scene hai ki agar hum flag ki start value ko 0..2..4 rakhte hai to usko buy mtlb smjhege.
    And 1..3..5 aaya to sell smjhege. Means even hai to buy and odd hai to sell.
9. Bas khatam yhi krke kaam ho gaya apna.
*/

class Solution {
    public int maxProfit(int[] prices) {
        int dp[][] = new int[prices.length][5];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }
        return helper(prices, 0, 0, dp);
    }

    public int helper(int[] prices, int i, int flag, int dp[][]){
        if(flag > 4 || i == prices.length){
            return 0;
        }

        if(dp[i][flag] != -1) return dp[i][flag];

        if(flag % 2 == 0){
            int buy = -prices[i] + helper(prices, i+1, flag+1, dp);
            int notBuy = helper(prices, i+1, flag, dp);

            return dp[i][flag] =  Math.max(buy, notBuy);
        }else{
            int sell = prices[i] + helper(prices, i+1, flag+1, dp);
            int notSell = helper(prices, i+1, flag, dp);

            return dp[i][flag] = Math.max(sell, notSell);
        }
    }
}