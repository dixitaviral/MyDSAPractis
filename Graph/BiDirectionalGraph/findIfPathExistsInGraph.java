/*
    There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.

 

Example 1:


Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
Output: true
Explanation: There are two paths from vertex 0 to vertex 2:
- 0 → 1 → 2
- 0 → 2
Example 2:


Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
Output: false
Explanation: There is no path from vertex 0 to vertex 5.

Intution:
1. Bhai ye ques start hota hai graph se. Input me ek edges matrix di hai, jo humko
    bata ri hai, ki kause vertices bi-directionally connected hai.
2. Abhi jo grid di hai, usse humko proper mapping ni mil rahi hai, to humko
    ek map banana hai, jo ki batae ki ek vertex se kitne vertices connected hai via edges.
3. Uske liye hum simple ek loop chalaege jo ki edges[i][0] se chalega, and value me exmpty
    list daal dega.
4. Then uske bad, vapas se ek loop chalao, jo ki vertices map karega jitne bhi map 
    key entry humne point 3 loop me kari hai.
5. Abhi vo kese hoga, simple hai, ek loop chalao jisme i to edges.length tal move karo.
6. Abhi humko ye pata hai ki, ek row means j jo hai vo at max 2 element ka hoga,
    kyuki har ek vertex kitne vertex se connected hai, uske liye alag alag rows hai. For
    example, agar 0 se 1 and 2 connected hai to array hai [[0,1],[0,2]].
7. To humko kya karege ki ek u variable lege and kahege u = map.get(edges[i][0])
    and v = map.get(edges[i][1]).
8. Abhi u and v dono list hai, to humko dono me ek dusre ko add karna hai, kyuki
    bi-directional hai, to u me v add karna hai and v me u add.
9. Abhi simple hai queue banao and source ko queue me daal do, and ek visited set banao, 
    jisme source ko add kar do.
10. Abhi queue empty hone tak loop chalao, and queue.poll karo
    and usko curr variable me daal do.
11. Abhi map se curr ke corresponding list nikal lo, and usme loop chalao, and check karo ki kya
    wo element destination ke barabar hai, agar hai to true return kar do.
12. Agar ni hai to check karo ki kya wo element visited set me hai, agar ni hai to usko visited
    set me add kar do and usko queue me daal do.
13. Agar loop khatam ho gaya to false return kar do.
*/

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> map = new HashMap();

        if(edges.length == 0) return true;

        int rowLen = edges.length;
        int colLen = edges[0].length;

        for(int i = 0; i < n; i++){
            map.put(i, new ArrayList());
        }

        for(int i = 0; i < rowLen; i++){
           int u = edges[i][0];
           int v = edges[i][1];

           map.get(u).add(v);
           map.get(v).add(u);
        }

        System.out.println(map);

        Queue<Integer> queue = new ArrayDeque();
        boolean []visited = new boolean[n];

        queue.add(source);
        visited[source] = true;

        while(!queue.isEmpty()){
            int vertex = queue.poll();
            List<Integer> temp = map.get(vertex);

            if(temp == null || temp.isEmpty()) continue;

            for(Integer num : temp){
                if(num == destination)
                    return true;

                if(!visited[num]){
                    queue.add(num);
                    visited[num] = true;
                }
                
            }
        }
        return false;   
    }
}