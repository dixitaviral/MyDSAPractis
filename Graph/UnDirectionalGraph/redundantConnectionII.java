/*
In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n), with one additional directed edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [ui, vi] that represents a directed edge connecting nodes ui and vi, where ui is a parent of child vi.

Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

 

Example 1:


Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
Example 2:


Input: edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
Output: [4,1]

Intution:
1. Bhai ye ques redundant connection jesa hi hai but ek extra cheez hai isme.
2. Aao dekhe ques ka puch ra hai:
    a. Sabse pehle to ques keh ra hai ki bhai ek rooted tree tha. Usme na jane ek edge aa gyi jisne 
        tree ko graph bana diya. Abhi vo edge batao konsi hai.
    b. ABhi rooted tree ke sath sath bola ki jo final graph bana after extra edge, vo 
        directed graph hai.
    c. Abhi ques ye bhi bol ra hai ki rooted tree mtlb:
        i. Root node ka koi parent ni hoga.
        ii. And baki sare nodes ka parent ek hi hoga, parent bole to vo vertex jisse hum edge ke through
            dusre vertex se connect krte hai.
3. To bhai, ek extra edge add hone se dikkat ye aai ki:
    a. Tree me cycle ban gyi, to cycle vali edge remove krni banti hai.
    b. Tumne kaha bohot badhiya kar dete hai.
    c. Fir mene bola par bhai ruko humko rooted tree ka bhi dekhna hai, jiska mtlb ye
        ki hum aisi edge hataege ya return karege jisko hatane se agar hum root se tree traverse kare
        to sare nodes visit ho jae and cycle bhi na bane.
    d. Abhi dimag khula, smjhe ye ques tricky hai.
    f. Kyuki abhi ye decide krna hai ki:
        i. Agar cycle vali edge hata dete hai, to do case banege:
            a. Tree rooted ban gaya and sare nodes visit ho gye and cycle bhi ni bani.
            b. Cycle to hat gyi, but tree rooted ni raha koi ek ya multiple nodes unvisited hai abhi.
        for example: 
            edges = [[2,1],[3,1],[4,2],[1,4]]

            iska graph banate hai:

                3-->1<---2   
                    |   /|
                    |   /
                    v  /   
                    4
    g. abhi dekh updar graph me cycle banane vali edge hai 1,4. But agar usko hataya to pura
        tree traverse ni ho paega. Tu check kar le hata kar.
    h. To abhi soh agar hum 2,1 vali edge ko hata de to, is case me cycle bhi hat gayi and
        tree bhi rooted ban gaya means tum abhi 3-1-4-2 is tarah traverse kr skte ho.
    j. Tum keh skte ho aise to 3,1 hata dete, but hata kar dekh lo, tree rooted ni rhega.
    i. To ye scene hai is ques ka.
4. Abhi problem humko smjh aa gyi ki do issue hai:
    a. Ek to cycle vali edge hatani hai.
    b. aisi cycle vali edge hatani hai jisse tree rooted ban jae.
5. Abhi intution dekhne se pehle ek bat or notice karo ki:
    a. Abhi upar vale example me dekho, humko kese pata chala ki 2,1 hatani hai na ki 4,1.
    b. Agar dhyan se dekhoge to 1 vetex ke 2 parent hai. 2 and 3.
    c. to humko ye smjh aata hai isse ki:
        i. Humko pehle aisi edges dhundni hai, jiska same child ho. Means aisi edge
            jo same vertex ko point kar ri ho.
6. Bas abhi intution seedhi hai:
    a. Sabse pehle isme tumko vo edges dhundni hai jo same vertex ko point kar ri ho.
    b. usko e1 and e2 me store kar lo. 
    c. Abhi simle logic hai ki:
        i. Agar e1 and e2 me se koi ek edge ans hai, to koi ek skip krke disjoint set union chala do.
        ii. Mtlb disjoint set chalao, and e2 edge skip kr do. 
        iii. Agar skip krne ke bad bhi cycle mile, to e1 return kar do.
        iv. Abhi aisa bhi ho skta hai ki e1 and e2 initialize na hue ho, kyuki unko aisi edges na mili
            ho jo same vertex ko point kar ri ho.
        v. To in this case humko ye smjh aata hai ki do parent vali edge ni hai, and still cycle mili.
            To return that current edge, instead of e1 and e2.
        vi. abhi agar ye disjoint set union pura chal jae and still koi cycle na mile that means e2 skip
            kiya tha and that is culprit so return e2.
*/  


class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int parent[] = new int[edges.length+1];

        int ans[] = new int[2];

        Map<Integer, Integer> map = new HashMap();

        for(int i = 0; i < edges.length+1; i++){
            parent[i] = i;
        }

        int e1[] = new int[2];
        int e2[] = new int[2];

        for(int edge[]: edges){
            int u = edge[0];
            int v = edge[1];

            if(map.containsKey(v)){
                e1[0] = map.get(v);
                e1[1] = v;
                e2 = edge;
            }else{
                map.put(v, u);
            }
        }

        for(int edge[] : edges){
            
            if(!Arrays.equals(edge,e2)){
                int u = edge[0];
                int v = edge[1];

                int pu = find(u, parent);
                int pv = find(v, parent);

                if(pu == pv){
                    if(e1[0] != 0) return e1;
                    return edge;// agar e1 and e2 0,0 hi rhe matlb ek vertices ke do parent ni hai is case me bhi agar cycle aa ri hai to edge return hogi.
                                // and e1[0] != 0 true hai iska mtlb humko ek vertice mili thi jiske do parents the, abhi do me e2 humne consider ni kiya
                                // to e1 edge gadbad kar ri hai. to vo return kar do
                }else{
                    parent[pv] = pu;
                }
            }
        }

        return e2;
    }

    public int find(int x, int[] parent){
        while(x != parent[x])   
            x = parent[x];
        return x;
    }

}