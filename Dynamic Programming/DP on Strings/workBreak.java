/*
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
 
Intution:

1. bhai ye ques seedha seedha hai, bas condition smjhni hai.
2. Ques keh ra hai ek word string di hai tumko us word string ko tumko aise split
    karna hai ki given array me jitne words hai vo sab match ho jae, agar ni hue match
    to return false.
3. Ab isme tumhre pas dekh jae to do choice hai:
    1. Do variable lelo i and j, jisme agar substring(i, j) list me kisi word se match ho ri hai
        to if block ke ander jao and jitna match hua hai string me i utna aage badha do, 
        simply i me j pass kar do kyuki j hi last index hai current substring ka and j ki 
        jagah j+1 pass kar do.
    2. Abhi if ke bahr do condition ban ri hai, ki maan lo match ho bhi gaya to kya pata
        current susbtring jisse matching hui, agar hum usi substring ko aage or badha le,
        mtlb ki or character append karke dekhe to kya pata koi or word match ho jae.
    3. To bas ye do conditions hai apni hai, base case me agar given string exhaust ho gyi
        means i start index hai to i exhaust hua mtlb pura match mil gaya to return true.
    4. Abhi since j end index hai substring ka jo ki exclusive hota hai to ye s.length() ke equal
        tak ja skta hai, to ye agar s.length() ke bahr chala gaya means return false.
    5. Abhi tumhre man me ek ques aaya hoga ki bhai, i start index hai susbtring ka, to ye
        kabhi end tak jaega hi ni.
    6. Ques si hai par upar if condition dekhe humne kya kaha tha ki agar susbtring kisi word
        se match hui to i ki jagah j ko bhej do, abhi j to last index par ho skta hai na
        isliye i exhaust ho skta hai.
4. Bas itna hi tha isme, baki dp lagana tumko aata hi hai vo mai ni batauga.

Neeche solution diya hai
*/

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet(wordDict);

        int dp[][] = new int[s.length()+1][s.length()+1];

        return helper(s, set, 0, 0, dp);
    }

    public boolean helper(String s, Set<String> set, int i, int j, int dp[][]){
        if(i == s.length()){
            return true;
        }

        if(j > s.length()){
            return false;
        }

        if(dp[i][j] != 0){
            if(dp[i][j] == 1) return true;
            else return false;
        }

        boolean take = false;

        if(set.contains(s.substring(i, j))){
            // match hone par do cases ho skte hai, ya to current word jo
            // match hua hai vo consume krke uske aage jo string bachi hai vo
            // consider kar lo

            // ya fir kya pata jo word humne abhi match kiya hai vahi same string
            // ko aage vale characters ke sath grow kare to koi or word bhi match ho jae.
            // to uski second branch hai ki extend krte raho. Extend krne vala logic if 
            // ke bahr likha hai.
            take = helper(s, set, j, j+1, dp);
        }

        boolean ans = take || helper(s, set, i, j+1, dp);

        if(ans){
            dp[i][j] = 1;
        }else{
            dp[i][j] = 2;
        }

        return ans;
    }
}