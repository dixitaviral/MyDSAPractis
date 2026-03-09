/*
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.

 

Example 1:


Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.
Example 2:

Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0

Intution:
1. Bhai simple ques hai ye bhi,
2. Jese num of island kiya tha below steps me:
    a. traverse the grid and found cell having 1 and increase island count.
    b. do dfs recursion in all fours directions and stop when found a zero. Also mark visited island zero so that they will not be counted again.
    c. return count of island.
3. Isme halka twist ye hai ki max area return krna hai, to isme int return karege with 1+dfs in all direction. Stop agar zero mile
4. And Mark visited as 0 jab ek cell visi ho chuki ho.
5. That's all.
*/

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int area = 0;


        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length;j++){
                if(grid[i][j] == 1)
                    area = Math.max(dfs(grid, i, j, 0), area);
            }
        }

        return area;
    }

    public int dfs(int [][] grid, int i , int j, int temp){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return 0;

        if(grid[i][j] == 0) return 0;

        grid[i][j] = 0;

        temp = 1+dfs(grid, i+1, j, temp)+
               dfs(grid, i-1, j, temp)+
               dfs(grid, i, j-1, temp)+
               dfs(grid, i, j+1, temp);

        return temp;
    }
}