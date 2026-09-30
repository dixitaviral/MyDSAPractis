/*
Given two strings s and t, return the number of distinct subsequences of s which equals t.

The test cases are generated so that the answer fits on a 32-bit signed integer.

 

Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit
Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag

Intution:

1. Bhai ques easy hai agar tum isko dekh pae to. mene dekh liya tha and mere se ho bhi gaya, aao smjhau 
    kese hua.
2. Pehle ques keh ra hai ki s string ki kitne subsequence hai jo t ke equal hai.
3. Ab tum kahoge ki isko aise kar skte hai ki sare subsequence bana le and ek ek krke match krke dekhe
    ki equal hai ya ni agar equal hai to count increase karo.
4. Par mere bhai kitni bhasad hai isme itni string banaega store krega, aao ek simple tareeka batata hu:
    a. Simple hai do variable lelo i and j.
    b. Abhi s ka ith char and t ka jth char match hai to tmhri take call ho gyi ki dono string ke character
        match hue to dono pointer ko aage badhao and recursion call kar do.
    c. Abhi socho ki agar tumne current character of s string consider ni kiya to next call me dusra susequence
        consider hoga usme vapas chalega ki char equal hai ya ni. like rabbbit and rabbit hai to rabb tak match hoga
        then i aaega vo match ni hoga to ek b not take vali call me skip ho jaega, but hum j ko tabhi badhaege
        jab match hoga.
    d. Bas yahi logic hai, abhi base condition ki baat kr le to:
        1. Agar i and j dono hi s and t ki length ke equal aa gye to mtlb ek match mila kyuki j hum tabhi badha rahe
            hai tab match mil raha hai to and i bho pura consume ho gaya ye ek case ho skta hai is case me return 1.
        2. Dusra hoga overflow case ki agar sirf i == s ki length then return 0, kyuki s string hi khatam ho gyi
            ab agar t string me kuch bacha bhi hai to compare kisse karoge.
        3. Abhi agar j == t.length() mtlb ek match mila kyuki hum j tabhi badha rahe hai jab match milta hai to
            return 1.
    e. Isi me dp laga do changing states tmhri i and j hai to bas last me take+notTake ko dp me store karo and base 
        condition ke bad dp check kar lo.
5. Ek bat ye hai ki take += recursion call hogi and same for notTake as well. Kyuki 1 humko tab hi milega jab j exhaust 
    ho jaega and j ko hum tabhi badha rahe hai jab match mil raha hai.

Bass khatam hai ye ques

*/

class Solution {
    public int numDistinct(String s, String t) {
        int dp[][] = new int[s.length()][t.length()];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }

        return helper(s, t, 0, 0, dp);
    }


    public int helper(String s, String t, int i, int j, int dp[][]){
        if(i == s.length() && j == t.length()){
            return 1;
        }

        if(i == s.length()){
            return 0;
        }

        if(j == t.length()){
            return 1;
        }

        if(dp[i][j] != -1) return dp[i][j];

        int take = 0;
        int notTake = 0;

        if(s.charAt(i) == t.charAt(j)){
            take += helper(s, t, i+1, j+1, dp);
        }

        notTake += helper(s, t, i+1, j, dp);

        return dp[i][j] = take+notTake; 
    }
}