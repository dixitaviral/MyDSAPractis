/*

Ques: You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.

Y
8 |      █           █               █       █
7 |      █           █               █       █
6 |      █       █   █               █       █
5 |      █       █   █   █           █       █
4 |      █       █   █   █       █   █       █
3 |      █       █   █   █       █   █   █   █
2 |      █       █   █   █       █   █   █   █
1 |  █   █       █   █   █       █   █   █   █
0 +------------------------------------------------
     B1  R1      B2  B3  B4      B5  B6  B7  R2

Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

Intution:
1. Bhai Two Pointer Approach use karni hai.
2. Simply dekhna hai ki konse pointer ki height choti hai usko hi move krna hai.
3. exact max water ke liye lowest height of two pointer and distance of x axis means j-i ko
    multiply kar do.

*/

class Solution {
    public int maxArea(int[] height) {
        int maxWater = 0;

        int i = 0; 
        int j = height.length - 1; 
        int temp = 0;

        while(i < j){

            if(height[i] <= height[j]){
                temp = (j-i)*height[i];
                maxWater = Math.max(temp, maxWater); 
                i++; 
            }else{
                temp = (j-i)*height[j];
                maxWater = Math.max(temp, maxWater);
                j--;
            }
        }

        return maxWater;
    }
}