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

Intution :
1. Bhai easy ques hai bas intution click hona hota hai agar ni hua to jhel jaoge.
2. Karna ye hai simple isme ki tumko compare krna hai pehle ki prev stock ka price current
    vale stock price se agar kam hai to mtlb bech skte hai profit nikalo current - prev se
    and max store krte jao.
3. Agar aisa ni hai mtlb ki prev stock price is greater than current stock price, to zahir hai 
    ki agar prev hi rakha and usse kam ka stock mil gaya hume to hum prev day vale rate par kyu
    lege, to current ko prev bana do.
4. Yahi karna hai. 
5. Upar to khair loop se ho gaya, agar recursion dp se krna hai to 1d dp banegi and recursion me
    choice game hoga, ki current stock take or not take.
6. take vale case me profit calculate karo and not take me skip kar do.
7. return karna hai max of take and nottake. Dp recursion solution likh ni raa aage likhuga.



Bonus:
1. Tum bas itna yaad karo ki present me jaha tum khade ho vaha se left me sabse min nikalna hai
    and current ko minus krke max store karna hai.
*/


class Solution {
    public int maxProfit(int[] prices) {
        int cuurent = prices[0];
        int max = 0;

        for(int i = 1; i < prices.length; i++){
            if(cuurent < prices[i]){
                max = Math.max(max, prices[i] - cuurent);
            }else{
                cuurent = prices[i];
            }
        }

        return max;
    }
}

