/*
You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.

Return the single element that appears only once.

Your solution must run in O(log n) time and O(1) space.
 

Example 1:

Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:

Input: nums = [3,3,7,7,10,11,11]
Output: 10

Intution:

1. Bhai ques keh raha hai ki tumko sorted array diya hai and tumko single element dhundna hai.
2. Abhi sorted array me kuch dhundna hai uske liye msst binary search hi lagegi joki
    sabse zada optimal hoti hai.
3. Aao is ques ka intution dekhe:
    a. So bhai agar tum upar ques me diye array ko dhyan se dekho to usme tumko ek pattern
        dikhega, and vo pattern ye hai ki agar tum left se dekhte ho to even index par duplicate
        numbers ka pehle element aata hai and odd index par dusra, lekin jab koi single
        element aata hai us case me pehla number pair ka odd par aane lgta hai and dusra even par.
    b. To bas yahi intution use krni hai, ki mid calculate karo and agar mid even nahi hai, to usko
        mid-- krke even karo.
    c. Abhi pattern check karo ki nums[mid] == nums[mid+1] hai agar to iska mtlb abhi tak pattern
        follow ho ra hai and that means single number right me hai, so hum i = mid+2 kar dege
        and +2 isliye kyuki humne pairs scan kar diye to 2 point aage badhege.
    d. Abhi agar aisa ni hai to bhai j = mid kar do. Kyu kyuki khud socho bhai jab tak even index
        odd se match hota rahe keeping in mind hum left to right ja re hai array me.
    e. and jab even index != odd index hai to iska mtlb yahi hua na ki left me single number hoga,
        kyuki ques keh raha hai ki pure array me sirf ek single number hai baki sare duplicates hai.
    f. Agar man me ye doubt hai ki j = mid kyu kiya mid+1 bhi to single number ho skta hai, to bhai
        ni hoga, kyuki humne dekha na ki agar even != odd hai that means pattern break and
        single number left me hoga right me abhi odd even vala pattern start ho gaya hai.
    g. Abhi simple hai hai jab loop end hoga tab i == j vali condition par hoga. That means nums[i]
        ya fir nums[j] jo ki dono equal hai vohi single number ka index hoga hamesha.

Bas khatam hai fir.
*/

class Solution {
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            int mid = (i + j) / 2;

            if (mid % 2 != 0) {
                mid--;
            }

            if (nums[mid] == nums[mid + 1]) {
                i = mid+2;
            }else{
                j = mid;
            }
        }

        return nums[j];
    }
}