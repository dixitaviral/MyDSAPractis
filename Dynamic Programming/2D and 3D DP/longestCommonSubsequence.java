/*
Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

 

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.

Intution:

1. Bhai ye ques to tumne khud se hi solve kar liya hai.
2. Subsequence basically koi bhi sequence ho skta hai jese string hai aab to subsequence ho skte hai
    a ab aab, only b etc.
3. Abhi isme humko batana hai ki do strings di hai usme se sabse largest common subsequence hai.
4. To isko sabse pehle recursion se karte hai bad me dekhege DP kese lagana hai kya state hogi.
5. Aao intution dekhe:
    a. dekho recursion sabse pehle to chalega jab do string ke character match karege
        mtlb agar match hua to i+1 j+1 karege and 1+ kar dege result me and ek max variable lekr 
        cur recursion cycle se jo common sbsquence ki len aaegi and jo already aa chuki hai ya max me store hogi
        usme se max store karege max me.
    b. Abhi do recursion branch or banegi, jo basiaclly subsequence vali hogi. 
    c. Dekho bhai pehla hua match vala case, abhi scene hoga ki match hua ni to ya i badh lo j mat badhao
        to match ho jae ya to j badhai i roko to match ho jaega.
    d. Abhi in dono branch me se bhi dono max nikalo but since isme hum kuch include ni kar rahe kyu
        kyuki ye ek tarah se not take vala case ho gaya hai.
    e. Bas abhi take and not take vale case se max return kar do.

6. Abhi isme DP kese lagegi, state i and j hogi bas usi par laga do memo[i][j] me max store karo return krte time.
7. Then upar agar memo[i][j] ki value hai to vahi return kar do.

ho gaya
*/

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int memo[][] = new int[text1.length()][text2.length()];

        for(int arr[] : memo){
            Arrays.fill(arr, -1);
        }
        return helper(text1, text2, 0, 0, memo);
    }

    public int helper(String s1, String s2, int i, int j, int memo[][]){
        if(i == s1.length() || j == s2.length()){
            return 0;
        }

        if(memo[i][j] != -1) return memo[i][j];

        int max = 0;

        if(s1.charAt(i) == s2.charAt(j)){
            int n = 1 + helper(s1, s2, i+1, j+1, memo);

            max = Math.max(max, n);
        }

        int a = Math.max(helper(s1, s2, i+1, j, memo), helper(s1, s2, i, j+1, memo));

        return memo[i][j] = Math.max(max, a);
    }
}