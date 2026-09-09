/*
Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.

Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content. Your goal is to maximize the number of your content children and output the maximum number.

 

Example 1:

Input: g = [1,2,3], s = [1,1]
Output: 1
Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3. 
And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
You need to output 1.
Example 2:

Input: g = [1,2], s = [1,2,3]
Output: 2
Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. 
You have 3 cookies and their sizes are big enough to gratify all of the children, 
You need to output 2.

Intution:
1. Dekho bhai ye ek dp ka plus greedy ka ques tion hai.
2. Tumko ye batana hai ki kitne maximum baccho ko cookies mil skti hai. Condition hai ki 
    agar child[i] means child kitni cookies le skta hai <= cookies[j] utni same ya zada cookies available 
    hai to de skte hai.
3. Abhi dekho bhai iss ques ko recursion se chaho, usse solve kar lo ya ek simpler approach hai
4. Sabse pehle tum socho ki agar hum dono arrays ko sort kr de, to kitna badhiya ho jaega.
5. Kyu badhiya hoga kyuki abhi humko ye pata hai ki start jo hai vo us children se karege jisko sabse kam cookie
    chahiye and similary start humn uss cookies se karege jo sabse kam available hai.
6. To agar pehli cookie and pehla child liye and child[0] <= cookies[0] to bhai badhiya i and j badha do, count badha do.
7. Abhi agar aisa ni hai to fir kesa hai, to fir ek baat hi hogi na ki bhai agar tum current cookie jo tum current bacche ko
    ni de pae, to tum dusre bacche ko bhi ni de skte, tumne kaha kyu bhai.
8. Mene kaha array hai sorted agar pehli cookie ya koi bhi cookie tum kisi bacche ko ni de pae to iska mtlb ye hua ki vo cookie
    bacche ko jitni chahiye usse kam hai, and current bacche ke bad jo bhi bacche aaega unko current vale se zada hi cookies chahiye 
    hogi, to current cookies tum baki baccho ko bhi ni de skte.
9. To is case me cookies skip kar do and check for other cookie.

Abhi ko tumko implement krna hai either from sorting + two pointers or with sorting + recursion + dp. Dp me state hogi i and j.
*/

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0;
        int j = 0;

        int count = 0;

        while(i < g.length && j < s.length){
            if(g[i] <= s[j]){
                count++;
                i++;
                j++;
            }else {
                j++;
            }
        }

        return count;

        // return helper(g, s, 0, 0);
    }

    public int helper(int[] child, int[] cookies, int i, int j){
        if(i == child.length || j == cookies.length){
            return 0;
        }

        int given = 0;
        int notGiven = 0;

        if(child[i] <= cookies[j]){
            given = 1 + helper(child, cookies, i+1, j+1);
        }else{
            notGiven = helper(child, cookies, i, j+1);
        }
        
        return Math.max(given, notGiven);
    }
}