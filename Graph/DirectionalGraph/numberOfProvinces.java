/*
There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.

 

Example 1:

~
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

Important Points:
1. BFS and DSU(Disjoint set Union or Union Find) se bhi kar skte hai, neeche solution diya hai but apne aap try krna bina dekhe, easy hai.
*/


// 1. DFS Solution
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

// 2. BFS Solution
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int visited[] = new int[isConnected.length];
        int numOfProvince = 0;

        Queue<Integer> queue = new ArrayDeque();

        for(int a = 0; a < isConnected.length; a++){
            if(visited[a] != 1){
                queue.add(a);

                while(!queue.isEmpty()){    
                    int city = queue.poll();

                    if(visited[city] == 1) continue;
                    visited[city] = 1;
                    
                    int row[] = isConnected[city];
                    for(int i = 0; i < row.length; i++){
                        if(isConnected[city][i] == 1){
                            queue.add(i);
                        }
                    }
                }

                numOfProvince++;
            }

        }

        return numOfProvince;
    }
}

/*
3. DSU Solution
   1. DSU solution but not completetly optimized. 
   2. Abhi kaha kaha improvement ho skta hai, aao dekhte hai. Plus ye improvements DSU ke part hai:
        a. Sabse smallest optimization, ki agar, PU and pv means parent of u and v are same, do do bara parent[pv] = pu kyu krna hai. 
        b. For that check laga do if pv == pu then continue.
        c. Abhi baat krte hai PATH COMPRESSION ki:
            // if already found parent for all child node of passed node, then store that parent node as parent of passed node AND ALL NODES IN THAT PATH.
            // so it doesn't do the calculation again, let's say we have graph 1-2-3-4. So parent array will look like
            // [1,1,2,3]. As 1's parent is 1, 2's parent is 1 and 3's parent is 2 and 4's parent is 3;
            // now if we need to find root of 4 that will be 1. So we go back from 4th index to 3 index then 3rd to 2nd and at 0th index
            // we will be able to find the root. 
            // So when coming back why not store the root node for all nodes in the path. As 4th parent is 3 then 3's 2 and 2s 1. 
            // Which means root of all 4 node is 1. 
            // If we store the root node for every node, we could get the root for any node in o(1) time.
        d. Abhi baat krte hai Union by rank:
            1. Bhai man lo do graph hai:
                1-2-3-4 and 5-6-7.
            2. Abhi agar tumko 1-2-3-4 me 5-6-7 ko add krna hai, to tumhre pas do option hai.
            3. Ya to   5-6-7          1-2-3-4
                    1<          or 5<
                      2-3-4           6-7
            4. Abhi upar dono me dekho agar first vale i height nikale to aaegi 4 and second vale ki nikale to aaegi 5. 
            5. Abhi smjho height agar zada hogi to parent dhundne me utni hi der lagegi.
            6. To hum check lagaege ki pehle parent nikal liya dono vertices ka, i.e i and j.
            7. Then check karege ki height dono me se konse node ki zada hai.
            8. Abhi ye check kese hoga, uske liye ek height array bana dege, and vertices ka parent nikalte time height set krte rhege
            9. Bhai starting me to sbki height 1 hogi, to height array initialize hoga 1 se.
            10. Then jab parent node add krne jaege, to dekhege
                a. Agar height[pu] > height[pv] to karege parent[pv] = pu. Kyuki jiski height zada hogi usme kam vale ko add krege
                b. Abhi vice versa, agar height[pv] > height[pu] to karege parent[pu] = pv.
                c. Abhi aati hai baat, starting ki jab sabki height same hogi.
                d. Tab ka check ye hoga, ki jabsabhi ki height same hai to chahe parent[pv] = pu kar lo, ye vice versa kar lo.
                e. But is case me tree ki height badhegi jis bhi graph tree me hum add kar rahe hai.
                f. To man lo humne kiya parent[pv] = pu. To height kiski badhi pu ki. To hum karege height[pu]++;
                g. ABhi tume kahoge ki baki ki do conditions me kyu ni height badhai. Iss liye ni badhai lala, kyuki
                h. Jiski height badi hogi usme chote vale ko root ke neeche add karege to height badhegi hi ni. Chahe to karke dekh lo.

            Bas yahi solution hai.


*/  

// 3.a DSU Solution without optimization
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int numOfProvince = 0;

        int row = isConnected.length;
        int col = isConnected[0].length;

        int parent[] = new int[row];

        for(int i = 0; i < row; i++){
            parent[i] = i;
        }

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(isConnected[i][j] == 1){
                    int pu = find(i, parent);
                    int pv = find(j, parent);

                    parent[pv] = pu;
                }
            }
        }

        int visitedRoot[] = new int[row];

        for(int i = 0;  i < row; i++){
            int root = find(i, parent);

            if(visitedRoot[root] != 1){
                visitedRoot[root] = 1;
                numOfProvince++;
            }
        }

        return numOfProvince;
    }

    public int find(int node, int []parent){
        while(node != parent[node]){
            node = parent[node];
        }

        return node;
    }
}

// 3.b DSU Solution with optimization
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int numOfProvince = 0;

        int row = isConnected.length;
        int col = isConnected[0].length;

        int parent[] = new int[row];// 0,0,2
        
        int height[] = new int[row];// 2,1,1

        for(int i = 0; i < row; i++){
            parent[i] = i;
            height[i] = 1;
        }

        for(int i = 0; i < row; i++){
            // why j = i, as we dont want to process both 1,0 and 0,1 which will have same result
            for(int j = i; j < col; j++){
                if(isConnected[i][j] == 1){
                    int pu = find(i, parent);//2
                    int pv = find(j, parent);//2

                    // agar pv and pu, means v and u ke parent same hai to vapas same kyu banana hai 
                    if(pv == pu) continue;

                    if(height[pu] > height[pv]){
                        parent[pv] = pu;
                    }else if(height[pv] > height[pu]){
                        parent[pu] = pv;
                    }else{
                        parent[pv] = pu;
                        height[pu]++;
                    }
                }
            }
        }

        int visitedRoot[] = new int[row];

        for(int i = 0;  i < row; i++){
            int root = find(i, parent);

            if(visitedRoot[root] != 1){
                visitedRoot[root] = 1;
                numOfProvince++;
            }
        }

        return numOfProvince;
    }

    public int find(int node, int []parent){
        if(node == parent[node]) return node;

        int root = find(parent[node], parent);
        // if already found parent for all child node of passed node, then store that parent node as parent of passed node.
        // so it doesn't do the calculation again, let's say we have graph 1-2-3-4. So parent array will look like
        // [1,1,2,3]. As 1s parent is 1, 2's parent is 1 and 3's parent is 2 and 4's parent is 3;
        // now if we need to find root of 4 that will be 1. So we go back from 4th index to 3 index then 3rd to 2nd and at 0th index
        // we will be able to find the root. 
        // So when coming back why not store the root node for all nodes n the path. As 4th parent is 3 then 3's 2 and 2s 1. 
        // Which means root of all 4 node is 1. 
        // If we store the root node for every node, we could get the root for any node in o(1) time.
        parent[node] = root;

        return root;
    }


}