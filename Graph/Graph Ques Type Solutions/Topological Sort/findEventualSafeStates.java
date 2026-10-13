/*

There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].

A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).

Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.


Example 1:

Illustration of graph
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Explanation: The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
Example 2:

Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
Output: [4]
Explanation:
Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.


Intution:

1. bhai ye ques me topological sort/Kahn Algorithm lagegi.
2. Ques ye keh ra hai ki ek hai terminal nodes jinse koi bhi out array ni gaya hai.
3. And ek hai simple nodes, jinse outgoing arrows ja ri hai.
4. Ek node se multiple out arrows bhi ja skte hai.
5. To tumko ye batana hai vo kon kon si nodes hai, jo directly ya indirectly 
    apne sare out arrows se kisi terminal node par ja ri hai.
6. Abhi iska intution dekho:
    i. Sabse pehle to tumko ye nikalna hai ki terminal nodes kon kon si hai.
    ii. Vo nikaloge tum aise ki jo graph array diya hai, usme konse index ka array empty hai.
    iii. jis bhi index ka array empty hai, iska mtlb koi out arrow ni ja ra hai usse.
    iv. Un nodes ko tum ek set me store kar lo.
    v. then simple DFS chala do.
    vi. DFS se tumko ye check karna hai, means base condition kya kya rahegi, but usse pehle aao dekhe
        parameters kya kya hoge:
        a. TermNodes: ye ek set hai jo ki terminal nodes ko store krke rakhega.
        b. graph : tum graph pass karoge jo ques me as input diya hai.
        c. node : dfs har node par chalega to konse node se start kar rahe ho vo node.
        d. visited : ek visited array, jo ki bataega ki current node pehle traverse ho chuku hai
                    and is node se path jaega to safe node hai term node tak pohocha rahi hai ye,
        e. path : ye ek set hai, jo ki path store karega for ongoing dfs cycle, then last me 
                    backtrack karege isko taki dusri branches bhi explore kar sake.
    vii. Bas abhi base condition dekh lete hai:
        a. Sabese pehli condition hai ki agar node ek terminal node hai, iska mtlb ek path me
            terminal node mil gyi hai to hum true return karege and next branch of same node
            explore karege.
        b. Dusri base condition hai ki agar same dfs cycle me path me dobara koi node aati hai
            to return false, kyuki pehle hi consider kar chuke hai.
        c. Teesri base condtion hai agar visited[node] true hota hai, iska mtlb use node se aage jo
            path jata hai vo already explored hai and vo terminal node ko jata hai, to return true.
    viii. fir simple path.add(node)
    ix. ek flag variable le lege jisme dfs ka output store karege, default true rakhege.
    x. Then for loop chalaega current node se associated nodes par and uske ander
        vapas dfs call karege.
    xi. But humko flag = flag && dfs() karna hai.
    xii. Ye isliye kyuki, ques keh ra hai agar node ki kisi bhi path se hum terminal node tak ni pohoche
        to vo node safe nahi hai hence return false. to and condition me agar koi false aaya to pura
        flag false ho jaega.
    xiii. abhi since ek node se do ya do se jada branches nikalti hai to humko backtrack karna pdega
        ek branch ko explore karke to path.remove(node).
    xiv. Then agar flag true hai iska mtlb current node ke sare path terminal node ko jate hai.
        hence visited[node] ko true mark kar dete hai.
    xv. And last me vahi flag return kar dete hai.

    Khatam.

    BFS solution:
        1. upar jo dfs algo likhi hai vo pure kahn algo ni hai.
        2. BFS vali pure kahn algo hai.
        3. Isme tum dekho humko sari nodes se traverse krke ye batana hai ki unke sare raste 
            terminal nodes ko jate hai ya ni.
        4. But agar hum isko ulta kar de. 
        5. Ek map banae jiski mapping ho from terminal node as parent and terminal nodes par kon
            kon si nodes se pohoch sakte hai, those will be child.
        6. Sath hi sath ek outdegree array maintain kar de.
        7. Outdegree array ye bataega ki har node se kitni out edges nikli hai
        8. Humne already indegree dekha hai ki kitne edges in ho re hai.
        9. Outdegree isliye use kar rahe kyuki agar hum terminal nodes se vapas connected
            nodes par jaege, but edge to that connected node to terminal node hai na.
        10. To dekha jae to to us connected node ki outdegree kam ho ri hai.
        11. Bas fir vahi simple queue vala logic. 
        12 Poll then add to result and then loop over associated nodes and then outdegree[node]--
        13. If outdegree[node] == 0, add it to queue.
        14. At last sort it as ques wants that and then return it.

*/

// DFS solution
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int length = graph.length;
        int indegree[] = new int[length];

        for(int i = 0; i < length; i++){
            for(int j = 0; j < graph[i].length; j++){
                indegree[graph[i][j]]++;
            }
        }

        Set<Integer> termNodes = new HashSet();

        for(int i = 0; i < length; i++){
            if(indegree[i] == 0){
                termNodes.add(i);
            }
        }

        List<Integer> list = new ArrayList();

        boolean visited[] = new boolean[length];

        for(int i = 0; i < length; i++){
            if(dfs(termNodes, graph, i, visited, new HashSet())){
                list.add(i);
            }
        }

        return list;
    }

    public boolean dfs(Set<Integer> termNodes, int graph[][], int node, boolean visited[], Set<Integer> path){
        if(termNodes.contains(node)) return true;

        if(path.contains(node)) return false;

        if(visited[node]) return true;

        path.add(node);

        boolean flag = true;

        for(int n : graph[node]){
            flag = flag && dfs(termNodes, graph, n, visited, path);
        }

        path.remove(node);
        if(flag)
            visited[node] = true;

        return flag;

    }
}

// proper kahn algorithm or topological sort
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int length = graph.length;
        Set<Integer> termNodes = new HashSet();
        int outdegree[] = new int[length];

        Map<Integer, List<Integer>> map = new HashMap();

        for(int i = 0; i < length; i++){
            if(graph[i].length == 0){
                termNodes.add(i);
            }
            outdegree[i] += graph[i].length;
            for(int j = 0; j < graph[i].length; j++){
                map.computeIfAbsent(graph[i][j], k-> new ArrayList()).add(i);
            }
        }

        Queue<Integer> queue = new ArrayDeque(termNodes);

        List<Integer> result = new ArrayList();

        while(!queue.isEmpty()){
            int node = queue.poll();

            result.add(node);

            for(int n : map.getOrDefault(node, List.of())){
                outdegree[n]--;

                if(outdegree[n] == 0)
                    queue.add(n);
            }
        }

        Collections.sort(result);

        return result;
    }
}