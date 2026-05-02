/*
In this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.

 

Example 1:


Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
Example 2:


Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output: [1,4]

Intution:

1. Bhai aao ques smjhte hai:
    a. jo edges 2d matrix di hai vo batati hai ki konse nodes ke beech me edge hai.
    b. and ye sari edges ek ek karke aa ri hai from index 0 to n.
    c. humko last tak sari edges dekh kar batana hai, ki konsi last edge hai jisne graph me cycle banai
        hai.
2. Bhai ye ques ke do approach hai:
    a. DFS approach(not much optimal): 
        i. Ye aproach mene khud se dhundi hai.
        ii. Approach simple hai:
            a. Sabse pehle adjacency map banana start karo.
            b. map banate time ye check karo ki kya do nodes u and v from edges array, exists as key in
                map.
            c. Iska mtlb ye hua ki u and v agar dono keys map me exist karti hai, to vo dobara repeat 
                ho ri hai.
            d. But itna kafi ni hai, ye one of the two conditions me hai. But ye condition ye prove
                ni krti hai u and v ke beeck me cycle ho skti hai.
                like graph hai
                    1-2-3-4 or ye man lo ki 2 to 3 edge hai vo sbse last me aai.
                    edges = [{1,2}, {3,4}, {2,3}]
            e. Abhi soch ki 2 and 3 ke liye already key exists karti hai map me
                but cycle ni bana ri ye edge.
            f. Tabhi ek extra condition chahiye, jo ki hai dfs chala u to v and check karna ki u se chalkar
                kahi v par to aa ja re hai.
            g. to ye teen condition lagegi. agar teeno condition true hai, means cycle exist krti hai.
                in that case save current ans u and v. then next ke liye karo, as humko ans return krna hai
                jo sbse last me edge ho and cycle create kr ri ho.
            h. agar condition fals hoti hai, to simple map me add kar do.
        iii. Abhi baat krte hai dfs method ki.
            a. DFS method me agar u == v ho gaya iska mtlb cycle mil gyi.
            b. to DFS method me pas hoga u and v and map and ek set jo ki visited track karega.
            c. sbse pehle condition hogi u == v if yes to return true.
            d. Uske bad set.add(u);
            e. then retrieve list of connected nodes from map.getOrDefault(u, List.of()). As map bhi sath
                me ban ra hai to chances hai ki u ka entry abhi map me na ho.
            f. then loop over the list and check if num of list is already visited by set.contains(num);
            g. then put dfs call inside if and pass num as u. Agar true return hota hai means cycle
                in this case return true. Tabhi upar vala if ans save karega.
            h. Else last me return false.
*/

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap();
        int[] ans = new int[2];

        List<List<Integer>> adj = new ArrayList();


        for(int i = 0; i < edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];

            if(map.containsKey(u) && map.containsKey(v) && dfs(u, v, map, new HashSet())){
                ans[0] = u;
                ans[1] = v;

                continue;
            }
            map.computeIfAbsent(u, k -> new ArrayList()).add(v);
            map.computeIfAbsent(v, k -> new ArrayList()).add(u);
            
            
        }

        return ans;
    }

    public boolean dfs(int src, int target, Map<Integer, List<Integer>> map, Set<Integer> set){        
        if(src == target){
            return true;
        }

        set.add(src);

        List<Integer> list = map.getOrDefault(src, List.of());

        for(int num : list){
            if(!set.contains(num))
                // why not direct return dfs(num, target, map, set)
                // kyuki list me or abhi num hai agar tu pehle par hi return kar dega
                // to baki ke kese scan hoge.
                // man le pehle hi false aa gaya, to direct return krne se baki ke nums scan ni hoge.
                if(dfs(num, target, map, set))
                    return true;
        }

        return false;
    }

}

