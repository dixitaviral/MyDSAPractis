/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

 

Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9


Intution:

1. Two Pointer hoge, i and j. i will start from 0 and j will start from end.
2. Two max variable bhi lagege jo bataege ki left me abhi tak konsi sbse badi wall hai and same for right.
3. Abhi left and right me sbse badi wall hum i++ and j-- karke hi track karege.
4. Then simple rule, i index ki wall choti hai ya equal hai jth index ki wall se to i++;
5. if jth index choti hai then j--;
6. Abhi socho jo wall choti hogi utna hi to max water bhar paoge na, ab chahe i side ki choti ho chahe j side ki
7. To water quantity caluclate karege min(leftMax, rightMax) then for example ek 4 size ki wall hai and ek 1
    size ki to total water unke beech 3(4-1) hi aaega na, similary
8. After min(leftMax, rightMax) humko choti wall mil jaegi ek side ki usko humko current index ki wall height 
    se, either i or j index vali ho choti hogi usse minus kar dena hai.

Bonus tip:
1. I know confusing hai bad me smjh ni aaega to uske liye piece of code de ra hu:
            if(height[i] <= height[j]){
                water += Math.min(leftMax, rightMax) - height[i];
                i++;
            }else{
                water += Math.min(leftMax, rightMax) - height[j];
                j--;
            }
Simple, sbse pehle min wall nikalo max walls from both side se, then current i and j index ki choti size vali
wal se minus kar do.


*/


class Solution {
    public int trap(int[] height) {

        int leftMax = 0;
        int rightMax = 0;
        int i = 0;
        int j = height.length-1;
        int water = 0;


        while(i < j){
            leftMax = Math.max(leftMax, height[i]);
            rightMax = Math.max(rightMax, height[j]);

            if(height[i] <= height[j]){
                water += Math.min(leftMax, rightMax) - height[i];
                i++;
            }else{
                water += Math.min(leftMax, rightMax) - height[j];
                j--;
            }
        }

        return water;
        
    }
}