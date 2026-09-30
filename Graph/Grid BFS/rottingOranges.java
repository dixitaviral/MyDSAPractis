/*
You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

 

Example 1:


Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.


Intution:
1. Bhai simple ques hai but pehla BFS ka ques hai. Or ab ek baat dhyan rakh ki BFS = queue.
2. Abhi isme hum queue use karege. 
3. And DFS kyu ni lag ra yaha par kyuki iss ques me ek condition hai, ki humko time batana hai ki kitne min me 
    fresh oranges rott ho jaege.
4. abhi man le 0th min par 2 oranges rotten hai and 1 min me dono rotten oranges ke pas vale oranges rot hone lagege. right.
5. abhi tum DFS krte to vo appraoch lekr aise jata ki pehla rotten orange hi traverse krta sbse pehle. dusra bad me.
6. But humko to parallel me kaam krna hai, ki 1 min me jitne 0th min me rotten orange the unko pas vale orange ko rott krna hai pehle
    min me.
7. Jiske liye hum queue and bfs use kar rahe hai.
8. Abhi intution simple hai:
    a. Sbse pehle grid par traverse karo and rotten oranges ke indexes queue me store kr lo.
    b. isi grid traversal me fresh oranges bhi couont kar lo.
    c. then ek or condition bhi hai, ki koi fresh orange ni hai to 0 return kr do.
    d. then while loop lagao jab tka queue empty ni ho jati and fresh count > 0 rehta hai.
    e. While loop ke ander queue ka current size lo and utne time ka for loop chala do.
    f. for loop ke ander queue se poll karo and un indexes ke charo tarah agar koi fresh orange hai usko rott karo and queue me uska index bhi daal do.
    g. sath me condition check krte raho ki i and j over flow na ho jae.
    i. then fresh count bhi decrease krte raho.
    j. then for loop khtm hone ke bad min++ kar do.
    k. akhir me agar fresh == 0 then return min else return -1;
*/


class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<Pair> queue = new ArrayDeque();
        int min = 0;
        int fresh = 0;

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 2){
                    queue.add(new Pair(i,j));
                }else if (grid[i][j] == 1){
                    fresh += 1;
                }
            }
        }

        if(fresh == 0){
            return 0;
        }

        while(!queue.isEmpty()){
            int size = queue.size();

            for(int idx = 0; idx < size; idx++){
                Pair pair = queue.poll();

                int i = pair.getI();
                int j = pair.getJ();
                
                if(i+1 < grid.length && grid[i+1][j] == 1){
                    grid[i+1][j] = 2;
                    queue.add(new Pair(i+1, j));
                    fresh--;

                }
                if(i-1 >= 0 && grid[i-1][j] == 1){
                    grid[i-1][j] = 2;
                    queue.add(new Pair(i-1, j));
                    fresh--;
                }
                if(j+1 < grid[0].length && grid[i][j+1] == 1){
                    grid[i][j+1] = 2;
                    queue.add(new Pair(i, j+1));
                    fresh--;
                }
                if(j-1 >= 0 && grid[i][j-1] == 1){
                    grid[i][j-1] = 2;
                    queue.add(new Pair(i, j-1));
                    fresh--;
                }
            }
            min += 1;
        }

        if(fresh == 0)
            return min-1;// min -1 isliye karna pad ra hai, kyuki jab ek bhi fresh orange ni bachega and that time queue me pairs reh gaya, to vo +1 krke ans aaega
                        // agar yaha -1 ni krna hai to while loop me && fesh > 0 ki condition laga do bs;
        else
            return -1;
    }
}

class Pair{
    int i;
    int j;

    public Pair(int i, int j){
        this.i = i;
        this.j = j;
    }

    public int getI(){
        return this.i;
    }

    public int getJ(){
        return this.j;
    }


}