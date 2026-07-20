/*
You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.

 

Example 1:


Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
Output: 2
Example 2:

Input: times = [[1,2,1]], n = 2, k = 1
Output: 1
Example 3:

Input: times = [[1,2,1]], n = 2, k = 2
Output: -1
 
Intution:

1. Bhai ques simple hai aao dekhte hai.
2. Ques keh ra hai:
    a. Ki n nodes hai and vo connected hai, and k node startig node di hai.
    b. Abhi k node se humko sari nodes par jana hai and minimun kitne time me sari node visit ho jaegi
        ye batao.
    c. Example smjho ki 1<-2->3->4. Har node se dusri node par jane ki value/time hai 1.
    d. Tum 1 unit time me 2 se 1 and 3 dono visit kr skte hai, then 3 se 4 jane ka again 1, to hua
        2.
    e. Hence total 2 unit time me sari nodes visit kr skte hai.
3. Abhi isko karege kese:
    a. Ki ek priority queue le lenge, jisme ek int array hoga, which will contain next node and us 
        node tak k node se pohochne me ktne time laga to vo.
    b. sorting krege priority queue ki by time that is a[1].
    c. Then times 2d array ko parse krke ek map bana lege Map<Integer, Map<Integer, Integer>> map.
    d. Then ek visited array banaege n+1 size ka kyuki time me starting node 1 start hogi.
    e. Is visited array me humko per index pohochne me from K kitne time laga ye store krege.
    f. Jab stored time se from k agar kam time mil gaya to vo store kar lege.
    g. Abhi visited array ko Integer.MAX_VALUE se initialize krege and then visited[k] = 0.
    h. visited[k] = 0 isliye kyuki k starting node hai, and k se k tak jane me time 0 hi lgega.
    i. Abhi queue ko initialize karege with k and time 0.
    j. Abhi loop chalaege while(!queue.isEmpty())
    k. poll karege and then value nikal lege.
    l. abhi ek check ye laga lete hai taki jo branch zada time legi usko skip kar de.
    m. to check lagege visited[v] < w if yes then continue.
    n. then map jo banaya tha map.get(v) and next node and us node ka time.
    o. us par loop lagega and then again v1 and w1 niklega.
    p. now check again w+w1. w is prev weight and w1 is current node weight.
    q. w+w1 < visited[v1] to update visited[v1] = w+w1 and queue.add(v1, w+w1).
    r. Abhi ye hone ke bad last me visited me loop chalao and check karo ki koi visited array me
        kahi Integer.MAX_VALUE mila iska mtlb koi aisa node hai jo unreachable hai return -1.
    s. If no then calculate ans, but since visited array me check karo ki max ans konsa hai,
        kyuki max ans vahi hoga jiski vajah se sari nodes visited hui. to return that. 
*/

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(
            (int a[], int b[]) -> a[1] - b[1]
        );

        Map<Integer, Map<Integer, Integer>> map = new HashMap();

        for(int arr[] : times){
            int u = arr[0];
            int v = arr[1];
            int w = arr[2];

            map.computeIfAbsent(u, a -> new HashMap()).put(v, w);
        }

        int visited[] = new int[n+1];

        Arrays.fill(visited, Integer.MAX_VALUE);

        visited[k] = 0;

        queue.add(new int[]{k,0});

        int ans = 0;

        while(!queue.isEmpty()){
            int temp[] = queue.poll();

            int v = temp[0];
            int w = temp[1];

            if(visited[v] < w){
                continue;
            }

            Map<Integer, Integer> tempMap = map.getOrDefault(v, Map.of());

            for(Map.Entry<Integer, Integer> entry : tempMap.entrySet()){
                int v1 = entry.getKey();
                int w1 = entry.getValue();

                if(w+w1 < visited[v1]){
                    visited[v1] = w+w1;
                    queue.add(new int[]{v1, w+w1});
                }
            }
        }

        for(int i = 1; i <= n; i++){
            if(visited[i] == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, visited[i]);
        }

        return ans;
    }
}

// without PQ
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap();

        for(int time[] : times){
            int u = time[0];
            int v = time[1];
            int w = time[2];

            map.computeIfAbsent(u, t -> new HashMap()).put(v, w);
        }

        Queue<int[]> queue = new ArrayDeque();

        int visited[] = new int[n+1];

        queue.add(new int[]{k, 0});
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[k] = 0;
        visited[0] = 0;

        int ans = 0;

        while(!queue.isEmpty()){
            int pair[] = queue.poll();
            int i = pair[0];
            int w = pair[1];

            Map<Integer, Integer> next = map.getOrDefault(i, Map.of());

            for(Map.Entry<Integer, Integer> entry : next.entrySet()){
                int node = entry.getKey();
                int value = entry.getValue();
                int sum =  w+value;
                if(visited[node] > sum){
                    visited[node] = sum;
                    queue.add(new int[]{node, sum});
                }
            }
        }

        for(int num : visited){
            if(num == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, num);
        }

        return ans; 
    }
}