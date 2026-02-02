/*
    Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.

Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

 

Example 1:

Input: s = "abciiidef", k = 3
Output: 3
Explanation: The substring "iii" contains 3 vowel letters.
Example 2:

Input: s = "aeiou", k = 2
Output: 2
Explanation: Any substring of length 2 contains 2 vowels.
Example 3:

Input: s = "leetcode", k = 3
Output: 2
Explanation: "lee", "eet" and "ode" contain 2 vowels.
 

Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
1 <= k <= s.length

Intution:

1. Intution same hai sliding window fixed vali, jese hi window = k ho jae, res store kr lo
    then first char remove krdo, and start++ kar do.
2. Key takeaway ye hai ki jab bhi tum string traverse kr re ho, tum check kar re ho current
    char vowel hai kya? if yes then count++;
3. Yhi count tum res me store kr re ho, jab window = k ho jati hai that is i-k+1 == k.
4. Abhi jab window shrink krte ho by doing start++, then count bhi to kam krna padega na.
5. Kyuki man lo jo char kam ho raha hai vo vowel ho.
6. to ek condition check lagaege ki if s.charAt(start) == vowel then only count--.
7. Bs fir clear ekdm.

*/



class Solution {
    public int maxVowels(String s, int k) {
        Set<Character> vowels = Set.of('a','e','i','o','u');


        int i = 0;
        int start = 0;

        int count = 0;

        int res = 0;

        while(i < s.length()){
            char c = s.charAt(i);
            if(vowels.contains(c)){
                count++;
            }

            if(i-start + 1 == k){
                res = Math.max(count, res);
                if(vowels.contains(s.charAt(start))){
                    count--;
                }
                start++;
                
            }
            i++;

        }

        return res;
    }
}