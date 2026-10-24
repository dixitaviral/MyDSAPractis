/*
A conveyor belt has packages that must be shipped from one port to another within days days.

The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.

 

Example 1:

Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
Example 2:

Input: weights = [3,2,2,4,1,4], days = 3
Output: 6
Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4
Example 3:

Input: weights = [1,2,3,1,1], days = 4
Output: 3
Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1

Intution:

1. Bhai ye ques pehle kya keh raha hai aao smjhte hai. Ques me given cheeze hai:
    a. Ek weights array diya hai jisme basically packages ke weights jinko ek ek krke order me
        ship karna hai.
    b. Dusra days diye hai ki tumko wegihts array me jitne package diye hai unko ship karna hai
        at most given days me.
2. Abhi in given details se tumko batana hai ki min kitna capacity size ka ship lagega within at most
    given days me.
3. ABhi tum sochoge ki bhai isme binary search kese hi lagega, aao batata hu intution:
    1. Dekho bhai array par to lagegi ni binary search to kispar lagau, socho zara humko nikalna kya hai?
    2. Capacity to capacity par binary search lagegi, to capacity par lagate hai.
    3. Abhi tum kahoge bhai but capacity ka lower and upper bound kya rakhege. Dekho bhai kuch to 
        given hoga jisme lower and upper bound mil jae tumko.
    4. So wegithts array me se upper and lower bound milega. Abhi socho isme kya lower hoga and
        kya upper hoga. 
    5. Lower bound hoga bhai max weight kyu, kyuki agar socho ek bar me ship karna hua max weight
        ya capacity kitna chahiye, ofcourse max weight jo hoga utni.
    6. Theek abhi upper bound, upper bound basically hoga sum of all weights. Agree?
    7. Chalo theek abhi tumko ye information mili hai. Ab tum seedha mid nikal lo, mid kya bata ra hai
    8. Mid basically ek capacity bata ra hai ki bhai agar itni ship ki capacity rakhte hai to ship 
        par weights array ke package kitne din me jaege.
    9. Abhi tumko iska logic likhna padega ki tum days count kar le jao and tumhre sare package bhi
        ship ho jae. To ye bhai tumko khud socha padega ya na smjh aae to neeche do solution diye
        hai ek maine likha hai jisme n^2 complexity hai ek AI se likhvaya hai vo linear hai 
        to refer kar lena.
    10. Abhi karna hai ki days nikal aae hai, in days se tum check karo ki agar kam din lage hai
        ya equal days lage hai in compare to given days to mtlb current candidate ans ho skta hai
        to min store karo and window or choti kar do kyuki kya pata isse kam anser mil jae.
        To j = mid-1 kar do.
    11. Ab agar days jada lag rahe hai to capacity badha do to i = mid+1.

    12. Bas fir last me return kar do ans.

Khatam
*/

class Solution {
    public int shipWithinDays(int[] weights, int days) {
        /*
        question kya keh raha hai:
            1. Ki bhai weights array diya hai
            2. Days diye hai.
            3. Information hai apne pas:
                a. Ki sir humko x days jitne bhi packages hai vo ship krne hai.
                b. Or aisa ni hai ki aap ek hi din me sare ship kar do, appko 
                    x days use krne hi krne hai.
                c. To upar dono information se aap ye pata lagao ki sir ship ki
                    kitni capacity hogi ki hum exact x dino me sare packages 
                    ship kar de.
        */

        int j = 0;
        int i = 0;
        // i ko max possible weight se isliye assign kar rahe
        // kyuki base condition socho ki weights.length jitne
        // package bhejne hai in weight.length jitne din me
        // to dimaag lagao ki kitna max capacity honi chahiye
        // ofcourse jo max capacity hogi vohi.
        for(int num : weights){
            j += num;
            i = Math.max(i, num);
        }

        int ans = Integer.MAX_VALUE;

        while(i <= j){
            int mid = (i+j)/2;

            // shipped count humkp bata ra hai ki humne 1 din me 
            // humne 1 din shippedCount jitne package ship kiye hai
            int shippedCount = 0;
            //-1 se assign islliye kiya kyuki neeche loop me hum +1 karke
            // is variable ko use kar rahe hai to agar 0 rakhege to 0 hamesha
            // +1 ho jaega jisse ki 0th element kabhi check hoga hi ni.
            int shippedTillHere = -1;
            int daysSpended = 0;

            // days par loop chala rahe hai and ander ek nested loop hai
            // joki current mid ke hissab se per day ship kar ra hai by
            // decreasing tempMid from shipped weights. And since humko 
            // adjacent hi sath me ship karne hai to agar koi package 
            // current tempmid se bada aaya to bhai vo aaj ship ni kar skte
            // nested loop break kar do.
            for(int d = 1; d <= days; d++){
                int tempMid = mid;

                for(int idx = shippedTillHere+1; idx < weights.length; idx++){
                    if(tempMid >= weights[idx]){
                        tempMid -= weights[idx];
                        shippedCount++;
                        // shipped till here ko yaha track isliye kar re hai
                        // kyuki shippedtillhere tab hi badhaege na jab kisi
                        // package ko ship krne ke liye consider karege
                        shippedTillHere = idx;
                    }else{
                        // iska logic upar likha hai mene.
                        break;
                    }
                }
                daysSpended = d;
                if(shippedCount == weights.length) break;
            }

            if(shippedCount == weights.length && daysSpended <= days){
                ans = Math.min(mid, ans);
                j = mid-1;
            }else{
                i = mid+1;
            }

        }

        return ans;
    }
}


// optimized version linear day tracking instead of nested

class Solution {
    public int shipWithinDays(int[] weights, int days) {

        int j = 0;
        int i = 0;

        for(int num : weights){
            j += num;
            i = Math.max(i, num);
        }

        int ans = Integer.MAX_VALUE;

        while(i <= j){
            int mid = (i+j)/2;

            int shipped = 0;
            int daysSpent = 1;

            for(int num : weights){
                if(mid >= shipped+num){
                    shipped += num;
                }else{
                    daysSpent++;
                    shipped = num;
                }
            }

            if(daysSpent <= days){
                ans = Math.min(mid, ans);
                j = mid-1;
            }else{
                i = mid+1;
            }

        }

        return ans;
    }
}