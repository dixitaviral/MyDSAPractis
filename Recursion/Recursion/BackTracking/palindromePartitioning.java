/*
    Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

 

Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]

Intution:

1. Bhai msst easy question hai bactracking ka.
2. Peeche ni jana hai aage badhna hai to start use kiya hai. Abhi humko har ek substring dekhi hai
    and check krna hai ki palindrome hai ya ni.
3. Uske liye simple for loop chalege, and substring banaege ek ek krke. Abhi substring banate time
    humko pata hai ki second parameter excllusive hota hai.
4. Jiski vajah se humko substring ke ander substring(start, i+1) pass krna hoga, but ye i+1 fatega
    and index out of bound dega, to for loop ki rnage kam kar dete hai, joki hogi s.length()-1 tak;
5. Abhi simple substring banao, if palindrome then add to ans, recurse and undo.
6. if not then continue.
7. Abhi base condition hogi jab start == s.length() ho jae.

bss ho gaya
*/

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList();

        create(result, new ArrayList(), 0, s);

        return result;
    }

    public void create(List<List<String>> result, List<String> li, int start, String s){
        if(start == s.length()){
            result.add(new ArrayList(li));
            return;
        }

        for(int i = start; i <= s.length()-1; i++){
            String temp = s.substring(start, i+1);

            if(checkIfPalindrome(temp)){
                li.add(temp);
                create(result, li, i+1, s);
                li.remove(li.size()-1);
            }

        }
    }

    private boolean checkIfPalindrome(String str){
        int a = 0;
        int b = str.length()-1;

        while(a <= b){
            if(str.charAt(a) != str.charAt(b))
                return false;
            a++;
            b--;
        }   

        return true;
    }
}