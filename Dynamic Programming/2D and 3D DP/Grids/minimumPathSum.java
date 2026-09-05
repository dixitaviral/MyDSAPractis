/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

 

Example 1:


Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
Example 2:

Input: grid = [[1,2,3],[4,5,6]]
Output: 12
 
Intution:
1. Bhai ye ques bhi easy hai.
2. Tumhre pas do choice hai ya to tum down ja skte ho ya to tum right.
3. And down and right me se jo min hoga vo choose krna hai tumko.
4. Base condition rahegi ki overflow ho jae to int max return karo, as hum min check kar rahe hai
    to 0 return kiya to 0 ans le lega.
5. Else agar hum last cell par pohoch gye hai to uski value return kar do.
6. Abhi last me return karna hai current cell + min of down and right.
7. Ab main baat aati hai memoization ki:
    a. Ya to hum alag dp array bana le usme i and j ko store kare and return kar de.
    b. Dusra hai hum given grid ko hi use kar le. Vo kese karege aao dekhe:
        i. Since ques ka constraint hai ki cell value hamesha positive hogi.
        ii. To hum grid me hi ans ko negative krke store kr skte hai, but jab
            hum usko return karege tab usko Math.abs me rakh kar krege taki vo
            positive ho jae.
        iii. grid me already calculated value padi hai isko hum aise check karege ki
            agar grid me negative value hai means already calculated hai else calculate karo.
        iv. Abhi tumhre man me ek ques hoga jiska ans hai ki grid usi cells ki update hogi ek bar me
            jo current path me cells aa rahe hai.
        v. Fir dobara me agar same cell aaya tab uski min value lege.
*/


// extra space solution
class Solution {
    public int minPathSum(int[][] grid) {
        int dp[][] = new int[grid.length][grid[0].length];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }
        return helper(grid, 0, 0, dp);
    }

    public int helper(int[][] grid, int i, int j, int [][] dp){
        if(i >= grid.length || j >= grid[0].length) return Integer.MAX_VALUE;

        if(i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];

        if(dp[i][j] != -1) return dp[i][j];

        int down = 0;
        int right = 0;

        down = helper(grid, i+1, j, dp);
        right = helper(grid, i, j+1, dp);

        return dp[i][j] = grid[i][j] + Math.min(down, right);
    }
}

// reusing same grid as dp
class Solution {
    public int minPathSum(int[][] grid) {
        return helper(grid, 0, 0);
    }

    public int helper(int[][] grid, int i, int j){
        if(i >= grid.length || j >= grid[0].length) return Integer.MAX_VALUE;

        if(i == grid.length - 1 && j == grid[0].length - 1) return Math.abs(grid[i][j]);

        if(grid[i][j] < 0) return Math.abs(grid[i][j]);

        int down = 0;
        int right = 0;

        down = helper(grid, i+1, j);
        right = helper(grid, i, j+1);

        grid[i][j] = -(grid[i][j] + Math.min(down, right));

        return Math.abs(grid[i][j]);
    }
}