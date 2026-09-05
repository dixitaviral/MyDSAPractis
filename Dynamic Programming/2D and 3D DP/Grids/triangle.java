/*
Given a triangle array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

 

Example 1:

Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
Example 2:

Input: triangle = [[-10]]
Output: -10
 
Intution:

1. Bhai ye ques bhi easy hai and min path sum jesa hai, bas isko dekh pakka fategi tmhri.
2. Fategi isliye kyuki bhai matrix ko jagah list di hai isme, but tension not iska
    solution tumhi ne nikala hai.
3. Isko dekh kar ye bhi mat kehna ki har row ka min nikal kar add kar dege and return kar dege.
4. Kyuki ques saaf keh raha hai ki prev row ka 1 element man lo 0th element hai to us 0th element ke bad
    next row se tum ya to same index ka element utha skte ho ya fir same index+1 utha skte ho.
5. Ye krte krte tumko last row tak jana hai and min path sum batana hai.
6. Agar tum min nikal kar karoge to bohot conditions hai jisme vo approach fail hoti hai.
7. To abhi isme main intution par aate hai ki karna kese hai:
    1. Sabse pehle yrr dekho base condition:
        a. Agar i == triangle.size() ho gaya to return kar do. But ye base condition abhi sense
            bana ri hai ek or batauga tab sense ni banaegi tum khud hata doge.
        b. Dusri base condition hai ki tum yrr last row me aa gye ho, iske bad koi row hai ni, also
            jo ques keh ra hai ki tum cur row ke kisi element se further next row ke i and i+1 ko access
            kar skte ho, but last row hai to next row hogi hi ni, hence condition aaegi:
            i == traingle.size() - 1 return traingle.get(i).get(j); J abhi batata aage kya hai.
        c. Abhi batao a base condition ki zrurt kyu hi padegi jab hum size - 1 par hi return kar rahe hai.
    2. Abhi aati hai logic ki baat:
        a. Sabse pehle nested list ko ek list variable me store kar lo with triangle.get(i).
        b. Abhi iske bad tmhre pas do choices hai next row ke liye but being inside current row.
        c. Ki tum i row ki kth element ke aage i+1 row se kth and k+1th element hi path me le skte ho.
        d. so ek zeroStep path hai means kth element and ek onestep path hai means k+1th element.
        e. to ye do choices par chala lo recursion list.get(j or k(kth element of curr row))+
            helper(triangle, i+1(next row), j(kth element to consider in zeroStep)) and second one
            list.get(j or kth element of curr row) + helper(triangle, i+1(next row), j+1(k+1)th element).
        f. Last me min of zeroStep and OneStep return kar do.
    3. AB aati hai memorization ki bat, to dekho isme list<List<Integer>> diya hai as input, to agar hum
        dp[][] matrix banate hai to ques keh raha hai ki negative values bhi hogi jiski vajah se hum
        -1 initialize ni kr skte.
    4. Uske liye hum dp banaege List<Integer[]> isme Integer array me default value null hai.
    5. To agar value null ni hai to mtlb dp.get(i)[j] return kar do else calculate karo.
    6. Similarly dp.get(i)[j] = Math.min(zeroStep, oneStep) return kar do at last.
*/

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        List<Integer[]> dp = new ArrayList();

        for(int i = 0; i < triangle.size(); i++){
            Integer arr[] = new Integer[triangle.get(i).size()];
            dp.add(arr);
        }

        return helper(triangle, 0, 0, dp);
    }

    public int helper(List<List<Integer>> triangle, int i, int j, List<Integer[]> dp){
        List<Integer> list = triangle.get(i);

        if(i == triangle.size() - 1) return list.get(j);

        if(dp.get(i)[j] != null) return dp.get(i)[j];

        int zeroStep = 0;
        int oneStep = 0;

        zeroStep = list.get(j) + helper(triangle, i+1,j, dp);

        oneStep = list.get(j) + helper(triangle, i+1, j+1, dp);

        return dp.get(i)[j] = Math.min(zeroStep, oneStep);
    }
}