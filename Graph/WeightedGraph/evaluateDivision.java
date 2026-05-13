/*
You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.

 

Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation: 
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
note: x is undefined => -1.0
Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
Example 3:

Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]


Intution:
1. Bhai ye ques start karta hai weighted graph ka concept.
2. Ques bohot simple hai, but isme data structure kese use krna hai vo thoda tricky hai.
3. Aao dekhe ques kya keh ra hai.
    a. ek array hai [a,b] and dusra array hai [2.0].
    b. Ye dono array mila kar mtlb nikalte hai ki a/b = 2.0;
    c. abhi let's say do array hai [a,b] and [b,c] and values array hai [2.0, 3.0].
    d. And ek aur array diya hai vo hai [a,c];
    e. ABhi tumko a,c nikalna hai. means a/c = kya??.
    f. Abhi tumko upar array diya hai, jo batata hai ki a/b ya keh lo a to be jane ke liye 2.0 
        value chahiye, and b to c jane ke liye 3.0 value chahiye.
    g. to fir a to c jana le liye follow krege ye path a -> b -> c.
    h. a->b ki values hai 2.0 and b->c ki values hai 3.0. Dono ko multiply karo as per ques.
        to milega 6.0 jo ki [a,c] ka answer hai.
4. Abhi ques clear hua hoga. And ye bhi clear hua hoga ki iss question me DFS lagega.
5. But DFS lagane se pehle humko graph banana padega kyuki values and array sab raw condition me hai
6. Unke beech ka relation establish krna hoga.
7. Uske liye hum ek map banaege, jo aisa rhega Map<String, Map<String, Double>> graph;
8. Abhi isko fill aise karege:
    a. Man lo array hai [a,b] and values [2.0].
    b. to map ki main key ho jaegi a and uske ander ek or map hai uski key ho jaegi b and value 2.0.
    c. to map kuch aisa dikhega: a={b=2.0}.
    d. Ek or array hota to jese ki [b,c] and value [3.0]. To map me aise add hota:
        {a={b=2.0}, b={c=3.0}}.
    f. Abhi ek or twist ye hai ki ques me sirf a,b nahi b,a bhi puch skta hai jiske liye tumko
        ulta bhi store krna pdega sath sath. Like [a,b] store kiya to [b,a] bhi store krna pdega.
    g. Abhi b,a ki value hogi 1.0/2.0 = 0.5. To map aise update hoga:
        {a={b=2.0}, b={c=3.0, a=0.5}}
    h. similary for c,b ke liye bhi hoga.
9. Abhi ye bana liya to ques simple hai:
    a. Queries array par for loop laga lo.
    b. Ques keh ra hai ki agar queries array me loop krte waqt agar kuch aisa mile jo main array me
        ni hai to -1.0 store kar do. To ye condition check kar lo by checking inside map.
    c. Then ye condition bhi hai ki man lo u and v equal hai and array me present hai to store 1.0
        in result array.
    d. abhi upar dono me se kuch ni to run dfs and find the product and store at result.
    e. ABhi dfs ki baat krte hai:
        i. Base condition rhegi if u == v then return product.
        ii. Then ek visited set lege taki cycle mile to abort kar sake.
        iii. visited set me u add kar do.
        iv. Then map.get(u) karke adjacent vertices and values nikal lo.
        v. Then us par loop chala do and check their if already visited hai ya ni.
        vi. If visited then skip else double ans = dfs(map, u, v, visited, product*map.getValue()).
        vii. if ans != -1.0 return ans.
        viii. Loop end time par return -1.0;

*/ 

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Map<String, Map<String, Double>> map = new HashMap();

        double res[] = new double[queries.size()];

        int count = 0;

        for(List<String> list : equations){
            String u = list.get(0);
            String v = list.get(1);

            double w = values[count++];

            map.computeIfAbsent(u, k -> new HashMap()).put(v, w);
            map.computeIfAbsent(v, k -> new HashMap()).put(u, 1/w);
        }

        for(int i = 0; i < queries.size(); i++){
            String u = queries.get(i).get(0);
            String v = queries.get(i).get(1);

            if(!map.containsKey(u) || !map.containsKey(v)){
                res[i] = -1.00000;
                continue;
            }

            if(u.equals(v)){
                res[i] = 1.000000;
                continue;
            }

            res[i] = dfs(u, v, map, new HashSet(), 1.0);
        }

        return res;  
    }

    public double dfs(String start, String end, Map<String, Map<String, Double>> map, Set<String> set, double product){
        if(start.equals(end)){
            return product;
        }

        Map<String, Double> temp = map.get(start);

        set.add(start);

        for(Map.Entry<String, Double> entry : temp.entrySet()){
            if(!set.contains(entry.getKey())){
                // product *= entry.getValue();
                double ans = dfs(entry.getKey(), end, map, set, product*entry.getValue());

                if(ans != -1.0) return ans;
            }
        }
        return -1.0;
    }
}