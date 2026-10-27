/*
There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.

 

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Example 3:

Input: nums = [1], target = 0
Output: -1

Intution:

1. Bhai ye question easy hai agar tumko iska intution click ho gaya to. Par vahi click ni hota hai jldi.
2. Ques keh ra hai target dhundo in a rotated sorted array me. 
3. Abhi iske kuch simple steps hai vo tumko dekhne hai:
    1. Sabse pehle to binary search lagegi isme.
    2. Abhi karna ye hai ki sabse pehle mid find karo and check karo mid hi to target ni hai, agar hai to
        return kar do.
    3. Ab asli khel shuru hai agar mid target ni hai. Is case me tumko simple ek cheez check krni hai.
    4. Dekho bhai intution ye hai ki tum mid se left jao ya right jao, array rotated hai but sorted hai
        to mid se ya to left side sorted hogi ya to right side. Agree?
    5. To simple ye check karo ki nums[i] <= nums[mid] hai if yes left sorted hai and agar no to right sorted hai.
    6. Abhi ek sorted side mil gyi hai humko.
    7. Isme abhi tum ye check karo jo sorted side hai either from i to mid ya fir mid to j, kya is range me target
        lie karta hai.
    8. Isse ye pata chalega ki sorted side me target lie kar raha hai ya ni, agar hai to pointer as per that shrink karo, 
        agar ni karta hai means other side me lie kar raha hai, to uske acc shirnk karo i ya fir j ko.
4. Yahi krke tumko target o(log n) me mil jaega
*/

class Solution {
    public int search(int[] nums, int target) {
        int i = 0;
        int j = nums.length-1;

        while(i <= j){
            int mid = (i+j)/2;

            if(nums[mid] == target){
                return mid;
            }

            // check which side is sorted 
            // either left or right will be sorted
            // then inside the one which is sorted check if
            // the target is between sorted side.
            // if yes shrink apointer acc.
            if(nums[i] <= nums[mid]){
                if(nums[i] <= target && target < nums[mid]){
                    j = mid-1;
                }else{
                    i = mid+1;
                }
            }else{
                if(nums[mid] < target && target <= nums[j]){
                    i = mid+1;
                }else{
                    j = mid-1;
                }
            }
        }

        return -1;
    }
}