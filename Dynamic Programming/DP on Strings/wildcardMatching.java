/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

 

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.

Intution:

1. Bhai ye acccha hai and theek hai agar tumko smjh aa gaya kya scene hai isme.
2. To sabse pehle scene samjhte hai:
    a. Ques keh raha hai ki tmko do strings di hai s and p. s me simple a-z chars hoge and
        s me a-z chars hoge + * and ? hoga.
    b. ? mark ka mtlb ki koi bhi ek character tum isse match kar skte ho and * ka mtlb hai 
        1 ya 1 se zada kitne bhi characters tum isse match kara skte ho.
3. To bhai tumko basically ye batana hai dono string ko compare karke ki * and ? vala rule
    laga kar string match ho ri hai ya ni.
4. Abhi iska intution dekhte hai:
    a. Sabse pehle ye dekho ki jo character dono string me equal hoga vo and agar p strong me kisi 
        jagah par ? aa gaya vo bhi single char equal vala case hoga na kyuki as per rule
        hum ? ki kisi ek char ke sath equal kar skte hai.
    b. To bhai ek equal condition ho gyi tmhri, ab aati hai bat * condition ki, ki agar tumhri p
        string jth char * hua to isme do options hai tmhre pas, ya to tum * char of p string se
        s string me jitne bhi i and i+1/2/3....n chars ko match kara do, ya fir kisi ek character
        ko match kara do and j aage badha do i stable rakho, theek.
    c. to isse do conditions nikal kar aai ki s.charAt(i) == p.charAt(j) || p.charAt(j) == '?' to 
        tumko i and j dono badhane hai and recursion call krni hai, plus dp me store karta hai
        and result return karna hai.
    d. Dusri condition hogi ki else if (p.charAt(j) == '*') to tmhre pas do cheez hai ek bar i
        badhao j roko and ek bar j badhao i roko. and isko bhi dp me store karo and return karo.
    
    e. Abhi bat krte hai base condition ki:
        i. Dekho ques seedhe keh ra hai ki dono strings puri consume krni hai tumko, tabhi
            ans nikalna hai.
        ii. To hoga yu ki agar i exhaust ho gaya but j still exhaust ni hua lekin bhai ab i exhaust
            ho gaya and jth char p me agar * hua tab hi vo empty string of s kyuki s already exhaust
            ho chuka hai so empty string se match hoga. Iske liye ab while loop laga padega j ko
            last tak le jao and agar beech me * ke alava koi or char aaya to return false, else while
            loop end hone par true return kar do.
        iii. Ek condition or hai jo ki ye sirf j exhaust ho gaya i bacha to i me koi wild card characters
            hai ni to j me match kiise karoge to return kar do sirf false;
    f. Abhi bhai sirf tum dp check or laga dena base condition ke bad, khatam fir.
*/

class Solution {
    public boolean isMatch(String s, String p) {
        int dp[][] = new int[s.length()][p.length()];

        return helper(s, p, 0, 0, dp);
    }

    public boolean helper(String s, String p, int i, int j, int dp[][]){
        if(i == s.length()){

            // bhai ye isliye hai kyuki agar I exhaust ho gaya
            // and man lo j bacha hai and usme sirf * hai to 
            // vo empty string of i se match ho skta hai
            // agar * ni hai to mtlb match ni hoga to return false
            // else j badhate jao end tak
            while(j < p.length()){
                if(p.charAt(j) != '*'){
                    return false;
                }

                j++;
            }
            return true;
        }

        if(j == p.length()){
            return false;
        }

        if(dp[i][j] != 0){
            if(dp[i][j] == 1) return true;
            else return false;
        }

        if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'){
            boolean ans = helper(s, p, i+1, j+1, dp);
            if(ans){
                dp[i][j] = 1;
            }else{
                dp[i][j] = 2;
            }
            return ans;

        }else if(p.charAt(j) == '*'){
            boolean ans = helper(s, p, i+1, j, dp) || helper(s, p, i, j+1, dp);

             if(ans){
                dp[i][j] = 1;
            }else{
                dp[i][j] = 2;
            }
            return ans;
        }

        return false;
    }
}