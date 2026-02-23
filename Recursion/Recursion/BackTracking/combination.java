/*
Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].

You may return the answer in any order.

 

Example 1:

Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
Example 2:

Input: n = 1, k = 1
Output: [[1]]
Explanation: There is 1 choose 1 = 1 total combination.
 

Intution:

1. Aao bhai intution dekhte hai iski.
2. Sbse pehle backtracking ke rule dekh lete hai:
    a. Ans banao
    b. Recursion karo.
    c. Then ans undo kar do, means last element jo add kiya vo hata do.
3. Abhi ye question kya keh ra hai ki 1 se lekr n tak, jisme n exlusive hai. Combination banao
    plus vo combination k size ka hona chahiye, and un combinations me se duplicates ni hone chahiye
    like [1,2] and [2,1].
4. Ab isse humko ek bat smjh aati hai ki, jo number hum ek bar combination banane me use kr chuke hai
    usko dobara use ni karna.
5. Example n = 4 and k = 2. means combination banna start hoga from 1 like below:
    1: [1,2], [1,3], [1,4]
    2: [2,3], [2,4]
    3: [3,4]
6. Abhi dekho 1 ko humne sirf 1 ke combinations me use kiya similary 2 and 3.
7. Isko karne ke liye humko ye smjh aaya ki hum ek start lena padega jo humko bataega ki yaha se
    number lena start karo, isse peeche se ni lena hai.
8. So simple hum loop me i = start karege and recursion me start ko i+1 se reset karege.
9. Akhir me ye batane ki zrurt to ni hai but fir bhi bata ra hu, ki combination banana hai to loop 
    + recursion use hoga.
*/



class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList();

        create(result, n, k, new ArrayList(), 1);

        return result;
    }

    public void create(List<List<Integer>> result, int n, int k, List<Integer> li, int start){
        if(k == li.size()){
            result.add(new ArrayList(li));
            return;
        }

        for(int i = start; i <= n; i++){

            li.add(i);

            create(result, n,k,li, i+1);

            li.remove(li.size()-1);
        }
    }
}