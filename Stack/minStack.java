/*
    Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.

 

Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2

Intutuion:
1. Bhai stack operation to normal hote hai bahi karne hai har function me. No change in that.
2. Abhi aata hai main logic in O(1) time me minimum element kaise nikalenge.
3. Ek extra stack banayenge jisme hum minimum elements ko store karenge.
    a. Uske liye ek extra minStack banao.
    b. Ek variable banao min = Integer.MAX_VALUE.
    c. Abhi jab bhi stack.push(val) karo, usse pehle check karo, min >= val, if yes then
        push that element to minStack and update min = val.
    d. minStack current min store karke rakhega hamesha, jo tumko minStack.peek()se milega.
    e. Abhi dhyan dene vali baat hai ki, jab main stack se pop hoga, tab humko check karna hoga,
        ki kya min ya minStack.peek() ka jo element hai vo pop hua hai.
    f. Agar hua hai to minStack se bhi pop kar do and min update kar do to current minStack.peek() se.
    g. Agar minStack pop karte time empty ho jae, to min = Integer.MAX_VALUE kar do.
    h. Baaki top() and getMin() to simple hai, bas stack.peek() and min return kar do.


Bonus tip:
1. Isko tum main stack me bhi kar sakte ho, extra space use kiye bina.
2. Vo kese:
    a. Simple, jo main stack hai usme tumko pairs store karne hai.
    b. Pairs as Stack<int[]>, abhi ye array 2 cheeze hold karega.
    c. 0th index par current element and 1th index par current min element.
    d. Baki tum samajhdar ho, kabhi intution padhna to lagana aise.

*/


class MinStack {

    Stack<Integer> stack;
    Stack<Integer> minStack;
    int min;

    public MinStack() {
        stack = new Stack();
        minStack = new Stack();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int val) {
        if(min >= val){
            min = val;
            minStack.push(min);
        }
        stack.push(val);
    }
    
    public void pop() {
        int temp = stack.pop();
        if(temp == min){
            int lastMin = minStack.pop();
            min = minStack.isEmpty() ? Integer.MAX_VALUE : minStack.peek();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */