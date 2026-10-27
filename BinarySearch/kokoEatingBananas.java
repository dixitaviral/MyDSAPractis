/*
Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

 

Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23

Intution:

1. Bhai this ques is same as findSmallestDivisorGivenThreshold ki tarah hai. To uski ka
    intution copy paste kar raha hu.
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
    public int minEatingSpeed(int[] piles, int h) {
        int max = 0;

        for(int num : piles){
            max = Math.max(max, num);
        }

        int i = 1;
        int j = max;
        int ans = Integer.MAX_VALUE;

        while(i <= j){
            int mid = (i+j)/2;

            long sum = 0;

            for(int num : piles){
                sum += (num+mid-1)/mid;
            }

            if(sum > h){
                i = mid+1;
            }else{
                j = mid-1;
                ans = Math.min(ans, mid);
            }
        }

        return ans;
    }
}