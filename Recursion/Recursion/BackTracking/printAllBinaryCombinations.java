/*
Statement
Given an integer n, return all possible binary strings of length n.

Each string should consist only of '0' and '1'.

You must use recursion to generate the strings — not loops over combinations.

📌 Example
Input:
n = 2

Output:
["00", "01", "10", "11"]


Order doesn’t matter — just all valid combinations.

Intution:

1. Bhai ye bhi backtracking ka ques hai, jisme humko binary combinations nikalne hai.
2. Backtracking ke rules:  
    a. Ek loop zrur lagega jo given input ke ith element ke hisaab se usse associated value
        ko traverse karega.
    b. Dusra ye ki jab answer store ho jaega to current loop iteration end hone se pehle
        jo current iteration me answer me change hua hai let's string ke end me kuch add hua hai
        usko remove kr do, taki next answer ban pae.
    c. Result store hamesha base condition meet hone par hi hoga.
3. Abhi neeche solution me humne ek array banaya hai, vo bs isliye ki har digit ki value ko store
    kar pae, like a dictionary.
4. Then har iteration me ith element ke hisaab se uski value ko map se nikalna hai.
5. Then uss value ko iterate krna hai loop ke ander, and result banana hai
6. Since humko sare combinations banane hai uske liye humko input ke har element par jakr
    usse associate value ke letters ko current result me add karna hai.
7. Ye kuch aisa lagega ki:
    a. Ander jate waqt result banao.    
    b. Base condition aane par result store karo list me.
    c. Wapas aate waqt current iteration me jo letter last me add hua hai usko remove kar do.
8. Bas yahi intutiion hai, simple hai but agar smjh aae to else thoda tricky hai.


*/

class Solution {
    public List<String> generateBinaryCobinations(int n){
        List<String> res = new ArrayList();
        int arr[] = {0,1};

        create(res, new StringBuilder(""), ,0, n, arr);
        return res; 
    }

    public void create(List<String> res, StringBuilder result, int i, ,int n, in arr[]){
        if(i >= n){
            res.add(result.toString())
            return;
        }

        for(int a = 0; a < arr.length;a++){
            result.append(arr[a]);

            create(res, result, i+1, n, arr);

            result.deleteCharAt(result.length()-1)
        }

    }
}