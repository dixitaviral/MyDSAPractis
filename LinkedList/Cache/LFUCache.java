/*
Design and implement a data structure for a Least Frequently Used (LFU) cache.

Implement the LFUCache class:

LFUCache(int capacity) Initializes the object with the capacity of the data structure.
int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.

When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.

The functions get and put must each run in O(1) average time complexity.

 

Example 1:

Input
["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

Explanation
// cnt(x) = the use counter for key x
// cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
LFUCache lfu = new LFUCache(2);
lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
lfu.get(1);      // return 1
                 // cache=[1,2], cnt(2)=1, cnt(1)=2
lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
                 // cache=[3,1], cnt(3)=1, cnt(1)=2
lfu.get(2);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,1], cnt(3)=2, cnt(1)=2
lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
                 // cache=[4,3], cnt(4)=1, cnt(3)=2
lfu.get(1);      // return -1 (not found)
lfu.get(3);      // return 3
                 // cache=[3,4], cnt(4)=1, cnt(3)=3
lfu.get(4);      // return 4
                 // cache=[4,3], cnt(4)=2, cnt(3)=3


Intution:
1. Bhai iska code mene pura ni likha hai kyuki kafi lengthy tha, tum kisi din try kar lena but concept smjh liya hai.
2. To concept dekhte hai:
    a. LFU basically kehta hai:
        i.  Vo node cache full hone par bahr niklegi, jiski frequency sabse kam hai mtlb  jo sabse kam bar fetch kiya gaya hai.
        ii. Agar man lo do entries ki frequency same rahi, to abhi konsa bahr nikaloge cache full hone par, to vo uss case me
            least recently used jo hoga usko nikalege.
    b. Abhi bat krte hai data structures kese use karege jo humko O(1) me return kar de value:
        i. Sabse pehle to same cache ke liye hashmap lenge jisme key and Node rakhege.
        ii. Dusra is case me ek or map lege jiska nam rakhege freq, isme hum store karege freq and Linkedlist ka head and tail.
        iii. Third hoga node, jisme variables rahege:
            i. value
            ii. key
            iii. freq
            iv. left
            v. right
    c. Ab bat krte hai concept ki ki use kese karege isko:
        i. Dekho bhai sabse pehle to humko ye samjhna hai LFU basiaclly LRU ka extended version hai.
        ii. LRU me recently used ke hisaab se nikalte the, isme hum pehle frequently used ke hisaab se nikalege
        iii. then agar same frequency hui to recently used ke hisaab se nikalege.
    d. Abhi aao dekhe use kese karege:
        i. Concept simple hai , hum frequency bucket maintain karege ki man lo 1 frequency vali jitni nodes hai
            sabke ek bucket me similarly 2 3 4 and so on....:
            map will look like

            1, freq1->freq1->freq1...
            2. freq2->freg2->freq2...
            

            freq1 and freq2 nodes hai jinki frequency 1 and 2 hai
        ii. Dusra hai simple cache ki ye key hai and ye key is node me stored hai.
        iii. Abhi node ke pas info hai ki meri kya key and meri kya freq hai, so that jab bhi koi key ke through 
            value fetch kare to node ke pas sari information hai ki node konsi freq bucket me hai.
        iv. ABhi kisi value ko cache me dalege to humko do jagah entry krni hai ek cache me and ek frequency map me
        v. freq map me first time entry krte time 1 frequency se start karege and jitni new nodes ke liye put
            call hoga sab isi 1 freq bucket me already existing node ke peeche add hoti rhegi.
        vi. Abhi man lo kisi value par get hua, to usko vapas se fetch kiya gaya, is case me hum simple cache me se
            node nikalege, node me se freq nikalege and us frequency bucket se vo node hata dege and
            us node ki freq badha dege and freq map me new bucket banaege from that new freq and that node.
        vii. Abhi isi case me man lo koi node freq same bhi nikal aai, to freq bucket me jo linkedlist hai
            vo basically lru par hi bani hai usme se hum node ki value return krke node head par ho ya tail par
            ya mid me usko tail par move kar dege.
        viii. Abhi concept hai ki agar cache ful ho gyi to hataege kese, uske liye hum ek minfreq krke variable
            lege jo ki humko bataega ki abhi tak ki sabse kam freq knsi hai
        ix. Jab cache full hoga tab is minfreq value vali bucket se hum pehli node hata dege, to cache me jagah ho jaegi
            and same humko cache me se hatani hogi.

to exact code to ni smjh skta kyuki bohot lengthy hai, but concept bata diya hai tumko.

Ek tip hai doabara jab karna tab isko method me tod kar karna like moveNodeToCorrectFreqBucket, addNodetoFreqBucket, removeFromFreqBucket
aise krke.

Abhi code neeche likha hai
*/

import java.util.*;

class LFUCache {

    int capacity;

    // key -> Node
    Map<Integer, Node> cache = new HashMap<>();

    // frequency -> {head, tail}
    Map<Integer, Node[]> freq = new HashMap<>();

    int minfreq = Integer.MAX_VALUE;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {

        if (!cache.containsKey(key)) {
            return -1;
        }

        Node node = cache.get(key);

        moveNodeToCrctFreqBckt(node);

        return node.val;
    }


    // Existing node ki frequency increase karne ke liye
    private void moveNodeToCrctFreqBckt(Node node) {

        int oldFreq = node.freq;

        // old frequency bucket se remove
        removeNodeFromFreq(node, oldFreq);

        // agar old bucket empty ho gayi
        if (!freq.containsKey(oldFreq) && minfreq == oldFreq) {
            minfreq++;
        }

        // frequency increase
        node.freq++;

        // new frequency bucket me add
        addNodeToFreq(node, node.freq);
    }


    // Node ko uske frequency bucket me tail par add karega
    private void addNodeToFreq(Node node, int frequency) {

        node.left = null;
        node.right = null;

        // Agar frequency bucket exist nahi karti
        if (!freq.containsKey(frequency)) {

            freq.put(frequency, new Node[]{node, node});
            return;
        }

        Node[] freqNode = freq.get(frequency);

        Node head = freqNode[0];
        Node tail = freqNode[1];

        // tail ke baad new node
        tail.right = node;
        node.left = tail;

        // update tail
        freqNode[1] = node;

        freq.put(frequency, freqNode);
    }


    // Specific node ko uski frequency bucket se remove karega
    private void removeNodeFromFreq(Node node, int frequency) {

        Node[] freqNode = freq.get(frequency);

        Node head = freqNode[0];
        Node tail = freqNode[1];


        // Sirf ek node hai
        if (head == node && tail == node) {

            freq.remove(frequency);

        }

        // Node head hai
        else if (head == node) {

            freqNode[0] = node.right;

            node.right.left = null;

            freq.put(frequency, freqNode);

        }

        // Node tail hai
        else if (tail == node) {

            freqNode[1] = node.left;

            node.left.right = null;

            freq.put(frequency, freqNode);

        }

        // Node beech me hai
        else {

            node.left.right = node.right;
            node.right.left = node.left;

        }

        node.left = null;
        node.right = null;
    }


    public void put(int key, int value) {

        if (capacity == 0) {
            return;
        }


        // Existing node
        if (cache.containsKey(key)) {

            Node node = cache.get(key);

            node.val = value;

            moveNodeToCrctFreqBckt(node);

            return;
        }


        // New node ke liye space nahi hai
        if (cache.size() == capacity) {

            Node[] freqNode = freq.get(minfreq);

            // minFreq bucket ka head remove hoga
            // because head = least recently used
            Node nodeToRemove = freqNode[0];

            removeNodeFromFreq(nodeToRemove, minfreq);

            cache.remove(nodeToRemove.key);
        }


        // New node always frequency 1 se start hogi
        Node node = new Node(value, key, 1, null, null);

        cache.put(key, node);

        addNodeToFreq(node, 1);

        // New node freq 1 par hai, so minimum bhi 1
        minfreq = 1;
    }
}


class Node {

    int val;
    int key;
    int freq;

    Node left;
    Node right;


    public Node(int val, int key, int freq, Node left, Node right) {

        this.val = val;
        this.key = key;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}