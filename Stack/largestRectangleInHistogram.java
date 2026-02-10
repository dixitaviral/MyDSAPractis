/*
Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

Example 1:

      █
      █
    █ █
    █ █
    █ █   █
█   █ █ █ █
█ █ █ █ █ █
--------------
2,1,5,6,2,3


Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
Example 2:


Input: heights = [2,4]
Output: 4
 

Constraints:

1 <= heights.length <= 105
0 <= heights[i] <= 104


Intution:

1. Bhai ye ques same hai sum of subarray min se, bas thoda sa change hai.
2. Tu stack golden rule refer kar le agar tu bhul gaya hai, yaad hai na, 
    maintain increasing or decreasing order.
3. Abhi is ques ka heart ye hai ki ek rectangle tabhi banna break hoga jab right me usse bada 
    element milega. Jese 1,5,6 me 1 ka rectangle 6 tak bnega to area hoga 1(height)*3(widht) = 3;
4. Banna break hone ka mtlb hai ki yaha se aage rectangle ka area ni jaega is iteration me.
5. To ques ka intution kuch ye rhega ki:
    a. Sbse pehle to loop ko i == len tak chalana hai, taki last element bhi compare ho jaye.
    b. Uske bad last step stack me index push karo, kyuki width nikalni hai current popped
        element ke liye jo tujhe index se milegi.
    c. Abhi beech ka part aata hai simple hai:
        i. Loop condition rhegi while(!stack.isEmpty() && heights[stack.peek()] > cur) kyuki 
            hume increasing order maintain karna hai.
        ii. Jab ye condition true hogi, iska mtlb abhi humko current se chota element mil gaya hai.
        iii. Abhi humko current element pop karna hai.
        iv. Fir aata hai width nikalne ka part:
            a. Width niklegi right(current i) - left(stack peek after pop) - 1 se.
            b. Right i isliye liya hai kyuki vo breakpoint hai jaha par humko chota element mila hai.
            c. Left stack peek after pop liya hai kyuki , vo puri range bataega.
                for example, 2,1,5,6,2,3 me me dry run karke dekhna, stack me jab index bachege
                1,4 means element hoge 1,2. To agar hum left ko popped element le lege mtlb ki 4
                to galat answer aaega kyuki agar tum dekho to 2 height ka rectangle 5,6,2 in teeno
                height par banega.
            d. I hope smjh aaya hoga. Abhi akhiri sawal ye ki bhai -1 kyuki karna pad ra hai,
                vo isliye kyuki hum ek extra 0 consider krte hai in case of i == len.
            e. To bhai index count < len ni == len tak jata hai, but len ke equal to koi index hoga ni
                tbhi hum -1 karte hai, taki width sahi nikle.
            f. to Width ka formula hua i - (stack.isEmpty() ? -1 : stack.peek()) - 1.
            g. Ab ek or cheez pop karte time stack empty ho skta hai, and hum stack.peek() nikalege
                for left, to error dega.
            h. Humne kaha baat to ni si hai but stack empty ke case me left hoga kya.
            i. Ab hum keh skte hai 0 hoga, but 0 kese ho skta hai:
                a. Man lo input hai [5], abhi humne dekha tha width i - left - 1 se niklegi.
                b. Plus i jaega i == 1 tak, kyuki hum ex extra 0 compare kar rahe hai.
                c. Rakh lo formula (i = 1) - (left = 0) - 1 to result hua 0.
                d. Ab 5*0 karo to aaega 0, to galat answer aaega.
                e. To hum left ko -1 consider karte hai, taki width nikle 1, aur area nikle 5*1 = 5, jo ki sahi answer hai.
                f. Or ye case sare inputs ke liye kaam karega.
            j. Abhi bas area calculate karna hai, area niklega height[mid] * width, aur usko max me store karna hai.
6. Bas ho gaya solution.
*/

class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack();

        int max = 0;
        int len = heights.length;

        for(int i = 0; i <= len; i++){
            int cur = i == len ? 0 : heights[i];
            
            while(!stack.isEmpty() && heights[stack.peek()] > cur){
                int mid = stack.pop();

                int left = stack.isEmpty() ? -1 : stack.peek();
                int width = i - left - 1;

                max = Math.max(heights[mid]*width, max);
            }

            stack.push(i);
        }

        return max;
    }

    
}