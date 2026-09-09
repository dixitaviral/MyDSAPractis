/*
Given an undirected graph with V vertices and E edges. The graph is represented as a 2D array edges[][], where each element edges[i] = [u, v] indicates an undirected edge between vertices u and v. Return all the articulation points (or cut vertices) in the graph. An articulation point is a vertex whose removal, along with all its connected edges, increases the number of connected components in the graph. 

The given graph may be disconnected, i.e., it may consist of more than one connected component. 
If no such point exists, return {-1}.
Examples :

Input: V = 5, edges[][] = [[0, 1], [1, 4], [4, 3], [4, 2], [2, 3]]

Output: [1, 4]
Explanation: Removing the vertex 1 or 4 will disconnects the graph as-
   
Input: V = 4, edges[][] = [[0, 1], [0, 2]]
Output: [0]
Explanation: Removing the vertex 0 will increase the number of disconnected components to 3.


Intution:
1. Bhai ye ques me humko articulation point dhundne hai. Articulation point basically vo nodes hote hai
    jinko hatane se graph do ya do components me toot jata hai.
2. Abhi isko simple hum tarjan's algorithm se karege. Tarjan's algorithm mai explain ni karuga.
    agar bhul gye ho to isi folder me tarjan's algorithm ki md file hai and criticalConnections.java file me bhi 
    as intution add kari hai.
3. As intution is this ques hum sirf ye dekhege ki tarjan's algorithm me kya tweaks kare to bridges se shift hokar hum
    articulation point finding par shift ho jae.
4. To bhai dekho bridges ki condition kya thi ki agar time[cur] < lowestTime[nbr] then it's a bridge.
5. Abhi socho zara point 4 ki condition true ho jae to puri cur, nbr edge hat jati thi. To is case me agar hum cur ko
    hata de to kya graph do parts me divide ho jaega. Haa right. to bas this is tweak 1.
6. abhi tweak 2 dekhne se pehle ek followup ques dekh lete hai jo tumhre man me aaya hoga -> ki cur hi kyu articulation
    point hai, nbr bhi to ho skta hai to nbr kyu ni liya, ya dono me se koi lelo frk ni pdta.
    a. Iska ans do teen cheeze smjh kar deta hu. Sabse pehle comparison dekh time[cur] < lowTime[nbr];
    b. Abhi soch agar time[cur] > lowTime[nbr] hota that means koi or node hai cur node se pehle, jisse
        nbr reachable hai. To definetly ye articulation point ni hai. Yaha tak agree? Okay next point.
    c. Abhi lowTime[nbr] time[cur] se bada hai, that means nbr sirf cur se reachable hai. Right? okay
    d. Abhi tujhe yahi break karna hai na ki cur se upar jo graph hai, vo via cur ke nbr se connected hai. Right?
    e. To mere bhai curr ko articulation point manege na, nbr ko kyu manege.
    f. Practically soch, to chalta hua aa ra hai ek point par aaya jaha se aage rasta usi point se hokr jaega.
    g. To agar tu vo point hata de lag gaye L, ab tu us point ke aage ni ja skta kyuki tune point hi hata diya.
7. Chal abhi dusra tweak dekhte hai:
    a. To dekh dikkat hai is condition me time[cur] < lowTime[nbr]. Abhi dar mat, time cur lowtime nbr < ye sab theek hai.
    b. Kuch extra add krna hai. But direct ni batauga, problem dikhegi tujhe to apne aap smjh jaega ki kya missing hai.
    c. Ek DFS picture dekh:
             u
            / \
          v   ...
         /
        x

        Maan le:

        time[u] = 2
        lowTime[v]  = 2

        Ab lowTime[v] == time[u].

        Iska matlab ye zaroori nahi ki v → u wahi edge hai jisse hum aaye the.

    d. low[v] ka matlab hai: v ki poori subtree kisi aise ancestor tak pahunch sakti hai jiski discovery time 2 hai. Yaani v, u 
       tak pahunch sakta hai.

    e. Ab ek subtle distinction 👇

        Agar:

        lowTime[v] > time[u]

        toh subtree v u ke upar nahi ja sakti → u-v bridge.

    f. Lekin: lowTime[v] == time[u] toh subtree v u tak pahunch sakti hai. Isliye u-v bridge nahi hai. Lekin...

    g. u ko hata diya toh v ki subtree ka connection u ke through hi tha → therefore u articulation point ho sakta hai.
    h. to abhi condition kya hoti time[cur] <= lowTime[nbr].

8. Now 3 tweak dekhte hai :
    a. Tweak 3 simply kehta hai bhai ki agar koi aisa graph aa jae like below:
        1-2-3. Abhi isme Articulation point hai 2. But isko tum general rule se karke dekho
        time[cur] <= low[nbr] to time and low array aise banege:

        time = [1,2,3] low = [1,2,3].

        abhi ye condition check hogi time[2] <= low[3] true. 2 is Articulation point(AP);

        now 2 se vapas jaega dfs condition aaegi time[1] <= low[2] bhai true, but 1 to AP hai ni. Exactly.
    b. Tweak 3 yahi keh raha hai ki aise DFS root jinka sirf 1 child ho uss case me vo root kabhi bhi AP ni hoga.
    c. To ye rule lagate hai and for loop ke ander DFS call karne se pehle root ko set krte hai.
    d. To condition bani: if(cur == root) continue. Par bhai ruko ek min agar koi aisa graph aaya jiske root ke
        2 ya 2 se zada child hai. Tab to vo root AP hua.

    e. To ye bhi condition laga dete hai, but usse pehle humko dfs ke ander rootChild maintain krna pdega,
        vo aise hi agar prev == root hai to rootChildCount++; Then condition banegi
        if(cur == root && rootChildCount < 2) continue;
    f. Ye hi tweak tha, but socho isko hum optimal kar skte hai. Kese aao dekhe. Dekho bhai jis root ke 
        1 se zada child hoge vo root obvious AP hoga, to uske liye hum is condition me kyu jae
        ye vali if(time[cur] <= low[nbr]). Instead hamare pas child count hai root bhi pata hai.
    g. To hum condition tweak kar skte hai if(cur == root){if(rootChildCount >= 2) list.add(cur or root)}; and
        rest nodes else if me jaegi else if(time[cur] <= low[cur]).
    h. Bas yhi tha tweak 3.
9.  Abhi kuch extra cheeze hai dekh lete hai. Dekho bhai kabhi aisa bhi ho skta hai ki um ek node ko do ya usse zada bar
    ans list me add kar doge. like 1 ke do child hai 2 and 3. So 2 se vapas aae 1 AP and then 3 par se vapas aae
    again 1 AP.
10. So iske liye ya to set maintain kar lo, usme add karo but unnecesaary calls still hogi. To better approach hai ki 
    ek boolean array bana lo isAP V size ka and jo bhi node AP mile us index par isAP ko true kar do.
11. So that agar dobara same node ke liye aae ki ye AP hai to array check laga do ki already AP bana chuke hai no new entry.
12. Bas yahi hai pura articulation point Algorithm.

*/

class Solution {
    
    static int counter = 0;
    static int rootChildCount = 0;
    static int root = 0;
    static ArrayList<Integer> articulationPoints(int V, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap();
        ArrayList<Integer> list = new ArrayList();
        int time[] = new int[V];
        int lowTime[] = new int[V];
        
        Arrays.fill(time, -1);
        Arrays.fill(lowTime, Integer.MAX_VALUE);
        
        for(int arr[] : edges){
            map.computeIfAbsent(arr[0], k -> new ArrayList()).add(arr[1]);
            map.computeIfAbsent(arr[1], k -> new ArrayList()).add(arr[0]);
        }
        
        boolean isAP[] = new boolean[V];
        for(int i = 0; i < V; i++){
            if(time[i] == -1){
                root = i;
                rootChildCount = 0;
                dfs(-1, i, map, list, time, lowTime, root, isAP);
            }
                
        }
        
        return list.size() == 0 ? new ArrayList<Integer>(List.of(-1)) : list;
    }
    
    static void dfs(int prev, int cur, Map<Integer, List<Integer>> map, ArrayList<Integer> list,
                    int time[], int lowTime[], int root, boolean isAP[]){
        
        counter++;
        time[cur] = counter;
        lowTime[cur] = counter;
        
        if(prev == root){
            rootChildCount++;
        }
        
        
        for(int node : map.getOrDefault(cur, List.of())){
            if(prev == node) continue;
            if(time[node] == -1){
                dfs(cur, node, map, list, time, lowTime, root, isAP);
            
                lowTime[cur] = Math.min(lowTime[cur], lowTime[node]);
                
                if(!isAP[cur]){
                    if(cur == root){
                        if(rootChildCount >= 2){
                            list.add(cur);
                            isAP[cur] = true;
                        }
                            
                    } else if(lowTime[node] >= time[cur]){
                        list.add(cur);
                        isAP[cur] = true;
                    }
                }
            }else{
                lowTime[cur] = Math.min(time[node], lowTime[cur]);
            }
            
        }
        
    }
}