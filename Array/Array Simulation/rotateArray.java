/*
Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.

 

Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]

Intution:
1. Bhai intution ye hai ki :
    a. Tumko reverse function likhna hai.
    b. Kyuki agar tum reverse function likhte ho according to k given in ques.
    c. To humko vahi ans mil jata hai.
    d. Sabse pehle pure array ko reverse kar do.
    e. Then again reverse karo 0 to k-1 tak. 
    f. Abhi k-1 kyu, kyuki k numbers ko humko rotate krke pehle lana hai but array 0 indexed hota hai.
    g. Jab tumne pehle pure array ko reverse kar diya, tab jo elements rotate hone the
        vo pehle aa gaye, kyuki rotate hokr jo elements last me aaege, vahi to pehle aaege.
    h. now since array 0 indexed hota hai to k-1 karoge na.
    i. Abhi jitne rotate ho kr pehle aane the vo aa gye sahi order me, but aage ke jo the vo
        tumne jab pure array ko reverse kiya vo peeche aae but unka order bhi bigad gaya.
    j. to abhi unko fix kar do by reversing array from k to len-1;
*/

class Solution {
    public void rotate(int[] nums, int k) {

        if(k == 0) return;

        // ye isliye kyu man lo 5 length ke array ko tum 7 bar reverse karoge
        // to vo equal hoga
        k = k % nums.length;

        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }
        

    public void reverse(int nums[], int start, int end){
        while(start < end){
            nums[start] = nums[start]+nums[end];
            nums[end] = nums[start]-nums[end];
            nums[start] = nums[start]-nums[end];

            start++;
            end--;
        }
    }
}