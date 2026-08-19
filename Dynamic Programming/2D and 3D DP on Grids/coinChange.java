/*
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

 

Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Example 3:

Input: coins = [1], amount = 0
Output: 0

Intution:
1. Aao bhai aaj se 2d DP lagae. Usse pehle ye ques smjho
2. Basically ye ques keh raha hai ki tumko ek array diya hai usme coins hai.
3. And ek amount diya hai. Vo amount tumko banana hai coins plus krke. Tum chaho 
    1 coin ko kitni bar bhi use kar skte ho and chaho to skip kar do.
4. Ye karke tumko min number of coins batane hai jo lagege for making amount number.
5. Abhi iska recursive solution kafi easy hai but main dp lagana hai isme:
    a. Sabse pehle to recursive solution dekhte hai usko intution kya hogi.
    b. Bhai sabse pehle base conditions dekh lete hai:
        i. Agar coins sum == amount ho gaya to 0 return kar do, as 0 bolega ye branch
            valid hai and isko consider karo.
        ii. Agar i == length ho gaya ya fir sum > amount ho gaya to Int max return kar do
            jo ki bataega ye branch consider ni karni hai.
    c. Abhi bat krte hai ki recursion kese lagega:
        i. To iss ques me bhi hamare pas choice hai ki har bar ya to tum current coin ko
            hi lo ya fir current coin ko skip kar do and har coin par yahi do choices hai.
        ii. To do recursion branch banegi ek take ki and ek skip ya not take ki.
        iii. Abhi since take vali branch me humko next iteration or recursion me updated sum
            and index pass karna hai to isko humko ek condition ke ander rakhna hai hoga for this ques,
        iv. Condition ye hogi ki agar current coin less hoga amount - sum se to fir take vala recursion
            chalaege and take ki value int max aai to skip else take += 1 karege. Abhi curr coint <= amount - sum 
            aisa kyu?
        v. Dekho man lo amount hai 5 and abhi tak ka sum hai 3 to hum max 2 chahiye 5 banane ke liye
            and agar 2 se jada aaya to usko skip kar dege. Vahi scene hai.
        vi. Abhi is condition ke bahr hum chalaege not take, abhi is condition ke bahr isliye kyuki
            not take me hum sum ni badha rahe bas i badha rahe hai. 
        vii. And not take me hum notTake += 1 bhi ni karege kyuki isme hum simple ye keh re hai ki hum current index
            ko skip kar rahe hai.

    d. ABhi last me return kar dege min of take and not take. Kyuki humko min coins batane hai.
    e. Itne se tmhra recursive solution done ho jaega. But ye TLE dega kyuki bohot sare combination banaege
        jese jese coin array badhega to hum isko memoize karege.
    f. Abhi memoize krne ke liye humko state pata hona zaruri hai ki konsi states hogi jispr hum memo banae.
    g. State hogi isme i and sum, kyuki i and sum ka change hona hi decide kar raha hai ki konsa count min hoga.
    h. To bas memo banega coins.length and amount+1 se.
    i. Bas fir kya return min of take and not take ko memo me store karo and upar memo me agar value hai to vo return
        karo khatam.

Ho gaua coin change with DP.
*/


class Solution {
    public int coinChange(int[] coins, int amount) {
        int memo[][] = new int[coins.length + 1][amount + 1];

        for(int brr[] : memo){
            Arrays.fill(brr, -2);
        }

        int res = helper(coins, amount, 0, 0, memo);

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public int helper(int[] coins, int amount, int i, int sum, int memo[][]) {
        if(sum == amount){
            return 0;
        }

        if(i == coins.length || sum > amount){
            return Integer.MAX_VALUE;
        }

        if(memo[i][sum] != -2){
            return memo[i][sum];
        }

        int take = Integer.MAX_VALUE;

        // agar coins[i] bada abhi ke bache hue us number se
        // jisko add karne par amount aaega to mtlb ni hai us coin ko
        // consider krne ka
        // for example amount hai 5 sum hai 3 to mtlb hum 2 or chahiye
        // 5 banane ke liye but agar coins[i] par number hai 3 tab isko 
        // ignore kar do kyuki 3+3 6 hoga jo ki 5 ni banaega
        if(coins[i] <= amount-sum){
            take = helper(coins, amount, i, sum + coins[i], memo);

            // iska mtlb hai ki upar se return hoga int max agar kuch 
            // condition break hui to, in that case current recursion branch
            // consider ni hogi, else hogi to take me 1 plus kar do as si branch
            // par upar se 0 return hoga.
            if(take != Integer.MAX_VALUE){
                take += 1;
            }
        }

        // not take ki condition yahi hai ki isko bas iterator mano ki
        // i ko aage badhai bina kuch dependent state like sum ko change kiye.
        int notTake = helper(coins, amount, i + 1, sum, memo);

        return memo[i][sum] = Math.min(take, notTake);
    }
}