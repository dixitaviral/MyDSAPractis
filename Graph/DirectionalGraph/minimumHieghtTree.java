/*
A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

 

Example 1:


Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
Example 2:


Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]

Intution:
1. Bhai dekh ye ques tedha hai thoda. So dhyan se dekhna.
2. Ques keh ra hai ki tujhe ek tree diya hai, and uss tree me agar tu har node ko root man le
    to konse aise root milege jiski height minimum ho, usko return kar do.
3. Abhi is ques ki intution smjhte hai:
    a. Normal flow jo tu sochega:
        i. Sare nodes ko pakdo and uss par dfs chala do
        ii. Jese hi leaf node mile, uski height calculate kar lo and usko store kar lo.
        iii. Abhi sare nodes ki height me se minimum height nikal lo and usko return kar do.
        iv. Iss approach me time complexity hogi O(n^2), kyuki har node par dfs chalaoge
            and dfs me alag se height calculate karoge, so O(n^2) ho gayi.
    b. Abhi is ques ki optimal approach dekhte hai:
        i. To bhai approach ye hai:
            1. Ki hum BFS use karege.
            2. Abhi BFS kese use karege dekhte hai.
            3. Intution ye hai ki tu leaf nodes jitni bhi hai usne start karega, means bfs queue me dalega.
            4. Then un leaf nodes ko layer by layer remove karega. For example graph hai
                    1
                    |
                0---2---3---4
            5. Abhi tu graph me leaf nodes 0,1,4 ko hata de bacha kya 2,3 jo ki tera ans hai.
            6. Abhi agar teri do leaf nodes bachi last me, to dono ans hai, or agar ek bachi to ek ans hai
        ii. Abhi isko implement kese karege:
            1. Sabse pehle edges graph ko adjacency map me convert kar le. Jo ki tujhe aata hai, yaha
                ni likhuga.
            2. Abhi sath me hi ek degree array bana le, jis loop me adjacency map banaega usi me
                fill kar le. Degree array ye bataega ki konsi node par kitni edges hai.
            3. Then ek queue bana le, jisme tu jitni leaf nodes hai, usko push kar de.
            4. Abhi tu kahega ki leaf nodes kese pata chalege, to bhai degree array me jiski
                degree 1 hai, vo leaf node hai.
            5. Abhi aati hai BFS while loop ki baat.
            6. Tu sochega ki BFS while loop chalega till queue.isEmpty(), but yhi par galat ho jaega.
            7. Mene upar kya bola tha ki tu layer by layer remove karega, mtlb current queue ki size
                ke barabar agar rakhega to logic kuch aisa banega ek leaf node ko tu 1 by 1 hataega.
            8. Jo ki galat ho jaega, and usse to ans node ko bhi remove kar dega.
            9. Isliye, tu loop lagaega while(n > 2) ka. 
            10. And then tu kya karega ki queue me current me sari leaf node padi hogi.
            11. Or tujhe leaf node hatani hai, to tu queue ka size nikal kar n -= size kar de. n means
                number of nodes, jisme se leaf nodes means queue.size() minus kar diya.
            12. Abhi dusri cheez ye krni hai ki, jo leaf nodes tune hatai hai vo still queue me hai and 
                other nodes jo leaf nodes se associated hai unki degree bhi to kam karni hai.
            13. To tu ek for loop chala queue.size jitna, then queue.poll kar le.
            14. Uske bad associated nodes ki list nikal le, and us list par loop chala de.
            15. Then uss list ke har num par degree[num]-- kar de.
            16. Abhi agar degree[num] == 1 aa jae, means jo num node hai vo leaf node ban jae
                to usko queue me dal de.
            17. Abhi ye tab tak chalega jab tak n > 2 hai.
            18. n > 2 ka logic ye hai ki ya to 2 nodes last me bachegi ya 1. Jitni bache vo tera ans.
            19. Bas khatam ho gaya.
*/

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap();
        int[] degree = new int[n];

        for(int i = 0; i < edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];

            degree[u]++;
            degree[v]++;

            map.computeIfAbsent(u, k -> new ArrayList()).add(v);
            map.computeIfAbsent(v, k -> new ArrayList()).add(u);
        }

        System.out.println(Arrays.toString(degree));
        System.out.println(map);

        Queue<Integer> queue = new ArrayDeque();

        for(int i = 0; i < n; i++){
            if(degree[i] == 1)
                queue.add(i);
        }

        while(n > 2){
            int size = queue.size();
            // decrement same level leaf nodes
            // as all node with degree 1 are present in queue, which means they are at same level.
            n -= size;

            // now how much leaf nodes that are decremented, poll them out from queue.
            for(int i = 0; i < size; i++){
                int node = queue.poll();
                // and then decrease the associates nodes degree
                for(int num : map.get(node)){
                    degree[num]--; 
                    // if found any leaf node again, then push it to queue.
                    if(degree[num] == 1){
                        queue.add(num);
                    }
                }
            }
        }

        return new ArrayList(queue);
    }
}