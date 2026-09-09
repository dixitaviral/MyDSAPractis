/*
Problem Statement: You are given an n, m which means the row and column of the 2D matrix, and an array of 
size k denoting the number of operations. Matrix elements are 0 if there is water or 1 if there is land. 
Originally, the 2D matrix is all 0 which means there is no land in the matrix. The array has k operator(s) 
and each operator has two integers A[i][0], A[i][1] means that you can change the cell matrix[A[i][0]][A[i][1]] 
from sea to island. Return how many islands are there in the matrix after each operation. You need to return an 
array of size k.

Note: An island means a group of 1s such that they share a common side.

Pre-requisite: Disjoint Set data structure

Examples
Example 1:
Input Format: n = 4 m = 5 k = 4 A = {{1,1},{0,1},{3,3},{3,4}} 
Output: 1 1 2 2 
Explanation: The following illustration is the representation of the operation:

Example 2:
Input Format: n = 4 m = 5 k = 12 A = {{0,0},{0,0},{1,1},{1,0},{0,1},{0,3},{1,3},{0,4}, {3,2}, {2,2},{1,2}, {0,2}} 
Output: 1 1 2 1 1 2 2 2 3 3 1 1 
Explanation: If we follow the process like in example 1, we will get the above result.

Intution: 

1. Bhai ye ques hai easy bas thora dimaag lagana hai.
2. Ques keh ra hai n*m matrix hai usme har jagah 0 hai, and tumko ek array diya hai jisme cordinates hai
3. Tumko un cordinates par 1 1 krke 1 dalna hai and then batana hai ki us 1 ko dalne ke bad total kitne island bane
4. Abhi 1 ko dalne ke bad island agar diff component me hua to island ka count badh jaega on that index.
5. But agar same component me aaya to island kam ho jaega.
6. Abhi isko krne ka ye tareeka hai ki :
    a. n*m matrix bana lo start me sab zero rahege.
    b. Then loop chalao given array par jiske ander cordinates hai jinko tumhe 1 krna hai.
    c. Abhi loop ke ander tumko DSU karna pdega, kyuki dsu krke hi agar 1 ke given cordinates adjacent hai to vo
        ek island banaege.
    d. To scene ye hai ki tumko u mil jaega with arr[i][0]*m+arr[i][1]. 
    e. Abhi tmko ek island variable bhi lena pdega jisko tum har index par increment karoge. Kyuki starting me
        to har 1 cell khud me as 1 island count hoga, but fir uske 4 directioanlly adjacent koi 1 milega to tum
        usse uska union kar doge.
    f. Abhi ek condition or hogi ki agar duplicate indices aate hai jispr already 1 hai to uske liye vapas
        island++ krne par duplicate count badhega isliye island++ na krke same island value assign kar dege
        arr[i] par. 
    g. Abhi arr[i] array hai jo hume return krna hai, jo ki bataega ki jab koi given indices par 1 dala
        uss time kitne number of island the hamare pas.
    h. Abhi simple 4 directionally check karo and agar tumko vapas 1 milta hai to union krke island-- kar do.
    j. Last me 4 direction vala loop jab khatam ho tab comp[i] = island kar do. Ho gaya kaam apna.
    
    Itna hi hai solution.
*/

class Solution{
    public int[] numOfIslands(int n, int m, int k, int[][] arr){

        int len = arr.length;

        int matrix[][] = new int[n][m];

        int parent[] = new int[n*m];

        int height[] = new int[n*m];

        for(int i = 0; i < n*m; i++){
            parent[i] = i;
            height[i] = 1;
        }

        int comp[] = new int[len];

        int dir[][] = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};

        int island = 0;

        for(int i = 0; i < len; i++){
            int a = arr[i][0];
            int b = arr[i][1];
            int u = a*m+b;

            if(matrix[a][b] == 1) {
                comp[i] = island;
                continue;
            }

            matrix[a][b] = 1;
            
            island++;
            for(int d[] : dir){
                int row = arr[i][0]+d[0];
                int col = arr[i][1]+d[1];
                
                if(row < 0 || col < 0 || row >= n || col >= m) continue;

                if(matrix[row][col] != 1) continue;

                int v = row*m+col;

                int pu = find(u, parent);
                int pv = find(v, parent);
                
                if(pu == pv) continue;

                island--;

                union(pu, pv, parent, height);
            }

            comp[i] = island;

        }

        return comp;
    }

    public void union(int pu, int pv, int parent[], int height[]){
        if(height[pv] < height[pu]){
            parent[pv] = pu;
        }else if(height[pu] < height[pv]){
            parent[pu] = pv;
        }else{
            parent[pv] = pu;
            height[pu]++;
        }
    }

    public int find(int x, int parent[]){
        if(x != parent[x]){
            parent[x] = find(parent[x], parent);
        }

        return parent[x];
    }
}