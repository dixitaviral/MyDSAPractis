/*
You are given a doubly linked list, which contains nodes that have a next pointer, a previous pointer, and an additional child pointer. This child pointer may or may not point to a separate doubly linked list, also containing these special nodes. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure as shown in the example below.

Given the head of the first level of the list, flatten the list so that all the nodes appear in a single-level, doubly linked list. Let curr be a node with a child list. The nodes in the child list should appear after curr and before curr.next in the flattened list.

Return the head of the flattened list. The nodes in the list must have all of their child pointers set to null.

 

Example 1:


Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
Output: [1,2,3,7,8,11,12,9,10,4,5,6]
Explanation: The multilevel linked list in the input is shown.
After flattening the multilevel linked list it becomes:

Example 2:


Input: head = [1,2,null,3]
Output: [1,3,2]
Explanation: The multilevel linked list in the input is shown.
After flattening the multilevel linked list it becomes:

Example 3:

Input: head = []
Output: []
Explanation: There could be empty list in the input.

Intution:

1. Bhai ye ques easy hai par thora tricky keh skte ho.
2. Tumko is ques me linkedlist di hai jisme left right and child pointers hai.
3. Abhi ques simple ye keh ra hai ki jo child pointer se linklist nikal rahi hai, usko sab flat krke single
    linkedlist return karni hai.
4. Abhi isko krne ka simple tareeka hai, aao dekhe kya:
    a. Sabse pehle ek dfs start karo and usme head node pass kar lo, and new linkedlist start me null hogi to null
        pass kar do.
    b. Then dfs ke ander base condition par tumko check krna hai ki agar head node null hai to new linkedlist ki
        current node return kar do.
    c. Then start krte hai ek newNode banao and reference uska method me jo new linkedlist ka reference pas skiya hai
        usme store kar do.
    d. Then newNode me head ki val assign kar do.
    e. Ab iske bad tmhre pas do if-else block hoge:
        i. Dekho agar current given linkedlist node me, agar child != null hai to dfs call kar do head.child and
            newNode pass kar do as prev node.
        ii. abhi vo puri child node ko traverse krega and new node banate hue ek linear linkedlist bana dega.
        iii. Abhi child != null ka mtlb ye thori hai ki right null hoga, to child jo linear linkedlist banaega
            uski tail par jo current main linkedlist node ka right node hogi tail point karega vo.
        iv. to jab tail ka pura flow ho jaega uske bad while loop laga kar tail nikal lena child linkedlist ka
        v. abhi tail.next = dfs(head.right, tail) pass kar dege is call me bhi.

        vi. Then else block me aa jao, usme sirf right jana hai dfs me head.right and newNode pass kar do.

5. Abhi upar vala jo code hai vo lega O(n^2) and vo isliye kyuki child list ka tail nikalne ke liye vapas
    tum puri list traversa kar rahe ho.
6. Iska solution ye hai ki jab child vali linkedlist banaoge, to dfs method uske liye alag bana do jo ki
    tumko child linkedlist ka head and tail bhi return kare sath hi sath linkedlist bhi banae.
7. Ye solution mene implement kiya ni hai dobara kabhi ye ques karna tab implement karna.
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
        if(head == null) return null;
        return dfs(head, null);    
    }

    public Node dfs(Node node, Node prev){
        if(node == null) return null;

        Node newNode = new Node();

        newNode.val = node.val;
        newNode.prev = prev;
        newNode.next = null;
        newNode.child = null;

        if(node.child != null){
            
            newNode.next = dfs(node.child, newNode);

            Node tail = newNode.next;

            while(tail.next != null){
                tail = tail.next;
            }

            tail.next = dfs(node.next, tail);
        }else{
            newNode.next = dfs(node.next, newNode);
        }

        return newNode;
    }
}