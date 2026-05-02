/*
You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.

 

Example 1:

Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.
Example 2:

Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].
Example 3:

Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].

Intution:

1. Bhai ye variable sliding window ka ques hai, two cases hai window valid and invalid:
    a. Window valid tab tak jab tak, koi do tarah ke fruits aa re hai.
    b. jese hi koi third type ka fruit aaya, window invalid ho jati hai prev vali.
    c. For example [3,3,3,1,2,1,1,2] abhi isme till index 3 i.e. 1 all good, only 3 and 1 type of
        fruit, but jese index 4 par aae, it's 2 now teen tarah ke fruits aa gye, 3,1,2.
    d. Isse hua kya jo window this hamari from start = 0 to i = 3, invalid ho gyi.
    e. Abhi next window 1 se start honi chahiye kyuki 1 se humko ek tarah ka fruit mil gaya
        and then further 2.
2. Abhi upar diye solution ko implement kese krna hai, max window nikalna to simple hai:
    sirf count = Math.max(i-start+1, count) se kaam ho jaega, where i = right pointer 
    and start = left pointer, and +1 for zero array index adjustment.
3. Main catch isme start ki position hai, ki usko kese move karoge:
    1. Hamesha yaad rakhna variable window me start ko move karane ke liye while loop lgta hai.
    2. Humne ek data structure chahiye, jo hume batae konsa fruit kitni bar aaya hai,
        and for that Map is best.
    3. Hum har ith element ko map me with it's frequency store krege jab tak map.size() <= 2 rehta
        hai.
    4. Ye isliye kyuki humko maitain karna hai, ki sirf do tarah ke fruits hi consider ho.
    5. Then max count nikalege, refer point 2 of outer heirarchy.
    6. Then else block me code aaega if map.size > 2.
        i. if so, then while loop lagao till map.size > 2.
        ii. Ek ek karke start index ke element ki frequency kam karo and start++;
        iii. When it becomes 0, then remove that element from map.
        iv. What this will do is, jo extra fruit pehle added tha, isko remove karega
            and humko correct start jo ki left pointer hai uski correct position par lekr aaega.
        v. fir simple while loop se jese bahr aao, to i++, kyu prev i ko already as first step add
            map me add kar chuke.
4. Hence we will get our code ready.
*/


class Solution {
    public int totalFruit(int[] fruits) {
        int i = 0;
        int start = 0;

        int count = 0;

        Map<Integer, Integer> set = new HashMap();

        while(i < fruits.length){
            set.put(fruits[i], set.getOrDefault(fruits[i], 0)+1);

            if(set.size() <= 2){
                count = Math.max(i-start+1, count);
                i++;
            }  
            else{
                while(set.size() > 2){
                    set.put(fruits[start], set.get(fruits[start])-1);
                    if(set.get(fruits[start]) == 0){
                        set.remove(fruits[start]);
                    }
                    start++;
                }
                i++;
            }  
        }

        return count;

    }
}