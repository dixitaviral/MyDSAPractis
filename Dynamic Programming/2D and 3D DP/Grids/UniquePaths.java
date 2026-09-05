/*
There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.

 

Example 1:


Input: m = 3, n = 7
Output: 28
Example 2:

Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down

Intution:
1. Bhai ye ques easy hai bohot since tumhe bfs dfs on grids laga rakh hai already in graph.
2. Abhi is ques me tumko downa dn right move krna hai jiska mtlb i and j me +1 krke aage badhna hai.
3. To bas and hamare pas do choices hai ya to hum right jaege ya to down jaege.
4. To do recursive call laga do and ek right ki and ek down ki.
5. dono se jo result aae usko add krke return kar do.
6. Abhi base condition ye hogi ki jab tum pohoch jaoge last index tak kisi path se to vaha se
    1 return kar dena hai.
7. Ek or base condition laga dena ki jo overflow ko sambhal le.
8. Last me down + right return krna hai same memo me store karna hai.
9. And isi memo se recursive call rokni hai.
*/

class Solution {
    public int uniquePaths(int m, int n) {

        int memo[][] = new int[m][n];
        for(int arr[] : memo){
            Arrays.fill(arr, -1);
        }
        return helper(m, n, 0, 0, memo);
    }

    public int helper(int m, int n, int i, int j, int [][] memo){
        if(i >= m || j >= n) return 0;
        
        if(i == m-1 && j == n-1) return 1;

        // upar ki dono conditions ki jagah ye bhi likh skte hai
        // since hum agar last row ya last col me pohochege to
        // seedha 1 return kar dege kyuki last row and last col
        // me sirf ek hi rasta hoga jo last cell tak le jaega

        // if(i == m-1 || j == n-1) return 1;


        if(memo[i][j] != -1) return memo[i][j];

        int down = 0;
        int right = 0;

        down += helper(m, n, i+1, j, memo);

        right += helper(m, n, i, j+1, memo);

        return memo[i][j] = down+right;
    }
}