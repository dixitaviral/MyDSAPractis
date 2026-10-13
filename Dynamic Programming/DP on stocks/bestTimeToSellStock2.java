/*
You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can sell and buy the stock multiple times on the same day, ensuring you never hold more than one share of the stock.

Find and return the maximum profit you can achieve.

 

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.

Intution:

1. Bhai ye ques thora zada hai like, tum jab ye kar rahe the tumko click ho gaya tha
    but tmne socha ni ye ni hoga.
2. To ye ques keh ra hai ki tum ek stock khareedo and usko bech do, aise multiple combinations 
    test krne hai and jo max profit profit aae like 7,1,5,3,6,4 me 1 pe khareedo and 5 par bech do
    and then 3 par khareedo 6 par bech do total max profit tmhe 7 milega.
3. Pichle vale ques me tumko sirf 1 khareed skte the and 1 bar bech skte the isme tum multiple
    khareed skte ho and multiple bech skte ho
4. But condition ye hai ki agar tumne khareeda hai to tum sirf bech skte ho and jab tak 
    bech ni diya tab tak dusra khareed ni skte.
5. I hope abhi tak tumko click kar gaya hoga ki karna kya hai and kese karna hai.
6. Chalo intution dekhe:   
    1. Bhai dekho options explore kare to ye hoge ki man lo agar abhi current i par khareed liya
        tumne to kya pata current vala stock price zada hai age vala kam hai to profit less
        ho gaya.
    2. Similarly abhi vale par profit milna tha lekin tumko next le liya to bekar ho gaya.
    3. To ye ho gyi buy ki choices abhi sell ki bhi choice hogi na ki agar current stock bech
        diya to kya pata aage bechte to zada profit milta.
    4. Similarly abhi bech dete to zada profit milta and aage ni mileg a.
    5. To abhi humko smjh aa ra hai ki total char recursion choice hogi buy not buy and sell not sell.
    6. Plus buy only if not already bought and sell only when already bought.
    7. To ek flag lenge jisme 0 hoga to buy karna hai 1 hoga to sell krna hai.
    8. Is hisaab se if block me buy rakh lo else me sell.
    9. Then buy not buy ka max return karo.
    10. Abhi ek or cheez array ka return kya hoga, return hoga current array element. AB tum kahoge
        ye kyu or kese.
    11. Bhai dekho current buy kiya iska matlb tumne apne pas se paise lagaye hai like from input
        1 pehle stock 7 khareeda to tumhre pas hua -7 + helper(i+1, sell now), abhi sell vali
        branch basically tumko next number return karega ya ni karega right.
    12. Aise hi sell me humne kaha bas bech do man lo 7 bech diya to 7 rs vapas mile mtlb likhege 
        7 + helper(i+1, buy now). 
    13. And we know not buy and not sell me consider kuch krna ni hai, to direct return without
        any state change just moving forward.
    14. Base condition will be i overflow in array then will be returned.
    15. Dp me state i and flag hogi and kese lagani hai you already know.

Neeche solution pasted hai
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
        if(i == prices.length) return 0;

        if(dp[i][flag] != -1) return dp[i][flag];
        
        if(flag == 0){
            // yaha par minus isliye laga hai, isko aise smjho
            // ki humne prices[i] rs lagaye hai and next call ki hai
            // helper ki, abhi usme flag set kiya hai 1 which means
            // stock hold kiya hai mene abhi next helper call kal
            // se aage ka jo bhi max profit hoga vo lekr aaegi.
            // notBuy recursion call bata raha hai ki aaj hold ni kiya
            // hai mene aaj ka skip kar diya hai.
            // Abhi minus isliye lagaya buy case me, kyuki kal se aage tak 
            // ka jo maxProfit aaega ussme humne abhi tak jo paisa lagaya hai,
            // usko minus karoge na for counting profit, tabhi.

            int buyProfit = -prices[i] + helper(prices, i+1, 1, dp); 
            int notBuyProfit = helper(prices, i+1, flag, dp);

            return dp[i][flag] = Math.max(buyProfit, notBuyProfit);
        }

        else{

            // sell profit me hum tabhi aae hai jab humne already ek stock hold krke 
            // rakha hai, to humne kaha aaj prices[i] par hum sell karege + next helper
            // me dusre stock buy not buy option par jaege.
            // iska mtlb sirf itna hai ki prices[i] tumne bas bech diya hai prev val
            // kya thi zada thi kam thi dont know bas bech diya hai and tumko prices[i]
            // ka sell profit hua hai, ab iske bad man lo array khatam, then notSellProfit
            // vali branch par bhi array khatam to dono par zero return hua
            // abhi max zahir hai ki sellProfit hoga, joki buy vali branch me return hoga.
            // abhi prices[i] return hua hai and uske pehle buy branch me prev buy negative me
            // tha to negtive positive ka hoga calculation and humko profit milega.
            int sellProfit = prices[i] + helper(prices, i+1, 0, dp); 
            int notSellProfit = helper(prices, i+1, flag, dp);

            return dp[i][flag] = Math.max(sellProfit, notSellProfit);
        }

    }
}