/*
On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.

A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.

Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.

 

Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Explanation: One way to remove 5 stones is as follows:
1. Remove stone [2,2] because it shares the same row as [2,1].
2. Remove stone [2,1] because it shares the same column as [0,1].
3. Remove stone [1,2] because it shares the same row as [1,0].
4. Remove stone [1,0] because it shares the same column as [0,0].
5. Remove stone [0,1] because it shares the same row as [0,0].
Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Explanation: One way to make 3 moves is as follows:
1. Remove stone [2,2] because it shares the same row as [2,0].
2. Remove stone [2,0] because it shares the same column as [0,0].
3. Remove stone [0,2] because it shares the same row as [0,0].
Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
Example 3:

Input: stones = [[0,0]]
Output: 0
Explanation: [0,0] is the only stone on the plane, so you cannot remove it.

Intutuion:
1. Bhai ye ques DSU ka hai simple hai.
2. Ques keh raha hai ki stones rakhi hai ek grid me.
3. Abhi agar 1 stone kisi row ya column me rakhi hai, then uss row ya column me dusri stone
    ni rakh skte.
4. To humko ques me batana hai ki kitni stones rakh kar kitni hata doge.
5. Abhi iski trick simple hai:
    a. Sabse pehle sare cordinates diye hai unka DSU kar do.
        i. tricky part hai ye karna.
        ii. Tum jab for loop likhoge for union like this:
            for(int edge[] : stones){
                int u = edge[0];
                int v = edge[1];
            }
        iii. To fir u and v to ek hi cell ke do cordinates hai, mtlb homko to 
            ek pure cell jisme stone rakhi hai usko dusre cell se connect krna hai na ki cordinates 
            ko.
        iv. Dekho baat to si hai, isme kya pata tum socho ki ek cordinate start variable me le lete hai
            and then 1 se start krte hai then connected krte chalte hai.
        v. But vo pata ni kese hoga and hoga bhi to bohot lengthy hoga.
        vi. Aao ek easy tareeka batata hu:
            a. Dekho bhai 0,0 0,1 1,0 1,1 ye sare cordinates ek hi component me hai.
            b. Isko karoge kese, jab tum ye karoge :
                for(int edge[] : stones){
                    int u = edge[0];
                    int v = edge[1];
                }
                Iss time par agar hum u and v ka union kar de to kya hoga aao dekhe.
            c. Usse pehle parent array hum bana lege by taking out maxRow and maxCol from stones 
                array.
            d. then hum parent and height array banaege len =  maxCol+1+maxRow+1 size ka.
            e. Then for loop chala dege till len and parent[i] = i and height[i] = 1;
            f. Abhi aisa kyu kar rahe, uska reason hai ki bhai DSU me hum parent height array 
                maintain krte hai ye to pata hai.
            g. To iss problem ko solve krne ke liye and simple banane ke liye hum row and col 
                ko milakar ek array bana lete hai row cells hoge 0 to row and col hoga row+1 to 
                parent.length-1 and same for height.
            h. Abhi vapas aate hai apne concept par, hum basically cordinates ka union karege
                like if cordinate is 0,0 then 0 parent is 0, then 0,1 then 1 parent is 0
                and 1,0, we already have 0 as both 1,0 and last 1,1 in which 1 already has 0 parent
            i. Issme hum same row and same col me jo bhi stones rakhe hoge, unka union find kar lege
            j. Abhi pura union find ni smjha ra mai, vo already mene isme DisjointSetUnion krke
                md file hai usme smjhaya hai.
        vii. Abhi simple hai:
            a. Since jo parent array hoga usme information hogi ki kitne connected components hai
                but usme kuch cells aise bhi hoge jinke parent vo khud hoge
                jiska mtlb hua, ko un par stones rakhi hi ni hai
            b. Abhi agar hum traditionally component find karege parent array me to fir ye unsed cells
                bhi ek parent me count ho jaege jo ki galat hai.
            c. So for this ques better approach is while doing union before that create a set and then
                during union in that set add the cordinates which comes in stone array.
            d. Now at last write a loop on that set and check if the used set numbers are parent
                of their own. 
            e. mtlb jitne aise numbers mile in the set jo ki khud ke parent hai, means vo hi kisi dusre
                ke parent hai.
            f. Bas aise koi number mile to count++;
        viii. Abhi at last stones.length-count. Iska mtlb hua ki jitne connected compoennt bane usme 
                se sirf ek stone rakhi jaega rest remove kar dege.
        ix. Bas khtm yahi hai solution

*/

class Solution {
    // sare connected components bana lo, and number of connected components return kar do. 
    // that's the answer.
    // mtlb jo stones same row or col me aa rahe hai, vo ek connected component
    // form karege. And ques keh ra bhai same row ya col me koi or stone rakhi hai
    // to ni rakh skte ho. To bas smjh lo
    public int removeStones(int[][] stones) {
        int maxRow = 0;
        int maxCol = 0;

        for(int edge[] : stones){
            int u = edge[0];
            int v = edge[1];

            maxRow = Math.max(u, maxRow);
            maxCol = Math.max(v, maxCol);
        }

        int len = maxRow+maxCol+2;

        int parent[] = new int[len];
        int height[] = new int[len];

        for(int i = 0; i < len; i++){
            parent[i] = i;
            height[i] = 1;
        }

        Set<Integer> set = new HashSet();
        
        // kuch line me smjhata hu, or ye padh kar na smjh aae to mtlb man shant ni hai
        // shant kar fir smjh
        // dekh humko stones ko apas me connect ni krna
        // balki humko co-ordinates ko same connected components me dalta hai
        // man lo 0,1 aaya abhi tumne 0 ka prent 0 ko bana diya abho col 1 hai
        // to overflow hokr bana 1+row+1 to man lo 3 ban gaya to 3 ka parent bhi bana 0
        // abhi dusra cordinate aaya 1,1. Now since 1 ka parent 1 hai, but 1+row+1 means 3 
        // ka parent 0 tha to 1 ka parent bhi 0 ban gaya. Bas ye smjho ki to u and v ko same
        // connected component me dalna hai and overflow maintain krna hai 
        for(int edge[] : stones){
            int u = edge[0];
            int v = edge[1];

            int pu = find(u, parent);
            int pv = find(v+maxRow+1, parent);

            if(pu == pv) continue;

            // since stones ke cordinates to bohot sare ho skte hai
            // like last stone 2,2 par rakhi but agar 2,1 cell unused hai
            // and tum usko bhi us karoge to vo bhi connected component me count hoga
            // so set me sirf vo row and col add karo jo use ho re hai
            // and since col overflow ke sath store ho ra hai to usko vese hi store karo
            set.add(u);
            set.add(v+maxRow+1);

            if(height[pv] < height[pu]){
                parent[pv] = pu;
            }else if(height[pu] < height[pv]){
                parent[pu] = pv;
            }else{
                parent[pv] = pu;
                height[pu]++;
            }
        }
        int comp = 0;

        // ye component count krne ka tareeka hai
        // jisme unused cells ek component ki tarah count na ho
        // so jo bhi used set me numbers pade hai, humko dekhna hai
        // vo agar kisi node ka parent vo khud hai to count that as component
        // kyuki connected component ka mtlb hota hai ek parent hoga multiple nodes ka
        // agar vo self parent hai to count that as node and if vo self parent ni hai iska mtlb
        // uska parent koi or hai.

        // or vese bhi jab hum parent dhundne jate hai to stop vahi lagate hai na jab x == parent[x]
        // hota hai same ye bhi kar raha hai
        for(int node : set){
            if(find(node, parent) == node){
                comp++;
            }
        }

        // ye immp hai, bhai jitne number of connected component hai 
        // unko tum ek stone man lo. to fir total stones me se jitni stone
        // allow ki vo hi num of compoentn hai.

        // aise smjho ki stones given as 7 and 7 stones ek connected comp
        // bana deti hai. to iske ques ke hisaab se 7 me se sirf ek stone hi rakh skte hai
        // rest we need to remove right?
        return stones.length-comp;
    }

    public int find(int x, int parent[]){
        if(x != parent[x]){
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }
}