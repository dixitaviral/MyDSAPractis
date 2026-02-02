/*
Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.

The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.

 

Example 1:

Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number. 
The second 1's next greater number needs to search circularly, which is also 2.
Example 2:

Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]

Intution:
1. To simple intution hai, jese next greater element me kiya tha humne:
    a. First push an element to stack.
    b. Then check if stack is not empty and peek element is smaller than current element.
    c. If yes then pop the peek element and store it in result array. 
    d. Now do this until you are getting peek lesser than current and pop and fill in result array.
    e. This thing will be same.
2. Abhi dikkat kya hai, ki yaha par array circular hai, mtlb last element ke bad fir se 
    hum first element pe ja sakte hai.
3. To iske liye hum kya karege ki hum array ko do bar traverse karege:
    a. Do bar traverse krne ke liye humko while loop nums.length*2 bar chalana hoga.
    b. Fir ab ques hai ki jab tak i < nums.length rhega tb to thieek hai.
    c. But jab i greater than or equal to nums.length hoga, tb hum 0th index se kese start krege.
    d. Uske liye simple i % length ka use karege jaha bhi hum nums array se element fetch kar re hai.
    e. Abhi socho, first time jab traverse karoge, us time stack me vo element already reh jaege
        jinke liye humko greater element ni mila hai.
    f. To second time traversal sirf compare karne ke liye hoga and because of that
    g. Hum stack.push skip kar sakte hai for round two.
    h. Hence hum check karege ki if count < length tabhi push karenge.
4. Finally return the result array.
*/



class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int res[] = new int[len];

        Stack<Integer> stack = new Stack();

        int i = 0;
        int count = 0;

        Arrays.fill(res, -1);
        
        while(count < len*2){
            i = i % len;
            
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i]){
                res[stack.pop()] = nums[i];
            }
            
            if(count < len)
                stack.push(i);
            i++;
            
            count++;
        }

        return res;
    }
}