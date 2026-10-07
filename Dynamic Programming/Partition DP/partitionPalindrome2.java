/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

 

Example 1:

Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
Example 2:

Input: s = "a"
Output: 0
Example 3:

Input: s = "ab"
Output: 1

Intution:

1. Bhai isme bhi partition dp lagegi. But ques kya keh ra hai aao dekh le.
2. Ques keh ra hai ki tumko batana hai ki min cuts laga kar string ko partition karo and jitni
    bhi string banaoge partition krke ya cut krke vo sari palindrome honi chahiye. And isme tumko
    min cuts return krne hai.
3. Man lo puri string hi palindrome hai to cuts 0 hoge.
4. Abhi intution dekh lete hai:
    1. Yr ye intution me jo time complexity hai vo kafi chali jati hai around 2800 ms. Uska
        jugaad bhi batauga.
    2. Par pehle 2800 ms me bhi kese karna hai ye dekh lete hai.
    3. To bhai burst balloon vala ques the jese ki i j k hai to i left and right burst karo
        then current burst karo.
    4. Isme hum same karege ki string pass hogi i = 0 to j = s.length() - 1. 
    5. Then k par loop chalega from i to j. And then since hum substring ki bat kar re hai
        and jo continuous hoti hai, to hum i to k substring check karege alag method bana kar
        ki kya palindrome hai, agar hai to cuts ko count kar lete cuts = 1+helper(s, k+1, j).
        k+1 , j isliye kyuki i to k already check kar li hai.
    6. Abhi kya karna hai ki ek check or laga do, mene ek cheez batai thi ki agar puri string
        palindrome hai to cuts 0 hoge. To ye check karna hai agar k == j hua to cuts ko 0 kar do
        else cuts = 1+helper(s, k+1, j). abhi k == j kyu. Kyuki bhai humne i to k already check kiya
        hai ki palindrome hai ya ni, and k == j ho, and j means s.length()-1 last index. which means
        whole string is a palindrome to 0 cuts needed.
    7. Bas Abhi min cuts ko store krte jao and last me return kar dena.
    8. For optimization i and j ki dp banaegi which will store min cuts between i and j. And isi
        ko return kar dena.
    9. Or optimizations lagana hai to agar same palindrome twice or more calculate kar re hai to 
        i j ki ek or dp bana skte hai jo already calculated palindrome store karega in dp
        to from i to k palindrome hai ya ni.
    10. Abhi as per leetcode isko or optimise kar skte hai by pre-computing palindrome vo try
        krna kabhi dobara ques lagana. Idea bas itna hai ki dp bana lo and i to k agar palindrome hai
        to store kar lo and isi palindrome dp ko helper method me pass kar do so that bar bar palindrome
        calculate na krna pade.

*/

class Solution {
    public int minCut(String s) {
        if(s.length() == 1) return 0;

        int dp[][] = new int[s.length()][s.length()];
        int isPali[][] = new int[s.length()][s.length()];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }

        for(int arr[] : isPali){
            Arrays.fill(arr, -1);
        }

        return helper(s, 0, s.length()-1, dp, isPali);
    }

    public int helper(String s, int i, int j, int dp[][], int isPali[][]){
        if(i > j) return 0; 

        if(dp[i][j] != -1) return dp[i][j];

        int min = Integer.MAX_VALUE;

        for(int k = i; k <= j; k++){
            int cuts = 0;

            if(isPali[i][k] == 0) continue;
            
            if(isPali[i][k] == 1 || isPalindrome(s, i, k)){
                isPali[i][k] = 1;
                // k == j ka mtlb hota hai, ki k j ke equal ho gaya
                // and humne palindrome string check ki thi i to k
                // tabhi hum if block ke ander aae, means puri string
                // from passed i and j is palindrome, to jab puri string hi
                // palindrome hai to min cuts 0 hoge, that's why cuts = 0;
                if(k == j) cuts = 0;
                else cuts = 1 + helper(s, k+1, j, dp, isPali);
                min = Math.min(min, cuts);
            }else{
                isPali[i][k] = 0;   
            }
        }       

        return dp[i][j] = min;
    }

    private boolean isPalindrome(String s, int start, int end){
        if(start == end) return true;

        while(start < end){
            if(s.charAt(start) != s.charAt(end)) return false;

            start++;
            end--;
        }

        return true;
    }
}