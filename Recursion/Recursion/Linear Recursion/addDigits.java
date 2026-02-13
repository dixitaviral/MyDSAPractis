
/*

    Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.

 

Example 1:

Input: num = 38
Output: 2
Explanation: The process is
38 --> 3 + 8 --> 11
11 --> 1 + 1 --> 2 
Since 2 has only one digit, return it.
Example 2:

Input: num = 0
Output: 0

Intution:
1. Bhai simple ques hai recursion se karna hai.
2. agar tu sum nikalega to formula banega sum = num % 10 + num / 10.
3. But ye condition simple loop me use hogi, abhi same tujhe recursion me karne ke liye
    recursion form me likhna pdega.
4. Soch tune last ka digit nikal liya num%10 se abhi tujhe baki ke digits nikal kar unka 
    sum nikalna hai.
5. Ab base condition me humko kaha gaya hai ki bhai jab single digit mil jae to return kar do.
6. Bas Base condition hogi num < 10
7. and recursrive condition hogi add(num%10 + add(num/10));
*/



class Solution {
    public int addDigits(int num) {

        if(num < 10){
            return num;
        }

        return addDigits(num%10 + addDigits(num/10));
    }
}