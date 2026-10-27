/*
Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.

 

Example 1:



Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation: 
Islands in gray are closed because they are completely surrounded by water (group of 1s).
Example 2:



Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1
Example 3:

Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2

Intution:

1. Bhai iski intution bhi simple hai.
2. Bas yaha par 0 mtlb land and 1 mtlb island.
3. Simple grid traverse karo then:
    a. humko pata karna hai ki konse aise 0's (land) hai jo 1's(water) se ghire hue hai.
    b. So jab grid traversal karege tab hi check kar lege ki agar cell == 0 hai tabhi dfs chalao, and dfs chalane ke bad
        charo direction me 1 mila tabhi result counter badhaege.
    c. Abhi base condition yhi rhegi ki i and j overflow na ho jae.
    d. dusri base condition jab humko dfs krte waqt 1 mil jae yani water.
    e. abhi char direction me chalana to aata hi hai recurison se.
    f. Ek chota observation hai ki bhai, hum seedha return dfs1&&dfs2&&dfs3&&dfs4 likh skte the alag alag kyu likha hai.
    g. Kyuki man lo agar pehle reursion false return karega to aage ke chalege hi ni kyuki and condition lagi hai.
        tabhi sare recursion alag alag chalae hai taki sbka result aa jae then sbko and karke return kr dege
    h. ab tum kahoge kya hi frk padta hai, mai kahuga padta hai bhai, ki man lo kuch cell visited mark hone the
        jo ki ni ho pae kyuki false condition ki vajah se baki recursion chale hi ni.
        Smjhe bhai. 
    chalo bss ho gaya ye question.
*/

class Solution {
    public int closedIsland(int[][] grid) {
        int res = 0;

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 0 && dfs(grid, i, j)){
                    res += 1;
                }
            }
        }

        return res;
    }

    public boolean dfs(int[][] grid, int i, int j){
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return false;

        if(grid[i][j] == 1){
            return true;
        } 

        grid[i][j] = 1;

        boolean f1 = dfs(grid, i+1, j);
        boolean f2 = dfs(grid, i-1, j);
        boolean f3 = dfs(grid, i, j+1);
        boolean f4 = dfs(grid, i, j-1);

        return f1 && f2 && f3 && f4;

    }
}