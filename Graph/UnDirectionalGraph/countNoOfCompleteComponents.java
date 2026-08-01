/*You are given an integer n. There is an undirected graph with n vertices, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting vertices ai and bi.

Return the number of complete connected components of the graph.

A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.

A connected component is said to be complete if there exists an edge between every pair of its vertices.

 

Example 1:



Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
Output: 3
Explanation: From the picture above, one can see that all of the components of this graph are complete.
Example 2:



Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4],[3,5]]
Output: 1
Explanation: The component containing vertices 0, 1, and 2 is complete since there is an edge between every pair of two vertices. On the other hand, the component containing vertices 3, 4, and 5 is not complete since there is no edge between vertices 4 and 5. Thus, the number of complete components in this graph is 1.

Intution:

1. Bhai ques easy hai bas ek trick hai.
2. Usse pehle ques dekhte hai kya hai exactly.
3. Ques simply keh ra hai bhai ek graph hai usme jitne complete component ho unka count return kar do.
4. Abhi sawal hai complete component kya hota hai, aao dekhe:
    a. Complete component basically ek connected component hota hai jiski har pair of nodes ke beech
        ek edge zrur hogi.
    b. Jese 2 nodes ke beech 1 edge, 3 nodes ke beech 3 edges, 4 nodes ke beech 12 edges and so on.
    c. Isko try krna khud bana kar, kitne bhi nodes ka graph bana and uski har node ke beech ek edge de dena.
    d. Isse ek pattern mila ki hamesha nodes*(nodes-1)/2 == no of edges.
    e. Aur ye nodes pure graph ki nahi hai, sirf ek connected component ki hai jo ki complete connected component
        ho skta hai.
5. Abhi baat aati hai isko solve kese karege.
6. Ek baat hamesha dhyan rakhna graph ke sare ques BFS/DFS se solve ho jaege. And jo connected component
    vale ques hai, vo hamesha Disjoint set union se solve ho jaege.
7. Abhi ye scene hai ki karege kese isko:
    i. DFS Approach:
        a. Simple hai. 
        b. Sabse pehle ek hashmap bana lo konsi nodes ke beech me edges hai.
        c. Then No of nodes par loop chala do.
        d. Main baat aati hai complete connected component hai ya ni ye kese pata karege.
        e. Simpe hai, do global variables lelo, nodes and edges.
        f. Har node par dfs chala lo, usse pehle nodes and edges ko 0 se initialize kar do.
        g. Then dfs ke ander tumko simple ye karna hai:
            a. sbse pehle visited start node check if yes then return.
            b. Then if not visited nodes++;
            c. Then Take out connected nodes from map.
            d. Abhi global var edges me edges += list.size().
            e. Kyuki Ek start node se kitni edges nikli hai yahi to map bata ra hai.
            f. fir no of connected nodes par for loop chala do.
            g. if visited then continue.
            h. else run dfs on that node.
    ii. BFS Approach:
        a. BFS approch bhi same as DFS approach hai
        b. But isme hum queue use krte hai.
        c. For loop chalao till n and every node mark visited and add to queue.
        d. Then BFS chalao nodes and edges count karo.
        e. Then same check nodes(nodes-1) == numOfEdges.
        f. Then count++;
8. Abhi jab dfs/bfs ho jae pura start node par tab uske path check if node(node - 1) ==  edges. Why not use
    /2. Kyuki dfs me har edge do bar count hogi, jese 0 se 1,2 and 1 se 0,2. To isme 0->1 already counted thi.
9. But 1-> 0 bhi count ho gyi. to edges bhi double ho jaegi.
10. Bas abhi ye condition true hai to count++ else no count increase.
11. At last return count.
 
*/


//DFS Approach
class Solution {

    int nodes = 0;
    int edgesNum = 0;
    public int countCompleteComponents(int n, int[][] edges) {
        int visited[] = new int[n];
        int count = 0;
        Map<Integer, List<Integer>> map = new HashMap();
        Map<Integer, Map<Integer, Integer>> cc = new HashMap();

        for(int i = 0; i < edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];

            map.computeIfAbsent(u, k -> new ArrayList()).add(v);
            map.computeIfAbsent(v, k -> new ArrayList()).add(u);
        }

        // every edge is getting counted two times because we have created mapping from u to v and v to u.
        // so in v's map list u will be there and u map list v will be there
        // but the edge is same only bro
        for(int i = 0; i < n; i++){
            if(visited[i] == 0){
                nodes = 0;
                edgesNum = 0;
                dfs(map, i, visited);

                if((nodes*(nodes-1)) == edgesNum)//2. Since in point 1 I have shown every edge is getting counted two times, hence instead of using n(n-1)/2, we used n(n-1).
                    count++;
            }
        }

        return count;
    }

    public void dfs(Map<Integer, List<Integer>> map, int start, int visited[]){
       if(visited[start] == 1){
             return;
       }

       visited[start] = 1;
       nodes++;

        List<Integer> list = map.getOrDefault(start, List.of());
        
        edgesNum += list.size();// 1. here every edge is getting counted two times.

        for(int node: list){
            if(visited[node] != 1){
                dfs(map, node, visited);
            }
        }
    }
}

// BFS Solution
class Solution {

    public int countCompleteComponents(int n, int[][] edges) {
       int visited[] = new int[n];

       Map<Integer, List<Integer>> map = new HashMap();

       for(int i = 0; i < edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];

            map.computeIfAbsent(u, k -> new ArrayList()).add(v);
            map.computeIfAbsent(v, K -> new ArrayList()).add(u);
       }

       Queue<Integer> queue = new ArrayDeque();
       int nodes;
       int numOfEdges;
       int count = 0;
       for(int i = 0; i < n; i++){
            if(visited[i] == 1) continue;
            
            queue.add(i);
            visited[i] = 1;// always mark node as visited when you put in queue.

            nodes = 0;
            numOfEdges = 0;

            while(!queue.isEmpty()){
                int node = queue.poll();//5

                visited[node] = 1; // 1 1 1 1 1 1
                nodes++;// 3

                List<Integer> list = map.getOrDefault(node, List.of());// 3,5

                numOfEdges += list.size();// 6

                for(int v : list){
                    if(visited[v] == 1) continue;

                    queue.add(v);//5
                    visited[v] = 1;
                }
            }

            if(nodes*(nodes-1) == numOfEdges)
                count++;
       }

       return count;
    }
}

// DSU Solution
class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        int parent[] = new int[n];
        int height[] = new int[n];
        Map<Integer, List<Integer>> map = new HashMap();
        int degree[] = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            height[i] = 1;
        }

        // dsu
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            degree[u]++;
            degree[v]++;

            int pu = find(u, parent);
            int pv = find(v, parent);

            if(pu == pv){
                continue;
            }

            if(height[pu] < height[pv]){
                parent[pu] = pv;
            }else if(height[pv] < height[pu]){
                parent[pv] = pu;
            }else{
                parent[pv] = pu;
                height[pu]++;
            }
        }

        int count = 0;

        // create components map
        for(int i = 0; i < n; i++){
            int father = find(i, parent);
            map.computeIfAbsent(father, k -> new ArrayList()).add(i);
        }


        // check if degree of each node in a component is equal to component size - 1;
        // as we know each node in a component will have edge from every vertex except itselt.
        // so nodes are 3 hence every node will have 2 edges incoming.
        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()){

            boolean comp = true;
            for(int num : entry.getValue()){
                if(degree[num] != entry.getValue().size()-1) {
                    comp = false;
                    break;
                }
            }

            if(comp) count++;
        }

        return count;
    }

    // parent find and path compression.
    public int find(int x, int[] parent) {
        if (x == parent[x])
            return x;
        int root = find(parent[x], parent);
        parent[x] = root;
        return root;
    }
}