/*
There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.

 

Example 1:


Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
Example 2:


Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3

Intution:
1. Bhai ye question graph ka hai and simple ques hai.
2. Karna sirf itna hai ki jo connected nodes hai like a to b and b to c then a to c indirectly
    connected hai, to vo ho gaya ek province. Abhi jo nodes connected ni bhi hai
    vo bhi ek province hai, kyuki vo apne aap me connected hai.
3. Abhi is ques ko solve krne ka tareeka hai:
    1. See jo matrix me i chalega vo hai node and j aage badhaege to vo bataega ki i node konsi
        node se connected hai.
    2. Logic ye rahega ki simple row par loop chalao and ek visited array bana lo 1d rhega
        jo ki aage hum set krege when a node will be visited.
    3. Abhi vo visited array par check lagao ki node kahi already visited to ni hai and count
        badha do, kyuki jab bhi ek node visit karenge to iska matlab hai ki ek province mil gaya, to count badhao.
    4. then simple dfs chala lo, abhi dfs kyu chalega kyuki humko janna hai kitne nodes
        connected hai, to humko deep me jana hoga.
    5. DFS me pass karo matrix and i and visited array. 
    6. sbse pehle jo node i hai usko visited mark kar do, visited[i] = 1.
    7. Abhi since humne main method me sirf row par loop chalaya tha, abhi dfs ke ander
        col par loop chalao and check karo:
        a. ki matrix[i][j] == 1 and not visited, then again us par dfs chala do.
        b. Abhi socho matrix[i][j] par 1 mila to next node konsi hogi, ans hai j node.
        c. to hum dfs me matrix pass karege and j pass karege as i and visited array.
        d. Ye dfs tab tak chalega jab tak current ith row ke liye sare j col check ni ho jate.
    8. Bas ho gaya.
*/

class Solution {
    public int findCircleNum(int[][] isConnected) {
        int rowLen = isConnected.length;
        int colLen = isConnected[0].length;
        int count = 0;
        int visited[] = new int[rowLen];

        for(int i = 0; i < rowLen; i++){
            if(visited[i] != 1){
                count++;
                dfs(isConnected, i, colLen, visited);
            }
        }

        return count;
    }

    public void dfs(int isConnected[][], int i, int colLen, int visited[]){
        visited[i] = 1;

        for(int j = 0; j < colLen; j++){
            if(isConnected[i][j] == 1 && visited[j] != 1){
                dfs(isConnected, j, colLen, visited);
            }
        }
    }
}