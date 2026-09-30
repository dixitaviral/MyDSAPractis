/*
Given two integers, start and end, along with an array of integers arr[]. In one operation, you can multiply the current value by any element from arr[], and then take the result modulo 1000 to obtain a new value.

Find the minimum steps in which end can be achieved starting from start. If it is not possible to reach end, then return -1.

Examples :

Input: arr[] = [2, 5, 7], start = 3, end = 30
Output: 2
Explanation:
Step 1: 3*2 = 6 % 1000 = 6 
Step 2: 6*5 = 30 % 1000 = 30
Input: arr[] = [3, 4, 65], start = 7, end = 175
Output: 4
Explanation:
Step 1: 7 * 3 = 21 % 1000 = 21  
Step 2: 21 * 3 = 63 % 1000 = 63  
Step 3: 63 * 65 = 4095 % 1000 = 95  
Step 4: 95 * 65 = 6175 % 1000 = 175 
Input: arr[] = [2, 4], start = 3, end = 5
Output: -1
Explanation: Starting from 3 and multiplying by 2 or 4 always produces even numbers after the first step. Since 5 is odd, it can never be reached.


Intution:

1. Bhai simple ques hai ye BFS and dist array se lag jaega.
2. Tumhre pas ek array diya hai and ek start and end number diya hai.
3. Abhi tumko start se end tak banana hai by multiplying and doing mod with 1000. Aur multiply krna hai
    array me jo numbers diye hai unse.
4. First example le agar to, 3 hai start number jisko 2,5,7 se mulitple karege to kuch numbers aaege like 6, 15, 21 abhi in number ko vapas
    array ke number se multiply krna hai to aaega 12, 15, 42 and so ...
5. Feel aa ra hai na BFS ka, ki har new number ek node hai and prev se next number transition ke liye egde ka weight 1 hi hai.
6. To fir simple hai ye single source BFS hai, sabse pehle start ko queue me dal do.
7. Now since humko bola gaya ki 1000 se mod krna hai iska mtlb jitne numbers generate hoge, vpo 1000 se less hoge.
8. To 1000 length ka distance array bana do, abhi queue se poll karoge and for loop chalao and curr num jo poll kiya hai 
    usko arr ke numbers se multiply krte jao.
9. Agar for loop ke ander newNum == end aata hai to iska mtlb end ban gaya to return kar do dist[polledNum]+1. Kyuki jis number se
    end bana hai uski dist + 1 steps hoge for reaching end.
10. Abhi agar end number ni hai to dist[newNum] != -1 hai to continue kar do. As weight sabka same hai to first time jab kisi number par pohochege
    to sabse shortest hi hoga vo.
11. Abhi agar -1 hai to dist[newNum] = dist[num] + 1 karke queue me dal do.
12. Abhi hum dist ko -1 se initialize kar rahe hai, kyuki start index jo hoga uski dist to 0 hogi na and default value bhi 0 hoti hai
    to ye mix na ho jae isliye -1 se initialize krte hai.
*/


class Solution {
    public int minSteps(int[] arr, int start, int end) {
        
        Queue<Integer> queue = new ArrayDeque();
        
        queue.add(start);
        int dist[] = new int[1000];
        Arrays.fill(dist, -1);
        dist[start] = 0;
        
        while(!queue.isEmpty()){
            
            int num = queue.poll();
            
            if(num == end) return dist[num];
        
            for(int n : arr){
                int newNum = (n*num) % 1000;
                
                if(newNum == end) return dist[num]+1;

                if(dist[newNum] != -1) continue;
                
                dist[newNum] = dist[num] + 1;
                
                queue.add(newNum);
                
            }
        }
        return -1;
    }
}