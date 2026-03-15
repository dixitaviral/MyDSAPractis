/*
Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

 

Example 1:


Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
Example 2:


Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.


Intution:
1. Bhai ye bhi ques simple hai. All direction prpagation ke liye current folder me ek grid propagation and bfs common rule ki text file hai usko refer kar lo.
2. Isme bhi BFS lagegi, and jaha BFS hai vaha queue zrur aaegi.
3. Always remember bfs queue ques ulta solve hote hai, ki bola hai agar water se land ki distance find karo, 
    to tum ulta karo, ki land se water ki distance find karo.
4. same isme hoga, ki sare land ke indexes ko queue me push kar do, and while queue not empty loop chalao.
5. then char direction me dekho koi adjacent water body ho usko update karo 1+land body.
6. Then since jo water body ko update kiya hai vo 0 se 1 ya 2 ho gyi hai, usko queue me push kar do, kyuki vo abhi 0 nahi rahi. Means aisa man lo jo non zero 
    value hai vo queue me jaegi kyuki vo ek land consider ho ri hai.
7. Then jo current water body update kro hai usko max me store kar lo max compare karke.
8. Bas jab tak queue empty na ho jae tab tak karo ye and then max - 1 return kar do.
9. Abhi max - 1 kyu return kiya, kyuki hum 1+land body kar rahe the agar yaad ho, but humko isme edges count krni hai, like array hai
    [1,0,0] -> to ye aise update hoga ki [1,2,0] -> [1,1,3]. Aisa isliye kyuki hum sbse pehle jab land se water ki
    distance calculate kar rahe the tab hume 1+land kiya tha and land already 1 hai, 
    to hamesha ek 1 extra aaega ans me, jisko humko -1 krke balance krna hai.
10. Then isme edge condition bhi hai, ki agar saare land ho ya saara water ho to -1 return krna hai jo ki tum check laga skte ho.
11. Land check aise hoga ki queue me hum land daal rahe hai agar queue empty means no land and queue me land insert krte time hi else block me
    water count kar lo, agar water count 0 hai o bhi return -1;

*/

class Solution {
    public int maxDistance(int[][] grid) {
        int rowLen = grid.length;
        int colLen = grid[0].length;
        int max = -1;
        int count = 0;

        int arr[][] = new int[][] {{1,0}, {0,1}, {-1,0}, {0,-1}};
        Queue<int[]> queue = new ArrayDeque();

        for(int i = 0; i < rowLen; i++){
            for(int j = 0; j < colLen; j++){
                if(grid[i][j] == 1){
                    queue.add(new int[]{i,j});
                }else{
                    count++;
                }
            }
        }

        if(count == 0 || queue.isEmpty()) return -1;

        while(!queue.isEmpty()){
            int[] pair = queue.poll();

            int i = pair[0];
            int j = pair[1];

            for(int idx = 0; idx < 4; idx++){
                int row = i+arr[idx][0];
                int col = j+arr[idx][1];

                if(row < 0 || col < 0 || row >= rowLen || col >= colLen) continue;

                if(grid[row][col] == 0){
                    grid[row][col] = 1+grid[i][j];
                    max = Math.max(max, grid[row][col]);
                    queue.add(new int[]{row,col});
                }
            }

        }

        return max-1;
    }
}