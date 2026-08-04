/*
Problem Statement: You are given an n, m which means the row and column of the 2D matrix, and an array of size k denoting the number of operations. Matrix elements are 0 if there is water or 1 if there is land. Originally, the 2D matrix is all 0 which means there is no land in the matrix. The array has k operator(s) and each operator has two integers A[i][0], A[i][1] means that you can change the cell matrix[A[i][0]][A[i][1]] from sea to island. Return how many islands are there in the matrix after each operation. You need to return an array of size k.

Note: An island means a group of 1s such that they share a common side.

Pre-requisite: Disjoint Set data structure

Examples
Example 1:
Input Format: n = 4 m = 5 k = 4 A = {{1,1},{0,1},{3,3},{3,4}} 
Output: 1 1 2 2 
Explanation: The following illustration is the representation of the operation:

Example 2:
Input Format: n = 4 m = 5 k = 12 A = {{0,0},{0,0},{1,1},{1,0},{0,1},{0,3},{1,3},{0,4}, {3,2}, {2,2},{1,2}, {0,2}} 
Output: 1 1 2 1 1 2 2 2 3 3 1 1 
Explanation: If we follow the process like in example 1, we will get the above result.


*/

class Solution{
    public int[] numOfIslands(int n, int m, int k, int[][] arr){

        int matrix[][] = new int[n][m];

        int parent[] = 
    }

    public int find(int x, int parent[]){
        if(x != parent[x]){
            parent[x] = find(parent[x], parent);
        }

        return parent[x];
    }
}