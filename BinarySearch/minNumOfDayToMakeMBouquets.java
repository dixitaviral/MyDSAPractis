/*
You are given an integer array bloomDay, an integer m and an integer k.

You want to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.

The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet.

Return the minimum number of days you need to wait to be able to make m bouquets from the garden. If it is impossible to make m bouquets return -1.

 

Example 1:

Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
Output: 3
Explanation: Let us see what happened in the first three days. x means flower bloomed and _ means flower did not bloom in the garden.
We need 3 bouquets each should contain 1 flower.
After day 1: [x, _, _, _, _]   // we can only make one bouquet.
After day 2: [x, _, _, _, x]   // we can only make two bouquets.
After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.
Example 2:

Input: bloomDay = [1,10,3,10,2], m = 3, k = 2
Output: -1
Explanation: We need 3 bouquets each has 2 flowers, that means we need 6 flowers. We only have 5 flowers so it is impossible to get the needed bouquets and we return -1.
Example 3:

Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
Output: 12
Explanation: We need 2 bouquets each should have 3 flowers.
Here is the garden after the 7 and 12 days:
After day 7: [x, x, x, x, _, x, x]
We can make one bouquet of the first three flowers that bloomed. We cannot make another bouquet from the last three flowers that bloomed because they are not adjacent.
After day 12: [x, x, x, x, x, x, x]
It is obvious that we can make two bouquets in different ways.

Intution:

1. Bhai ye ques hai easy agar ek bar tumne pakad liye liya concept.
2. Usse pehle aao question dekhte hai kya bol raha hai.
3. Ques simply keh raha hai ki ek bllomDay array diya gaya hai jisme present numbers k a mtlb hai
    ki konse day par konsa flower khilega.
4. Then tumko m and k diye hue hai m mtlb itne bouquets banane hai and k mtlb har bouquets
    me kitne flowers honge.
5. Also ek rule or hai ki jo tum k flowers choose karoge for creating bouquets, vo adjacent flowers
    hone chahiye ye nahi ki kahi se bhi uthae le rahe ho.
6. To ye rules hai abhi tum soch rahe hoge bhai isme binary search kaha se lagegi.
7. To aao intution dekhe kese lagegi:
    1. Concept smjho pehle. Isme aadha tumhi ne crack kar liye tha ek jagah atke the but later smjh
        kar laga liya tha.
    2. To bhai smjho ki binary search me humko i and j ki value chahiye hoti hai, vo kaha se 
        aaegi.
    3. Socho min konse din flower khilega and max konse din flower khilega agar ye information
        humko mil jae to hum us range me jitne flowers bloom hoge hum choose krke bouquets bana
        skte hai right?
    4. And min and max kaha se milega, vo milega tumko bloomDay array se. Chalo badhiya min and
        max mil gaya abhi tumko mid bhi nikal liye ab kya karoge mid ka?
    5. Ek concept to ye hai ki i to mid tak dekho kitne flowers bloom ho skte hai and un flowers
        se kitne bouquets ban skte hai. Condition kuch aisi hogi ki while(i<=j) binar search ka loop
        hoga.
    6. Then tumne mid nikala hai and ab tum ye dekho ki for loop chala lo pure array par and 
        check karo kon kon se days hai jo mid se chote ya equal hai. 
    7. Abhi jitne adjacent flowers tumko mid se kam ya equal mile vo count kar lo and sath me
        check kar lo ki flowers count == k hua to ek bouquet ban jaega to bouquet ka bhi count 
        badha do. And yes bouquet banane ke bad flowers = 0 krna pdega. For count next flowers.
    8. Isi ke else block me flowers = 0 kar lo, kyuki agar adjacent flowers ni mile to flowers count
        bekar ho gaya.
    9. Abhi itna to mene bhi soch liye tha. Iske aage ka scene smjh ni aa ra tha ki bhai i and j
        kese set karu abhi.
    10. To uske liye pata chala ki dekho bhai agar mid se less days me bouquet jo bane hai to >=
        m bane hai, mtlb ki sab mid se left me hi ban gye, to humko min ans return karna hai
        to hum j = mid-1 kar dege.
    11. And agar jitne bouquet chahiye the usse kam bane hai to to ofcourse humko mid ke right
        jana padega so i = mid+1;
    12. Bas fir to msst last me ans return kar do.
*/

class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        // bloomday array -> this array basically tells you, on which day
                             // the ith flower will bloom.
        // m -> m means itne bouquets banane hai
        // k -> k means per bouquet kitne flower chahiye
        // rules:
        //  1. pehle rule ye hai ki ek bouquet banane ke liye
        //     tumko k flowers chahiye but tum sirf adjacent 
        //     k flowers hi use kar skte ho
        //  2. agar m*k > hua array size se to return -1 kyuki,
        //     m*kk bata ra hai ki kitne total fool lagege but
        //     agar utne fool hai hi ni to ho ni paega
        

        if((long)m*k > bloomDay.length) return -1;

        int max = 0;
        int min = Integer.MAX_VALUE;

        for(int num : bloomDay){
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        int i = min;
        int j = max;
        int ans = Integer.MAX_VALUE;

        while(i <= j){
            int mid = (i+j)/2;

            int flowers = 0;
            int bouquets = 0;

            for(int num : bloomDay){
                if(num <= mid){
                    flowers++;

                    if(flowers == k){
                        bouquets++;
                        flowers = 0;
                    }
                }else{
                    flowers = 0;
                }
            }
            
            
            if(bouquets >= m){
                ans = Math.min(ans, mid);
                j = mid-1;
            }else{
                i = mid+1;
            }
        }

        return ans;
    }
}