/*
Given a string s, find the length of the longest substring without duplicate characters.

 

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

Intution: (Pehla question bina chatgpt ke (: )

1. Isme two pointers lagege, and longest subarray niklega j-i+1 se, kyuki i and j do pointer move hoge.
2. ek while loop lgega if repeating character aata hai to, reason given below:
    a. Man lo repeating character aa gaya, to uss point tak ka subarray to bekr ho gaya na.
    b. iska mtlb humko i(left pointer) ko tb tak aage move krna pdega and set.remove(s.charAt(i))karna pdega
     jab tak vo repeating character set se remove na kar de 
    c. tabhi hum, set.remove(s.charAt(i)) kar re hai and then i++ kar re hai.
3. Rest simple hai set me add karo and j++ karo and j-i+1 se max length nikal lo.
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int i = 0; 
        int j = 0;

        int res = 0;

        Set<Character> set = new HashSet();

        while(j < s.length()){
            while(set.contains(s.charAt(j))){
                set.remove(s.charAt(i));
                i++;
            }

            set.add(s.charAt(j));
            res = Math.max(res, j-i+1);
            j++;

        }

        return res;

    }
}