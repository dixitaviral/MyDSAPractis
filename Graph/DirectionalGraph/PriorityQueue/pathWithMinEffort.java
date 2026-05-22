/*
    You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

 

Example 1:



Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
Example 2:



Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
Output: 1
Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
Example 3:


Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
Output: 0
Explanation: This route does not require any effort.

Intution:
1. Bhai ques ye seedha hi hai, but baat brute force and optimal solution ki hai.
2. Chalo Brute Force dekhte hai, usse pehle ques dekho kya keh ra hai:
    a. Keh ra hai ki matrix me tumko 0,0 se height.length-1, height[0].length-1 tak ke sare path dhundne hai
    b. Abhi matrix me kuch number diye hai jo us cell ki height bata rahe hai.
    c. To jab sare path traverse karoge by going up down left and right, to har path me ek 
       jump hogi from one cell to other jo ki maximun jump hogi us path ki.
    d. Jump nikalni kese hai: jese hum gye from 0,0 to 0,1. 
        to jump = Math.abs(height[0][0]-height[0][1]).
    e. Abhi jitne path hoge, unki sabki max jump nikalo, then min of all path's jump return kar do.
    f. Yahi karna hai.
3. Abhi brute force solution dekhte hai:
    a. simple hai tumko DFS chalana hai isme and sare paths ki max jump nikalni hai.
    b. Abhi ques keh ra hai ki tum char direction me ja skte ho, to mtlb ye hua ki humko char max jump
        milegi.
    c. to abhi krege isko aise ki seedha ek dfs call karege, jisme pass karege:
        1. heights matrix
        2. visited array
        3. start row index that will be 0
        4. start col index that will be 0
        5. ek maxEffort var pass karege jo har path ka max lakr dega 
        6. or current cell ki height as prev height.
    d. Abhi bat krte DFS ki:
        i. since hum char direction me move karege, conditions aaegi ki array se bahr ke index
            ban jae, like 0,0 hai but -1,0 aa gaya jo ki invalid hai. Same right up and down ke
            liye bhi hoga.
        ii. to hum restrict kar dege i < 0 || j < 0 || i >= height.length || j >= height[0].length.
        iii. Is case me return karege Integer.MAX_VALUE, kyuki ye path to invalid ho jaega na fir.
        iv. Since hum last me min of all path's max jump nikalege, to invalid path ko aise hi
            remove krege integer max value return krke.
        v. After this visited condition lagegi, if cell visited then return integer max value.
        vi. then maxEffort count karege by Math.max(maxEffort, Math.abs(prev-height[i][j])).
        vii. then again base condition ki agar hum bottom right cell pohoch gye hai to return maxEffort.
        viii. mark cell visited.
        ix. Do the four direction call, ye ni explain karuga, you know already how to do this.
        x. Abhi iske bad, tumko prev = 0 and visited[i][j] = 0 karna pdega, as we do in backtracking.
        xi. Aisa isliye kyuki hum current path me cycle na mile aisa chahte hai, to visited ko locally 
            use krna hai, globally ni.
        xii. Means jab dusri direction ka path follow kare and same cell aa jae jo prev path me aaya tha
            hume usko traverse krna hai is new path me but old path me same index dobara traverse ni karna.
        xiii. Same with prev usko bhi = 0;
        xiv. Abhi simple return Math.min(Math.min(p1,p2), Math.min(p3,p4));
        xv. Ho gaya brute force.


Aao dekhte hai kya optimization kar skte hai abhi, as above intution only clear 8 test cases out of 76.

1. Medium Optimized DFS solution with memoization but still TLE
    a. is optimization me hum simple ek matrix array add kar dege.
    b. Usko initialize kar dege Integer.MAX_VALUE se.
    c. 0,0 ko 0 hi rkhege.
    d. Abhi intution kya rhegi aao dekhe:
        i. Dekh bhai, matrix traverse krte time, multiple paths aate hai, jisme same node multiple
            times traverse hoti hai.
        ii. Abhi ek bat soch, agar let's say cell hai 2,1 ye path p1 and p2 dono me aaya
        iii. Abhi p1 path me humko already min jump to that cell mil chuki hai, and p2 me jump 
            badi aa ri hai current se. To ye path hum skip kar skte hai.
        iv. Abhi ques hoga bhai hum ek path ki max jump nikal rahe hai then all path min jump.
        v. To mai kahuga ha bhai si to hai, dekho p1 ne 2,1 tak pohochne me jump lagai 3 whi p2 
            laga ra hai 5. To hum compare to alag paths ki jump ko hi kar re hai na to min jump 
            hi karege calculate.
        vi. to simple hai, agar humko current cell ki maxEffort se bada koi effort mila, hum 
            Integer.MAX_VALUE return kare dege.
        vii. ye ek ni base condition ho gyi.
        viii. then after marking visited, hum max effort matrix ko fill karege dege maxEffort Value.
        ix. Abhi isko undo ni karna hai as visited kyuki ye globally use hoga naki inside a path.
    e. Isse hum 46 test cases pass kar jate hai out of 76. But TLE still aati hai.

2. // Optimal Solution with PQ and memoization
    a. Ab bhai max optimization ki bari hai.
    b. Idea ye hai ki 0,0 se start kiya do direction me ja skte hai 1,0 and 0,1.
    c. Abhi isme hum pehle vo path choose karege jiski jump min hogi.
    d. Ye hoga kese, humko DFS se switch karna pdega BFS me and PriorityQueue use krni pdegi.
    e. PriorityQueue me int arr[] jaega, jisme cell index hoga, and us cell se prev cell ki jump 
        value hogi. 
    f. PQ ko sort karege with jump value in increasing order chote se bada.
    g. Isme bhi upar vale solution me min jump for a cell for diff paths vali optimization lagayi thi
        vo bhi lagege.
    h. Abhi PQ bana li initialize krege isko with queue.add(new int[]{0,0,0}); 0,0 start index and 0 
        start jump.
    i. maxEffortMatrix banegi jo ki initilized hogi with Integer.MAX_VALUE.
    j. Then BFS started:
        i. Sabse pehle queue.poll().
        ii. Then if start row and start col which is passed in queue, is equal to bottom right 
            index, so return the jump. As discussed inside queue, we will pass start row, start col
            and jump to that col. Which will be already min as we are using PQ and memoization.
        iii. Then check if effort matrix current cell jump is min than passed jump in queue, if yes
                then continue.
        iv. Then start a for loop, on array public static int arr[][] = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};
        v. This will help us move in four direction.
        vi. Then create two var row and col.
        vii. do this int row = i+array[0];
                int col = j+array[1]; which means one by one you are increasing or decreasing i and j 
                to create new cell index in all four direction.
        viii. After creating it, check if row and col are not overflowed or underflowed.
        ix. After this take out the currEffort by Math.abs(height[i][j]-height[row][col]);
        x. Then take out max effort of that path, which will be Math.max(currEffort, w). W is what being
            passed in queue.
        xi. Then check effort matrix if for cell row and col, we already have a min jump than curr.
        xii. If yes then continue and skip current loop cycle 
        xiii. If no then update matrix[row][col] and add to queue.add(row, col, currEffort);
        xiv. That's it at last return -1. 
        Xv. Kyuki ans to BFS ke ander hi return kar re hai in point j.ii


*/


//Brute force DFS SOLUTION but ends up with TLE
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int visited[][] = new int[heights.length][heights[0].length];
        
        return dfs(heights, visited, 0, 0, 0, heights[0][0]);
    }

    public int dfs(int[][] heights, int[][] visited, int i, int j, int maxEffort, int prev){
        if(i < 0 || j < 0 || i >= heights.length || j >= heights[0].length){
            return Integer.MAX_VALUE;
        }

        if(visited[i][j] != 0){
            return Integer.MAX_VALUE;
        }

        if(i > 0 || j > 0)
            maxEffort = Math.max(maxEffort, Math.abs(prev-heights[i][j]));

        if(i == heights.length-1 && j == heights[0].length-1){
            return maxEffort;
        }

        visited[i][j] = 1;

        int p1 = dfs(heights, visited, i+1, j, maxEffort, heights[i][j]);
        int p2 = dfs(heights, visited, i, j+1, maxEffort, heights[i][j]);
        int p3 = dfs(heights, visited, i-1, j, maxEffort, heights[i][j]);
        int p4 = dfs(heights, visited, i, j-1, maxEffort, heights[i][j]);

        prev = 0;
        visited[i][j] = 0;

        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }
}

// Medium Optimized DFS solution with memoization but still TLE
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int visited[][] = new int[heights.length][heights[0].length];

        int maxEffortMatrix[][] = new int[heights.length][heights[0].length];

        for(int arr[] : maxEffortMatrix)
            Arrays.fill(arr, Integer.MAX_VALUE);
                
        return dfs(heights, visited, 0, 0, 0, heights[0][0], maxEffortMatrix);
    }

    public int dfs(int[][] heights, int[][] visited, int i, int j, int maxEffort, int prev, int[][] maxEffortMatrix){
        if(i < 0 || j < 0 || i >= heights.length || j >= heights[0].length){
            return Integer.MAX_VALUE;
        }

        if(visited[i][j] != 0){
            return Integer.MAX_VALUE;
        }

        if(i > 0 || j > 0){
            maxEffort = Math.max(maxEffort, Math.abs(prev-heights[i][j]));
        }

        if(maxEffort >= maxEffortMatrix[i][j] && (i != 0 && j != 0))
            return Integer.MAX_VALUE;
            

        if(i == heights.length-1 && j == heights[0].length-1){
            return maxEffort;
        }

        visited[i][j] = 1;
        maxEffortMatrix[i][j] = maxEffort;

        int p1 = dfs(heights, visited, i+1, j, maxEffort, heights[i][j], maxEffortMatrix);
        int p2 = dfs(heights, visited, i, j+1, maxEffort, heights[i][j], maxEffortMatrix);
        int p3 = dfs(heights, visited, i-1, j, maxEffort, heights[i][j], maxEffortMatrix);
        int p4 = dfs(heights, visited, i, j-1, maxEffort, heights[i][j], maxEffortMatrix);

        prev = 0;
        visited[i][j] = 0;

        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }
}

// Optimal Solution with PQ and memoization
class Solution {
    public static int arr[][] = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};
    public int minimumEffortPath(int[][] heights) { 
        PriorityQueue<int[]> queue = new PriorityQueue<>(
            (int[] a, int b[]) -> a[2] - b[2]
        );

        queue.add(new int[]{0,0,0});

        int matrix[][] = new int[heights.length][heights[0].length];

        for(int m[] : matrix)
            Arrays.fill(m, Integer.MAX_VALUE);

        while(!queue.isEmpty()){
            int [] temp = queue.poll();
            int i = temp[0];
            int j = temp[1];
            int w = temp[2];

            if(temp[0] == heights.length-1 && temp[1] == heights[0].length-1){
                return w;
            }

            if(matrix[i][j] < w) continue;

            for(int array[] : arr){
                int row = i+array[0];
                int col = j+array[1];

                if(row < 0 || col < 0 || row >= heights.length || col >= heights[0].length) continue;

                int eff = Math.abs(heights[row][col] - heights[i][j]);

                int newEffort = Math.max(eff, w);

                if(matrix[row][col] <= newEffort) continue;

                matrix[row][col] = newEffort;
                queue.add(new int[]{row, col, newEffort});
            }            
        }
                
        return -1;
    }
}