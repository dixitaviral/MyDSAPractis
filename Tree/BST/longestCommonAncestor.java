/*
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

 

Example 1:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:


Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
Example 3:

Input: root = [1,2], p = 1, q = 2
Output: 1

Intution:
1. Bhai ye ques bhi easy hai bas thora sa dimag lagana hai.
2. longest common ancestor mtlb ki do nodes ke beech jo common node hai usko batana hai humko.
3. Abhi hum isme do recursion chalaege:
    a. ek left node vala recursion and ek right node vala recursion.
    b. Base condition hogi agar root left right jate jate null jo jae to return null.
    c. Dusri base condition hogi ki agar left right jate jate given p and q mil gaya hai to vahi se node return kar do.
    d. Abhi aati hai main baat.
        i. Jab p and q  mil jaege, to mtlb hue p and q ki root node hogi jisse dono mile hai to vo root node 
            return kar do.
        ii. p and q dono mile pata kese chalega vo aise pata lgega ki 
        iii. Agar left and right recursive call ki return values agar null ni hai to mtlb p and q dono mil gye.
        iv. Abhi man lo right side of tree me q ni mila but humko p mil gaya left me, that means q jo hoga
            p ki branch me neeche kahi hoga, that means hamara lca hue left.
        v. Similarly agar man lo left size of tree me p ni mila but right side me q mil gaya to lca hua right.
*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);

        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null) return root;

        if(left != null) return left;

        return right;
    }

    
}