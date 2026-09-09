/*

A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
Your code will only be given the head of the original linked list.

 

Example 1:


Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
Example 2:


Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]
Example 3:



Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]
 

Constraints:

0 <= n <= 1000
-104 <= Node.val <= 104
Node.random is null or is pointing to some node in the linked list.

Intution:

1. Dekho bhai ques tumko smjh aa gaya hoga ki deep copy banani hai.
2. Abhi scene ye hai iski do approach hai, pehli O(n) vali hai but space lagega and dusri bhi O(n) vali hai but
    space ni lagega, aao dono dekhe:
    a. Pehle approach jisme space lagegi bohot straight forward and simple hai:
        i. Sabse pehle ek node,node ka ek hashmap bana lo.
        ii. Then ek while loop chalao usme simple original linkedlist ki har node ke liye ek copied node bana do
            and val dalte jao.
        iii. Then alag while loop chalao and usme vapas head se start karo and map me se head ki copied node nikalo
            then jo head ka next hoga uski associated bhi copied node map me padi hogi vo assign kar do.
        iv. Similarly head ki random node ke liye bhi koi copied random node hogi usko map se nikal kar assign kar do
        v. Bas khatam.
    b. Abhi dusri approach me humko extra space means hashmap ni use krna hai isme fir total 3 steps hai:
        i. Sabse pehle to tum original linkedlist me hi har node ke aage uski copied node bana do 
            A->A'->B->B'... smjhe mtlb copied node bana kar beech me lagate raho, while loop me.
        ii. Abhi first point ke bad sabki coped node ban gyi hogi to abhi bari aati hai, random node ki, to pehli
            copy node hogi head.next theek hai head.random.next jo hogi vo copy.random ke barabar hogi na, kyuki 
            har node ki copy uske just bad stored hai and head ke random ki copy bhi random node ke bad hogi.
        iii. Abhi simple copied list ko alag krna hai, ek copyHead node lelo usme head.next store kar do, kyuki
            head.next firts copied node hogi, then first copied node = head.next abhi head.next = copied.next
            and copied.next = head.next.next aise krke sabka hoga
        iv. At last return copy head.

*/


/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {

        if(head == null) return null;
        
        Node temp = head;

        // create a copy of each node
        while(temp != null){

            Node copy = new Node(temp.val);

            copy.next = temp.next;
            temp.next = copy;

            temp = copy.next;
        }

        // point the original random to copy random

        temp = head;

        while(temp != null){
            Node copy = temp.next;

            if(temp.random != null)
                copy.random = temp.random.next;

            temp = copy.next;
        }

        // separate copied one from original one

        temp = head;
        Node copyHead = temp.next;
        while(temp != null){
            Node copied = temp.next;

            temp.next = copied.next;

            if(temp.next != null)
            copied.next = temp.next.next;


            temp = temp.next;

        }
        
        return copyHead;
        
        
        // space solution below, non-space one is up
        
        // Map<Node, Node> map = new HashMap();
        // Node temp = head;

        // while(temp != null){
        //     map.put(temp, new Node(temp.val));

        //     temp = temp.next;
        // }

        // temp = head;

        // while(temp != null){
        //     Node copy = map.get(temp);

        //     copy.next = map.get(temp.next);
        //     copy.random = map.get(temp.random);

        //     temp = temp.next;
        // }

        // return map.get(head);
    }
}