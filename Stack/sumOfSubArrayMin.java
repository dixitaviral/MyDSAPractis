/*
    Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.

 

Example 1:

Input: arr = [3,1,2,4]
Output: 17
Explanation: 
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.
Example 2:

Input: arr = [11,81,94,43,3]
Output: 444

Intution:

1. Stack ka golden rule yaad hai na. Maintain increasing or decreasing order.
2. Yaha par min dhundna hai har subarray ka, to order increasing maintain karna hai.
3. Abhi neeche dekh :
    --------> ye arrow dikha ra hai tujhe ek direction me move krna hai and subarray bhi isi direction
    [3,1,2,4] me banega.

4. Chal yaha tak smjh aa gaya.
5. Abhi humko increasing order maintain karna hai, iska mtlb peek element se current i element chota hota hai
    to pop hoga.
6. Abhi soch point 5 vali condition ka kya mtlb hua:
    1. Since tu increasing order maintain kar raha hai, to peek element abhi tak ka sbse bada element hua.
    2. Tu usko pop kar de.
    3. Ab pop krne ke bad tujhe ye janna hai ki peek element kitno se bada hai and kitno se chota hai.
       for example [4,5,6,3,5] tu 4 ko dekh 4 se pehle koi chota element ni hai, to left count me tu sirf
        4 ko count karega to left = 1, abhi right me dekh 4 se continuous bade 6 tak hai, right hua,
        3, Ab tujhe ye pata chal gaya hai ki 4 kitno se chota hai means kitne sub array me 4 min hai.
    4. Abhi simple pop elemet * left * right kar de and res me add kar de.
7. Abhi ek baat socho, tu jab stack me push karega to usme index push karega, kyuki tujhe 
    left and right count nikalna hai.
8. Bas ho gaya solution.
    


*/

class Solution {
    public int sumSubarrayMins(int[] arr) {
        Stack<Integer> stack = new Stack();

        int sum = 0;

        int len = arr.length;

        for(int i = 0; i <= len; i++){

            int temp = i == len ? 0 : arr[i];
            
            while(!stack.isEmpty() && arr[stack.peek()] > temp){

                int mid = stack.pop();

                int left = stack.isEmpty() ? mid+1 : mid-stack.peek();
                int right = i-mid;

                sum += arr[mid] * left * right;
            }

            stack.push(i);
        }

        return (int)(sum % (Math.pow(10,9)+7));
    }
}