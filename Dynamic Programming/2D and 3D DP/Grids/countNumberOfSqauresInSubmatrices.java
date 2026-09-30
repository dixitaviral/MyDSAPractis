/*
Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

 

Example 1:

Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation: 
There are 10 squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.
Example 2:

Input: matrix = 
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation: 
There are 6 squares of side 1.  
There is 1 square of side 2. 
Total number of squares = 6 + 1 = 7.

Intution:
1. Aao bhai ye ques dekhe isko karna kese hai and usse pehle ques kya keh ra hai.
2. To ques keh ra hai ki number of square sub matrix count krke return karo. Abhi number os sqaure
    submatrix me sirf 1 hoge. To dekho agar koi cell me 1 hai to vo 1 size ka square hai.
3. Abhi isi single size sqaure se ques ka intuition kholta hai, aise hi 2 size ka sqaure kitne hai
    vo batao, same 3 4... abhi tumko ye sare size ke sqaure count krke return karna hai number.
4. To aao intution dekhe:
    a. To mene kaha start me ki agar kisi cell me 1 hai to vo 1 size ke sqaure me count hoga.
    b. Abhi isi 1 se tumko 2 size ka square banana padega to kese banaoge. Agar tum ek 1 right me
        add kar do and ek one down me add kar do and ek one diagonal me neeche add kar do. To
        banega 2 size ka square. Aao dikhau kese:
        

        sabse pehle 1 size ka square -> 1
        Add 1 to right, okay -> 1 1
        Add 1 to down, okay -> 1 1
                               1
        Add 1 to diagonal, okay -> 1 1
                                   1 1
    Bana 2 size ka sqaure.
    c. That means ki 1 size ka sqaure count krne ke bad, agar hum diagonal vale cell par jate hai
        to humko peeche dekhna pdega na up left and diagonal up agar teeno jagah 1 hai and current
        cell par bhi 1 hai to 2 size ka sqaure ban gaya.
    d. Abhi isko agar tum naiva recursion and memorization se karoge to kafi messy ho jaega. Instead
        isko iterative DP se bottom up se karo to si rhega.
    e. Abhi bottom up se krne ke liye pehla step to jante hi ho ki tumko ek grid size ki dp matrix
        banani hai then usme initial state ke liye kuch initialization bhi karni pdti hai.
    f. Iss case me initialization hogi boundary cells par since hum upar se neeche jaege dekhte hue.
    g. To jitne bhi boundary cells hai jisme 1 hai agar hum top se dekhte hai to sirf vahi cells
        1 square banaege, to dp boundary cells me jaha jaha grid me 1 hai vahi same dp me kar do.
    h. Abhi ek imp note, dp[i][j] ka mtlb kya hai yaha par, dp[2][2] = 2 hai man lo, to iska
        mtlb ye nikal ra hai ki cell 2,2 tak 2 sqaures possible hai, kisi bhi size ke ho skte hai
        but 2 hi possible hai.
    i. to humne boundary cells ko initialize kar diya, plus humko dp[i][j] ka mtlb bhi pata hai
    j. Abhi bacha hai last step vo ye hai ki further dp cells kese fill karoge. 
    k. Ek or acchi cheez batata hu intution aage ki build krne ke liye:
        man lo matrix hai 

        mat        iski dp
        0 1 2 3    0 1 2 3
      0 1 1 1 1    1 1 1 1
      1 1 1 1 1    1 2 2 2
      2 1 1 0 1    1 2 0 1
      3 1 1 1 1    1 2 1 1 

      Abhi upar dhyan se dekho 1,1 par total 2 squares possible hai ek 1,1, par 1 khud and ek 
      1 1 ye vala square.
      1 1

      But but, agar tum dekho to 2,2 par 0 hai which means 3,3 par 2 size ka square tabhi possible
      hota agar 2,2 par 1 hota, but since ye zero hai to humko min lena padega of up, left and
      diagonal up.
      
      Mtlb smjho simple hai yrr, hum min isliye le rahe hai kyuki diagonal neeche dekhoge agar
      to jo upar vale me se sabse min hoga usi ko consider karoge na, abhi kyu aisa kyuki jo 
      minimum value hai, woh baaki dono directions mein bhi utni ya usse zyada available hai. 
      Isliye current (i,j) par us minimum size ka square charo taraf se complete ho jaata hai.

      Kehne ka mtlb hai ki man lo

      teen side hai 2,3,4 abhi isme se hum 2 isliye lenge kyuki jo side 3 and 4 hai, usme 2 size
      ki side cover ho ri hai means 2 size ki edge hamko har direction se milegi that's why.

    l. To dp[i][j] = 1 + min (left, top, digonal-top);
    m. Abhi simple puri dp matrix fill hone ke bad sare cells ka addition krke return kar do.
*/

class Solution {
    public int countSquares(int[][] matrix) {
        int dp[][] = new int[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++){
            if(matrix[i][0] == 1) dp[i][0] = 1;
        }   

        for(int i = 0; i < matrix[0].length; i++){
            if(matrix[0][i] == 1) dp[0][i] = 1;
        }

        for(int i = 1; i < matrix.length; i++){
            for(int j = 1; j < matrix[0].length; j++){
                if(matrix[i][j] == 1){
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
                }
            }
        }

        int total = 0;

        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                if(dp[i][j] != 0){
                    total += dp[i][j];
                }
            }
        }

        for(int arr[] : dp){
            System.out.println(Arrays.toString(arr));
        }

        return total;
    }
}