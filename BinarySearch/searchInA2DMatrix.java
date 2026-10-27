/*
You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.

 

Example 1:


Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
Example 2:


Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false

Intution:

1. Bhai ye ques me 2d matrix me tumko target number search krna hai.
2. Easy hai ques, ye keh ra hai ki bhai har row me left most is < right most and har row ka first 
    number will be lesser than the last number of prev row.
3. To bhai aao intution dekhe:
    a. Sabse pehle to ye pata karo ki target number kis row me hai, vo kese pata karoge aao dekhe:
        1. Sabse pehle row ka low and high nikalo and then binary search me uska mid nikalo.
        2. Then mid row check karo ki mid row ka left most number is lesser/equal and right most is 
        greater/equal, if yes then us row ko ek array me store krke naive binary search laga do.
        3. Abhi mid row me ni mila to check karo agar mid row ke left most se chota hai number to
            means number upar ki rows me hoga to high = mid - 1.
        4. Else low = mid+1.

Khatam

*/

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        int lowRow = 0;

        int highRow = matrix.length-1;
        int highCol = matrix[0].length-1;
        

        while(lowRow <= highRow){
            int midRow = (lowRow+highRow)/2;
            
            if(matrix[midRow][0] <= target && target <= matrix[midRow][highCol]){
                int arr[] = matrix[midRow];

                int i = 0;
                int j = arr.length-1;

                while(i <= j){
                    int mid = (i+j)/2;

                    if(arr[mid] == target) return true;
                    else if(target > arr[mid]){
                        i = mid+1;
                    }else{
                        j = mid-1;
                    }
                }

                return false;
                
            }else if(matrix[midRow][0] > target){
                highRow = midRow - 1;
            }else{
                lowRow = midRow + 1;
            }
            
        }

        return false;
    }
}