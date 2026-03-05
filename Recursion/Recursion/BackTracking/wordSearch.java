/*
    Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true

Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true

Intution:

1. Bhai hai to ye bhi backtracking ka question, jisme vahi rule lagege:
    a. Ans banao
    b. recursion
    c. Undo
2. Ab tweak ye hai is ques me:
    a. Hamare pas 2D array hai, and possible movement for searching the word in the 2D array is:
        up, down, left, right.
    b. to fir choices bhi char hogi, kyuki hume char direction me jakr check krna hoga.
    c. Ek or baat, 3 cheeze hai ques me:
        i. Ki sbse pehle ek cell pakadna hoga
        ii. Then check krna hai ki word ka jo char hai vo match ho ra ya ni. Agar match hua to
        iii. Vapas char choices hai up jao, down jao, left jao and right jao.
    d. Abhi ek cell pakdoge kese, do nested loop lagate ho jese for traversing a 2D array, vahi krna hai
    e. And ek ek element pass krna hai recursion method me.
    f. and ek variable lelo idx ya wordLength jo increment karoge when a char match.
    g. Or ek check laga do if not metch then vo direction me search krna banta hi ni hai to false 
        return kr do.
    h. Also index check laga lo, since ek ek cell ke liye char direction traverse karoge, to
        i and j bhejoge recursion call me.
    i. Abhi char condition hogi na i+1(row down), i-1(row up), j+1(col right), j-1(col left). To iske
        liye indexes 0 se chote na ho and total length se jada na ho, vo check lagega, agar ho to
        return false.
    j. abhi last me undo kar do. 
    k. abhi undo karoge kya, bhai aisa ho skta hai ki ek element ko ek call me dobara traverse kar lo
        to hum ya to visited use krege, ya to uss index ko visited mark kar dege, main array me
    l. man lo '#' mark kar diya, abhi kahoge iska check lagana padega, mai kahuga ni lagana hai
        kyuki check lagaoge bhi to false return karoge, and agar word char match na ho
        vo condition me already hum false return kar rahe hai. to that will take care.
    m. But isko undo karna pdega na, vapas se jo element hataya tha vo laga do.
    n. Also jo do for loop lagaoge usme recursive function if me call karoge, agar true aaya to return
        true, else false aaya to next cell check, bss last me return false agar pura 2D array 
        traverse ho gaya hai word ni mila to false hi hoga.

*/



class Solution {
    public boolean exist(char[][] board, String word) {
        int visited[][] = new int[board.length][board[0].length];

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(create(board, word, i, j, 0, visited)){
                    return true;
                }
            }
        }

        return false;
    }

    // can be done with visited also but extra memory was getting used.
    public boolean create(char[][] board, String word, int i, int j, int wordLength, int visited[][]){
        if(wordLength == word.length()) return true;

        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;

        //if(visited[i][j] == 1) return false;

        char temp = board[i][j];

        if(temp != word.charAt(wordLength))
            return false;
        //visited[i][j] = 1;
        board[i][j] = '#';

        boolean found = create(board, word, i+1, j, wordLength+1, visited) || create(board, word, i-1, j, wordLength+1, visited) ||
                        create(board, word, i, j+1, wordLength+1, visited) || create(board, word, i, j-1, wordLength+1, visited);


        board[i][j] = temp;
        //visited[i][j] = 0;

        
        return found;
    }
}