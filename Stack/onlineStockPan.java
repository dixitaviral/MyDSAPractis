/*
    Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.

The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and going backward) for which the stock price was less than or equal to the price of that day.

For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2, then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4 consecutive days.
Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock today is 8, then the span of today is 3 because starting from today, the price of the stock was less than or equal 8 for 3 consecutive days.
Implement the StockSpanner class:

StockSpanner() Initializes the object of the class.
int next(int price) Returns the span of the stock's price given that today's price is price.
 

Example 1:

Input
["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
[[], [100], [80], [60], [70], [60], [75], [85]]
Output
[null, 1, 1, 1, 2, 1, 4, 6]

Explanation
StockSpanner stockSpanner = new StockSpanner();
stockSpanner.next(100); // return 1
stockSpanner.next(80);  // return 1
stockSpanner.next(60);  // return 1
stockSpanner.next(70);  // return 2
stockSpanner.next(60);  // return 1
stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
stockSpanner.next(85);  // return 6

Intution:
1. Bhai pehle question samjhte hai:
    a. Hume ek aisa class design karna hai jo daily stock prices ko collect kare.
    b. Har din ke liye hume stock price ka span return karna hai.
    c. Span ka matlab hai ki us din se pehle kitne consecutive din the jinke prices us din ke
       price se kam ya barabar the.
2. Optimal complexity O(n) hi rahegi kyuki hume har price ko ek baar process karna hai.
3. Steps:
    a. Hum ek stack use karenge jo hume pairs store karne me help karega.
    b. Pairs mtlb simple array hoga jo 0th index par price store karega and 1 index par span 
        store karega.
    c. Starting me sbka span 1 hi rhega kyuki har din ka apna price hota hai.
    d. abhi while loop lagaege jisme check karege
        i. if stack is not not empty.
        ii. if stack.peek()[0] <= current price that is passed in the method.
        iii. Agar condition match hogi tab span variable me stack.pop()[1] ko add karenge, jo
            ki simple pichle din ka span hoga.
    e. Finally, current price and calculated span ko stack me push karenge.
    




*/

class StockSpanner {

    Stack<int[]> stack;

    public StockSpanner() {
        stack = new Stack();
    }
    
    public int next(int price) {
        int span = 1;
        while(!stack.isEmpty() && stack.peek()[0] <= price){
           span += stack.pop()[1];
        }
        stack.push(new int[]{price, span});

        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */