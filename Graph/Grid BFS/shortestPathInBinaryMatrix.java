/*
Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

 

Example 1:


Input: grid = [[0,1],[1,0]]
Output: 2
Example 2:


Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4
Example 3:

Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1

Intution:
1. Bhai sbse pehle to solution dekh kar ghabrana ni, kyuki ye tumne khud se kiya hai. To agar ek bar kiya to bohot bar bhi kar skte ho.
2. Plus solution bada dikh zrur raha hai, lekin hai ekdm seedha and systematic.
3. Chalo abhi tum ye to jante ho ki BFS me queue lagti hai. To queue hum lagaege
4. Abhi intution smjh lete hai
    a. Scene ye hai, ki cell 0,0 hamesha 0 rahega and cell right bottom last cell 0 rahega, agar ni hai to -1 return kar do.
    b. To abhi shortest path find kese krna hai ye dekhte hai
          0 1 2
       0 [0,0,0]
       1 [1,1,0]
       2 [1,1,0]

       i. Abhi upar matrix dekho, idea ye hai ki humko left right up down ke sath sath diagonally bhi traverse krna hai.
       ii. Idea hai ki dekho humko 8 dorections me traverse krna hai usko liye tum ek array bana lo sbse pehle jo tumko 8 directions me
            traverse krne me help karega.
            a. Array kyu or kisliye, aao smjhata hu.
            b. Array rahega 2d, jisme ek value hogi {{1,0}} abhi socho agar i = 0 and j = 0 hai, and tumko next row 0th element fetch krna hai
                to tum simple iss 2d array se 1 value uthao. and i+1(2d array first row 0th index) , j+0(2d array first row 1th index), ans hua i = 1 and j = 0;
                pohoch gaye tum 1,0 par. And tumne bottom traverse kar liya.
            c. Similarly, tum 8 directions me atmost ek cell se traverse kar skte hai 4 hogi straight up down left right and the same directions diagonally.
            d. Chalo ye idea ho gaya ki traverse kese karega four direction me.
       iii. Abhi seekhte hai, ki Queue kyu li humne, vo isliye kyuki humko ek contiguous path chahiye, jo ek ke bad ek aata ho.
       iv. Abhi tum keh skte ho array le lete fir, humko hamesha element first vala chahiye, kyu, kyuki vo batata hai 
            ki current cell se next possible move kidhr kar skte hai. Ye hum array se kar skte the, but usko bhi as queue use krna pdta.
        v. Abhi simple hai, queue me 0,0 index push karo. then loop chala do while queue not empty ka.
        vi. queue poll karo, and jo cell ke index poll hue hai , uss cell ki value ko +1 kar do.
        vii. ABhi ques hoga, kyu +1 karna hai, dekho humko path find karna hai, usse pehle ek concept smjh lete hai.
            a. Matrix me traverse krte time, do cheeze hoti hai, ek hoti hai edge and ek hoti hai nodes/vertices. 
            b. Abhi edge jab count krte hai tab current ni next node update krte hai, ki current node se next node jane ke liye
                kitni units lag ri hai.
            c. Isme humko path find karna hai, jiska mtlb nikalta hai, ki humko har node par visit krna hoga.
            d. To chahe 0,0 ho ya koi or path ki valid node ho, vaha tak pohochne ke liye humne kitni nodes traverse kri hai. Ye store krte hai aage
                badhte badhte.
        viii. Theek to current node ko +1 kar diya jo ki start me 0,0 hai and aage next node aati rahegi.
        ix. Abhi karna hai ki humko current node se 8 direction me dekhna hai ki kidhar valid path ho skte hai.
        x. To humne upar traversal ki baat to kari hi hai, uss tarah se hum current indices me uss increment array ke indices ko add krte rhege
            and current cell se next possible node ko check karege.
        xi. agar valid hai, means 0 hai, to next node ko current ki value se initialize kr dege. Abhi initialize kyu kiya
            kyuki humko pichli node ye batati hai, ki uss pichli node tak aane me itni units lagi hai.
        xii. Plus humko current node jisme humne pichli node ki value assign ki hai, usko bhi count krna hai, and also current node ek valid node hai,
            to usko hum queue me add kar dege taki aage uss node ke further valid nodes check ho pae.
        xiii. Abhi since humne current node jisme prev ki value assign ki hai usko queue me add kiya hai to next ietration me jakr current node
            bhi count hogi kyuki hum current node +1 kar rahe hai and then uske bad hi uski next possible valid nodes ko
            traverse kr rahe hai.
        xiv. aise krte karte, hum bottom right node par pahuch jaege and bottom right node me total path set ho jaega.
        xv. Jisko hum return kar dege as answer. 
        xvi. Agar bottom right node ki valule still 0 hai, to iska mtlb ki vo node unreachable hai and hum -1 return kar dege.

        ho gaya solve.
       
*/

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int rowLen = grid.length;
        int colLen = grid[0].length;

        if(grid[0][0] != 0 || grid[rowLen-1][colLen-1] != 0) return -1;

        int inc[][] = new int[][]{{1,0},{0,1},{-1,0},{0,-1},{1,1},{-1,1},{1,-1},{-1,-1}};

        Queue<int[]> queue = new ArrayDeque();

        queue.add(new int[]{0,0});

        while(!queue.isEmpty()){
            int pair[] = queue.poll();
            int i = pair[0];
            int j = pair[1];
            
            grid[i][j] = 1+grid[i][j];

            for(int idx = 0; idx < 8; idx++){
                int row = i+inc[idx][0];
                int col = j+inc[idx][1];

                if(row < 0 || col < 0 || row >= rowLen || col >= colLen) continue;

                if(grid[row][col] == 0){
                    grid[row][col] = grid[i][j]; // Math.min not needed, kyuki tum already check kar rahe ho, ki ye cell already visited na ho by
                                                    // this condition if(grid[row][col] == Integer.MAX_VALUE/2)

                    queue.add(new int[]{row,col});
                }
            } 
        }

        return grid[rowLen-1][colLen-1] == 0 ? -1 : grid[rowLen-1][colLen-1];


    } 
}