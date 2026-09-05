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

DSU explaination:
1. Bhai DSU se krne ki trick hai.
2. Ques keh raha hai ki tum un un cells par ja skte ho from current cell jinki height ya
    given cell number is lesser than or equal to current.
3. Abhi ques ye bhi keh ra hai ki ye height increase bhi ho ri hai current ki y 1 every unit time
4. Now tumko ek aisa rasta batana hai jisme sabse kam time me tum last bottom right cell tak pohoch
    jao and height ya time ya number cell ka us path ka max but sare paths ka minimal ho.
5. To isko DSU se krne ka tareeka aao dekhe:
    a. Bhai logic simple hai. Humko pata hai ques ke according ki agar t=x time par hum us 4 direction
        adjacent cell ko visit kar skte hai jiski value current cell se less than ya equal to x ho.
        Itni baat smjh aai.
    b. To isi concept ko dsu se krte hai. Mtlb t=x par jin jin cells ko visit kar skte ho vo aa jaege
        same component me. Now ye krte krte hum ek check lagaege ki parent of cell 0,0 and n*n-1 last cell
        if equal then return that time, in which 0,0 and bottom right cell got into same connected component.
    c. Abhi isko implement kese karege aao vo dekhe:
        i. Sabse pehle to ek time 2d array bana lo, jo basically ye store karega ki konse cell cordinates hai
            on particular time. like time[3] = new int{2,3};
        ii. Abhi vapas parent and height array bana lo as DSU kar rahe hai to needed hai.
        iii. Then same parent and height array ko initialize kar lo. Remember humko DSU me parent and height
            array matrix ki size ka banana hota hai, but since matrix 2d hai to hum row and col wise parent 
            and height array banate hai. To size hoga parent and height array ka matrix.length*matrix.length-1
        iv. Abhi mst humko apna logic lagana hai jo upar dekha tha time vala, ki humko pata hai grid me
            min time 0 hoga and max time hoga n*n-1. 
        v. to bas from 0 to n*n-1 tak loop chala do. 
        vi. Abhi humko DSU me u and v chahiye hota hai, usko kese banaege aao dekhe.
        vii. Bhai dekho man lo start time hai 0 and time array jo upar banaya tha usme se 0th index par jo
            cordinates stored hai vo nikalo. Abhi u ke liye tumko ek unique value chahyie jo tum 
            map kar sako parent and height array me.
        viii. Uske liye tum karoge u = i*matrix.length(col length lete hai but is ques me row col equal hai to koi issue ni hai)+j.
        ix. Abhi u hamare pas hai v banana hai, now v vo neighbour hoga jo 4 directionally adjacent hai and uss v ka time/height is <= curr hai.
        x. abhi 4 directionally adjacent cells i and j jo upar time cordinates nikale the vo kese banana hai ni batauga, agar ni smjh aae to to
            solution dekh lena.
        xi. Then jab tmhre pas me adjacent cells man lo row and col then pehle to check lagaoge ki overflow to ni hai
        xii. Then ye check karoge ki time/height of neighbour cell is not greater than current.
        xiii. Abhi v banaoge to banega v = row*matrix.length+col; 
        xiv. Abhi tmhre pas u and v hai kar do DSU inka. 
        xv. After DSU check if parent of 0 is equal to parent of n*n-1, if yes return time.
        xvi. Bas itna hi ques hai and uska ans hai.
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


// DSU solution

class Solution {
    public int swimInWater(int[][] grid) {  
        int len = grid.length;

        int time[][] = new int[len*len][2];

        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                time[grid[i][j]] = new int[]{i,j};
            }
        }

        int parent[] = new int[len*len];
        int height[] = new int[len*len];

        for(int i = 0; i < len*len; i++){
            parent[i] = i;
            height[i] = 1;
        }

        int dir[][] = new int[][]{{0,1},{1,0},{-1,0},{0,-1}}; 

        for(int t = 0; t < len*len; t++){
            int i = time[t][0];
            int j = time[t][1];
            
            int u = i*len+j;
            for(int arr[] : dir){
                int row = arr[0]+i;
                int col = arr[1]+j;

                if(row < 0 || col < 0 || row >= len || col >= len) continue;

                int v = row*len+col;

                if(grid[row][col] > t) continue;

                int pu = find(u, parent);
                int pv = find(v, parent);

                if(pu == pv) continue;

                if(height[pv] < height[pu]){
                    parent[pv] = pu;
                }else if(height[pu] < pu){
                    parent[pu] = pv;
                }else{
                    parent[pv] = pu;
                    height[pu]++;
                }
                // let's say len = 5 so array will be 0 to 4 similarly 
                // len*len = 25 but indexes will be from 0 to 24. hence len*len-1
                // not len-1*len-1 as it will give 5-1*5-1 = 16 which is wrong
                if(find(0, parent) == find(len*len-1, parent)) return t;
            } 
        }

        return -1;
    }

    public int find(int x, int parent[]){
        if(x != parent[x]){
            parent[x] = find(parent[x], parent);
        }

        return parent[x];
    }
}