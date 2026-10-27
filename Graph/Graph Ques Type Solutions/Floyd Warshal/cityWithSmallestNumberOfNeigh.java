/*
There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.

Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.

Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.

 

Example 1:



Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
Output: 3
Explanation: The figure above describes the graph. 
The neighboring cities at a distanceThreshold = 4 for each city are:
City 0 -> [City 1, City 2] 
City 1 -> [City 0, City 2, City 3] 
City 2 -> [City 0, City 1, City 3] 
City 3 -> [City 1, City 2] 
Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.
Example 2:



Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
Output: 0
Explanation: The figure above describes the graph. 
The neighboring cities at a distanceThreshold = 2 for each city are:
City 0 -> [City 1] 
City 1 -> [City 0, City 4] 
City 2 -> [City 3, City 4] 
City 3 -> [City 2, City 4]
City 4 -> [City 1, City 2, City 3] 
The city 0 has 1 neighboring city at a distanceThreshold = 2.

Intution:
1. Bhai, is problem ka basic idea simple hai. Humein har city se har dusri city tak minimum distance nikalni hai.
2. Sabse pehle hum graph ko adjacency matrix me convert karte hain. Agar direct edge hai to uska weight daal dete hain, warna Infinity rakhte hain.
3. Phir Floyd Warshall algorithm use karte hain, jisse hum har node ke liye sabse chhota path nikal sakte hain.
4. Jab sabse chhote distances ready ho jaate hain, to hum har city ke liye count karte hain ki uske distanceThreshold ke andar kitne cities reachable hain.
5. Jo city minimum number of reachable cities ke saath aati hai, wahi answer hoti hai. Agar do cities ka count same ho, to hum greatest city number wali city return karte hain.
6. Isme hum Dijkstra bhi use kar sakte hai and bellman ford bhi but floyd warshal ek suitable algo hai for finding path between all vertices.
7. Agar isme n > 100 hota to flyod warshal TLE de skta hai in that case Dijkstra algorithm use krte hai with simple ek extra for loop till n
    add krke before doing queue addition and bfs.
*/

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int dist[][] = new int[n+1][n+1];

        // initialize matrix with Integer MAX and leave as 0 where i and j are equal
        // as distance from same node to same node is always 0
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != j){
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }


        // now fill the matrix with the edges given along with their weight
        for(int i = 0; i < edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];

            dist[u][v] = w;
            dist[v][u] = w;
        }

        // Now Apply floyd warshal algorithm, to take out shortest distance from all nodes
        // to all nodes
        for(int via = 0; via < n; via++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    // since Inter.MAX_VALUE me kuch add karoge to overflow ho jaega tabhi ye check lagao
                    if(dist[i][via] != Integer.MAX_VALUE && dist[via][j] != Integer.MAX_VALUE)
                        dist[i][j] = Math.min(dist[i][j], dist[i][via] + dist[via][j]);
                }
            }
        }

        int count = 0;
        int minCount = Integer.MAX_VALUE;

        int answer = -1;

        for(int i = 0; i < n ; i++){

            count = 0;
            for(int j = 0; j < n; j++){
                // agar i ==  hai, mtlb node khud se khud ki distance dekh rahi hai i.e. 0
                // to uss distance ko ignore karna hai else 0 vali distance bhi count++ hoga
                // or humko neighbour ki distance check krni hai
                if(i != j && dist[i][j] <= distanceThreshold){
                    count++;
                }
            }

            // Agar same count aa jae to last appeared one ans hoga
            if(count <= minCount){
                minCount = count;
                answer = i;
            }
        }

        return answer;
    }
}

// Dijkstra Solution
class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap();

        for(int edge[] : edges){
            map.computeIfAbsent(edge[0], k -> new HashMap()).put(edge[1], edge[2]);
            map.computeIfAbsent(edge[1], k -> new HashMap()).put(edge[0], edge[2]);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        Map<Integer, Set<Integer>> cityCount = new HashMap();

        for(int i = 0; i < n; i++){
            queue.add(new int[]{i, 0});

            int dist[] = new int[n];

            Arrays.fill(dist, Integer.MAX_VALUE);

            dist[i] = 0;

            cityCount.put(i, new HashSet());

            while(!queue.isEmpty()){
                int pair[] = queue.poll();

                int j = pair[0];
                int cost = pair[1];

                if(cost > dist[j]) continue;

                Map<Integer, Integer> next = map.getOrDefault(j, Map.of());

                for(Map.Entry<Integer, Integer> temp : next.entrySet()){
                    int node = temp.getKey();
                    int value = temp.getValue();

                    int sum = cost + value;

                    if(node == i) continue;

                    if(sum > dist[node] || sum > distanceThreshold) continue;

                    cityCount.computeIfAbsent(i, k -> new HashSet()).add(node);

                    queue.add(new int[]{node, sum});

                    dist[node] = sum;
                }
            }
        }
        int count = Integer.MAX_VALUE;
        Set<Integer> set = new TreeSet();

        for(Map.Entry<Integer, Set<Integer>> temp : cityCount.entrySet()){
            count = Math.min(count, temp.getValue().size());
        }

        for(Map.Entry<Integer, Set<Integer>> temp : cityCount.entrySet()){
            if(count == temp.getValue().size()){
                set.add(temp.getKey());
            }
        }

        return new ArrayList<>(set).get(set.size()-1);
    }
}