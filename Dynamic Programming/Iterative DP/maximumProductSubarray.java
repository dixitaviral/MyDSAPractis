/*
Given an integer array nums, find a subarray that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

Note that the product of an array with a single element is the value of that element.

 

Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

Intution:
1. Dekh bhai brute force to iska yehi hoga ki do loop laga lo.
2. Then product krte jao sare combination and max product return kar do.
3. Vo n^2 complexity le jaega.
4. Aao isse better approach hai:
    1. Dekh bhai problem hai negative numbers kyuki -2*-2 = +4 ho jata hai.
    2. To abhi scene hai ye ki total 3 variable lelo:
        a. min
        b. max
        c. ans
    3. Inko 0th number array se assign kar do.
    4. Abhi loop start karo from 1 to n-1;
    5. Dekho logic ye hai ki jo chota ya negative number hai vo aage jakr bada ban skte hai.
    6. Uske liye hi hum min and max track kar rahe hai.
    7. To mast ek variable lo loop ke ander newMax and usme max of current number and
        product between cur num and min and max variable store kar do.
    8. Upar hum abhi tak ka sabse max number store kar rahe hai, abhi ek or variable lelo
        newMin isme tum min of curr number and product between product and min and max store karo.
    9. Abhi tumko isi newMin and newMax se max nikal kar ans store karna hai.
    10. And last me update kar do min and max variable from newMin and newMax variable.
    11. Return ans.

Khatam.
*/

class Solution {
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int ans = nums[0];

        for(int i = 1; i < nums.length; i++){
            int newMax = Math.max(nums[i], Math.max(max*nums[i], min*nums[i]));

            int newMin = Math.min(nums[i], Math.min(max*nums[i], min*nums[i]));

            max = newMax;
            min = newMin;

            ans = Math.max(ans, Math.max(newMax, newMin));
        }

        return ans;
    }

    
}