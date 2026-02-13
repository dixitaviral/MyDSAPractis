/*
Given a string s, return true if the s can be palindrome after deleting at most one character from it.

 

Example 1:

Input: s = "aba"
Output: true
Example 2:

Input: s = "abca"
Output: true
Explanation: You could delete the character 'c'.
Example 3:

Input: s = "abc"
Output: false


Intution:
    1. Bhai simple question hai recursion se karte hai.
    2. Dekh ques keh ra hai ki tujhe ek mismatch maaf hai. Buut ek se zada mismatch hue to false.
    3. Abhi agar puri string palindrome hai tab to return true after traversing whole string.
    4. Abhi twist aata hai ki agar pehle mismtach aaya to i move kare ya j:
        a. Solution with recursion hai to hum sare case explore kar skte hai.
        b. Hum check karege dono ek ek bar move krke. But usse pehle humko flag update krna hai
            kyuki ek hi mismatch allowed hai. Or ek check bhi lagana hai ki flag agar ek bar use ho chuka
            hai to dobara agar mismatch aae to seedha false return kar do.
        c. Simple condition ki agar char i and j are not equal to 
            return karege check(i+1, j, flag) || check(i, j-1, flag);
        d. Ek bar i ko badhaya and ek bar j ko.
        e. Abhi hume ye pata hai dono me se kisi ek me answer aaega to beech me or condition lagegi.
    5. Bas fir kya last me to humko palindrome check hi karna hai, to seedha check(i+1, j-1, flag)
    6. Base condition bhi i >= j ho to return true.
*/

class Solution {
    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length()-1;
        boolean flag = true;

        return check(i, j, s,flag);
    }

    public boolean check(int i, int j, String s, boolean flag){

        if(i >= j){
            return true;
        }

        if(s.charAt(i) != s.charAt(j)){
            if(!flag) return false;
            flag = false;
            return check(i+1, j, s, flag) || check(i, j-1, s, flag);
        }
        
        return check(i+1, j-1, s, flag);
    }

}