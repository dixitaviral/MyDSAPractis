/*
You are given a rows x cols matrix grid representing a field of cherries where grid[i][j] represents the number of cherries that you can collect from the (i, j) cell.

You have two robots that can collect cherries for you:

Robot #1 is located at the top-left corner (0, 0), and
Robot #2 is located at the top-right corner (0, cols - 1).
Return the maximum number of cherries collection using both robots by following the rules below:

From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
When both robots stay in the same cell, only one takes the cherries.
Both robots cannot move outside of the grid at any moment.
Both robots should reach the bottom row in grid.
 

Example 1:


Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
Output: 24
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
Total of cherries: 12 + 12 = 24.
Example 2:


Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
Output: 28
Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
Total of cherries: 17 + 11 = 28.

Intution:

1. Bhai ques almost tumne laga diya tha ye vala, bas ek cheez tmko smjh ni aai thi, is vajah
    se chatgpt se puchna pad gaya, but koi ni smjh aa gaya hai abhi.
2. Sabse pehle question dekhte hai kya keh raha hai:
    a. Keh ra hai do robots hai ek 0,0 par hai and ek 0,col-1 par hai.
    b. Ye dhyan se dekhna, ki ques keh ra hai dono ek sath chalne chahiye, alag alag ni. Ab tum
        kahoge ek sath kaise chala du, batauga aage.
    c. Keh ra hai har robot sirf in direction me move kar skta hai, i+1, j+1 and i+1, j and
        i+1, j-1. Isme i hamesha +1 badh ra hai and j alag alag so ye ek impo point hai aage
        dekhte hai.
    d. Abhi keh ra hai dono robots jo hai, vo agar same cell par aa gye to ek hi robot cherry utha
        sakta hai.
    e. Fir keh ra hai maximum possible cherries return karo jo dono robot milkar utha skte hai.
3. Ab aate hai intution par:
    a. Dekho bhai sabse pehle isko solve krte hai ki bhai ek sath robot kese chalaege:
        i. Dekho bhai mene upar ek point bataya tha ki i+1 hamesha ho ra bas j alag alag
            change ho ra hai.
        ii. Means dono robot upar di hui direction me chalege, jisme hamesha i+1 ho ra hai.
        iii. Bas j differently change ho ra hai to agar hum same i ko dono robots ke liye use kar
            le and j ke liye diff j1 and j2 maintain kare to baat ban skti hai.
        iv. Also j ko agar tum dekho to usme ya to -1 ya to 0 and ya to 1 add ho ra hai.
        v. Plus do j1 and j2 hoga jinke beeche combinations banege na, ki kabhi j1+1 hua to kabhi
            j2-1 hua, sare paths traverse krne ke liye alag alag combinations test karne hoge.
        vi. To combinations ke liye mast do for loop laga do from d1 -> -1 to 1 and d2 -> -1 to 1.
        vii. To tmhra recursion call kuch aisa dikhega helper(i+1, j1+d1, j2+d2).
        viii. Baki ni smjh aaya to neeche code check kar lo fir.
    b. Ab asli yahi game tha ki sath kese chalae, isi me hum last me max cherries return karege, to
        upar vali recursion call me hum max maintain krke chalege.
    c. Plus cherries kese add karni hai:
        a. Dekho bhai sabse pehle to ek variable le lena cherries and usme grid[i][j1] add karna
            abhi ques me according j1 le skta hai cell ki cherry ya j2, koi ek lega and since hum
            sum return kar re hai to ultimately hum aise likh sakte hai cherry = grid[i][j1]; 
            if(j1 != j2) cherry += grid[i][j2]; Ab agar equal case hoga to sirf ek bar add hoga.
        b. Abhi last me return karte time, max+cherries return karna hai tumko.
    d. Base condition me j1 and j2 ka overflow check krna hai and i ka bhi and return 0 krna hai.
    e. Agar cherry ki value negative hoti tabhi false condition me int min return krte may be.
    f. False condition ques ke acc j1 and j2 ka overflow hona hai, whereas i ko humko last row tak
        pohochana hai vo true condition hai.
    g. Baki visited array me dp lagegi vo per cell max cherry rkhega vo tum khud set kr skte ho. Bas
        state isme i , j1, j2 banegi to vo dhyan rakhna
*/

class Solution {
    public int cherryPickup(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int visited[][][] = new int[row][col][col];

        for(int arr[][] : visited){
            for(int a[] : arr){
                Arrays.fill(a, -1);
            }
        }

        return helper(grid, 0, 0, col-1, visited);
    }

    public int helper(int grid[][], int i, int j1, int j2, int visited[][][]){
        if(j1 < 0 || j2 < 0 || j1 >= grid[0].length || j2 >= grid[0].length){
            return 0;
        }

        if(i == grid.length) return 0;

        if(visited[i][j1][j2] != -1) return visited[i][j1][j2];

        int cherries = grid[i][j1];

        if(j1 != j2){
            cherries += grid[i][j2];
        }

        int max = 0;

        for(int d1 = -1; d1 <= 1; d1++){
            for(int d2 = -1; d2 <= 1; d2++){
                max = Math.max(max, helper(grid, i+1, j1+d1, j2+d2, visited));
            }
        }

        return visited[i][j1][j2] = cherries + max;
        
    }
}