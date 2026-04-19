/*
Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.

A good subarray is a subarray where:

its length is at least two, and
the sum of the elements of the subarray is a multiple of k.
Note that:

A subarray is a contiguous part of the array.
An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 

Example 1:

Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
Example 2:

Input: nums = [23,2,6,4,7], k = 6
Output: true
Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
Example 3:

Input: nums = [23,2,6,4,7], k = 13
Output: false

Intution:
1. Bhai ye ques me bhi orefix sum lagega and hashmap lagega.
2. Abhi iss ques ki intution se pehle ek masst maths ka chota topic smjhata hu:
    a. Dekh bhai agar tere pas nukmber hai 31 and dusra number hai 4, to jab 31 ka modulo nikalega
        to aaega 3, yaha tak clear hai. 
    b. Chalo abhi hum ek 4 ke hi multiple ko 31 me add krte hai let's say 8. to 31+8 = 39. 
    C. Badhiya, abhi 39%4 karo jo ki aaya 3. 
    d. Ooo bhai, iska mtlb ki agar same number me 4 or say it 'k' ke multiple ko add krke
        modulo nikaloge to bhi remainder same aaega.
    e. Abhi same logic apne ko iss ques me lagana hai.
3. Abhi intution simple hi hai:
    a. Sabse pehle step 1 me curSum lelo jisme tumko given nums loop step me add krne hai.
    b. Abhi isko curSum ko k se modulo krke curSum me store kar lo.
    c. Abhi 3 step, check if curSum exist in map or not, if exits, then check karo ki map me jo index
        store kiya hai modulo as key and index as value, uska diff with current index
        is greeater or equal to 2, if yes, return true.
    d. Or ha 3 point me if condition lagegi and usi if ke else me tum map fill kaorge with 
        curSum after moduulo and current index.
4. Then if loop ends return false, that's it.
*/

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int curSum = 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap();

        map.put(0,-1);

        for(int i = 0; i < nums.length; i++){
            curSum += nums[i];

            curSum %= k;

            if(map.containsKey(curSum)){
                if(i-map.get(curSum) >= 2)
                    return true;
            }else
                map.put(curSum, i); 
        }

        return false;

    }
}