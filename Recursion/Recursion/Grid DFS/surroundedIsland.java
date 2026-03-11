/*
You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:

Connect: A cell is connected to adjacent cells horizontally or vertically.
Region: To form a region connect every 'O' cell.
Surround: A region is surrounded if none of the 'O' cells in that region are on the edge of the board. Such regions are completely enclosed by 'X' cells.
To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.

 

Example 1:

Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]

Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]

Explanation:


In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.

Example 2:

Input: board = [["X"]]

Output: [["X"]]

Intution:
1. Bhai bit tricky hai ye but derived numOfIsland jese ques se hi hai.
2. Isko karne ke do tareeke hai ek mene nikala hai and ek chatgpt ne, ofcourse chatgpt ka solution zada optimal and
    and mera solution less optimal and also mere solution me TLE bhi aa jati hai.
3. But hum dono solution dekhege, so that ques clear ho jae.
4. But usse pehle iss ques ko krne ka ek rule hai jo ques me ni diya hai mai batata hu:
    a. Tum sochoge ques dekh kar ki easy hai ques. Bss ander vale jo 'O' hai unko x bana do and boundary wall vale jitne zero hai 
        unko chor do.
    b. But ques asli me keh ra hai ki bhai jo 'O' ke path, abhi path kese bhi ho skte hai up down left right. To jo 'O' ke path
        boundary tak pohoch rahe hai vo paths convert ni krne X me. 
    c. Abhi kuch path aise bhi hi skte hai jo boundary tak ni ja re ander hi khtm ho ja ra sirf unko covert krna hai 'X' me.
5. Chalo pehle mera solution dekhte hai: (ye dfs recursion explaination hai, jisko tumko grid traversal ke ander call krna hoga.)
    a. mera solution simple kehta hai, ki jab tak boundary na aa jae recursion chalao charo direction me.
    b. and usse pehle check krte jao, ki i and j over flow na ho.
    c. agar traversal ke beech kahi X and # mil jae, # next point me batata hu, iss case me true return karo. 
    d. # basically cells ko visited mark karne ka tareeka hai, agar ek cell already visited hai, to same cell se vapas
        path na traverse ho, usse bachne ke liye hum # par bhi true return kar rahe hai.
    e. Iske bad  check karo ki boundary to ni aa gayi, kyuki hum X and # par return kr rahe hai,
        iska mtlb boundary sirf 'O' par hi aaegi, and # and X par aati bhi hai to vo valid case ho jata hai.
    f. Abhi simple board[i][j] = # mark visited.
    g. then traverse all four sides separately, so that sare traversal ho else and condition krke likhoge
        to ek false aaya to baki ni chalege.
    h. Abhi jo visited mark kiye the unko vapas O bana do.
    i. then return all four direction result with && operator.
    j. then bhai grid loop me tumko true ya false return hoga acc. if O boundary tak ja ra hai ya ni.
    k. If true then, tumko abhi fill krna hoga kyuki agar true return hua to tmko pata hai ki in i and j indexes se jo charo direction me
        O path hoga vo boundary touch ni kr ra hai.
    l. to uske liye ek fill method likh do jo bs teen cheeze karega:
        i. Sbse pehle over flow check.
        ii. uske bad check if current cell is not having anything other that O, kyuki O ko hi X banana hai na.
        iii. mark the O as X and traverse in all four direction.
6. Abhi chatgpt optimal solution dekhte hai:
    a. Isme thora ulta chalna hai. Code lamba hai but step wise easy hai
    b. Teen steps hai:
        i. Sbsepehle boundary traverse karo agar koi O mile to uss O ka dfs recursion karo and usko visited # mar k kar do.
        ii. Then boundary se aane vale paths abhi # marked hai to ander se traverse karo, and ander jitne O mile unke path DFS traverse karke 
            X bana do.
        iii. then vapas pure grid par loop chalao and jaha visited mark kiya tha usko vapas O bana do. 

        khtm ho gaya.

*/

// my solution: but gives TLE
class Solution {
    public void solve(char[][] board) {
        for(int i = 1; i < board.length-1; i++){
            for(int j = 1; j < board[0].length-1; j++){
                if(board[i][j] == 'O'){
                    if(dfs(board, i, j)){
                        fill(board,i,j);
                    }
                } 
            }
        }
    }

    public boolean dfs(char[][] board, int i, int j){
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;

        if(board[i][j] == 'X' || board[i][j] == '#') return true;

        if(i == 0 || i == board.length-1 || j == 0 || j == board[0].length) return false;

        board[i][j] = '#';

        boolean f1 = dfs(board, i+1, j);

        boolean f2 = dfs(board, i-1, j);

        boolean f3 = dfs(board, i, j+1);

        boolean f4 = dfs(board, i, j-1);

        board[i][j] = 'O';

        return f1 && f2 && f3 && f4;
    }

    public void fill(char board[][], int i , int j){
        if(i < 1 || j < 1 || i == board.length-1 || j == board[0].length-1) return;

        if(board[i][j] != 'O') return;

        board[i][j] = 'X';

        fill(board, i+1, j);

        fill(board, i-1, j);

        fill(board, i, j+1);

        fill(board, i, j-1);

    }
}




// chatgpt optimal solution
class Solution {
    public void solve(char[][] board) {
        // traverse boundary and mark # to O's touching boundary
        //---------------------------------------------------
        for(int i = 0; i < board.length; i++){
             if(board[i][0] == 'O'){
                dfs(board, i, 0);
             }
             if(board[i][board[0].length-1] == 'O'){
                dfs(board, i, board[0].length-1);
             }
        }

        for(int j = 0; j < board[0].length; j++){
             if(board[0][j] == 'O'){
                dfs(board, 0, j);
             }
             if(board[board.length-1][j] == 'O'){
                dfs(board, board.length-1, j);
             }
        }
        //-----------------------------------------------------

        //traverse inner grid and mark all O's to X now
        //-----------------------------------------------------

        for(int i = 1; i < board.length-1; i++){
            for(int j = 1; j < board[0].length-1; j++){
                if(board[i][j] == 'O') dfs1(board, i , j);
            }
        }

        //-----------------------------------------------------

        //revert visited to O
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '#'){
                    board[i][j] = 'O';
                }
            }
        }

    }

    public void dfs(char[][] board, int i, int j){
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length) return;

        if(board[i][j] == '#' || board[i][j] == 'X') return;

        board[i][j] = '#';

        dfs(board, i+1, j);
        dfs(board, i-1, j);
        dfs(board, i, j+1);
        dfs(board, i, j-1);
    }
    

    public void dfs1(char[][] board, int i, int j){
        if(i < 1 || j < 1 || i >= board.length-1 || j >= board[0].length-1) return;

        if(board[i][j] == '#' || board[i][j] == 'X') return;

        board[i][j] = 'X';

        dfs1(board, i+1, j);
        dfs1(board, i-1, j);
        dfs1(board, i, j+1);
        dfs1(board, i, j-1);
    }
}


