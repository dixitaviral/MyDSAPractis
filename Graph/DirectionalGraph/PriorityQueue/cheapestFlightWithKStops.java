/*
There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.

 

Example 1:


Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
Output: 700
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
Example 2:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
Output: 200
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
Example 3:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
Output: 500
Explanation:
The graph is shown above.
The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.


why we need BFS in this question:
    1. Because if we use dfs, we will first iterate over the first path only going deep and taking out the answer.
    2. Whereas there could be multiple answer or path you can say with different prices.
    3. If you will use BFS then in that case, you can iterate over multiple paths easily one by one before going deep first.
    4. Which will make it easy to compare the prices and return the cheapest one.

Intution:
1. Bhai ye ques simple hai BFS se lag jaega, but kafi twists hai isme, neeche points me batata hu.
2. Ques kya keh ra hai chal smjhte hai:
    a. Ques keh ra hai ki tujhe n cities di hai.
    b. Aur tujhe ek 2D array diya hai jisme flights di hai, jisme 3 cheze hai:
        i. From Node: Means source node jaha se flight start ho rahi hai.
        ii. To Node: Means destination node jaha flight ja ri hai.
        iii. Price: src to dst jane me kitna expense hoga.
    c. Then src and dst diya hai, and k diya hai, jo batata hai ki atmost itne stops me pohochna hai.
    d. Agar k stops se kam me bhi pohoch gye to bhi valid ans hai.
    e. But tujhe har path me se within k stops me jo cheapest price vala path hai vo return krna hai.
3. Ab baat krte hai ki intution ki:
    a. Sabse pehle toh hume graph banana hoga.
    b. Sabse accha tareeka hai graph banane ka by using map, jisme src node key hogi.
    c. Then map jo banega vo map of map hoga, jisme key hoga src node and values hoga ek or map,
        jisme key hogi dst and value hogi src to dst price.
    d. Abhi baat krte hai BFS ki, but usse pehle reason dekho upar jakr ki bfs kyu lagega, DFS
        kyu ni.
    e. Abhi smjh aa gaya hoga BFS, to aao dekhte hai:
        i. Sabse pehle hume ek queue banani hogi, jisme src node ko dalege.
        ii. But ek ques, kya sirf src node queue me dalne se kaam ho jaega. Socho kitna zada over head
            hoga.
        iii. Abhi overhead isliye, kyuki hamare pas multiple path hai, jo same dst tak pohocha rahe hai
             starting frr=om src node. To humko comparison bhi to karna hoga na, ki konsa path
             cheapest and accordingly k ko bhi decrease krna hoga. 
        iv. Agar tum ye sochoge ki k ko update krke queue, me next node dal do, to bhai k ka state current
            loop me hai jabki next node loop ke next iteration me process hoga.
        v. To isliye queue me hum instead of just src node, ek list dalege, jisme 3 cheeze rkhege:
            1. Current node: Means current node jaha hum hai.
            2. Current price: Abhi tak total kitna price hua hai starting from source.
            3. Remaining stops: ABhi tak kitne stops use kiye hai, ya kitne stops bache hai.
        vi. Isse kya hoga hum ek puri state ko queue me dal rahe hai na ki just node.
        vii. To humne start kiya src node se, jisme humne price 0 dala src node dali, and 
            start me k daal diya.
        viii. Abhi BFS start kiya while(!queue.isEmpty()) se. 
        ix. Abhi sabse pehle hum queue.poll() krke list nikalege. List me vahi src, stops and price rhega.
        x. Abhi check karege ki kya current node dst ke barabar hai, if yes then we will have
            minFare variable, jisme hum min of current price polled from queue and minFare store karege.
        xi. Abhi list me jo src node hai usko use krke map se associated map nikalege, so that
            next nodes par traverse kar sake.
        xii. Abhi next node par traverse krne se pehle check lagaege if(associatedMap is not null). Kyuki
            ho sakta hai ki current node se koi flight hi na ho to null aaege.
        xiii. Abhi map par loop chalege.
        xiv. Abhi loop me sbse pehle check karege, jo current stops hai, usko agar minus 1 karke 0 se kam
            aata hai and current node is not euqal to dst, then skip that path.
        xv. Abhi ek optimization karte hai, ki man lo hamare pas already minFare me koi value hai,
            and current price agar sum me add karne ke bad minFare se zada aata hai, to koi mtlb ni hai
            us path ko explore krne ka, to skip that path as well.
        xvi. Abhi agar dono check pass ho jate hai, to msst current node inside map traversal and 
            current price ko sum me add krke and current stops minus 1 krke queue me as list dal do.
        xvii. Abhi BFS complete hone ke bad check kar lo, if minFare in still MAX, the return -1 else
                return minFare.

Important Note:
    1. Abhi upar jo solution hai vo TLE dega, kyuki usme humne randomly path choose kiya hai.
    2. Ek optimization already laga hai, jisme hum dekhte hai ki if already a min price exists
        then and next path price is greater than minPrice, then skip that path.
    3. But abhi ek optimization scope hai, ki hum randomly price select na krke, vo price
        select kare jo min ho, so that or bhi paths ko skip kar ske.
    4. Uske liye humko priority queue use krna padega. and jisme values store hogi, as per the lesser price.
    5. So change hoga:
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>(
            (List<Integer> a, List<Integer> b) -> a.get(1) - b.get(1)
        );
    6. But ye abhi bhi TLE dega still.
    7. Abhi ek optimization ye hai, ki minFare ko hum check kar rahe hai theek hai.
    8. But man lo humko dusre paths explore krne hai, and same path hum already explore kar chuke hai
        with minimum price, to us path ko vapas explore krna with higher cost doesn't make sense.
    9. To hum ek 2d array bana lete hai:
        int[][] arr = new int[n+1][k+1];
    10. Or isko Integer.MAX_VALUE se initialize kar do.
    11. Abhi before putting the node into queue, we will check ki for this node and stop, 
        did we already have a cheaper price in arr, if yes then skip current path.
    12. Else go ahead with that path and update arr[node][stops] = sum+entry.getValue().
    13. Abhi ek or bat soch, is optimization ke lagane ke bad jo hum minFare check kar rahe the
    14. vaha hum minFare check krne ke bajaye, direct return sum kar skte hai.
    15. Kyuki priority queue me already smallest price pehle aaega and with that smallest price.
    16. humko sbse cheap price hi milega. To humko ans mil gaya direct return kar skte hai.

*/

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap();

        for(int flight[] : flights){
            int u = flight[0];
            int v = flight[1];
            int w = flight[2];

            map.computeIfAbsent(u, a -> new HashMap()).put(v, w);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(
            (int[] a, int[] b) -> a[1] - b[1]
        );

        queue.add(new int[]{src, 0, k});
        int minFare = Integer.MAX_VALUE;

        int dist[][] = new int[n+1][k+1];

        for(int arr[] : dist){
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        while(!queue.isEmpty()){
            int[] list = queue.poll();

            int sum = list[1];
            int stops = list[2];
            int node = list[0];

            // already pq cheapest hi return karega
            // so agar node == dst hua means cheapest path mil chuka hai
            if(node == dst){
                //agar PQ use na karte to ye line needed thi. Reason upar important notes 
                // me likha hai mene point 13-16 me.
                // minFare = Math.min(minFare, sum);
                return sum;
            }

            Map<Integer, Integer> temp = map.getOrDefault(node, Map.of());

            for(Map.Entry<Integer, Integer> entry : temp.entrySet()){
                if(stops-1 < 0 && entry.getKey() != dst) continue;

                // this is making optimal in a way, that if we already have a minFare compared to current
                // then doesn't matter to calculate new one.
                // since hum sum return kar rahe hai this line is not needed now.
                // if(minFare < sum+entry.getValue()) continue;

                // this is making it optimal in a way which it is seeing the cheapest path
                // for that stop and that node.
                // if already a cheap path exists then don't calculate that path.
                if(sum+entry.getValue() < dist[entry.getKey()][stops]){
                    dist[entry.getKey()][stops] = sum+entry.getValue();
                    queue.add(new int[]{entry.getKey(), sum+entry.getValue(), stops-1});
                }
            }
        }

        return -1;
    }
}