/*
Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.

 

Example 1:

Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Example 2:

Input: word1 = "leetcode", word2 = "etco"
Output: 4

Intution:

1. Bhai ye ques bhi minInsertionsToMakeStringPalindrome vale jesa hai.
2. Isme tumko refer krna hai LCS longest common subsequence, basically same banane ke liye
    tmko same letters and same seuqence chahiye kon aage aaega kon peeche aaega.
3. Abhi agar tum longest common subsequence nikalte ho do string ke beech ki, to tumko ye pata
    chal jaega ki ye common letters already available hai dono me.
4. Abhi bas lcs ki length ko minus kar do dono words se, kyuki vo characters dono me common hai
    to unko hataoge nahi, bache jitne hai unko hata doge.
5. Bas to last me word1 length - LCS length + word2 length - LCS length.
6. ABhi LCS kese nikalte hai ye mene already bataya hai dusre file me same folder me vaha se dekho.
*/

class Solution {
    public int minDistance(String word1, String word2) {
        int dp[][] = new int[word1.length()][word2.length()];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }

        int l = lcs(word1, word2, 0, 0, dp);

        return (word1.length() - l) + (word2.length() - l);
    }

    public int lcs(String s, String str, int i, int j, int dp[][]){
        if(i == s.length() || j == str.length()) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        int take = 0;
        int notTake = 0;

        if(s.charAt(i) == str.charAt(j)){
            take = 1 + lcs(s, str, i+1, j+1, dp);
        }
            
        notTake = Math.max(lcs(s, str, i+1, j, dp), lcs(s, str, i, j+1, dp));
        
        return dp[i][j] = Math.max(take, notTake);
    }
}