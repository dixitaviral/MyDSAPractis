/*
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.

 

Example 1:


Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
Example 2:


Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

Intution:
1. Bhai ques easy hai, and smjhane ki zrurat to hai ni ki kya karna hai.
2. Seedha intution par jump krte hai:
    a. Dekho ek tree ka example lete hai:
                                            1
                                           / \
                                          2   3
                                         / \
                                        4   5
    b. Abhi cases dekhte hai kon kon se paths valid hoge:
        i. Sabse pehle to path ho skta hai left -> root -> right for example in above tree 4-2-5.
            a. But ye path kya tum calling recrsive tree ko return kr skte ho. Socho tumne 4-2-5 return kar diya
                1 ko to path kya bana

                                    1
                                   / 
                                  2
                                 / \
                                4   5
            b. Bhai 4-2-5 to theen hai but 2 ke upar 1 ye kesa path hua path linear ek line me hoga na 
                uski or branches kese hogi.
            c. Which means ye path 4-2-5 ko humko return ni krna hai bas agar ye path ki value max hai
                to ans me store kar lo.
        ii. Abhi dusre case ki baat krte hai:
            a. Abhi upar jo humne case discuss kiya hai usme humne left root right path discuss kiya tha
            b. Jo upar 1 ko edge ja ri hai usko humne kaha ye ni lege kyuki invalid path ban jaega. Vo kehna theek hai.
            c. Par path dekho ye 4-2-1 ye to linear line me hai and correct path hai na to isko to consider karna banta
                hai na.
            d. Chalo yaha tak smjh aaya theek hai, abhi ye dekho jab jo 1 hai vo root hai usme se neeche left and right
                koi ek path lena padega dono le ni skte kyuki path invalid ho jaega.
            e. Abhi left and right me se hoga konsa jisko root me add karege, vo hoga left and right me se jo bhi max
                hoga.
            f. to fir root.val + max(left, right).
        iii.Abhi third condition hai dekhte hai:
            a. Abhi thord condition ye aati hai ki right and left dono side negative value hai and 
                and root positiove to max root hi hoga.
            b. To uske liye humko max of root and left, right return krna hoga.
    c. To ye 3 condition hai abhi ek condition or hai jo ki hum kadane algo se lege ki negative values ko hum
        ni consider karege.
    d. To ab baat krte hai base condition ki vo hogi if (node == null) return null.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);

        return max;
    }

    public int dfs(TreeNode node){
        if(node == null) return 0;

        int left = Math.max(0, dfs(node.left));
        int right = Math.max(0, dfs(node.right));

        int curr = node.val + left + right;//1. considering left root right means curvy path

        max = Math.max(curr, max);

        int two = node.val + Math.max(left, right);// 2. considering to return up but in that case
                                                   //   only max of right and left will be considered

        return Math.max(node.val, two); //3. third condition will be if both left and right ans is lesser than root
                                        //   then consider root value only.
    }
}
