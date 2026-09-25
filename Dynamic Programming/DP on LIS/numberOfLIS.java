/*
Given an integer array nums, return the number of longest increasing subsequences.

Notice that the sequence has to be strictly increasing.

 

Example 1:

Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:

Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.

Intution:

1. Bhai ye ques smjhne me 3 din lag gaye, ques interesting hai aao smjhte hai.
2. Ques keh raha hai ki array me jitne longest increasing subsequence hai sare number return karo.
3. Abhi dekho isme sabse pehle tumko longest increasing subsequence nikalna hai, through ietrative
    method, vo kese nikalta hai mene tumko bataya hai longest increasing subsequence me
    vo refer karo. But recap dekh skte hai:
    1. Dekho recap ye hai ki agar hum ek i index lele jo ki 0 se start ho and ek j index lele vo
        bhi 0 se start kare. And do loop chala de.
    2. Abhi agar jo nums[j] < nums[i] hai iska mtlb ek lis form ho ra hai, us case me humko,
        dp[i] me store kar dena hai max (dp[i], dp[j]+1);
    3. Abhi dp ko dekhte hai sabse pehle to dp ko humne 1 se initialize karna hoga, vo isliye kyuki
        start me har number jo array me present hai vo apne aap me ek LIS hai 1 length ka tabhi
        start me 1 se initialize kiya hai mene.
    4. Ab scene hai ki max(dp[i], dp[j]+1) kyu kar rahe hai. Dekho agar first time ki bat dekhe 
        to dp[i] to 1 hi hoga, and agar nums[j] < nums[i] hua, which means nums[j] tak ka jo bhi
        max len LIS hoga, usme hum i vala or add kar rahe to, but aisa bhi ho skta hai ki 
        i par already LIS max stored ho, to check kar lege ki jis j se hum i par aae hai usse 
        max LIS length niklegi ya already jo calculated hai usse niklegi. Tbhi max check kar rahe hai.
    5. Abhi scene hai ye, ki humko check karna hai dp me after calculation ki konsa len max
        hai vo return kar do.
4. Abhi isi LIS method me, humko simple ek ways array maintain karna hai, tumko dimag me aaega
    ki count nam rakh de, but ways nam rakhne se kafi clear intuition rehti hai instead of count
    nam rakhne ke, kafi clear rehta hai.
5. Abhi dekho do conditions ho skti hai isme:
    a. Loop ke ander abhi hum simply max store kar de rahe hai dp[i] me.
    b. But count/number basically count of ways hai jisse humko max length ka LIS milega.
    c. To instead max calculation, isko hum raw tarah se krege.
    d. To ek condition to hui ye ki agar dp[i] < dp[j]+1, ye case kya bolta hai, ye case ye bata
        raha hai ki dp[j]+1 greater hai dp[i] se which means dp[j] ke through jo LIS banega
        vo current dp[i] LIS se greater length ka hai, to is case me hum simple jese krte the
        vese hi dp[i] update kar dege. But isse humko ye bhi pata chala ki jo i hai vo j tak ka
        LIS hai usme add ho ra hai, means j tak LIS jitne bhi tarah se bana hai, ab utni hi tarah
        se i vala lis bhi banega. So ways[i] = ways[j];
    e. Abhi dusra case dekhte hai, jo ki bol ra hai ki agar dp[i] == dp[j]+1, iska mtlb hua ki,
        humare pas ek x length thi dp[i] par and ek or path se humko same x length mil ri hai
        i.e dp[j]+1, iska mtlb ye path koi dusra hai par length same mil ri hai humko.
        Abhi is case me ways kese behave karega, dekho bhai pichle me humne dekha ki equal
        kar dege kyuki same LIS ka part ban ra hai. But isme hum karege ways[i] += ways[j];
    f. Add kyu kiya, add isliye kiya kyuki jo hume diff route se same LIS length mili hai, 
        but vo same route kitne or routes se aaya hai. Dimaag par zor dalo. Equal hona hi
        bata ra hai ki same LIS length dobara mili hai humko, abhi current way kitne ways se 
        bana hai usko info ways[j] par hogi, and kitni ways se already existing
        bana hai uski info ways[i] se banega. To dono ko add karege.
6. Bas abhi ways count krne ke bad, tum max len nikal lo dp se, and vo max len jaha jaha ways
    array me aa ri hai, vo sare sum krke return kar do.
7. Aisa isliye ki zaruri ni hai ki ek hi index par tumko max len mil jaegi. Kyuki subsequence 
    zaruri ni sirf 0 se start ho.

8. Bas vo sum return kar do.


*/

class Solution {

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;

        int dp[] = new int[n];
        int ways[] = new int[n];

        int max = 0;

        Arrays.fill(dp, 1);
        Arrays.fill(ways, 1);

        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){

                // agar prev number jisse LIS start hoga, vo lesser hai
                // current means current LIS ka part hoga
                if(nums[j] < nums[i]){

                    // agar prev number ka LIS length and current number bhi add kar rahe hai
                    // kyuki current bada hai prev se, to prev ka abhi tak ki LIS length + 
                    // current num add hoga so +1, bada hai current already calculated LIS length
                    // of current se, means better greater length mili, so update that.
                    // abhi ways update isliye, kyuki update se pehle tak current kisi or
                    // path se reachable tha, but better length mili to current ki length
                    // update hui, so current abhi prev ke through reachable hai, to jitne ways
                    // se prev recahbale hai utne hi ways se current bhi reachable hoga.
                    if(dp[j]+1 > dp[i]){
                        dp[i] = dp[j]+1;
                        ways[i] = ways[j];
                    }

                    // Isme length hum update ni kar rahe in dp, kyuki same length doabar mili hai
                    // better length ni mili. 
                    // But isse humko ye pata chala ki prev se humko ek dusra way mila jisse hum
                    // same length of LIS bana skte hai, tabhi prev jin jin ways se ban skta hai,
                    // abhi utne hi ways se current bhi ban paega, hence adding ways j to current ways
                    else if(dp[j]+1 == dp[i]){
                        ways[i] += ways[j];
                    }
                }
            }
        }


        // ye zaruri ni ki dp[n-1] par tumko max length mile, 
        // kyuki yaha dp[i] ka mtlb ye hai ki i index par ho number
        // hai is number tak sabse max LIS length ye hai.
        // jo ki kahi bhi ho skti hai. Tabhi max len find karo.
        for(int num : dp){
            max = Math.max(num, max);
        }


        // abhi max len to bohot sare index par mil skti hai
        // to jitne indices in dp par max len mili hai, same indices
        // in ways max ways milege, agar index diff diff hai to add 
        // ways and return ans.
        int ans = 0;
        for(int i = 0; i < n; i++){
            if(dp[i] == max){
                ans += ways[i];
            }
        }

        return ans;
    }
}
