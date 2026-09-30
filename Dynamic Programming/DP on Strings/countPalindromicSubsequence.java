/*
Given a string of digits s, return the number of palindromic subsequences of s having length 5. Since the answer may be very large, return it modulo 109 + 7.

Note:

A string is palindromic if it reads the same forward and backward.
A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
 

Example 1:

Input: s = "103301"
Output: 2
Explanation: 
There are 6 possible subsequences of length 5: "10330","10331","10301","10301","13301","03301". 
Two of them (both equal to "10301") are palindromic.
Example 2:

Input: s = "0000000"
Output: 21
Explanation: All 21 subsequences are "00000", which is palindromic.
Example 3:

Input: s = "9999900000"
Output: 2
Explanation: The only two palindromic subsequences are "99999" and "00000".

Intution:

1. Ques straight foward hai, ki 5 length ke subsequence banao from given string and then check
    karo ki vo palindrome hai ya ni agar hai to count karo and last me return kar do.
2. Abhi isko krne ke liye mene jugaad nikala tha:
    a. Jugaad ye nikala tha ki:
        1. Simple ek stringbuilder banao, usme ek ek character add krte jao, then recusrion call karo
            i+1 par.
        2. Then check lagao ki stringbuilder ki length 5 ho gyi hai to kya vo palindrome hai ya ni.
            Agar hai to count++ and return.
        3. Agar ni hai to vapas append karo. Abhi 5 length hone par return hoga, then vo
            neeche jaega backtrack karo sb ko jo bhi last add kiya hai ab uske bina
            ek aur call karo.
        4. Yahi jugaad hai, abhi isme dikkat ye hai ki TLE de ra tha, ab tum kahoge dp laga do.
        5. Mene kaha laga to du but string builder bhi ek state hai isko index ke form me
            kese store karoge, tum bologe stringbuilder hi store kar lo, mai kahuga theek hai
            but MLE aa jaegi.
3. Abhi baat krte hai optimised solution ki jisme dp lag skte, or ek bat agar aise kisi ques me
    tum string bana rahe ho actual me, to vo solution dp ke perspective se kam ni karega, dp me
    tumko hamesha index ya length par khelna hoga.
4. To aao optimised version dekhe:

 */


// TLE solution, and isme DP ni lagegi, kyuki, sb bhi store krna
// pdega jo ki memory limit exception de degi.
class Solution {
    int count = 0;
    public int countPalindromes(String s) {
        if(s.length() < 5) return 0;

        int mod = 10_000_0007;

        helper(s, 0, new StringBuilder());

        return count % mod;
    }


    public void helper(String s, int i, StringBuilder sb){
        if(sb.length() == 5){
            if(isPalindrome(sb))
                count++;
            return;
        }

        if(i == s.length()) return;

        sb.append(s.charAt(i));

        helper(s, i+1, sb);

        sb.deleteCharAt(sb.length()-1);

        helper(s, i+1, sb);
    }

    public boolean isPalindrome(StringBuilder sb){
        int i = 0;
        int j = sb.length()-1;

        while(i < j){
            if(sb.charAt(i) != sb.charAt(j)){
                return false;
            }

            i++;
            j--;
        }

        return true;
    }
}