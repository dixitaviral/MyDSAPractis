/*
    The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.

You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.

For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.

Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.

 

Example 1:

Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
Output: [-1,3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
- 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
- 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
Example 2:

Input: nums1 = [2,4], nums2 = [1,2,3,4]
Output: [3,-1]
Explanation: The next greater element for each value of nums1 is as follows:
- 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
- 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.

Intution:
1. Bhai ques dekhne me tough hai, but simple hai:
    a. nums1 ke har element ke liye hume nums2 me uske aage ka pehla bada element find karna hai.
    b. Agar aisa element nahi milta to -1 return karna hai.
    c. Bohot badhiya, abhi ques smjh aa gaya hai, abhi intution dekhte hai.
2. Sabse pehle optimal complexity jaegi iski O(nums1.length + nums2.length) kyuki hume 
    dono arrays ko ek baar traverse karna hai.
3. Abhi intution start karte hai in following steps:
    a. Hum ek stack use karenge jo hume next greater elements ko track karne me help karega.
    b. Hum ek map use karenge jo hume har element ka next greater element store karne me help 
        karega.
    c. Hum nums2 ko traverse karenge aur har element ke liye check karenge ki kya wo 
        stack ke top element se bada hai.
    d. Ek while loop chalaege with condition (!stack.isEmpty() && stack.peek() < current element).
    e. Why using while loop, kyuki kya pata stack me pehle ke bhi elements ke liye next greater
        na mil ho.
    f. like 6,5,4,3,2,1,7 -> isme 6 5 4 3 2 1 sabke liye next greater element 7 hai.
    g. Toh jab tak stack ka top element current element se chota hai, tab tak hum stack ke top 
        element ko pop karenge aur uske next greater element ko current element set karenge map me.
    h. Finally, hum nums1 ko traverse karenge aur har element ke liye map me se uska next greater 
        element fetch karenge. Agar map me wo element nahi hai, to hum -1 return karenge.

*/

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack();

        Map<Integer, Integer> map = new HashMap();

        int res[] = new int[nums1.length];


        for(int i = 0; i < nums2.length; i++){
            while(!stack.isEmpty() && stack.peek() < nums2[i]){
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }

        int a = 0;

        for(int i = 0; i < nums1.length; i++){
            res[a++] = map.getOrDefault(nums1[i],-1);
        }

        return res;
    }
}