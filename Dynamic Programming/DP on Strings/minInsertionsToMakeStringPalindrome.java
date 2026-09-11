/*
Given a string s. In one step you can insert any character at any index of the string.

Return the minimum number of steps to make s palindrome.

A Palindrome String is one that reads the same backward as well as forward.

 

Example 1:

Input: s = "zzazz"
Output: 0
Explanation: The string "zzazz" is already palindrome we do not need any insertions.
Example 2:

Input: s = "mbadm"
Output: 2
Explanation: String can be "mbdadbm" or "mdbabdm".
Example 3:

Input: s = "leetcode"
Output: 5
Explanation: Inserting 5 characters the string becomes "leetcodocteel".

Intution:

1. Bhai ye ques me dekhne me hai ki yrr ye nikalne ke liye to characters ko insert krna pdega
    string me ek bar start ki taraf then end ki taraf.
2. Then tum sochoge ki jaha tak char match ho re hai vaha tak si hai beeche me kahi insert krke
    ho jaega.
3. But usme bhi scene hai ki kaha insert karoge aage ki tarah ya peeche ki tarah, mtlb ho jaega
    aise bhi but itna karne ki zrurt hai ni iss ques me.
4. For example string lelo "leetcode" abhi isme longest palindromic subsequence kya hoga, ek hai jese
    ete ya fir ece ya ee but longest hai 3 length ka.
5. Abhi tum ete lelo, to first e ke liye already dusra e hai last me and t to ek hi rhega.
6. To bhai scene ye hai ki agar tum ek string ka LPS nikal lo and uss lps length ko total string ki
    length me si minus kar do, to humko ye pata chal jaega ki konse aise characters hai
    jinke duplicate end side me exist ni krte ya start side me.
7. Utne hi characters humko chahiye ek string ko palindrome banane ke liye.

abhi LPS ka solution tumko pata hi hai, last string length - me lps length kar do khtm.
*/

class Solution {
    public int minInsertions(String s) {
        int i = 0;
        int j = s.length() - 1;

        int dp[][] = new int[s.length()][s.length()];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }

        // jo subsequence already palindrome hai, unke alava
        // jitne char bache utne insertions krne pdege for making
        // the string as palindrome.
        return s.length() - helper(s, i, j, dp);
    }

    public int helper(String s, int i, int j, int dp[][]){
        if(i == j) return 1;

        if(i > j ) return 0;

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

