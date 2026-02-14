/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.


2 → abc
3 → def
4 → ghi
5 → jkl
6 → mno
7 → pqrs
8 → tuv
9 → wxyz

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = "2"
Output: ["a","b","c"]

Intution:
    1. Bhai ab chalu hota hao tagda recursion and ye backtracking ka question hai:
        a. Backtracking ke rules:
            i. Ek loop zrur lagega jo given input ke ith element ke hisaab se usse associated value
                ko traverse karega.
            ii. Dusra ye ki jab answer store ho jaega to current loop iteration end hone se pehle
                jo current iteration me answer me change hua hai let's string ke end me kuch add hua hai
                usko remove kr do, taki next answer ban pae.
            iii. Result store hamesha base condition meet hone par hi hoga.
    2. Abhi neeche solution me humne ek map banaya hai, vo bs isliye ki har digit ki value ko store
        kar pae, like a dictionary.
    3. Then har iteration me ith element ke hisaab se uski value ko map se nikalna hai.
    4. Then uss value ko iterate krna hai loop ke ander, and result banana hai.
    5. Since humko sare combinations banane hai uske liye humko input ke har element par jakr
        usse associate value ke letters ko current result me add karna hai.
    6. Ye kuch aisa lagega ki:
        a. Ander jate waqt result banao.
        b. Base condition aane par result store karo list me.
        c. Wapas aate waqt current iteration me jo letter last me add hua hai usko remove kar do.
    7. Bas yahi intutiion hai, simple hai but agar smjh aae to else thoda tricky hai.
*/


class Solution {
    static StringBuilder result = new StringBuilder("");
    public List<String> letterCombinations(String digits) {
        Map<Character, String> map = new HashMap();


        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");

        List<String> res = new ArrayList();

        create(map, digits, res,0);
        return res;
    }

    public void create(Map<Character, String> map, String num, 
                                List<String> res, int i){
        
        if(i >= num.length()){
            res.add(result.toString());
            return;
        }

        String letters = map.get(num.charAt(i));

        for(int a = 0; a < letters.length(); a++){
            result.append(letters.charAt(a));

            create(map, num, res, i+1);

            result.deleteCharAt(result.length()-1);
        }
    }
 
}