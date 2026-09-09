/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 

Example 1:


Input: root = [1,2,3,null,null,4,5]
Output: [1,2,3,null,null,4,5]
Example 2:

Input: root = []
Output: []
 

Constraints:

The number of nodes in the tree is in the range [0, 104].
-1000 <= Node.val <= 1000

Intution:

1. Bhai ques hai easy bas ek choti si trick hai aao dekhe.
2. Ques me simple ye keh raha hai ki preorder traversal karo tree ka pehle and ek string bana lo.
3. Ye cheez tum karoge serialize method me.
4. Abhi deserialize method me tumko isi string ko traverse krke same tree banana hai.
5. Man lo tmhre pas tree hai:
                    1
                   / \
                  2   3
                     / \
                    4   5

    a. abhi humne kaha ki humko as per ques inorder traversal karna hai. Preorder traversal mtlb root - left - right.
    b. To upar vale tree ka preorder traversal rhega jo ki tm sochoge 12345.
    c. Mene kaha abhi upar vala tree bhul jao and 12345 se tree banao or batao kesa banega
    d. Tumne kaha theek abhi lo:
                    1
                   / \
                  2   3
                     / \
                    4   5
    f. accha agar tum ye tree banao to galat hai kya?
                    1
                   / \
                  2   3
                 / \
                4   5       
                
    g. Ab batao ye to ambiguity ho gyi. To iska mtlb tumne kuch miss kiya jo ki hai null nodes.
    h. Abhi tum agar null add kar do ki 123nullnull45. Ab tree banao same first vale jesa tree banega.
    i. isliye null information bohot crucial hai. Abhi agar tree banaoge to 123 means 1 root 2 left 3 right.
    j. Abhi 2 ke neeche null hai to 45 3 ke neeche add hoge.
6. Bas yahi dhyan rakha na, ab aao intution dekh le:
    a. Sabse crucial part ques ka hai ki tumko isme bfs karna hai means queue lagegi.
    b. Then tumko Queue lena hai but object linkedlist ka banana hai hai, kyuki ArrayDeque tumko 
        null store krne nahi dega. Sath me ek stringbuilder bana lo jisme string store kaorge.
    c. Serialize method intution dekhte hai:
        i. root ko queue me add kar do.
        ii. Then while loop chalao until queue is not empty.
        iii. Then queue se node poll karo:
            a. Agar node null hai to string me null in from of string add kar do.
            b. Agar null nahi hai to node.val ko string builder me append karo, queue me node.left and node.right
                add kar do.
            c. Dhyan rahe comman zaruri hai har string add karne ke bad man lo number hua 11 to comma spearated rahega
                to 11 pura fetch hoga deserialize krte time ni to 1 1 alag ho jaege.
            d. Bas fir string return kar do.
    d. Abhi dekhte hai deserialize ki intution:
        a. Sabse pehle tum string ko split kar do with comma.
        b. Then Vapas se ek queue lelo and string array jo banaya hai split krke uska 0th element ki ek
            treenode bana do and usko queue me add kar do.
        c. then ek index lelo jo arr iterate karega starting from 1 kyuki 0 already le chuke hai hum.
        d. Abhi bfs chala do and inside that check kar lo agar ith element of array is not null then
            then ek treenode banao usme ith element add karo and curr polled node ke left me assign kar do.
            and last me queue me add kar do.
        e. After this i++, as prev element already taken care by left node or being null it is rejected.
        f. Then similarly for right node, check if i < arr length and is not null if yes then new treenode
            assign ith value inside treenode val and map it to the rifht of curr node.
        g. then add it into the queue, so that further number as child add hote rahe.
        h. end me i++ so that while loop continue ho to next element par ho.

bas itna hi krna hai.

*/

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "null";

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();

            if(curr == null){
                sb.append("null,");
                continue;
            }

            sb.append(curr.val).append(",");
            queue.add(curr.left);
            queue.add(curr.right);
            
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("null")) return null;

        String arr[] = data.split(",");

        Queue<TreeNode> queue = new ArrayDeque<>();

        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        queue.add(root);

        int i = 1;

        while (!queue.isEmpty() && i < data.length()) {

            TreeNode temp = queue.poll();

            // left
            if (!arr[i].equals( "null")) {
                temp.left = new TreeNode(Integer.parseInt(arr[i]));
                queue.add(temp.left);
            }
            i++;

            // right
            if (i < data.length() && !arr[i].equals( "null")) {
                temp.right = new TreeNode(Integer.parseInt(arr[i]));
                queue.add(temp.right);
            }
            i++;
        }

        return root;
    }
}