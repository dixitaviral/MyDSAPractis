/*
Given an integer array arr, partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer.

Example 1:

Input: arr = [1,15,7,9,2,5,10], k = 3
Output: 84
Explanation: arr becomes [15,15,15,9,10,10,10]
Example 2:

Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
Output: 83
Example 3:

Input: arr = [1], k = 1
Output: 1
 
Intution:

1. Bhai ques thoda tricky hai smjhne ke liye bhi and karne ke liye but mai acche se smjhauga
    to smjh aaega.
2. Abhi sabse pehle dekhte hai question kya keh raha hai:
    1. Ques keh raha hai ki tumko min 1 and max k size ke partition/subarray banane hai and 
        unka max num find krna hai then vo max num kitne size ke subarray ka max hai vo pata karo
        jese 15 max hai 2 size ke subarray ka to 15*2 krna hai kyuki to vo 2 size ya k size keh lo
        k numbers me sabse bada hai to vo utni bar multiply ho jaega. same or bhi subarrays ki value
        nikalni hai. Isme tumko k size ques me diya hoga and arr bhi diya hoga.
    2. Then iske bad scene hai ki aise tumko choices krte krte max sum return krna hai. Vohi ans hai.
3. Abhi isko krna kese hai intution dekhte hai:
    a. bhai intution basically hai partition dp ka hi hai but thori alag tarah se lagega.
    b. Also isme ye catch hai ki left vala concept ni hai, why because we are dealing with
        subarrays jo ki contiguous hote hai.
    c. to humko sirf aage badhana hai pointer, to hum isme ek stable pointer lege and ek moving
        pointer, moving pointer hoga j and stable hoga j.
    d. Abhi intution simple hai first recursion call hogi 0 se with k passed. Then for loop chalega.
    e. Abhi for loop me ek catch hai ki humko max k size ka subarray bana kar check krna hai, to
        for loop ki exhaust condition bhi k par depend karegi. i humne pass kiya hai method me
        to current i se j pointer kitna aage ja skta hai if we were given a 0 indexed array?
        i+k-1. Apne aap bhi nikal lo chaho to.
    f. Plus ek or catch hai ki agar array size hai 6 k hai 3 and man i = 4 ho gaya. 6 size ke hisaab
        se array ka last index hua 5. And hamko man lo k diya hai 3. abhi i(4)+k(3)-1 hua 6 ye to
        overflow kar jaega, so hum for loop ka exhaust rakhege min(i+k-1, arr.length-1);
    g. abhi simple for loop chalao abhi abhi tak ka sabse max element maintain karo for that
        calculated window.
    h. Abhi tum jab max num nikaloge tab tumko max number between j-i+1 many numbers ka max mila 
        of current window, and humko yahi krna hai ki jo max number hai vo k window me kitno se
        bada hai utne se multiply krna hai and then next window ke liye yhi se 
        recursion call ho jaegi jo hogi helper(arr, k, j+1);
    g. Abhi ye loop and recursion chalega to 1-1 index hi check hoga jese 1,5,3,2,5,6 hai to call flow
        aise jaega ki pehle 1 check hua max 1 hua then 1*1+helper(arr, k, j+1). Fir same 5 ke sath
        same 3 and .... 6 tak hoga fir array overflow and us recursion call me max 6 hoga. Then 
        vaha se jab call vapas aaegi to tum aa jaegi 5 vali call par next number hoga 6, dono me max
        6 abhi 6*j-i+1 + helper(arr, k, j+1) to 5,6 means 2 number se 6 bada 6*2 + helper call overflow
        to ans max hua 12. Aise hi sare combinations banege and hum ans max update krte rhege.
    h. Abhi ho gaya ye then dp laga do isme ek hi index hai i to 1d array banega and usi ko use
        krke memoize kar do.
    
Ho gaya khatam.
*/

class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int dp[] = new int[arr.length];

        Arrays.fill(dp, -1);

        return helper(arr, k, 0, dp);
    }

    public int helper(int arr[], int len, int i, int dp[]) {
        if(i == arr.length) return 0;

        int partitionLen = Math.min(i+len-1, arr.length-1);

        int max = 0;
        int ans = 0;

        if(dp[i] != -1) return dp[i];

        for(int j = i; j <= partitionLen; j++){
            max = Math.max(max, arr[j]);

            int temp = (max * (j-i+1)) + helper(arr, len, j+1, dp);

            ans = Math.max(temp, ans);
        }

        return dp[i] = ans;
    }
}