/*
Given an integer array nums, return the length of the longest strictly increasing subsequence.

 

Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1

Intution:

1. Bhai ye ques tumne kiya kai bar hai but since kabhi proper notes ni banae hai to bhul jate ho.
2. Actually tumko smjh aata hai kese krna hai bas ek chota sa tweak hai tum bhul jate ho, aao batau.
3. Usse pehle ques dekhte hai keh ra hai ki longest increasing subsequence ki length batao.
4. Abhi increasing mtlb 1234... and subsequence to jante ho order na break ho baki kese bhi number
    utha lo.
5. Aao dekhe karna kese hai:
    a. Dekh bhai subsequence sunte hi take not take recursion yaad aata hai jo ki humko krna hai
        iss ques me.
    b. Abhi dusri cheez ye ki jo subsequence hoga, usme humko condition check krni hogi
        ki prev element jo hai vo current element se chota ho, tabhi vo increasing 
        subsequence hoga.
    c. Ab tum sochoge theek nums[i-1] < nums[i] likh dete hai but bhai i-1 mtlb ki current se
        just pehle vala element, ye valid hai but zaruri thori ki just prev element ko consider
        kar bhi re hai ya ni.
    d. Abhi fir kya karna hai, to humko ek prev index variable maintain krna hai jisme hum current
        i pass karege and i -> i+1 karke pass karege.
    e. Ab bat aati hai recursion start krte time kya initial value pass karege, to bhai i to 0
        rahega, fir prev ki value kya hogi uski value rakhege -1.
    f. ABhi tum kahoge ki prev ki value 0 rakh do and i ki 1 and fir karo compare. But mere bhai
        ek bat socho jo subsequence hoga, usme zaruri thori hai ki hum 0th index consider karege
        hi, agar humne prev par 0 index se intialize kar diya, to iska mtlb hum keh re hai
        subsequence 0th index vale number se start hi hoga, but aisa bhi to ho skta hai 0th number
        consider kiya bina jo subsequence bane usme ans aa jae.
    g. Tabhi hum prev ko -1 rakhege at start. Abhi hum take me 1 + recursin call inside
        condition if(prev == -1 || arr[prev] < arr[i]). ABhi tum kahoge ki arr[prev] to outofbound
        dega jab prev = -1 hoga, mene kaha ni dega kyuki pehli condition true hogi, prev == -1
        and since hum or condition laga rahe beech me to pehla true hai to vo dusra check hi ni
        karega.
    h. And if condition ke bahr notTake vali condition jisme hum i+1 and prev as it is pass karege
        jabki take vale me i jaega as i+1 and prev jaega as i.
    i. Then base condition sirf i ke arr me overflow check ke liye hogi, jsime return 0 karna hai.
    j. Last me max of take and not take return kar do.
    k. Abhi dp lagana to jante ho ki last me return ke time par dp me store kar lo and base 
        condition ke bad check laga do, but but, since hamari states hai i and prev and prev ki
        initial value hai -1 to dp me prev as -1 outofbound error dega, to is case me hum 
        dp[i][prev+1] karege isi par store and isi par fetch.
*/

class Solution {
    public int lengthOfLIS(int[] nums) {
        int dp[][] = new int[nums.length][nums.length+1];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }
        return helper(nums, 0, -1, dp);
    }

    public int helper(int nums[], int i, int prev, int dp[][]){
        if(i == nums.length) return 0;

        if(dp[i][prev+1] != -1) return dp[i][prev+1];

        int take = 0;
        int notTake = 0;

        if(prev == -1 || nums[prev] < nums[i]){
            take = 1+helper(nums, i+1, i, dp);
        } 

        notTake = helper(nums, i+1, prev, dp);

        return dp[i][prev+1] = Math.max(take, notTake);
    }    
}


/*
1. Below given solution me simple approach use hui hai.. jo tumhe yaad ho to graph me
    bohot bar use ki hai, ki prev cell tak ki distance from 0th cell agar n and to current cell
    ki hogi n+current cell cost. Right?

2. Same isme karna hai ki, sabse pehle dp ko 1 se initialise kar do, kyuki har number khud me
    me LIS hai to array se min 1 length ka LIS to milega hi.
3. Abhi ek loop chalao i = 0 to n on whole array length and ek loop chalao from 0 to < i.
4. Abhi dekho man lo i cell par ho, and j cell i se chota hai to mtlb vo LIS me aa skta hai
    but man lo i cell already calculated ho and i cell ka LIS current banne ja rahe LIS se chota
    bhi ho skta hai.
5. Uss case me hum max check karege, ki max of (current lis, jth lis + 1).
6. Abhi yaha jo mene jth lis likha vahi concept ki baat kar ra tha graph me jo humne lagaya tha.
7. To j tak ka LIS already pata hai and j se bada element koi mila i par to vo bhi lis ka part
    hoga to jth lis + 1 i cell ki nayi value ho jaegi if current is lesser.
8. Bas yahi loop chalate raho and last me jo bhi max num mile dp me usi ko return kar do.
9. Abhi tum sochoge ki ye bottom up approach hai, to result dp[n-1] me mil jaega, but ni
    yaha dp ka mtlb ye hai ki kisi index i par jo value hogi, vo i tak ka sabse max possible
    lis length hai, joki dp[n-1] ni bhi ho skti hai.
*/
// more optimised solution 

class Solution {
    public int lengthOfLIS(int[] nums) {
        
        int n = nums.length;

        int dp[] = new int[n];

        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        int max = 0;
        for(int num : dp){
            max = Math.max(max, num);
        }

        return max;
    }
}