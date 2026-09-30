/*
Given a string s, find the longest palindromic subsequence's length in s.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

 

Example 1:

Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".
Example 2:

Input: s = "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".


Intution:

1. Bhai ye ques easy hai, tum khud bhi soch pa rahe hoge ki take not take laga do, then take and nottake ka
    max return kar do. Plus ek ispalindrome method bana do vo ye check karega ki palindrome hai ya ni.
2. But socho palindrome ka mtlb kya hota hai ki dono side se i and j start karo jab tak, equal hai
    tab tak shrink karo, jaha par equality break ho vaha par tmhre pas 2 choice hai agar tum sirf i badhao
    to kya pata j ke equal aa jae and ek bar sirf j kam karo to kya pata equal ho jae.
3. Bas yahi logic lagana hai, aao intution dekhe:
    a. Sabse pehle base condition ki baat ki jae:
        a. To dekho bhai 2 base condition hogi, ek agar i > j hai to return 0 kar do.
        b. Dusri condition hai agar i == j hua that means i and j beech me kahi mile hai aakar
            to condition hui ki 1 length ka humko palindrome mila hai to 1 return kar do.
    b. Abhi aati hai main logic ki baat:
        a. Bhai isme sabse pehle to tumko subsequence banane padega, uske liye tumko take not take
            vala recursion likhna hai.
        b. But take hoga ek condition ke ander ki agar charAt i == charAt j to take call kar do, with 
            2+helper(...). ABhi 2 + isliye kyuki 2 characters equal hue to vo dono ek string me 
            aaege and humko max length return karni hai na.
        c. Abhi bat krte hai not take ki, ye else condition me aaega.
        d. Abhi isme tweak ye hai ki man lo do characters equal ni hai and humko subsequence check karna
            hai to hum kisi ek char ko skip kare and dusre ko vahi rakhe to kya pata ek palindrome ban
            jae, so for the same reason hum isme return karege max of helper(i+1, j), helmper(i, j-1);
        e. Abhi last me return kar dege max of take and notTake.
    c. Abhi DP ki baat hai to i and j states hai 2d dp bana lo -1 se initialize kar do.
    d. Store karo dp me and if dp me -1 ni hai means return dp[i][j].

    Khatam.
*/

class Solution {
    public int longestPalindromeSubseq(String s) {
        int dp[][] = new int[s.length()+1][s.length()+1];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }

        return helper(s, 0, s.length() - 1, dp);
    }

    public int helper(String s, int i, int j, int dp[][]){
        if(i > j) return 0;

        if(i == j) return 1;

        if(dp[i][j] != -1) return dp[i][j];

        int take = 0;
        int notTake = 0;

        if(s.charAt(i) == s.charAt(j)){
            take = 2 + helper(s, i+1, j-1, dp);
        }else{
            notTake = Math.max(helper(s, i+1, j, dp), helper(s, i, j-1, dp));
        } 

        return dp[i][j] = Math.max(take, notTake);
    }
}