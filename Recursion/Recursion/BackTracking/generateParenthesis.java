/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]

Intution:

1. Bhai simple two branches mtlb 2^n complexity vala question hai which means hamesha 2 choices
    hai tere pas. Means two choices vala recusrion tree banega isme.
2. Abhi dekh karna kya hai, isme do recursion use hoge, abhi tu sochega bhai, rule ke hisaab se
    jab ko inlucde exclude krne vali choice leni hoti hai tb do recursion lgte hai:
    a. to mai kahuga ha bhai isme bhi include exclude vali hi choices hai.
    b. ha vo bat alag hai pehle ke ques me, to 3 length ki string hai and 2 length ki banega to bhi valid hogi
    c. and is ques me tujhe '(' and ')' dono ko include krna hai, but ye bhi to dekh ki still choice include
        exclude krne vali hai. Kese? chal batata hu
    d. tujhe is ques me parenthesis string banate banate, parenthesis string valid hai ya ni ye bhi to 
        dekhna padega na.
    e. Abhi open se zada close ni ho skte, pehla bracket open hi hona chahiye, in sb cases me include exclude
        hi to kar rahe ho.
    f. I hope smjh aaya hoga.
3. Chalo abhi intution par jump krte hai.
4. Simple hai but ye alag tarah se lagega, kyu? Kyuki isme tumko string banane se pehle validate bhi karna hai.
5. Bas fir pattern vahi backtracking vala rhega ki bhai:
    a. Ans banao
    b. Next branch par jao, means next recursion call karo.
    c. if base condition met to ans store karo, else or keep making ans.
    d. then return ke bad, jo last element add kiya tha usko remove kar do.
6. Upar itna pdhne ke bad, agar tumko click kar gaya ki do if block lagege do badhiya, ni kiya click 
    to batata hu kyu lagege:
    a. See ye to tmko pata hai na ki valid banane ke liye first '(' hona chahiye.
    b. then first ke bad do choices hai
        i. ya to vapas '(' ya to ')'. (Vo bat alag hai ki code execution pehle sare open banaega then vapas
            hue close vale check karega.)
        ii. abhi lekin open brackets hai string me to close bhi aa skte hai and close hai to open bhi aa 
            skte hai like ()() -> abhi isme vapas se open bracket laga skte hai na
        iii. Itni BC krne ka mtlb tha ki bhai do if isliye lagege kyuki dono condition true ho skti hai.
                if-else lagate to ek hi condition trigger hoti.
7. bas fir kya ek open variable and ek close variable lagega jo count karega and conditions me help karega.
    open < n and close < open.
8. In sb steps ko follow krke ans aa jaega.



*/


class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> li = new ArrayList();

        helper(n, li, new StringBuilder(""),0,0);

        return li;
    }

    public void helper(int n, List<String> result, StringBuilder sb, 
                        int open, int close){
        if(sb.length() == n*2){
            result.add(sb.toString());
            return;
        }

        // Conditions kya rahegi badi easily soch skte ho but koi time lagega,
        // mai batata hu abhi bad me khud se seekh jaoge
        // 1. open bracket count hamesha n se kam rhega, kyuki socho total jo       
        // valid bracket string generate karoge usme chahe jese combinations 
        // rakho but hamesha open = close rhege for ex: (()),(),()()()
        // 2. Abhi second condition milti hui hai, ki close < open. Jo ki tum
        // khud smjh jaoge.
        // Abhi yahi do conditions jo hai, vahi do branches ka kam karegi.
        // dekho neeche.
        // ek last point since dono hi conditions valid ho skti hai to dono 
        // ko traverse krna pdega jiske liye do if lagege

        if(open < n){
            open = open+1;
            sb.append('(');
            helper(n, result, sb, open, close);
            open = open - 1;
            sb.deleteCharAt(sb.length() - 1);
        }

        if(close < open){
            close = close+1;
            sb.append(')');
            helper(n, result, sb, open, close);
            close = close-1;
            sb.deleteCharAt(sb.length()-1);
        }
    }
}