/*
    Given an integer n, return true if it is a power of two. Otherwise, return false.

An integer n is a power of two, if there exists an integer x such that n == 2x.

 

Example 1:

Input: n = 1
Output: true
Explanation: 20 = 1
Example 2:

Input: n = 16
Output: true
Explanation: 24 = 16
Example 3:

Input: n = 3
Output: false

Intution:
1. Bhai simple ques hai recursion se karna hai.
2. Recursion me teen cheeze hoti hai:
    a. Base case: 
        i. Agar n == 0 hai to false return kar do, kyuki 0 kese hi calculate karoge.
        ii. Agar n == 1 hai to true return kar do, kyuki 2^0 = 1 hota hai. and isko 
            calculate kese hi karoge to seedha base case me aa gaya.
    b. Recursive case:
        i. Agar divide krte krte odd number aa gaya to false return kar do.
        ii. Abhi divide kyu kar rahe hai, kyuki agar tu dekhega 2^3 = 2*2*2.
        iii. And 2 se divisble to 18 bhi hai but tu jab 18/2 karega tab 9 aa jayega, 
            aur 9 odd hai to false return kar do.
    c. Last step me tu return kar dega isPowerOfTwo(n/2) ko, 
    kyuki tu n ko 2 se divide kar raha hai, aur jab tak n 1 
    nahi ho jata tab tak tu divide karta rahega.

*/


class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n == 0){
            return false;
        }
        if(n == 1){
            return true;
        }

        if(n % 2 != 0)
            return false;

        return isPowerOfTwo(n/2);
    }
}