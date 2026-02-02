/*
    Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
 

Example 1:

Input: s = "()"

Output: true

Example 2:

Input: s = "()[]{}"

Output: true

Example 3:

Input: s = "(]"

Output: false

Example 4:

Input: s = "([])"

Output: true

Example 5:

Input: s = "([)]"

Output: false


Intuition:
1. Bhai intution bilkul simple hai, bas 3 conditions yad rakho:
    a. Agar valid parenthesis had to stack me push kar do.
    b. Agar invalid hai mtlb ki closing bracket hai to 2 cheeze check karni hai:
        i. Stack empty to invalid ho gaya, kyuki stack empty hai, iska mtlb koi opening bracket ni
            mila and closing bracket aa gaya. To invalid and return false.
        ii. Stack empty ni hai to top element check karo ki matching opening bracket hai ki ni.
            agar hai to pop kar do, ni to invalid.
2. Bas fir last me stack.isEmpty() return kar do.
*/

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            if(c == '(' || c == '{' || c == '['){
                stack.push(c);
            }else{
                if(stack.isEmpty()){
                    return false;
                }else{
                    if(c == ')' && stack.peek() == '('){
                        stack.pop();
                    }else if(c == ']' && stack.peek() == '['){
                        stack.pop();
                    }else if(c == '}' && stack.peek() == '{'){
                        stack.pop();
                    }else{
                        return false;
                    }
                }
            }
                
        }

        return stack.isEmpty();
    }
}