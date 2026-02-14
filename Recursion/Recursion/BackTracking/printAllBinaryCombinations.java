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