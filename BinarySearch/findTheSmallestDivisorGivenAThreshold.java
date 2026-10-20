/*
Given an array of integers nums and an integer threshold, we will choose a positive integer divisor, divide all the array by it, and sum the division's result. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.

Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).

The test cases are generated so that there will be an answer.

 

Example 1:

Input: nums = [1,2,5,9], threshold = 6
Output: 5
Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1. 
If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2). 
Example 2:

Input: nums = [44,22,33,11,1], threshold = 5
Output: 443

Intution:

1. Bhai ques sabse pehle kya keh raha hai ye smjhte hai.
2. Ques basically 3 chhez bol raha hai:
    a. Sabse pehle ek divisor choose kro us number se array ke sare elements ko
        divide kar do.
    b. Then jo number aae unko add kar do.
    c. Abhi agar ye sum given threshold kam ya equal hai to ye ans candidate hai.
3. Bas abhi tumko upar di hui condition se minimum ans nikalna hai.
4. Aao abhi intution dekhte hai:
    a. Sabse pehle to bhai concept kya hai ye dekhte hai.
    b. Humko isme binary search lagani hai jiske liye humko i and j chahiye.
    c. i hoga basically 1, abhi 1 kyu, kyuki i and j basically divisor ki range hai jo bataege
        ki divisor kitna max humko lena hai.
    d. to agar i = 0 le lege to 0 se koi divide hi ni hoga infinite aa jaega hence i = 1.
    e. Abhi j le lege array ki sabse zada max value. Kyu? Kyuki jo array ki max value hogi 
        vahi threshold hai array ke number ko divide krke min ans lane ka isliye.
    f. ABhi yaha se steps simple hai ki i and j humko pata hai mid nikal lo.
    g. Then jitne array ke numbers hai sabko mid se divide krke sum kar lo.
    h. Abhi ques ek cheez or keh ra hai ki jab array numbers ko mid se divide karoge to ans
        decimal me bhi aa skta hai to uska ceil number lena hai. Isko krne ke liye hum
        ye formula lagaege num +mid -1/mid. Isse tumko ceil value milegi.
    i. Abhi agar ye sum bada aata hai threshold se that means jo divisor hoga vo chota to 
        i = mid+1 karo.
    j. Abhi agar sum chota ya barabar hua threshold se to vo ans candidate hai to usko ek ans
        variable me store krte jao min of prev and curr. and since isme sum chota hai to divisor
        bada krte hai to j = mid-1 karege.
    k. Last me ans return kar do.
*/

class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int max = 0;
        for(int num : nums){
            max = Math.max(num, max);
        }
        
        int i = 1;
        int j = max;

        int min = Integer.MAX_VALUE;

        while(i <= j){
            int mid = (i+j)/2;

            int sum = 0;

            for(int num : nums){
                sum += (num+mid-1)/mid;
            }
            // sum bada hai that means divisor chota hai to usko badhao
            // else chota karo
            if(sum > threshold){
                i = mid+1;
            }else{
                min = Math.min(mid, min);
                j = mid-1;
            }
        }

        return min;
    }
}