/*

You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.

Example 1:

Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.


Intution:

1. Bhai neeche solution bhale bada dikh raha ho, but solution hai simple. Explain zada
    kiya hai isliye intution lengthy lag raha hai par hai ni.
2. Dekho bhai kuch steps hai bas vahi krna hai.
3. Usse pehle ques dekho keh raha hai ki grid me 0 and 1 diye hai. 
4. 1 means land 0 means water. Abhi ques keh raha hai ki kisi ek 0 ko tum 1 bana skte ho
    then us zero ko one bana kar jo island form hoga usme 1 ka count kitna hoga.
5. Abhi Isi ko check karke max 1 count return karna hai, jaha jaha zero hai vaha vaha 1 rakho then
    dekho kitna bada island form ho raha hai.
6. Abhi isko krne ke kuch simple steps hai:
    a. Sabse pehle to tum 0's ke indexes ko store kar lo ek list me and edge case bana do
        ki agar zero vali list ka size 0 hai means sare 1 hai to mtlb water hai hi ni
        kewal island hai us case return kar do grid.length*grid.length. Kyuki utne hi 1 hoge.
    b. Agar zero list == grid.length*grid.length hai means puri grid me 0's hi hai. To fir
        unme se ek ko tum 1 bana skte ho to island ka size hoga 1 to 1 return kar do.
    c. Abhi isko krne ke bad, grid me jitne 1 hai unka union kar do. Abhi cells ke beech ka
        jisme 1 unka union kese krna hai ye ni batauga mai. Bhul gye ho to padho jakr. Tumhri
        glti hai.
    d. 1's ka union krne ke bad, ek bna lo map, jo ki simple ye store karega ki jitne 1's hai
        unke sabke alag alag components ban rakhe hoge.
    e. ABhi tum kahoge alag alag kyu sare 1's ka ek component hoga, to mene kaha ha ho skta hai
        or nahi bhi. For example grid ke top left ke 4 cells me 1 hai and bottom right ke 4
        cells me 1 hai, to ye do component hue, abhi connect hoge ni kyuki beech me zero hoga.
    f. Abhi isi example se grid badi kar do to usme bhi aise cases aaege. 
    g. Abhi aate hai main bat par vapas ki 1 cells ke component ka map bana lo by ek component 
        ka parent and usme kitne number of childs hai including parent.
    h. Isme optimization ye laga skte ho ki jo 0 cells hai unki entry map me mat krna.
    i. Abhi tumne jo zeros vali list banai thi us par lagao loop and  nikalo 0 vale indices
        plus banao ek set and ek size variable assign it from 1. Set and size kyu aage batata hu.
    j. Abhi 0 vali indices se 4 directionally karo traverse, ye kese karege ni batauga. 
    k. Fir 4 dirction ki jo indices banegi ek ek karke loop ke ander overflow check karo and
        agar neighbour cell 0 hai to continue kar do, kyuki zero list me traverse krte krte
        hum curr cell ko hi 1 man rahe hai and at most ek 0 cell ko 1 kar skte hai.
    l. Abhi agar 0 ni hai to 1 hoga, uske parent tumne pehle hi nikal rakha hai, bas uska root
        nikalo using find(row*len+col, parent).
    m. Abhi jab root mil jae, tab check karo ki root jo hai vo set me already exist to ni krta
        ye vahi set hai jo humne zero loop start me banaya tha.
    n. Abhi ye check isliye karna hai kyuki 0 se hum 4 directionally dekhege, agar man lo
        4 me se 2 ya 2 se zada 1's same component me hue to hum utne hi baar us component ke 1 count
        ko size me add krte rhege. Humko unique 1 components ke 1's ke count ko add krna hai.

        aise smjho
                           A1(2)
                      B1(2)  0  B1(3)
                           A1(2)

        abhi upar ke grid ko dekho, beech me 0 hai and do components hai A and B a component me
        two 1's hai and B component me three 1's hai.

        Jab 0 se 4 directionally adjacent 1 ke component milege, to hum make sure karege na
        ki A component ke jo 1's hai ek hi bar count ho, but diff component hai like B uske 
        1's bhi count ho but ek bar. And since 0 bhi 1 ban raha hai to vo ek 1 or add hoga
        . Abhi ye add hoga size variable and and 0 se 1 covert hua to 1 or add hoga, isliye
        size initialize 0 se karvaya tha. 

        to ye banega size+= map.get(root); map simply kitne 1 ke components hai and unme kitna 1 
        count hai ye stored hai usme.
    o. Abhi 4 direction loop ke end ke bad ans = Math.max(ans, size). Kyuki jo component me
        zada 1 hoge 0 ko 1 banane par vo return karna hai.
*/

class Solution {
    public int largestIsland(int[][] grid) {
        int len = grid.length;
        List<int[]> zeroIndexes = new ArrayList();

        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                if(grid[i][j] == 0){
                    zeroIndexes.add(new int[]{i,j});
                }
            }
        }

        if(zeroIndexes.size() == 0) return len*len;

        if(zeroIndexes.size() == len*len) return 1;

        int parent[] = new int[len*len];
        int height[] = new int[len*len];

        for(int i = 0; i < len*len; i++){
            parent[i] = i;
            height[i] = 1;
        }

        int dir[][] = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                if(grid[i][j] == 1){
                    int u = i*len+j;

                    for(int arr[] : dir){
                        int row = i+arr[0];
                        int col = j+arr[1];

                        if(row < 0 || col < 0 || row >= len || col >= len) continue;

                        if(grid[row][col] == 0) continue;

                        int v = row*len+col;

                        int pu = find(u, parent);
                        int pv = find(v, parent);

                        if(pv == pu) continue;

                        union(parent, height, pv, pu);
                    }
                }
            }
        }

        

        int ans = 0;

        Map<Integer, Integer> map = new HashMap();

        for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                if(grid[i][j]==0) continue;
                int root = find(i*len+j, parent);
                map.put(root, map.getOrDefault(root, 0)+1);
            }
        }

        for(int arr[] : zeroIndexes){
            Set<Integer> set = new HashSet();
            int size=1;
            for(int d[] : dir){
                int row = arr[0]+d[0];
                int col = arr[1]+d[1];

                if(row < 0 || col < 0 || row >= len || col >= len) continue;

                if(grid[row][col] == 0) continue;

                int v = row*len+col;

                int root = find(v, parent);

                // agar current 0 vale index se four directionally move krne par
                // 2 ya 2 se zada cells 4 me se same component me hoge
                // to uss component ka size utni bar increase ho jaega isliye ye 
                // check same component aane par sirf ek bar count increase krta hai
                if(set.add(root)){
                    size += map.get(root);
                }

                ans = Math.max(ans, size);
            }
        }

        return ans;
    }

    public void union(int parent[], int height[], int pv, int pu){
        if(height[pv] < height[pu]){
            parent[pv] = pu;
        }else if(height[pu] < height[pv]){
            parent[pu] = pv;
        }else{
            parent[pv] = pu;
            height[pu]++;
        }
    }

    public int find(int x, int parent[]){
        if(x != parent[x]){
            parent[x] = find(parent[x], parent);
        }

        return parent[x];
    }
}