/*
There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:

There are no self-edges (graph[u] does not contain u).
There are no parallel edges (graph[u] does not contain duplicate values).
If v is in graph[u], then u is in graph[v] (the graph is undirected).
The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.

Return true if and only if it is bipartite.

 

Example 1:


Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
Output: false
Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.
Example 2:


Input: graph = [[1,3],[0,2],[1,3],[0,2]]
Output: true
Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.

Intuition:
1. Bhai sbse pehle ye ques smjhte hai, ki kya hota hai bipartite graph.
2. Example smjho man lo ye ek graph hai-> 1-2, abhi agar hum do set banae ek set A = {1} 
    and set B = {2}.
3. Abhi set A and B me, dono nodes ek dusre se connect hoti hai. But twist ye hai
    ki same set me koi bhi nodes ek dusre se connect ni honi chahiye. Bas agar aisa
    set bana lete ho, to graph bipartite hota hai.
4. Abhi is ques me ye logic implement kese karege:
    a. Dekho bhai isme do condition use hogi:
        i. If the node is visited or not.
        ii. If the next connected node is having same color.
    b. Mtlb ye hai ki humko graph me ek cycle dhundni hai, ki kahi hum ghum kar
        same node par to ni aa gaye.
        i. Abhi tum kahoge, to isme ye color color kya kar rahe ho, ye to simple hai
        ii. Ki dfs chalao and agar same node par ghum kar aate hai, vo mil gyi cycle.
        iii. Abhi iska jawab ye hai ki, jese ek graph hai:
            1-2
            | |
            3-4
        iv. Abhi man lo hum 1 se chale, humne 1 ko color 2 de diya and then 2 par color 1
            de diya, then 3 par aae color vapas 1 de diya, then 4 par aae color vapas 2 de diya, 
            then 1 par aae to 1 par color 2 hai, to agar color sequence dekhoge
            to aaega 1-2-1-2-1, to iska mtlb hai ki hum same node par ghum kar to aae
            and color bhi alternate hai, aisa ni hai ki do bar same color aaya.
        v. Abhi dusra example lete hai:
            1-2
            |\|
            3-4
        vi. isme dekho 1 se ek edge 4 ko gyi hai, abhi color assign karo to aise hoga
            2-1-2-2, ye color path hai 1-2-4-1 nodes ka. Abhi dekho 2 repeat hua, ek bar 4 node par aaya
            then 4 se 1 par gye tab aaya. 
        vii. Yhi diff hai ki hum sirf cycle ni dhund rahe, balki ye bhi dekh rahe hai, nodes 
            evenly distributed hai. Or yehi logic se hum pata karte hai ki graph ko
            do alag sets me divide kar sakte hai ya ni, hence graph bipartite hai ya ni.
    c. Abhi is ques ko solve krne ka tareeka hai:
        i. Sabse pehle ek array bana lo col jo ki hoga graph.length ke size ka. Ye array humko help karege
            nodes ke col store krne me and also node visited hai ya ni ye check karne me.
        ii. Then ek for loop of the rows and inside that loop, check if col[i] == 0, then only
            start DFS, else mtlb hua node already visited no action should be take.
        iii. ABhi inside row loop hum if condition ke ander dfs chalaege, agar dfs se false
             return kiya it means, graph me same color cycle mili hai and graph bipartite ni ho skta
        iv. Abhi aate hai dfs ke ander. dfs ke param hoge:
            1. graph
            2. col array
            3. i node
            4. curr color: ye curr node ka color hoga, hum isko 1 and 2 me rkhege. Agar curr color 0
                hua to node visited ni hai.
        v. Abhi sbse pehle node ko visited mark karege by col[i] = curr;
        vi. Then ek col me loop chalaege, till graph[i].length.
        vii. then nieghbour node ko ek variable me store kar lege.
        viii. Abhi check karege ki neighbour node not visited hai by col[neighbour] == 0. 
        ix. then  inside if not visited condition, humko ek or nextCol variable lena hai jisme hum curr ki 
            flipped value store karege by curr == 1 ? 2: 1.
        x. ABhi kahoge aisa kyu, to upar logic me humne dekha tha ki next neighbour node ke pas
            alternate color hona chahiye. To agar current node ko 1 assign kiya hai to neighbour node
            2 and vice versa.
        xi. Abhi if(!dfs(graph, col, neighbour, nextCol)) return false. Iska mtlb ye ki agar
            aage DFS krne par humko kahi bhi same color cycle mil gyi to false return karega dfs and yaha se
            main function ko false return hoga.
        xii. Abhi outer if condition ki else if condition lagegi, see if condition me hum check
            kar rahe the ki node already visited ni hai, else if me aa gye mtlb node visited hai.
        xiii. But visited node ka color same hai ya ni, agar same hai to color cycle mil gyi to retunr false.
        xiv. Agar same ni hai but visited hai, to iska mtlb hua same node par vapas aa gye, to vo skip kar do
            and next neighbour ko dekho.
    d. Abhi main function me last me return true kar do, kyuki pura dfs chala and
        color cycle ni mili iska mtlb graph bipartite hai.

*/

class Solution {
    public boolean isBipartite(int[][] graph) {
        int col[] = new int[graph.length];

        for(int i = 0; i < graph.length; i++){
            if(col[i] == 0){
                if(!dfs(graph, col, i, 2))
                    return false;
            }
        }

        return true;
    }

    public boolean dfs(int graph[][], int col[], int i, int curr){
       col[i] = curr;

       for(int j = 0; j < graph[i].length; j++){
            int neighbour = graph[i][j];

            if(col[neighbour] == 0){
                int nextCol = (curr == 1 ? 2 : 1);

                if(!dfs(graph, col, neighbour, nextCol))
                    return false;
            }else if(col[neighbour] == col[i])
                return false;
       }

       return true;
    }
}