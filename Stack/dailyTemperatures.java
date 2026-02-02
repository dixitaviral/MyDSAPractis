/*
Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]


Intuition:
1. Ye ques next greater element jaisa hi hai, bas thoda sa variation hai.
2. Hume har element ke liye next greater element find karna hai, aur uske index 
    ka difference nikalna hai.
3. Optimal complexity O(n) hi rahegi, kyuki hume array ko ek baar traverse karna hai.
4. Steps:
    a. Hum ek stack use karenge jo hume indices ko track karne me help karega.
    b. Hum result array banayenge jisme hum final answer store karenge.
    c. Hum temperatures array ko traverse karenge.
    d. Har element ke liye check karenge ki kya wo stack ke top element se bada hai.
    e. Agar bada hai, to hum stack ke top element ko pop karenge aur uske index ka 
        difference current index se nikal kar result array stack.pop() vale index par
        store karenge.
    f. Ye process tab tak chalega jab tak stack empty nahi ho jata ya current element 
        stack ke top element se chota ya equal nahi ho jata.
    g. Finally, hum current index ko stack me push karenge.
    h. Loop complete hone ke baad, jo elements stack me bache honge unke liye result 
        array me 0 hi rahega kyuki unke liye koi next greater element nahi hai.
*/

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int res[] = new int[len];

        Stack<Integer> stack = new Stack();
        int a = 0;

        for(int i = 0; i < len; i++){
            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                int temp = stack.pop();
                res[temp] = i-temp;
            }

            stack.push(i);
        }

        return res;
    }
}