/*
There is a singly-linked list head and we want to delete a node node in it.

You are given the node to be deleted node. You will not be given access to the first node of head.

All the values of the linked list are unique, and it is guaranteed that the given node node is not the last node in the linked list.

Delete the given node. Note that by deleting the node, we do not mean removing it from memory. We mean:

The value of the given node should not exist in the linked list.
The number of nodes in the linked list should decrease by one.
All the values before node should be in the same order.
All the values after node should be in the same order.
Custom testing:

For the input, you should provide the entire linked list head and the node to be given node. node should not be the last node of the list and should be an actual node in the list.
We will build the linked list and pass the node to your function.
The output will be the entire list after calling your function.
Example 1:


Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
Example 2:


Input: head = [4,5,1,9], node = 1
Output: [4,5,9]
Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.

Intution:

1. Bhai ye ques dekhne me tricky hai par hai bohot easy.
2. Isme tumko bas itna karna hai ki node ki jo next node hogi uski
    value given node me dal do and then given node ke next me
    next node ka next dal do and next node ka next null kar do bas ho gaya

    1->2->3->4

    man lo 2 di hai given node for deletion
    2 ki jagah 3 kar do
    1->3->3->4

    abhi dusra vala 3 ka next means 4 usko prev 3 se connect kar do, and bache hue 3 ka next null kar do

    1->3->4 ho gaya.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        ListNode next = node.next;

        node.val = next.val;

        node.next = next.next;

        next.next = null;
    }
}

