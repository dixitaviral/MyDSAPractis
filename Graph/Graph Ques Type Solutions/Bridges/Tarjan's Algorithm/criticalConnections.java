/*

There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

Return all critical connections in the network in any order.

 

Example 1:


Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.
Example 2:

Input: n = 2, connections = [[0,1]]
Output: [[0,1]]

Intution:

1. Bhai dekho ques keh ra hai graph me bridges dhundo.
2. Bridges kya hota hai aao dekhe:
    a. Bridge basically vo edges hoti hai graph me jinko agar hata do, to graph do alag subgraphs me
        divide ho jae.
    b. Unhi sari edges ko return krna hai answer me.
3. Abhi iska logic chupa hai tarjan's algo me, to aao dekhe kya hai tarjans algorithm:
    a. Tarjans algorithm ke do components hai vahi main hai baki dfs hai. And vo do main components hai:
        i. time array: ye basiacally n size ka hota hai.
            i. jitni nodes hogi utne size ka hoga, initialize tum -1 se kar do jisse visited ki tarah bhi
                use ho jae.
            ii. Abhi ye array basically har node ka time store karta hai ya step keh lo, ki kis time/step
                par hum kisi node par pohoche hai. starting from 1 to n.
        ii. lowestTime array: ye array bhi n size ka hoga and isko tum aise smjho ki ye track rakhta hai
                ki agar tum kisi node ke through nbr node par gye and vo nbr node kisi aise node se bhi reachable hai
                jo ki uss node pehle aaya hai jisse tum curr node par aae ho.

            i. Example ke liye smjho graph hai 6
                                              / \
                                             8---9 
                Abhi upar vale graph me tum 6 to 8 and 8 to 9 gye then 9 ke adjacent 8 and 6 hai
                to 9 8 par vapas ni aaega kyuki abhi usi edge ko use krke 8 to 9 aae hai. 
                to 9 6 par jaega. Abhi 6 jo hai vo 8 se pehle aaya. to 9 apne lowestTime array
                index par min(9,6) dal dega. Jo ki ye mtlb nikal raha hai, agar 8-9 vali edge hat bhi jae
                to 9 via 6 8 tak ja skta hai.
            ii. Abhi ek ques hoga why min (9,6). Vo isliye kyuki kabhi kabhi aisi condition bhi aaegi
                ki 9 aisi node par jaega to 8 se pehle ni aati to 9 ka lowest 9 hi rakhna chahiye.
    b. Abhi tumko do component smjh aae hoga acche se. Abhi smjhte hai isko implement kese karege:
        i. Sabse pehle to hum upar do array hai vo bana lege. time array ko hum as visited bhi use karege to
            usko -1 se initialize kar dege. And lowestTime me hum low node store karege to uske liye int max
            se usko initialize kar dege,
        ii. Abhi graph se adjacency map bana lo. 
        iii. abhi simple dfs chala do. But main baat hai dfs ke parameters kya rhege and base condition kya hogi
            aao dekhe.
        iv. Sabse pehle hum logic dekh le ki kese bridges find hoge. Abhi humne sirf arrays dekhe hai usko use krna 
            kese hai ye ni pata.
        v. To hum start aise karege ki 0 node se dfs start kar dege. 
        vi. But main logic kya hoga bridge find krne ka aao dekhe:
            a. Dekho bhai humne kaha tha ki ek time array lege, to hum ek global timeCount variable le lege and usko
                0 se assign kar dege.
            b. Abhi jab dfs call hogi tab tab ye counter badhega curr node index par time array me and lowestTime array me timeCounter ki entry karega
                ki graph hai 1-2-3-4 to entry hogi 1,2,3,4. Basically ye hume bata ra itne steps me hum kisi node tak pohoche and firs time
                itne hi lowest steps lene padege to lowestTime array bhi utne hi value se update hoga.
            c. Abhi lowestTime array kese fill hoga. Vo fill hoga visited base condition hit hogi. 
            d. Abhi dekho upar jo humne example liya tha 6-8-9-6 isme path follow hua 6-8-9-6 to jab 9 ke bad 6 aaya to 9 ko
                pata chala ki yrr mai 6 ke through bhi 8 ko reach kar skta hu. To uss time par 9 ka time array me entry thi
                9 and 6 ki thi 6. To 9 ne apna lowest update kar diya ki bhai mai 6 ke through bhi reachable hu and 6 ke through 8 par
                ja skta hu.
            e. But visited condition kya hogi aai dekhe:
                1. Sabse pehli to easy hai ki hume visited check krna hai via time array if time[curr] != -1.
                2. Abhi iske ander humne jese dekha ki lowestTime array update case aa skta hai, to uski condition bhi check hogi.
                3. Abhi uski condition yahi thi na ki humko agar next node visited milti hai to hum prev node ke time se
                    current node ke time ko compare krte hai.
                4. Jiska mtlb hua humko dfs me ek prev variable bhi pass krna hoga jo bataega ki ye prev node thi.
                5. to jab 9 se hum 6 par gye to prev node pass hogi 9 and cur node pass hogi 6.
                6. To condition bani if(time[curr] < time[prev]) then lowestTime[prev] = min(time[prev], time[cur]);
                7. Abhi humne dekha hi tha ki min kyu lagate hai ki koi condition aisi aa jae jisme cur node ka time zada ho
                    to hum existing lowestTime hi rkhege.
                8. Then simple return kar dege 
            f. But 6 already visited hai to vapas 6 par hum dfs ni lagaege. To lowestArray entry update aise hogi.
            g. Abhi bat krte hai first time node aaya then time and low array me entry hui, then uske nbr nodes traverse karege.
            h. Par usse pehle ye socho ki upar vale graph ka example lete hai vapas ki 9 8 se pohoche hum. Abhi 9 se do raste hai
                9 to 8 and 9 to 6. Par tum sirf visited check kar ke agar lowestTime update karoge to is hisaab se 9 ka lowestTime
                8 bhi ho skta hai. 
            i. Jo ki galat ho jaega na kyuki same edge se hum aae the tumne usi ko vapas count kar diya, but humko to koi dusra raste
                dekhna hai jo ki 9 par aae.
            j. Isse bachne ke liye hum traversa krte time check lagaege if(prev == nbr) continue; Abhi man lo dfs(9) chal ra hai
                prev hoga 8 isme and 9 ke nbr node hue 8 and 6. Abhi vahi condition aa gyi na if prev == nbr to conitnue kar do.
            k. Abhi iske bad hum dfs(nbr, cur) call kar dege jisme nbr adjacent node jogi and curr jo parent node hogi. 
            l. Abhi visited hoga to lowestTime update hokr return aaega or nahi hoga visited to aage continue karega.
            m. dfs call ke bad ek or cheez krni hai hume. Dekho bhai 9 ko pata chala ki mai 6 se reachable hu. To ye bat 
                prev node ko bhi to batani chahiye ki bhai tum bhi reachable hoge 6 se.
            n. Uske liye hum curr parent node me bhi ye karege lowestTime[cur] = min(lowestTime[cur], lowestTime[nbr]);
            o. Abhi iske bad bridge check condition lagegi:
                i. Bridge check condition badi simple hai and practically bola jae to agar nbr node cur node ke alava kisi
                    or node se reachable ni hai to vo edge hui bridge.
                ii. Jiska mtlb hua ki time[cur] < lowestTime[nbr] to bridge hai and add both nodes as a edge in result.
                iii. Abhi tum kahoge ki bhai time pf curr ko lowestTime of nbr se kyu compare kiya, kayde se lowestTime ko
                    dono ke compare krna tha.
                iv. Dekho bhai vo isliye kyuki iss check se pehle humne lowestime ko update kiya hai. To valie galat aa skti hai.
            p. To bhai yahi thi puri kahani.

            Important point ye hai ki:
                1. dfs call jo main method se tum karegoge, to n tak loop chala kar krna and if koi node already visited hai to skip kar dena.
                2. Aisa isliye ki agar disconnected graph diya hai ki n = 5 jisme 0,1,2 ek graph bana rahe and 3,4 alag graph bana rahe.
                3. To isse ye dono subgraph caluclate ho jaege.
            
*/


class Solution {
    int timeCount = 0;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Map<Integer, List<Integer>> map = new HashMap();

        for(List<Integer> arr : connections){
            map.computeIfAbsent(arr.get(0), k -> new ArrayList()).add(arr.get(1));
            map.computeIfAbsent(arr.get(1), k -> new ArrayList()).add(arr.get(0));
        }

        List<List<Integer>> res = new ArrayList();

        int time[] = new int[n];
        int lowestTime[] = new int[n];

        Arrays.fill(time, -1);
        Arrays.fill(lowestTime, Integer.MAX_VALUE);

        dfs(-1, 0, time, lowestTime, map, res);

        return res;
    }

    public void dfs(int prev, int cur, int time[], int lowestTime[], 
                    Map<Integer, List<Integer>> map, List<List<Integer>> list){

        if(time[cur] != -1){
            if(time[cur] < time[prev]){
                // Math.min isliye use kar rahe hain kyunki lowestTime[prev]
                // pehle se kisi aur back edge ki wajah se chhota ho sakta hai.
                // Agar ab hume ek aur visited ancestor mile jiska discovery
                // time bada hai, to purani (smaller) value lose nahi honi chahiye.
                lowestTime[prev] = Math.min(time[cur], lowestTime[prev]);
            }

            return;
        }

        timeCount++;

        time[cur] = timeCount;
        lowestTime[cur] = timeCount;

        for(int node : map.getOrDefault(cur, List.of())){

            // ye check basically ye keh ra hai ki agar 0 --- 1 ek edge hai
            // jo ki bidirectional hai. Abhi 1 ke bad koi edge ni hai to 
            // 1 ke adjacent 0 hi hai. But humne 0 pehle traverse kar liya to 
            // vapas kyu aae. Concept ye hai ki jis edge se tum aae ho
            // uss edge ko direct vapas ni jana hai kisi node ke through chale
            // jao usme bhi return aana hai but lowestTime value ke sath.

            // Fir tumhra ques ho sakta hai
            // 1. Ki why not cur == node and why prev == node.
            // vo isliye ki sabse pehle dfs(-1, 0) chala then chala (0, 1) then
            // (1,0) abhi is flow me prev hoga 0 cur hoga 1 and vapas node hoga 0
            // abhi tumko dekho or batao ki node == prev hoga ya node == cur hoga
            if(node == prev) continue;
            dfs(cur, node, time, lowestTime, map, list);

            // ye aise smjho
            /*
                            6
                           / \
                          7   9
                         /    |
                        8-----

            1. Abhi agar upar vala graph dekho and path hai 6-7-8-9. Abhi 9 jo hai
            vo ya to 8 par aaega ya 6 par. 
            2. Since 8 par aa ni skta kyuki same edge se vo 9 par aaya hai to 6 par jaega. 
            3. Abhi 6 already visited but 6 ka lowestTime 6 hai and 9 ka 9. To ye upar base 
                condition me jakr 9 ka lowest time kar dega 6, jiska mtlb ye hua ki bhai
                9 keh ra hai 6 ke through bhi mai mil jauga and vo bhi 8 se pehle
            4. Ab aati hai neeche vali line ki bat, neeche vali line kehti hai ki 9 6 ke 
                pas jakr vapas 6 lowestTime lekr aaega, and 8 ko bataega ki bhai hum dono
                6 se reachable hai. To hamare beech ki edge bridge ni hui
            5. Kyuki hata bhi doge tab bhi 9 6 ke through 7 and then 8 par aa jaega.
            6. Abhi same 7 ke sath hoga ki 8 bataega bhai 9 keh ra hai ki vo 6 se reachable hai
                agar vo hai to mai bhi hu and mai hu to tum bhi ho.
             */
            lowestTime[cur] = Math.min(lowestTime[cur], lowestTime[node]);

            // abhi ye sabse simple but thori tricky kahani
            /*
            1. Keh ra hai bhai ki agar upar vale graph me hum ek edge add kar de
                                   5-----6
                                        / \
                                       7   9
                                      /    |
                                     8-----

            2. is moment par 6 ke pas hai lowest time 6 mtlb ki bhai sabse kam time
                me bhi 6 tak pohochne ke liye 6 par seedha aana pdega, koi or rasta ni hai
            
            3. But usse pehle comaparison to 8 and 9 ka bhi hua hoga to vaha bridge kyu ni mila aisa kya
                compare hua.
            4. Dekho bhai lowestTime dono ka compare ni kar skte, kyu? Kyuki dono ka lowest time hua hai
                change. To values galat aaegi.
            5. To abhi dono ka time bhi compare ni kar skte jo ki hoga 8 < 9 true and bridge condition 
                ho jaegi joki real me hai ni.
            6. To humko karna hai  time of curr < lowestTime. Iska maltb ye nikal ra hai ki:
                a. Agar jo nbr node hai vo ya to apne khud se ya kisi or node se reachable hai
                    vo value hold karega lowest.
                b. Abhi vo lowest value agar curr node ki time value se badi hai mtlb 
                    agar upar 9 to 6 path na hokr 9 to 10 path hota to 9 ki lowest value 9 hi rehti
                    ya aagr chal kar 9 10 11 9 ye path aata to 9 ki lowest value 11 ho jati ya min
                    store krte hai upar to 9 hi rehti.
                c. Still 9 hamesha 8 ke bad hi aaega ya 11 hoti tab bhi 8 ke bad hi 11 aata. That means 
                    ki 8 se pehle aisi koi node ni hai jo 9 tak ja ri ho, jisse agar
                    8 to 9 vali edge hata bhi de to hamare pas us prior node ke through
                    8 to 9 jane ka rasta ho.
            */
            if(time[cur] < lowestTime[node]){
                list.add(List.of(cur, node));
            }
        }
    }
}