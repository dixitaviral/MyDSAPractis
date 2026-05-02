
/*
    You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

 

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.
 

Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 104

Intution:

1. Bhai two pointer lene hai i = 0 and j = 1.
2. compare karna hai if(prices[i] < prices[j]), isse ye prove ho ra ki aaj stock rate kam hai,
    and next day zada hai.
3. So we should buy it, and store it in res like this res = Math.max(res, prices[j]-prices[i]);
4. And do j++; jab tk prices[i] < prices[j] hai.
5. jese hi prices[i] > prices[j] mile, iska mtlb jth day stock price current se bhi kam hai.
6. so humko uss din stock khareedna chahiye, to hum i = j kar dege, and j++, kyuki stock next day
    hi sell kr skte hai.
7. Hence, humko max profit mil jaega.
*/


class Solution {
    public int maxProfit(int[] prices) {
        int i = 0;
        int j = 1;

        int res = 0;

        while(j < prices.length){
            if(prices[i] < prices[j]){
                res = Math.max(res, prices[j]-prices[i]);
                j++;
            }else{
                i = j;
                j++;
            }
        }


        return res;
    }
}