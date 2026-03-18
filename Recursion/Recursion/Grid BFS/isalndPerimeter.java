/*

You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

 

Example 1:


Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
Output: 16
Explanation: The perimeter is the 16 yellow stripes in the image above.
Example 2:

Input: grid = [[1]]
Output: 4
Example 3:

Input: grid = [[1,0]]
Output: 4
 
Intution:
1. Bhai ek baat smjh lo, queue kaha use krni hai or kaha ni.
2. Jab grid ka har cell apni value khud de ra ho jese ki iss question me har cell ki jitni boundary water ya grid boundary se touch ho ri hai
    to perimeter me 1 add krna hai.
3. To isme humko queue ki zrurt ni hai, hum seedha
    a. grid traverse karege, and jaha par land mila means grid cell ki value 1 mili to hum 1 4 add kar dege permiter me.
    b. but uske bad hi ye check karege ki grid ki charo boundary agar land se touch ho ri hai to -2 kar dege.
    c. abhi sawal ye ki ye kese check karege.
    d. simple hai jis cell par khare ho, usi par check karo, agar i > 0 && [i-1][j] == 1 then perimeter - 2 and j > 0 && [i][j-1] == 1 then again perimeter - 2.
    e. AB tumhra ek sawal hoga, ki bhai i-1 and j-1 krne se sirf left and up ki trf check kiya humne, to total 2 lands mile, to permiter me har bar -1 hona
        chahiye. And tum -2 kar rahe ho.
    f. valid sawal hai, but hum left ya up dekh kar 2 is liye minus kar rahe hai, kyuki:
        i. man lo tumhra pehla cell hai [1,0] and logic ke according tumne left and up check kiya.
        ii. abhi man lo up me water hai to kuch minus ni hua, and left me boundary hi hai, to bhi kuch minus ni hua;
        iii. but right me land hai, kaide se minus hona chahiye tha, but ni kiya. Chalo koi ni. Loop aage badha.
        iv. abhi aa gye hum [1,1] par. abhi same [1,1] ka humne left check kiya, aur land mil gaya to humne -2 kar diya.
        v. ab smjho current cell me ke left me land tha to uska -1 and left cell ke right me mtlb current cell jo ki land hai uska -1
            dono mila kar kitne hua -2.
        vi. to mtlb ye nikla ki hum current cell par khade hokr jab left ya up dekh rahe hote hai.
        vii. to sath sath hum ye bhi dekh rahe hote hai ki left ya up cell ke adjacent means down and right me land hai kya and uske liye
            hum extra -1 krte hai, joki ho jata hai -2.
        viii. ab tum kahoge itna complex logic ki kya zrurt thi, to mai kahuga bhai, line of code kam ho jati hai.
        ix. tumko right most and down most check krne ki zrurt ni hai, kyuki obviously uske bad water hoga acc to ques, to tum kuch minus karoge.

    bss ho gaya questions.
*/


class Solution {
    public int islandPerimeter(int[][] grid) {

        int perimeter = 0;
        int rowLen = grid.length;
        int colLen = grid[0].length;

        for(int i = 0; i <rowLen; i++){
            for(int j = 0; j < colLen; j++){
                if(grid[i][j] == 1){
                    perimeter += 4;

                    if(i > 0 && grid[i-1][j] == 1) perimeter -= 2;
                    if(j > 0 && grid[i][j-1] == 1) perimeter -= 2;
                }
            }
        }

        return perimeter;
    }
}