/*

    You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.

We repeatedly make duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.

 

Example 1:

Input: s = "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
Example 2:

Input: s = "azxxzy"
Output: "ay"

Intution:
1. Stack use karenge, stack me char push karte jao.
2. Jab bhi koi char aisa mile jo stack ke top ke barabar ho to pop kar do.  
3. Finally stack me jo bacha hua hoga wahi answer hoga.

*/


class Solution {
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack();
        int len = s.length();
        String res = "";
        for(int i = 0; i < len; i++){
            char ch = s.charAt(i);
           if(!stack.isEmpty() && stack.peek() == ch){
            stack.pop();
           }else{
            stack.push(ch);
           }
            
        }

        while(!stack.isEmpty()){
            res = stack.pop() + res;
        }

        return res;
    }
}