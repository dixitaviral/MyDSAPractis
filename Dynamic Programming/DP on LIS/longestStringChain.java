/*
You are given an array of words where each word consists of lowercase English letters.

wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.

Return the length of the longest possible word chain with words chosen from the given list of words.

 

Example 1:

Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
Example 2:

Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
Example 3:

Input: words = ["abcd","dbqca"]
Output: 1
Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.

Intution:

1. Bhai ye ques dekhne darawna hai but hai easy. Or ye ques tmhre bhai ne khud solve kiya hai.
2. Aao ques smjhe, ques keh raha hai ki longest chain batao jisme har prev word se current word
    ban ske, and prev word ki length + 1 == current word ho. Aisa chain kitni size ki max ban skti
    hai vo batao.
3. Abhhi isko karne ke do tareeke hai:
    1. Isme simple teen steps hai:
        a. Sabse pehle array ko sort kar do on the basis of their length, jisse hoga ye
            chote words sare pehle aa jaege and bade aage chale jaege, to tumko prev word
            compare krne ki zarurt ni pdegi.
        b. Then simple usi sorted array par recursion + memoization chala do, jime ki tum
            current word loge ya ni loge.
        c. Abhi jo current word hai and prev word hai uske liye ek method bana lo check krne ke
            liye if prev is predecessor of current.
        d. Is check method me compare karege sabse pehle prev.length + 1 == cur.length and then
            check karege ki prev me jo characters hai vo same order me hai jese cur me hai
            agar ha to return true, else false.
        e. Bas fir last me recursion me take not take ka max return karna hai.
        f. Iski complexity hogi n^2 * L.
    2. Isme hume hashmap ka use karege and idea ye hai ki ek word pakdo, usme se ek ek character
        hatao and check karo ki kya vo hashmap me hai ya ni:
        a. Sabse pehle array ko length ke basis par sort kar do, jisse chote words pehle aa jaege.
        b. Ab ek word pakdo, usme se ek-ek character hatao and check karo ki resulting 
            word HashMap me hai ya ni.
        c. Agar HashMap me hai, to iska matlab jo word humne banaya hai vo current word ka 
            predecessor hai.
        d. HashMap me har word ke saath us word tak ki maximum chain length store karege.
        e. Agar predecessor HashMap me mil gaya, to current word ki chain length predecessor 
            ki chain length + 1 hogi.
        f. Har word ke liye uske L possible predecessors banenge, and har predecessor 
            string banane me O(L) lagega.
        g. Isliye iski complexity O(n * L²) hogi.

        Dusri vali approach khud try krna ek bar.
*/

// first approach:
class Solution {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, (a,b) -> a.length() - b.length());

        int dp[][] = new int[words.length][words.length+1];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }
        
        return helper(words, 0, -1, dp);
    }


    public int helper(String[] words, int i, int prev, int dp[][]){
        if(i == words.length) return 0;

        int take = 0;
        int notTake = 0;

        if(dp[i][prev+1] != -1) return dp[i][prev+1];

        if(prev == -1 || checkPredecessor(words[prev], words[i])){
            take = 1 + helper(words, i+1, i, dp);
        }

        notTake = helper(words, i+1, prev, dp);

        return dp[i][prev+1] = Math.max(take, notTake);
    }

    public boolean checkPredecessor(String s1, String s2){
        if(s1.length()+1 != s2.length()) return false;
        int i = 0;
        int j = 0;

        while(i < s1.length() && j < s2.length()){
            if(s1.charAt(i) == s2.charAt(j)){
                i++;
                j++;
            }else{
                j++;
            }
        }

        return i == s1.length();
    }
}