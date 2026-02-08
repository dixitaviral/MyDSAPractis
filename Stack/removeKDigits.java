/*
    Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.

 

Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

Intution:

1. Stack use karege isme and stack use karte time ek golden rule hota hai.
2. Vo golden rule ye hai ki stack order maintain karne me help krta hai, either increasing or decreasing.
3. Same yaha apply karna hai, jo bhi increasing k number hai unko hatana hai and remaining elements jo
    stack me bache unko pop karke result me dalna hai.
4. Abhi is question me kuch special cases hai, following:
    a. K elements remove krne ka mtlb ye hai ki jab tak k == 0 na ho jae tb tk remove karna hai,
        chahe string empty kyu na ho jae.
    b. To hum iss quest ko 3 steps me karege:
        1. Step 1: Sabse pehle increasing k numbers ko remove karo.
        2. Step 2: Abhi increasing number remove krne ke bad bhi case ho skta hai ki, k still 0 na hua ho.
            To us case me hum increasing decreasing ni dekhege and stack.pop() karege jab tak k == 0 na ho jae.
        3. Step 3: ABhi scene hai ki stack me result pop karo and string builder me daal do.
            Jab stack empty ho jae, tb string reverse kar do, kyuki stack me lifo order me store hota hai.
        4. Abhi ek baat socho maan lo string bani "0200" isme leading '0' hai, ye hamare liye matter ni karta
            but leetcode me answer ni aaega.
        5. To leading zero hata do simple while(res.length() > 0 && res.charAt(0) == '0') res.deleteCharAt(0);
        6. Abhi ek case aur hai, maan lo string empty ho jae, to us case me answer "0" dena hai, 
            kyuki empty string ka matlab 0 hi hota hai. To return statement me check kar lo ki
             res.toString() == "" ? "0" : res.toString();

    
*/


class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack();
        
        int len = num.length();

        StringBuilder res = new StringBuilder("");
        
        //Step 1: remove increasing k number from string.
        for(char c : num.toCharArray()){
            while(!stack.isEmpty() && k > 0 && stack.peek() > c){
                stack.pop();
                k--;
            }
            stack.push(c);
        }

        //There can be still numbers left inside stack, which are not increasing,
        // but if k is still not zero you have to remove them to make minimal digit possible.
        while(k > 0 && !stack.isEmpty()){
            stack.pop();
            k--;
        }

        //Now just pop out remaining stack elements as that is smallest digit now.
        while(!stack.isEmpty()){
            res.append(stack.pop());
        }

        //since stack inserts in lifo order, so we have reverse it.
        res.reverse();

        //remove leading zeros. We have to consider 0th index only, and there only leading zeros
        // we will find.
        while(res.length() > 0 && res.charAt(0) == '0'){
            res.deleteCharAt(0);
        }

        // if the answer it empty string, then we have to return 0 as string.
        return res.toString() == "" ? "0" : res.toString();

    }
}