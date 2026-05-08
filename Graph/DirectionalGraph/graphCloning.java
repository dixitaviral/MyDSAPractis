/*
Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}
 

Test case format:

For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.

An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

 

Example 1:


Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
Example 2:


Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
Example 3:

Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.

Intution:
1. Bhai ye ques tune ek bar me kiya hai, ye yad rakh.
2. Simple kuch ni krna hai, bas graph jo diya gaya hai uska clone banana hai. Same object return 
    ni kr skte kyuki, reference check hoga, usme fail ho jaega.
3. To bhai iski intution hai ki:
    a. Ek node di hai, uske do parts hai ek value and ek list of associated neighbours.
    b. Tera kaam itna hai ki:
        i. root node pas kr de DFS me.
        ii. Ek map bana le, jisme tu original node and uski clone ko store krega.
        iii. ABhi dfs me entry krte time check kar le ki jis node ka clone tu banane ja ra hai
            vo already map me hai kya, agar hai, return kar de uske clone ko.
        iv. Agar ni hai, to uska ek naya clone bana le, then usko map me rakh de.
        v. Then node jiska clone banaya tune, uske sare neighbours ki list par for loop chala de.
        vi. Then un for loop vali nodes ke liye, dfs chala de, jisme neighbour node par kar
            and vapas map pass kar de.
        vii. iss dfs call se jo return hoga vo current node jo outside for loop hai.
            uska child hoga, to simpel outside for loop vali jo cloned node banai hai tune.
        viii. Uske nighbour list me is child node ko add kar de.
        ix. For loop ke bahar abhi simple to current new node hai usko return kar de.
4. Khatam, ho gaya solve.
*/


// Definition for a Node.
// class Node {
//     public int val;
//     public List<Node> neighbors;
//     public Node() {
//         val = 0;
//         neighbors = new ArrayList<Node>();
//     }
//     public Node(int _val) {
//         val = _val;
//         neighbors = new ArrayList<Node>();
//     }
//     public Node(int _val, ArrayList<Node> _neighbors) {
//         val = _val;
//         neighbors = _neighbors;
//     }
// }


class Solution {
    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap();

        if(node == null) return null;

        return dfs(node, map);
    }

    public Node dfs(Node node, Map<Node, Node> map){
        if(map.containsKey(node)){
            return map.get(node);
        }

        int val = node.val;
        List<Node> neighbors = node.neighbors;
        Node newNode = new Node(val, new ArrayList());

        map.put(node, newNode);
        for(Node temp : neighbors){
            Node child = dfs(temp, map);

            newNode.neighbors.add(child);
        }

        return newNode;
    }
}