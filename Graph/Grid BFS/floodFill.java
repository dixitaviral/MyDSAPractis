/*
    You are given an image represented by an m x n grid of integers image, where image[i][j] represents the pixel value of the image. You are also given three integers sr, sc, and color. Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].

To perform a flood fill:

Begin with the starting pixel and change its color to color.
Perform the same process for each pixel that is directly adjacent (pixels that share a side with the original pixel, either horizontally or vertically) and shares the same color as the starting pixel.
Keep repeating this process by checking neighboring pixels of the updated pixels and modifying their color if it matches the original color of the starting pixel.
The process stops when there are no more adjacent pixels of the original color to update.
Return the modified image after performing the flood fill.

 

Example 1:

Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2

Output: [[2,2,2],[2,2,0],[2,0,1]]

Explanation:



From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.

Note the bottom corner is not colored 2, because it is not horizontally or vertically connected to the starting pixel.

Example 2:

Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0

Output: [[0,0,0],[0,0,0]]

Explanation:

The starting pixel is already colored with 0, which is the same as the target color. Therefore, no changes are made to the image.

 Intution:

 1. Bhai iski intution same hai baki grid BFS question se.
 2. Mai puri intutuin iss question me ni btauga.
 3. Kyuki agar tum iss question me struggle kar gye, iska mtlb BFS vapas padhna hai tumko.
 4. To steps ye hai:
    a. sbse pehle edge case dekh lo, ki color == image[sr][sc] to image return kar do.
    b. Abhi simple jo starting pixel diya hai sr and sc usko ek variable me store kar lo.
    c. then us starting cell ko sr and sc ko color se initialize kar do and queue me push kar do.
    d. then tumko queue ke ander char direction me BFS chalana hai and check krna hai ki koi cell ka color agar starting original color se match hota hai
    e. To usko bhi color se initialize kar do and queue me push kar do
    f. End me iamge return.
*/

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        if(color == image[sr][sc]) return image;
        int arr[][] = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};

        int rowLen = image.length;
        int colLen = image[0].length;

        Queue<int[]> queue = new ArrayDeque();

        queue.add(new int[]{sr, sc});

        int rang = image[sr][sc];

        image[sr][sc] = color;

        while(!queue.isEmpty()){
            int[] pair = queue.poll();

            int i = pair[0];
            int j = pair[1];

            for(int idx = 0; idx < arr.length; idx++){
                int row = i+arr[idx][0];
                int col = j+arr[idx][1];

                if(row < 0 || col < 0 || row >= rowLen || col >= colLen) continue;

                if(image[row][col] == rang){
                    image[row][col] = color;
                    queue.add(new int[]{row, col});
                }
            }
        }

        return image;
    }
}