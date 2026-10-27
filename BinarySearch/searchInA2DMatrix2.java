/*
Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
 

Example 1:


Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
Output: true
Example 2:


Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
Output: false
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= n, m <= 300
-109 <= matrix[i][j] <= 109
All the integers in each row are sorted in ascending order.
All the integers in each column are sorted in ascending order.
-109 <= target <= 109

Intution:

1. Bhai ye ques me tumko row and col dono sorted diye hai and tumko target find karna hai
    ki hai ya ni.
2. Abhi isko krne ke do tareeke hai:
    1. Pehli intution:
        a. Sabse pehli to mst binary search ki approach hai.
        b. Sari rows scan karo and har row par binary search laga kar dekho ki target 
            present hai ya ni.
        c. Bas acc result return kar do.
    2. Dusri approach kafi interesting hai:
        a. Isme tum matrix ke top - right corner mtlb first row last col cell se start karo.
        b. Abhi humko pata hai sare row col already sorted hai to usi ka fayda uthaege.
        c. Agar top-right ko hum current man le to check karo kya current target se bada hai.
        d. Agar ha to vo column skip kar do, kyu kyuki bhai col sorted hai and first number from
            top to bottom target se bada hai to neeche jaoge to or bada number hi milega. To col skip
            and row -- means left ki tarah move ho.
        e. Abhi agar jo current number hai vo chota hai target se means current se left gye row me
            to to or chote number hi milege to bhai left skip means row skip move down. means col --;
        f. aise krte krte ans mil jaega bro.

*/

// first solution:

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        for(int i = 0; i < matrix.length; i++){
            if(binarySearch(matrix[i], target)){
                return true;
            }
        }

        return false;
    }

    public boolean binarySearch(int arr[], int target){
        int i = 0;
        int j = arr.length-1;

        while(i <= j){
            int mid = (i+j)/2;

            if(arr[mid] == target){
                return true;
            }else if(arr[mid] < target){
                i = mid+1;
            }else{
                j = mid-1;
            }
        }

        return false;
    }
}

// second solution

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int lowRow = 0;
        int lowCol = 0;

        int highCol = matrix[0].length-1;
        int highRow = matrix.length-1;

        int row = 0;
        int col = highCol;

        while(row <= highRow && col >= lowCol){
            int current = matrix[row][col];

            if(current == target){
                return true;
            }else if(target > current){
                row++;
            }else{
                col--;
            }
        }

        return false;
    }
}