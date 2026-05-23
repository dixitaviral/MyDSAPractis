/*
You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).

It starts raining, and water gradually rises over time. At time t, the water level is t, meaning any cell with elevation less than equal to t is submerged or reachable.

You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.

Return the minimum time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).


Example 1:

Input: grid = [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
Example 2:


Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation: The final route is shown.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.

Intution:
1. Bhai ye ques bhi same hai path with min effort se.
2. Isme keh ra hai ki:
    a. grid ke cell (0,0) se bottom right (n-1,n-1) tak jitne paths hai unki max height nikalo.
    b. Then sare path ki max heights me se min height return kar do. 
3. Ques intution zada explain ni karuga, as it's already written in pathWithMinEffort.java
    a. So simple ek priority queue lenge jisme ek array jaega containing state of a cell.
    b. Uss array me cell indices and us cell par abhi tak ka min height from all paths jaega.
    c. Ek max matrix maintain krni hai, jo har cell ki abhi tak ki sare paths me min height jo mili
        hai vo store karege.
    d. Abhi queue se poll krke paths explore kar lo, sath hi sath uss path ka max height nikal lo
    e. Then matrix me check kar lo agar current cell ki min height already hai to queue me add mat
        karo.
    f. Else kar do add queue and matrix bhi update kr do.
    g. Matrix hi kaam karegi as visited array also.
    h. Also PQ ko sort krna hoga height se.

*/

// directly optimized, intution same as pathWithMinEffort.java
class Solution {
    public static final int arr[][] = new int[][]{{0,1}, {1,0}, {-1,0}, {0,-1}};
    public int swimInWater(int[][] grid) {  

        PriorityQueue<int[]> queue = new PriorityQueue<>(
            (int a[], int b[]) -> a[2] - b[2]
        );

        int [][] matrix = new int[grid.length][grid[0].length];

        for(int mat[] : matrix){
            Arrays.fill(mat, Integer.MAX_VALUE);
        }

        matrix[0][0] = grid[0][0];

        queue.add(new int[]{0,0,grid[0][0]});

        while(!queue.isEmpty()){
            int temp[] = queue.poll();

            int i = temp[0];
            int j = temp[1];
            int w = temp[2];

            if(i == grid.length-1 && j == grid[0].length-1){
                return w;
            }

            for(int dir[] : arr){
                int row = i + dir[0];
                int col = j + dir[1];

                if(row < 0 || col < 0 || row >= grid.length || col >= grid[0].length){
                    continue;
                }                

                int max = Math.max(w, grid[row][col]);

                if(matrix[row][col] <= max) continue;

                matrix[row][col] = max;

                queue.add(new int[]{row, col, max});

            }
        }

        return 0;
    }
}