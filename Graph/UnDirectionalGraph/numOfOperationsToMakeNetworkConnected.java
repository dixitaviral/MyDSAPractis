/*
There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network.

You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.

Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.

 

Example 1:


Input: n = 4, connections = [[0,1],[0,2],[1,2]]
Output: 1
Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
Example 2:


Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2
Example 3:

Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
Output: -1
Explanation: There are not enough cables.
 
Intution:

1. Bhai aao ques dekhte hai:
    a. n computers hai and  and usme se kuch computers connected hai extra cables ke through.
    b. abhi kuch computers jo apas me conneted hai unke extra cables bhi hai.
    c. humko un extra edges me se minimum edge move krke jotne non-connected computers hai unko connect kar dena hai.
2. Abhi dekhte hai karna kese hai:
    a. Sabse pehle edge case ki agar nums of edges < nums of computers/vertices - 1, to return -1. Kyuki agar 
        5 vertices hai to minimum 4 edges hogi hai. Agar 4 se kum edges hai to non-connected computers connect krne ke liye edges hi ni hai.
    b. Abhi baat krte hai krna kese hai:
        i. Number of connected components find krne hai, by DisjointSet Union. Abhi DSU tumne alredy kar rakha hai.
        ii. To vo ni bata ra mai, ni aae to notes bane hai vo dekh lo.
        iii. Abhi tumko connected components find krne hai, jab pu != pv hai, to n-- karna hai.
        iv. n mtlb num of vertices, to jab bhi do vertices ek component me aaegi to n-- hoga.
        v. Also edges jo di hai, usme kuch vertices cover ho ri hai and kuch ni ho ri hai. 
        vi. Abhi jab parent array banaoge usko n ke size ka banaoge but Union find karege edges par hi karege.
        vii. Fir simply union find krne ke bad n-1 return kar dege. Kyuki n-- krte time union find me sari vertices count kam ho gaya jo ek compoenet me aa gyi.
        viii. Bachi n vertices, to n vertices ko connect krne me n-1 edges lagegi, vahi return kar do. Khatam.

*/

class Solution {
    public int makeConnected(int n, int[][] connections) {
        if(connections.length < n-1) return -1;
        int row = connections.length;
        int parent[] = new int[n];
        int height[] = new int[n];

        for(int i = 0; i < n; i++){
            parent[i] = i;
            height[i] = 1;
        } 

        for(int i = 0; i < row; i++){
            int u = connections[i][0];
            int v = connections[i][1];

            int pu = find(u, parent);
            int pv = find(v, parent);

            if(pu == pv) continue;
            n--;

            if(height[pu] > height[pv]){
                parent[pv] = pu;
            }else if(height[pv] > height[pu]){
                parent[pu] = pv;
            }else{
                parent[pv] = pu;
                height[pu]++;
            }
        }
        
        return n-1;
    }

    public int find(int x, int parent[]){
        if(x == parent[x]) return x;

        int root = find(parent[x], parent);

        parent[x] = root;

        return root;
    }
}