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

// DFS Solution
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


// BFS Solution
class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        Queue<int[]> queue = new ArrayDeque();

        int dir[][] = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    count++;
                    grid[i][j] = '0';
                    queue.add(new int[]{i,j});

                    while(!queue.isEmpty()){
                        int pair[] = queue.poll();

                        int row = pair[0];
                        int col = pair[1];

                        for(int arr[] : dir){
                            int a = row+arr[0];
                            int b = col+arr[1];

                            if(a < 0 || b < 0 || a >= grid.length || b >= grid[0].length) continue;

                            if(grid[a][b] == '1'){
                                grid[a][b] = '0';
                                queue.add(new int[]{a,b});
                            }
                        }

                    }
                }
            }
        }

        return count;
    }
}

// DSU Solution
class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        int dir[][] = new int[][] {{1,0}, {0,1}, {0,-1},{-1,0}};
        int totalLen = grid.length*grid[0].length;

        int row = grid.length;
        int col = grid[0].length;

        int parent[] = new int[totalLen];
        int height[] = new int[totalLen];

        for(int i = 0; i < totalLen; i++){
            parent[i] = i;
            height[i] = 1;
        }

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == '1') count++;
            }
        }

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    grid[i][j] = '0';
                    for(int a = 0; a < dir.length; a++){
                        int ni = i+dir[a][0];
                        int nj = j+dir[a][1];

                        if(ni < 0 || nj < 0 || ni >= row || nj >= col) continue;

                        if(grid[ni][nj] == '0') continue;
                        

                        // i*col+j is the formula used to covert a whole matrix
                        // cell into a single number through which we can store
                        // or fill the parent array.

                        // where as if you want to union the cordinates then you have
                        // to manage the over flow, means row will be treated from 0 to rowLen
                        // in parent array and col will be treated from rowLen+1 to 
                        // parent.length-1;
                        int curr = i*col+j; // u
                        int neighbour = ni*col+nj; // v

                        if(union(parent, height, curr, neighbour)){
                            count--;
                        }
                    }
                }
            }
        }

        return count;
    }

    public int find(int x, int parent[]){
        if(x != parent[x]){
            parent[x] = find(parent[x], parent);
        }

        return parent[x];
    }

    boolean union(int parent[], int height[], int curr, int neighbour){
        int pi = find(curr, parent);
        int pj = find(neighbour, parent);

        if(pi == pj) return false; // as if same, means part of same island. No count decrease

        if(height[pi] < height[pj]){
            parent[pi] = pj;
        }else if(height[pj] < height[pi]){
            parent[pj] = pi;
        }else{
            parent[pj] = pi;
            height[pi]++;
        }

        return true; // means marked in a component, so count should decrease.
    }
}