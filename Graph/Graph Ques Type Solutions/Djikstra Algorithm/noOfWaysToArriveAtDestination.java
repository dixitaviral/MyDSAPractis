/*
You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.

You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.

Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.

 

Example 1:


Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
Output: 4
Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
The four ways to get there in 7 minutes are:
- 0 ➝ 6
- 0 ➝ 4 ➝ 6
- 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
- 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
Example 2:

Input: n = 2, roads = [[1,0,10]]
Output: 1
Explanation: There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.

    Intution:
    1. Bhai sbse pehle to solution dekh kar ghabrana ni, kyuki ye tumne khud se samjha hai.
    2. Isme bhi idea seedha hai, bas ek simple graph problem hai jahan humein shortest cost ka path nikalna hai.
    3. Abhi samjhte hain ki yahan queue ki jagah priority queue kyun lagti hai.
    4. Normal queue sirf order ke hisaab se elements nikalta hai, lekin yahan har edge ka cost alag hai.
       Isliye agar hum normal queue use karenge to humein har baar sabse chhota path ka node milne ki guarantee nahi hogi.
    5. Isliye hum priority queue use karte hain. Priority queue humein har baar sabse chhota cost wala node pehle deta hai.
    6. Abhi idea ye hai ki humein start node se har node tak minimum distance track karni hai.
       Iske liye hum dist array use karte hain.
    7. Saath hi humein ye bhi track karna hai ki us minimum distance tak kitne alag raaste hain.
       Iske liye hum ways array use karte hain.
    8. Jab hum kisi node ko visit karte hain, to hum uske neighbors ko dekhte hain.
       Agar current node se kisi neighbor tak ka path chhota ho raha hai, to dist update kar dete hain.
    9. Agar same shortest distance ka aur path milta hai, to ways ko add kar dete hain.
       Isse matlab hota hai ki ek aur shortest path mil gaya.
    10. Is tarah hum start se end tak sabse chhota cost aur uske kitne raaste hain, dono nikal lete hain.

*/

class Solution {

    public int countPaths(int n, int[][] roads) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap();

        for(int road[] : roads){
            int u = road[0];
            int v = road[1];
            int w = road[2];

            // graph ko adjacency list style me store kar rahe hain
            map.computeIfAbsent(u, k -> new HashMap()).put(v, w);
            map.computeIfAbsent(v, k -> new HashMap()).put(u, w);
        }

        // sabse chhota distance wala node pehle process karne ke liye priority queue use kar rahe hain
        PriorityQueue<long[]> queue = new PriorityQueue<>((long []a, long b[]) -> Long.compare(a[1], b[1]));

        long dist[] = new long[n];
        int ways[] = new int[n];

        int mod = 1000000007;

        Arrays.fill(dist, Long.MAX_VALUE);

        // start node se khud tak distance 0 hai aur ek hi way hai
        dist[0] = 0;
        ways[0] = 1;

        queue.add(new long[]{0, 0});

        while(!queue.isEmpty()){
            // sabse chhota cost wala node nikal rahe hain
            long pair[] = queue.poll();

            int i = (int)pair[0];
            long j = pair[1];

            if(j > dist[i]) continue;

            for(Map.Entry<Integer, Integer> entry : map.getOrDefault(i, Map.of()).entrySet()){
                int node = entry.getKey();
                int value = entry.getValue();

                long sum = j+value;

                // agar yeh naya path pehle se better hai to distance update karte hain
                if(sum < dist[node]){
                    dist[node] = sum;
                    ways[node] = ways[i];

                    queue.add(new long[]{node, sum});
                }
                // agar same minimum distance ka aur path mil gaya, to ek aur shortest way mil gayi
                else if(sum == dist[node]){
                    ways[node] = (int)((long)(ways[i]+ways[node]) % mod);
                }
            }
        }

        return ways[n-1];
    }
}