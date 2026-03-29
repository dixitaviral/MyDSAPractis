/*

There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.

 

Example 1:


Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
[0,4]: [0,4] -> Pacific Ocean 
       [0,4] -> Atlantic Ocean
[1,3]: [1,3] -> [0,3] -> Pacific Ocean 
       [1,3] -> [1,4] -> Atlantic Ocean
[1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean 
       [1,4] -> Atlantic Ocean
[2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean 
       [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
[3,0]: [3,0] -> Pacific Ocean 
       [3,0] -> [4,0] -> Atlantic Ocean
[3,1]: [3,1] -> [3,0] -> Pacific Ocean 
       [3,1] -> [4,1] -> Atlantic Ocean
[4,0]: [4,0] -> Pacific Ocean 
       [4,0] -> Atlantic Ocean
Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
Example 2:

Input: heights = [[1]]
Output: [[0,0]]
Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.

Intution:
1. Bhai solution dekhne me bada lag skta hai, but question easy hai.
2. Chalo ques smjhte hai:
    a. Ques keh ra hai ki left and up side me pacific ocean hai and down and right me atlantic.
    b. Abhi heights matrix me se vo cell dhund kar batao, jispar agar pani gira means rainfall hui,
        to dono ocean me pani jakr gire.
    c. Abhi consider krne vale baat ye hai, aisa cell jisse dono ocean me pani girega, vo cell 
        ki height zada hogi, and uske left ya right ya up ya down, koi bhi do ya charo height
        kam hogi, tabhi to high to low height ki taraf pani jaega.
    d. To bas humko aise cells ki list return karni hai.
3. Abhi ques smjh aaya to intution bhi dekh lete hai easy hai:
    a. Dekho bhai, humko do ocean sides di hai or pucha gaya hai ki aise cell batao, jisse pani dono ocean 
        me gire jakr.
    b. To intution ye hai, ki sbse pehle queue me pacific boundary vale cell lelo and us pr BFS chalao
        and charo direction me move karo and dekho ki current cell ki height next cell se choti hai
        kya?. 
    c. Ab tum kahoge, current cell height < next cell height kyu check kar rahe ye to ulta hai.
    d. to bhai yhi reverse thinking lgegi isme, see boundary se increasing height vala jo
        path hoga, vahi to bataega ki iss path se sbse uchi height vale cell par pani gira
        to isi path se pacific me girega.
    e. Sath hi sath, ek visited array bhi bana lo, jo ki cells ko visited mark karega, taki
        same path dobara traverse na ho.
    f. Same krna hai atlantic ke sath bhi, jo pacific ke sath kiya.
    g. Abhi dono par bfs chala kar tumhre pas do visited array hoge.
    h. Abhi socho, jin cells se dono ocean me pani gir ra hoga, vo dono visited array me,  
        visited marked hogi.
    i. to bs vhi cells lekr result me add kar dena hai, means dono visited cells ka 
        intersection hi ans hai.
    j. Bas ho gaya kaam.
 */

    class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int arr[][] = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

        int rowLen = heights.length;
        int colLen = heights[0].length;

        List<List<Integer>> list = new ArrayList();

        int pacific[][] = new int[rowLen][colLen];

        Queue<int[]> queue = new ArrayDeque();

        // Step 1: BFS on pacific boundary
        for(int i = 0; i < rowLen; i++){
            queue.add(new int[]{i,0});
            pacific[i][0] = 1;
        }

        for(int i = 1; i < colLen; i++){
            queue.add(new int[]{0,i});
            pacific[0][i] = 1;
        }

        bfs(queue, pacific, heights, arr);
        //-------------------------------------------

        // bfs on atlantic boundary
        int atlantic[][] = new int[rowLen][colLen];

        for(int i = 0; i < colLen; i++){
            queue.add(new int[]{rowLen-1, i});
            atlantic[rowLen-1][i] = 1;
        }

        for(int i = 0; i < rowLen; i++){
            queue.add(new int[]{i, colLen-1});
            atlantic[i][colLen-1] = 1;
        }

        bfs(queue, atlantic, heights, arr);
        //---------------------------------------------


        // finding intersection of atlantic and pacific
        for(int i = 0; i < rowLen; i++){
            for(int j = 0; j < colLen; j++){
                if((pacific[i][j] == 1) && (atlantic[i][j] == 1)){
                    list.add(List.of(i,j));
                }
            }
        }

        return list;
    }

    public void bfs(Queue<int[]> queue, int[][] visited, int[][] heights, int[][] arr){
        int rowLen = heights.length;
        int colLen = heights[0].length;

        while(!queue.isEmpty()){
            int pair[] = queue.poll();

            int i = pair[0];
            int j = pair[1];

            for(int idx = 0; idx < 4; idx++){
                int row = i+arr[idx][0];
                int col = j+arr[idx][1];

                if(row < 0 || col < 0 || row >= rowLen || col >= colLen) continue;

                // if not visited and current cell height is smaller than next cell height.
                if(visited[row][col] != 1 && heights[row][col] >= heights[i][j]){
                    queue.add(new int[]{row, col});
                    visited[row][col] = 1;
                }
            }
        }
    }
}