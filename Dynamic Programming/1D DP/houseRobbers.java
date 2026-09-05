/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

 

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.

Intution:
1. Bhai ye ques bhi easy hai kafi, aao smjhe.
2. Ye ques keh raha hai ki chor jo hai bo adjacent ghar me hi chori kar skta hai.
3. And fir ye batao ki max kitna paisa vo chura skta hai.
4. Abhi iska intution dekhte hai:
    a. Dekho bhai isme tmhre pas do choice hai ya to tum pehle ghar me chori karo to tum
        dusre ghar me ni kar skte uske agle ghar me kar paoge.
    b. Abhi agar tumne 1 ghar me chori ni ki to tum 2 ghar se start kar skte ho, then 4
        ghar and so on.
    c. To ye ho gyi tmhri do choices, abhi iski base condition dekhte hai ki agar i index 
        nums.length ke barabar ya usse zada ho gaya to return 0.
    d. Ek is condition b point ke pehle lagegi jo ye bataegi ki man lo tu dusre ghar me 
        chori krne gye to hoga nums[i+1], par agar i+1 exist hi na kare to code fatega
        isliye ek overflow check laga do ki if (i+1 < nums.length) then b point likho.
    e. Now last me max of amount between point a and b jo aee vo return kar do.
    f. Fir memo ki bat aati hai to 1d memo banega base condition ke bad check hoga agar
        memo me value hai to vo use karo else normal flow.
    g. Same way jab last me max return karoge to to memo me store krke return krna.

Bas itna hi hai.
*/

class Solution {
    public int rob(int[] nums) {
        int memo[] = new int[nums.length];

        Arrays.fill(memo, -1);
        return helper(nums, 0, memo);
    }

    public int helper(int[] nums, int i, int[] memo){
        if(i >= nums.length) return 0;

        if(memo[i] != -1) return memo[i];
        
        int am1 = nums[i] + helper(nums, i+2, memo);

        int am2 = 0;

        if(i+1 < nums.length){
            am2 = nums[i+1] + helper(nums, i+3, memo);
        }

        return memo[i] = Math.max(am1, am2);
    }
}