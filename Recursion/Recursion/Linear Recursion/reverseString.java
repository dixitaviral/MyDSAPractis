/*

Write a function that reverses a string. The input string is given as an array of characters s.

You must do this by modifying the input array in-place with O(1) extra memory.

 

Example 1:

Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:

Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]

Intution:
1. Bhai simple ques hai recursion se karna hai.
2. Recursion me teen cheeze hoti hai:
    a. Base case: 
        i. Agar i >= j hai to return kar do, kyuki jab i aur j same point pe aa jayenge to reverse karna band kar do.
    b. Recursive case:
        i. Abhi tu reverse kar raha hai, to pehle tu swap kar dega s[i] aur s[j] ko.
        ii. Abhi tu i ko 1 se increment kar dega aur j ko 1 se decrement kar dega, kyuki tu next pair ko reverse karega.
    c. Last step me tu return kar dega reverse(i+1, j-1, s) ko, kyuki tu next pair ko reverse karega.

Bonus tip:
1. Kabhi is ques ko revisit karna to sochna agar swapping recursive call ke bad kare tb 
    kya answer aaega.

*/

class Solution {
    public void reverseString(char[] s) {
        
        int i = 0;
        int j = s.length-1;
        reverse(i, j, s);
    }

    public void reverse(int i, int j, char[] s){
        if(i >= j){
            return;
        }

        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;

        reverse(i+1, j-1, s);
    }
}