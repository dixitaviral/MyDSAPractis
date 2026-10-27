/*
Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.

 

Example 1:

Input: nums = [1,3,5,6], target = 5
Output: 2
Example 2:

Input: nums = [1,3,5,6], target = 2
Output: 1
Example 3:

Input: nums = [1,3,5,6], target = 7
Output: 4
 

Intution:
1. Bhai simple hai ye isme tumko ya to agar element mil gaya to return kar do vo index, and agar
    ni mila to jis index par vo number hona chahiye vo return karo.
2. Abhi ye ques basically hoga binary search se.
3. Abhi dekho binary search se tumko ye pata hai ki target element search kese kare, but ye ni
    pata ki target element agar array ne hai ni to uski position array me kya hogi ye kese pata kare.
4. Iske liye simple intution hai:
    1. Dekho bhai man lo tumko koi element array me ni mila to jo while loop hai vo end kaha hoga?
    2. jab j < i hoga. Iska mtlb ye nikla ki i index par jo number hum dhundna chah re hai vo
        insert hoga and j index par vo number jaega jo i par tha
    3. To isme simple tumko i index return kar dena hai.
Bas ques khatam

*/

class Solution {
    public int searchInsert(int[] nums, int target) {
        int i = 0;
        int j = nums.length-1;

        while(i <= j){
            int mid = (i+j)/2;

            if(nums[mid] == target) return mid;

            else if(nums[mid] > target) j = mid-1;

            else i = mid+1;

        }

        return i;
    }
}