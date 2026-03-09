/*
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3

Intution:

1. Bhai ye ques bhi word search se dervied hai, but usse bohot simple hai.
2. Humko ek grid di hai, usme jitne 1's humko mil rahe hai, jo ek sath likhe hue hai and koi bhi zero beech me ni aa ra hai
    vo sare 1 mila kar 1 island call hoge.
3. Isko karne ka simple tareeka hai, aao intution dekhte hai:
    a. Sbse pehle grid hai to do loop lagege hi traversal ke liye.
    b. Then inside loop check karo ki, agar kisi cell me 1 aata hai, to counter badha do.
    c. abhi us 1 ke adjacent and nested adjancent jitne 1 hai usko dfs recursion chala kar 0 kar do, taki next call me
        vo vapas count na ho.
    d. abhi baat aati hai dfs ki, isme simple recursion lagega, backtracking means undo ni krna hai, kyuki undo kiya
        to vapas se jin 1's ko humne as 1 island count kiya tha vo dobara count ho jaege.
    e. Baki funda vahi hai, ki char direction me traverse krna hai. Jo ki batauga ni tum khud jante ho.
4. Bss main function me res counter return kr do ho gaya.

*/

class Solution {
    public int numIslands(char[][] grid) {
        int res = 0;

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length;j++){
                if(grid[i][j] == '1'){
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    public void dfs(char[][] grid, int i, int j){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return;
        if(grid[i][j] == '0'){
            return;
        }

        grid[i][j] = '0';

        dfs(grid,i+1,j);
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);

    }

}