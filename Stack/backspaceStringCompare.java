/*
Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.

 

Example 1:

Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".
Example 2:

Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".
Example 3:

Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".

Intuition:
1. Bhai ekdm simple hai, dono strings ke liye stack bna lo.
2. Jo character # ni hai usko stack me push kar do.
3. Jo # hai to stack se pop kar do, but pop karne se pehle check kar lo ki stack empty to ni hai.
4. Finally dono stacks ko compare kar do.

*/

class Solution {
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> stack = new Stack();
        Stack<Character> s2 = new Stack();

        int i = 0;

        int j = 0;

        while(i < s.length() || j < t.length()){
            if(i < s.length()){
                char c1 = s.charAt(i);
                if(c1 != '#')
                    stack.push(c1);
                else{
                    if(!stack.isEmpty())
                        stack.pop();
                }
                i++;
            } 
            if(j < t.length()){
                char c2 = t.charAt(j);
                if(c2 != '#')
                    s2.push(c2);
                else{
                    if(!s2.isEmpty())
                        s2.pop();
                }
                j++;
            }
            
        }

        return stack.equals(s2);
    }
}