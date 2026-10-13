/*
    You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

 

Example 1:


Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
Example 2:


Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
Output: 0
Explanation: All 1s are either on the boundary or can reach the boundary.

Intution:
1. Bhai ye ques bhi simple hai, bs shart ye hai BFS aana chahiye.
2. Or iss ques me jo cell hai vo apni value khud ni de rahe and dusre cell par depend kar rahe hai
    isliey isme queue lagi else, for loop grid traversal me hi kaam ho skta tha.
3. Abhi iss ques ke simple steps hai:
    a. sbse pehle grid traverse karo, jo bondary par 1 mile unko marked kar do 0 se, taki bfs ke time par vo count na ho and queue me add kar do.
    b. idea ye hai ki jo 1 boundary cells humko ander tak le ja rahe hai, vo invalid hai. rest jitne bache vo valid hai.
    b. and same traversal me jo boundary par 1 ni hai means inner 1's hai, unko count me add kar do.
    c. Abhi bfs chalao means whilw queue not empty. for every cell in queue check in four direction and mark that 1 as 0 and do count--;
    d. and add that cell to queue, so that next adjacent 1's can be marked and count-- can be done.
    e. that's it the ques is done.
*/

class Solution {
    public int numEnclaves(int[][] grid) {
        
        int rowLen = grid.length;
        int colLen = grid[0].length;

        int arr[][] = new int[][] {{1,0},{0,1},{0,-1},{-1,0}};

        Queue<int[]> queue = new ArrayDeque();
        int count = 0;

        for(int i = 0; i < rowLen; i++){
            for(int j = 0; j < colLen; j++){
                if(grid[i][j] == 1){
                    if(i == 0 || j == 0 || i == rowLen-1 || j == colLen-1) {
                        grid[i][j] = 0;
                        queue.add(new int[]{i,j});
                    }else{
                        count++;
                    }
                    
                }
            }
        }

        while(!queue.isEmpty()){
            int []pair = queue.poll();

            int i = pair[0];
            int j = pair[1];

            for(int idx = 0; idx < 4; idx++){
                int row = i+arr[idx][0];
                int col = j+arr[idx][1];

                if(row < 0 || col < 0 || row >= rowLen || col >= colLen) continue;

                if(grid[row][col] == 1){
                    grid[row][col] = 0;
                    queue.add(new int[]{row,col});
                    count--;
                }
            }
        }

        return count;
    }
}    