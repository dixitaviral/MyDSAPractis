/*
You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.

Return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The testcases are generated so that the answer will be less than or equal to 2 * 109.

 

Example 1:


Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
Example 2:


Input: obstacleGrid = [[0,1],[0,0]]
Output: 1

Intution:

1. Bhai easy question hai ye bhi isme bas obastacle di hai ki jis raste me obstacle aa jae to way
    choose ni karna hai.
2. Abhi scene ye hai ki isko krne se pehle mene striver ka video dekha tha jisme bhai ne tabulation
    dp se karke dikhaya tha. (Tabulation dp ya iterative dp usko bolte hai jisme recursion ki jagah
    hum for loop ya loops ka use krke dp solution likhte hai).
3. To bhai neeche jo mene solution tabulation vala likha hai usko chaho to ignore kar skte ho, kyuki
    mene lecture dekha to thora click hua tha else mujhe si se ni aata hai.
4. Baki tmhri mrzi agar zarurt lage and seekh liya hai to smjh lena.
5. Abhi aate hai intution par:
    a. Sabse pehle dekhte hai recursion + memo solution:
        i. Isme seedha seedha pichle ques vala sab use hua hai bas ek condition or add ho
            gyi hai.
        ii. Dusra isme humne alag se memo ke liye 2d array ni banaya hai, kyuki obstacle array jo given
            hai usi ko use kiya hai
        iii. Sabse pehle obstacle array ko initialize kiya hai -2 se jaha par obstacle hai, and -1 se
            jaha clear hai koi obstacle ni hai.
        iv. Then recursion call kiya hai jisme obstacle array, i and j pass kiya hai.
        v. Abhi base conditions simple hai agar i and j overflow ho gye ya kisi index par obstacle
            aa gaya to hum 0 return kar dege.
        vi. Ek condition hai ki agar last cell par pohoch gye to return 1.
        vii. Uske bad obstacle array me -1 ni hai agar means alreayd calculate ho chuka hai to already
            stored value return kar do.
        viii. Abhi down and right ka recursion call karo.
        ix. Then return krte time down + right ko osbtacle array me i and j par store kar do and
            return kar do bas ho gaya.
    b. Abhi dekhte hai tabulation vala solution:
        i. Bhai ye bhi simple hai but is ques ka kiya hai mene pehli bar to I dont think zada acche
            se smjha pauga but isi dp folder me ek tabulation vs recurisve dp file hai usko dekh lena
        ii. Abhi isme krna kuch ni hai simple humne jo obstacle array initilize kiya with obstacle
            as -2 else -1.
        iii. Usi ko reuse krege, hoga ye ki do nested loop lagao, and 0,0 par 1 store kar do.
        iv. Then agar obstacle aa jae means i and j par -2 aae to skip kar do
        v. Abhi dekho bhai jo tabulation dp hoti hai vo ans store krte hue chalti hai, to agar 
            tum common sense se socho ki jo 0,0 hai agar tum 0 row lelo ya 0 column lelo, in dono
            me se kisi bhi cell par 0 se jaoge to 1 hi path hai koi or path ni hai.
        vi. Basically ye tabulation sabse pehle yahi krta hai ki first row me sabki distance nikal li
            from 0,0 abhi second row ki jab distance nikalni hai to aise nikalega na ki 
        vii. Agar hum cell 0,0 par hai to hum ya to 0,1 par ya 1,0 par ja skte hai as per ques right
            or down.
        viii. Abhi humne first me dekha ki sare cells tak 0 se pohochne ka 1 way hoga jo har cell me
            stored hai.
        ix. Then second row ki bari aai to hua 1,0 isme bhi 1 store hoga, kyu hoga ye socho ki
            0,0 se 1,0 par or konsa rasta hai right ya down move krne par sirf.
        x. Then abhi jo cell hoga 1,1 uski vali kese aaegi socho, hum 0,0 -> 0,1(right)->1,1(down) and
            0,0 -> 1,0(down) -> 1,1(right) means 0,0 se 1,1 tak aane ke liye 1,0 and 0,1 vali num of
            ways ko add kar do to humko 1,1 tak ways mil jaege from 0,0.
        xi. bas yahi logic lagana hai ki agar i > 0 and j > 0 to jo up vala cell hai usko value means
            i-1, j and left vali value means i,j-1 and then up +left ka addition then store it into
            obstacleGrid i and j.
        xii. Jab ye loop khatam hoga tab last cell tak pohochne ke liye last cell access kar lo
            usi me value padi hai.
Bas khatam hai

*/

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;

        if(obstacleGrid[0][0] == 1 || obstacleGrid[row-1][col-1] == 1) return 0;

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(obstacleGrid[i][j] == 1){
                    obstacleGrid[i][j] = -2;
                }else{
                    obstacleGrid[i][j] = -1;
                }
            }
        }

        return helper(obstacleGrid, 0,0);

        //Tabulation solution below.

        // obstacleGrid[0][0] = 1;

        // for(int i = 0; i < row; i++){
        //     for(int j = 0; j < col; j++){
        //         if(obstacleGrid[i][j] == -2) continue;

        //         if(i == 0 && j == 0) continue;

        //         int up = 0;
        //         int down = 0;

        //         if(i > 0 && obstacleGrid[i-1][j] != -2) 
        //             up = obstacleGrid[i-1][j];

        //         if(j > 0 && obstacleGrid[i][j-1] != -2) 
        //             down = obstacleGrid[i][j-1];

        //         obstacleGrid[i][j] = up+down;
        //     }
        // }

        // return obstacleGrid[row-1][col-1];
    }

    public int helper(int[][] mat, int i, int j){
        if(i >= mat.length || j >= mat[0].length || mat[i][j] == -2) return 0;
        if(i == mat.length-1 && j == mat[0].length-1) return 1;

        if(mat[i][j] != -1) return mat[i][j];

        int down = 0;
        int right = 0;

        down = helper(mat, i+1, j);
        right = helper(mat, i, j+1);

        return mat[i][j] = down+right;
    }
}