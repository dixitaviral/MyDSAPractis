/*
Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.

Return the minimized largest sum of the split.

A subarray is a contiguous part of the array.

 

Example 1:

Input: nums = [7,2,5,10,8], k = 2
Output: 18
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
Example 2:

Input: nums = [1,2,3,4,5], k = 2
Output: 9
Explanation: There are four ways to split nums into two subarrays.
The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.

Intution:

1. Bhai ye ques same hai as capacity to ship packages vale ques se but mujhe click ni hua tha
    to solve krne me kafi time lag gaya.
2. Abhi is ques ko smjho, keh ra hai ki bhai tumko k diya hai and ek array diye hai usme
    numbers hai abhi vo numbers ko tumko k partitions me divide krna hai and un k partitons ka kuch k sums niklege
    usme se max sum. But agar different combinations try kare but consecutive hi number lege tab bhi
    kuch alag k partitions bhi ban skte hai. to k partitions ka max, but diff diff k partitions ka min 
    return krna hai bole to mi(max sum of partitions).
3. Abhi isko smjhne ke liye and intution build krne ke liye ulta dekho ki k partitions banane hai and unka
    sum karna hai and un sum ka max nikalna hai then koi diff combination try krke vapas k partitons banane
    and sabka max sum ka min nikalna hai.
4. mene kaha accha theek khai. To bhai smjho baat ko ki:
    a. Agar hum array ka max number lele mtlb ki array me single number ka max sum parition vo number hoga
        mante ho.
    b. Abhi pure array ka sum kar de to ek sum nikal jaega theek.
    c. Abhi ek cheez socho, ki jo bhi ans banega jo bhi sum kisi partition ka ans banega, vo isi range
        me ni aaega?
    d. Soch kar dekho ki sare single partitons ka max array ka max num hoga and agar pure array ko
        ek partiton man lo to usse zada to ans ni hoga.
    e. To bhai isi se humko low and high ki value mil gyi hai.
    f. Ab bas logic itna sa hai ki mid nikalo. Abhi smjhna zaruri hai ki mid hamesha ek ans candidate hai
        kyuki humko min(max) sum return karna hai of a partition. And humne us partition ki hi range 
        nikali hai and usi range me ans hoga. And hum mid nikalte hi hai check krne ke liye ki isse bada
        ans ho skta hai ya ni.
    g. Abhi array par traverse karo and mid se jo jo number bade hai unka sum krke compare krte jao
        jab tak sum mid se chota hai vo sare numbers ek partition me aaege correct?
    h. Jese hi sum mid se bada ho partition temp variable ko by 1 badha do. then next partition ke liye
        sum ko current number se assign kar do. so that next partition ban paega ya ni ye check ho.
    i. Abhi dekho bhai zaruri ni hai ki k partitions hi bane agar mid bada hua kafi to mostly number and unke
        sum obvious mid se chote hoge and jab chote hoge to utne kam partitions banege. 
    j. To bhai jab partitions ban jae as per curent mid to humko check karna hai vo partitions k se kam hai 
        ya ni. Abhi ek confusion ho skta hai tumko ho for mid. To bhai jab hum par mid se sum compare kar rahe
        the numbers ka and jese hi numbers ka sum mid se bada hota tha to hum partitions++ krke sum reset krke
        current num kar dete the. Isse kya mtlb nikla ki us mid se jitne partition count aae, usme max sum mid 
        hi ya mid se chota hoga na bhai, kyuki jese hi mid se sum bada hua turnt humne sum reset kar diya.
    k. Abhi partition <= k hai to j = mid-1 kyuki kam partition mtlb mid bada hai chota karo. min ans store
        karo.
    l. else i = mid+1. 

Khatam bhai.
*/


class Solution {
    public int splitArray(int[] nums, int k) {
        int low = 0;
        int high = 0;

        for(int num : nums){
            high += num;
            low = Math.max(num, low);
        }

        int ans = Integer.MAX_VALUE;

        while(low <= high){
            int mid = (low+high)/2;

            int partition = 1;
            int sum = 0;
            
            for(int num : nums){
                if(mid >= sum + num){
                    sum += num;
                }else{
                    partition++;
                    sum = num;
                }
            }
            
            // <= isliye kyuki agar partiton < k aaya
            // to bhi hum un partition value se k partiton
            // bana skte hai and still sum mid hi aaega
            if(partition <= k){
                ans = Math.min(ans, mid);
                high = mid-1;
            }else{
                low = mid+1;
            }
        }

        return ans;
    }
}