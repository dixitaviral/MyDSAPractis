/*
You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

 

Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 

Intution:
1. Bhai ye tumhra pehla DP ka ques tha jo ki tumne almost laga diya tha apne aap se.
2. Abhi ques simply keh ra hai ki man lo 2 stairs hai inko kitni bar me chad skte ho.
    a. pehle 1+1 krke chado.
    b. then 2 ek sath chad jao.
    c. To total steps ways hue 2.
3. Bas yahi is ques me karna hai.
4. Abhi dekhte hai karoge kese:
    a. Dekho bhai is ques me hamare pas choices hai and dono choices ko consider krna hai.
    b. Jiske liye sabse suitable hai recursion.
    c. Abhi recursion kuch aise lagega ki :
        i. Ques me keh raha hai ya to 1 step chal skte ho ya to do step ek bar me chal skte ho.
        ii. To iske do mtlb hue:
            a. Base condition aaegi n == 0 par.
            b. Abhi ek condition hai man lo n = 1 hai to ek step chalne par n == 0 hoga
                jo ki theek hai.
            c. But same n ke liye agar tum dp step chaloge to ans hoga n = -1 but humko 0 se 
                neeche jana hi ni hai.
            d. Jisse hume ye pata chala ki do recursion chalane hai ek n-2 karega and ek
                n-1 karega.
            f. Or ye do recursion bhi if block se covered hoge if(n >= 1) and if(n >= 2). Otherwise
                ans negative me chala jaega jo ki worth ni hai.
    d. Abhi recursion lagane ke bad tumko ek step chal kar and do step chal kar jitne values aai hai dono 
        ki unki add krke return kar do.
    e. Abhi baat aati hai base condition kya rahegi, jo ki hai n == 0 hua to return 1;
    f. Abhi tum kahoge 0 kyu ni return kar rahe, vo isliye ki agar koi recursion path 0 tak pohocha hai,
        uska mtlb hua ki usne sare stairs chad liye and usko ek path combination mil gaya hai. 
    g. And humko path combination count krne hai na ki un combinations me total kese steps liye ye ni.
    h. For example humko 2 stair chadne hai to humn 1+1 and ek sath 2 chada. To total ways 2 hue.
        jo ki answer hai. Na ki 3 jo ki tumne ye bataya ek bar 1+1 karke chado to 2 ho gye then 
        ek bar me 2 chad jao ye 1 hua mila kar 3 ho gye. To ye ni batana hai.
    i. ABhi jo climbOne and climbTwo me tum 1+recursion ni karoge, kyuki 1 + recu krne ka mtlb
        ye nikal raha hai ki har step count karna hai.
    j. But humko to upar tak chadna hai by combination of steps to hum kitni bar 0 tak pohoche uss time
        humko 1 add krna hai.
    k. Abhi baat aati hai memoization ki: to isko aise smjho ki tumne recursion krke subproblem me break kar diya
    l. Abhi har recursion tumko us recrusion cycle ki n value ke liye addition of climb1 and climb2 return karega.
    m. To tumko usi n ke liye memo[n] = climb1+climb2 karna hai and is ko recursion me check krke return krna hai.

Code dekh lo neeche for your reference agar smjh ni aa raha to.
*/


class Solution {
    public int climbStairs(int n) {
        int memo[] = new int[n+1];
        return helper(n, memo);
    }


    public int helper(int n, int memo[]){
        if(n == 0){
            return 1;
        }

        if(memo[n] != 0) return memo[n];

        int climbOne = 0;
        int climbTwo = 0;

        if(n >= 1)
            climbOne += helper(n-1, memo);

        if(n >= 2)
            climbTwo += helper(n-2, memo);

        return memo[n] = climbOne+climbTwo;
    }

}