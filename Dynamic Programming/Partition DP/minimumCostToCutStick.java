/*
Given a wooden stick of length n units. The stick is labelled from 0 to n. For example, a stick of length 6 is labelled as follows:


Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.

You should perform the cuts in order, you can change the order of the cuts as you wish.

The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts. When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their lengths is the length of the stick before the cut). Please refer to the first example for a better explanation.

Return the minimum total cost of the cuts.

 

Example 1:


Input: n = 7, cuts = [1,3,4,5]
Output: 16
Explanation: Using cuts order = [1, 3, 4, 5] as in the input leads to the following scenario:

The first cut is done to a rod of length 7 so the cost is 7. The second cut is done to a rod of length 6 (i.e. the second part of the first cut), the third is done to a rod of length 4 and the last cut is to a rod of length 3. The total cost is 7 + 6 + 4 + 3 = 20.
Rearranging the cuts to be [3, 5, 1, 4] for example will lead to a scenario with total cost = 16 (as shown in the example photo 7 + 4 + 3 + 2 = 16).
Example 2:

Input: n = 9, cuts = [5,6,1,4,2]
Output: 22
Explanation: If you try the given cuts ordering the cost will be 25.
There are much ordering with total cost <= 25, for example, the order [4, 6, 5, 2, 1] has total cost = 22 which is the minimum possible.

Intution:

1. Aao bhai ek or question dekhte hai partition dp ka.
2. Sabse pehle question dekhte hai, basiaclly ye ques keh ra hai ki  ek lambi si stick di hai n length ki and ek 
    array diya hai tumko m size ka jisme kuch numbers hai lesser than n.
3. Abhi tumko jo array me number diye hai un un numbers ya stick length par cut lagana hai, and cut lagate waqt
    ans return karna hai jo ki hogi stick ki length current cut se just pehle vali.
4. Array me jitne numbers diye hai tumko sare use krne hai order koi bhi ho skta hai doesn't matter. And ye sare
    cuts krke tumko min ans return krna hai.
5. Abhi iski ek do intution hai aao batata hu:
    a. First intution:   
        1. Pehli intution is just like burst balloon, ki current length ki stick ki length sabse bad me consider karo
            and pehle kaat do.
        2. man lo stick length hai 7 and tumko cut lagaya 3 par to tumhre pas do stick aa gyi ek 0-3 length ki and ek
            3 to 7 length ki and current ans nikla 7. 
        3. Abhi tum 0 to 3 vali stick ke liye ek recursion call karoge and same 3-7 ke liye recursion call karoge and 
            unka ans return hoga.
        4. Abhi humko kuch structure banta dikh ra hai ki agar hum start recursion call me start var end var arr pass kare
            start = 0 end = 7 or given n and array give.
        5. To next recursion call dikhegi helper(start, arr[i], arr) + helper(arr[i], end, arr) + (end-start) (this will tell 
            length of current stick).
        6. Abhi start and end exhaust hoge jab tak do recursion call 0 return kar degi kyuki min ans base case me 0 hi hoga.
        7. To ans jo aaege unka min return hoga, vohi uske upar vali helper call me return jaega.
        8. Abhi isme ek catch hai yrr, man lo start = 3 and end = 5 and arr[i] = 6 hai abhi ye 6 vala cut to start
            and end ke beech hai ni to iss case me continue kar dege. 
        9. To mota mota ye hai ki ek for loop chala do array par usme cut overflow check kar lo and ha overflow me || 
            condition lagegi and ni. And ye tum khud socho kyu?
        10. Abhi ek bat or humko min ans return karna hai to ans ki initial vali int min rakhege, and man lo ki 
            for loop chala and cut start and end ke bahr hai and aisa sare cuts ke sath hai tab ans int min return kar
            dega?
        11. For solving this humko ye dekhna hoga ki agar koi bhi cut hua hai means if condition cuts overflow
            vali cross hui to ek bool var lelo found uske true kr do and for loop ke bad agar ye true hai that means
            ans return karo agar false hai that means cuts overflow the to 0 return karo.
        12. bas bhai recursion done abhi dp laga dete hai:
            a. Abhi yaha asli game hai, game ye hai ki tum dp state kya banaoge, start and end agree?
            b. Abhi start and end ki max value kya hai, you said given n I said yes correct. Iski
                dp bana do and same isi me ans store karo ya 0 hai to 0 store karo and return karo.
            c. Par mere bhai isme memory limit exceed ho jaegi, pucho kyu, kyuki bhai ques keh ra hai ki n ki value max 10^6
                ho skti hai means itne size ki dp matrix banegi.
            d. Yahi gap hai is solution me.
    b. Second intution:
        1. Ab bhai second intution dekhte hai, daro ni simple hai and upar vali approach ke similar hai bas kuch tweak hai.
        2. Bhai hamesha yad rakho agar dp me MLE aa ri hai that means tum index ke form me dp store ni kar re and if index 
            ke form me store kar re still MLE hai them ques requires solution other than dp.
        3. Filhal upar vale solution me we were using value instead of index. Dhyan se dekho to start and end are nothing
            but diff values that lies before n stick length.
        4. Iska simple approach hai ki sabse pehle array ko sort kar do. Isse hoga kya ki if condition jo hum laga rahe the
            cuts overflow na ho uske liye vo ni lagani padegi kyuki humne pata hai ki agar current cut 3 hai to aage cut 2 ni 
            aaega. But tum kahoge ques keh ra ki kisi bhi order me tum cut kar skte ho to kisi bar sorted order bhi test
            ho jaega.
        5. Mene kaha haa bhai theek keh re ho, sort karna necessary ni hai but better bana deta hai. Else tum found vali approach
            laga do chalega.
        6. Abhi dusra tweak sabse important, humne kya kaha tha ki index ke form ke represent karo to MLE ni aaega for this ques.
        7. To tumko kaha ki theek hai hum start = 0 index pass kar dete hai and end = ? array sort krke jo sabse bada cut num hoga
            usko pass kar dete hai, si ja re ho bhai but ek issue hai. Current length means starting length man lo 7 hai vo kese count 
            karoge, ab tum agar ye kahoge last me add kar dege, to bhai uska idea ni hai but vo galat hoga I know.
        8. Instead tum aise karo ki ek naya array bana do of size curr array+2. So do index extra mile abhi isme 0th index me
            0 store kar do and last index me n. Rest beeche me same array ke nums store kar do from 1 to arr.length-1.
        9. Abhi isi array ko recursion me pass karo. Aisa kiya kyu humne, kyuki dekho bhai hum length nikal rahe the start ki by 
            end - start. Abhi hum obviously arr[end]-arr[start] karege but kya vo si length hogi current me ni. 
        10. Upar vale solution me hum start length nikalte the by n-0. Isme humne 0 and n store kar rakha hai array me and
            vahi array pass kar rahe hai to usi ka fayda uthate hai na.
        11. To recursion call bani len = helper(start-1, arr[i], arr)+helper(arr[i], end+1, arr) + (arr[end+1] - arr[start-1]), jaha
            par for loop i = 1 se ques given array .length tak chalega, kyuki cuts to vahi given hai na and naye array me bhi
            given cuts are avaibale from 1 to arr.length-1. That's why
        12. Abhi aise krke hi min ans nikalege and return karege. Abhi isme dp easily lag jaegi. Bas dp banaoge arr.length+2 2d matrix
        13. Ans nums store hoge vahi dp[start-1][end+1] par and vahi se check hoge and return bhi hoge.

Khatam bhai.
*/

class Solution {
    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);

        int arr[] = new int[cuts.length+2];

        int dp[][] = new int[cuts.length+2][cuts.length+2];

        for(int a[] : dp){
            Arrays.fill(a, -1);
        }

        arr[0] = 0;
        arr[cuts.length+1] = n;

        for(int i = 0; i < cuts.length; i++){
            arr[i+1] = cuts[i];
        }
        
       return helper(1, cuts.length, arr, dp);
    }

    public int helper(int start, int end, int arr[], int dp[][]){

        if(start > end) return 0;

        if(dp[start-1][end+1] != -1) return dp[start-1][end+1];

        int ans = Integer.MAX_VALUE;

        for(int i = start; i <= end; i++){         
            int len = (arr[end+1] - arr[start-1]) + helper(start, i-1, arr, dp) + helper(i+1, end, arr, dp);

            ans = Math.min(len, ans);
        }

        return dp[start-1][end+1] = ans;
    }
}