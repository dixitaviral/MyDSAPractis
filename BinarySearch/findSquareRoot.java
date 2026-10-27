/*
Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
 

Example 1:

Input: x = 4
Output: 2
Explanation: The square root of 4 is 2, so we return 2.
Example 2:

Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.

Intution:

1. Bhai ques easy hai bas tujhe sqaure root kese nikalte hai ye pata hona chahiye.
2. agar tujhe root 2 ki value nikalni hai to kese nikalega, aao dekhte hai:
    a. Sabse pehle root 1 ki value dekhte hai 1*1 = 1, to root 1 ki value 1 hai.
    b. Abhi root 2 ki value 1 se thori badi hogi na agar root 1 ki value 1 hai to right?
    c. Theek to abhi agar hum kahe 1.5 * 1.5 = 2.25. Mtlb 2.25 ka root value hai 1.5.
    d. To root 2 ki value kam hogi 2.25 ki root value se joki hai 1.5 right. To chalo 1.4 * 1.4
        krke dekhe jo ki hai to aai 1.96 1.4*1.4 ki value.
    e. Abhi 1.4 * 1.4 krke bhi 2 ni pohoch rahe. Iska mtlb root 2 ki value 1.4 and 1.5 ke beech me
        hogi kahi so that will be 1.41, 1.42 and so on ....
    d. Kul mila kar root ki ki approx value hogi 1.41.
3. Same intution tumko binary search me lagana hai and nikalna hai:
    a. Sabse pehle 0 to given x ka mid nikalo, check karo if (long)mid * mid == x, if yes then return.
    b. Abhi agar (long)mid*mid != x, in this case check if (long)mid*mid > x to j = mid-1; 
    c. else i = mid+1;
    d. EK ques aa ra hoga ki (long) kyu laga rahe isme, vo isliye kyuki agar int range se bahr hua 
        to long casting ki vajah se outof bound ni jaega.
    e. Abhi mid * mid == x hua to theek else hume pata hai ki nearest value return krni hai jo ki
        loop break hone par j hogi.
    f. To last me return j. Abhi j hi kyu return kar rahe hai kyuki loop khatam hone par min value
        j ki hogi, i could cross given x that's why. hume least min or equal value chahiye.
*/

class Solution {
    public int mySqrt(int x) {
        int i = 0;
        int j = x;

        while(i <= j){
            int mid = (i+j)/2;

            if((long)mid*mid == x) return mid;

            if((long)mid*mid > x){
                j = mid-1;
            }else{
                i = mid+1;
            }
        }

        return j;
    }
}

