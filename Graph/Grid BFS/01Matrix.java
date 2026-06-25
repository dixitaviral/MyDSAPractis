/*
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two cells sharing a common edge is 1.

 

Example 1:


Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]
Example 2:


Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]
 
Intution:
1. Bhai isko do tarah se solve kiya hai mene.
    a. Meri approach:
        i. Bhai sare non-zero cell ke pair ko queue me dal do.
        ii. Then while queue not empty, sare pairs ke liye dfs chala do charo direction me.
        iii. charo direction ke steps nikal lo, ki kab zero mila, agar ni mila to return max_value/2. Taki baki min compare ho ske. Else zero return karoge
                to invalid steps bhi count kr lega vo.
        iv. Then charo direction ka min count krke return kar do.
        v. Also visited bhi mark kar do, as current path me ye node already visit ho chuka hai, so dobara ye node consider hogi to same path vapas banega
             and undo kar lena akhir me jo backtracking me skte hai.
        vi. Bas ho gaya solution. but ye TLE dega.
    b. AI approuch, solve by me:
        i. Bhai isme pure BFS use karege.
        ii. Trick ye hai ki:
            a. Sbse pehle jitne zero vale cells hai unko kar do queue me push, means uske indexes.
            b. Sath me jitne 1s vale index hai unko kisi or number se mark kar do, like -1. Aisa isliye kar rahe hai, kyuki easy rhega ki current cell ki value
                -1 hai yani visited ni hai, and agar -1 ni hai the visited hai.
            c. then while queue not empty, loop chalao and charo direction me 0 cell ke adjacent jo -1 hai, unki distance update kar do
            d. Abhi update kese krna hai, dekho mat[row][col] = 1+mat[i][j] karna hai, yaha i and j 0 cell ke index hai jo queue se nikale hai
                and row, col -1 vala index hai jo char direction me traverse krte time banae hai adjacent vale.
            e. to simple add prev col +1;
            f. sath hi sath count --;
            g. sath me current mat[row][col] update hua hai jo, unko queue me push kar do.
            h. Vo kyu, vo isliye:
                1. Man lo hum 1,1 par hai jisko abhi humne -1 se 1 me update kiya hai and 0,1 par ek 0 tha. and 2,1 par vapas -1 hai.
                2. Abhi humne 1,1 ko update krne ke bad queue me push kar diya theek?
                3. Abhi jab 1,1 ko hum char direction traverse karege to neeche traverse krte waqt humko -1 mil gaya.
                4. To hum uss cell ko aise update krege na mat[row][col] = 1+mat[i][j].
                5. Jaha row, col 2,1 index hai and i, j 1,1 index hai.
                6. Abhi bhi ni msjh aaya to smjhata hu:
                    a. Humne simply kaha ki bhai 1,1 ki 0 se already min distance humne nikal li hai, to 1,1 ke adjacent koi cell hai
                    b. jo ki already calculate ni hua hai abhi tak and vo hai 2,1. to simple 1,1 ki distance +1 kar do, to 2,1 ki min zero distance aa jaegi.
                    c. kyuki hum queue use kar rahe hai, jisme pehle vo cell ke adjacent cell update hoge, jo ki zero se just next me hai, then aise cell process hoge, jo 0 ke
                        just next ni hai.
                    d. like 2,1 jo humne discuss kiya.
*/




// my solution that has given TLE. BFS + DFS dono laga di isme
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        Queue<int[]> queue = new ArrayDeque();
    
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                if(mat[i][j] != 0)
                    queue.add(new int[]{i,j});
            }
        }


        while(!queue.isEmpty()){
            int[] pair = queue.poll();

            int minSteps = dfs(mat, pair[0], pair[1]);

            mat[pair[0]][pair[1]] = minSteps;
        }

        return mat;
    }

    public int dfs(int[][] mat, int i, int j){
        if(i < 0 || j < 0 || i >= mat.length || j >= mat[0].length) return Integer.MAX_VALUE/2;

        if(mat[i][j] == 0){
            return 0;
        }

        if(mat[i][j] == -1) return Integer.MAX_VALUE/2;

        int temp = mat[i][j];
        mat[i][j] = -1;

        int step1 = 1+dfs(mat, i+1, j);
        int step2 = 1+dfs(mat, i-1, j);
        int step3 = 1+dfs(mat, i, j+1);
        int step4 = 1+dfs(mat, i, j-1);

        mat[i][j] = temp;


        return Math.min(Math.min(step1, step2), Math.min(step3, step4));
    }
}


// Abhi bhi solution mera hai but approach AI ki hai
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        Queue<int[]> queue = new ArrayDeque();
        int count = 0;
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                if(mat[i][j] == 0)
                    queue.add(new int[]{i,j});
                else{
                    count++;
                    mat[i][j] = -1;
                }
                    
            }
        }

        if(queue.size() == mat.length*mat[0].length) return mat;

        int[][] in = new int[][] {{1,0},{-1,0},{0,1},{0,-1}}; 

        while(!queue.isEmpty() && count > 0){
           int[] pair = queue.poll();
           int i = pair[0]; 
           int j = pair[1];
           
           for(int id = 0; id < 4; id++){
                int row = i+in[id][0];
                int col = j+in[id][1];

                if(row < 0 || col < 0 || row >= mat.length || col >= mat[0].length) continue;

                if(mat[row][col] == -1){
                    mat[row][col] = 1+mat[i][j];
                    count--;
                    queue.add(new int[]{row, col});
                }
           }
        }

        return mat;
    }
}