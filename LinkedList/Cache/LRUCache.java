/*
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.

 

Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4

Intution:

1. Bhai dekho idea simple hai LRU cache banane ka.
2. LRU cache mtlb hota hai least recently used, ki bhai jo sabse kam use hua hai, cache bhar 
    jane par element jo sabse kam use hua hai usko hata do and new add kar do.
3. Existing key hai but value change hui hai to value update kar do and abhi sabse jada updated
    value jo sabse recently fetch ki gayi hai vo recent me move ho jaegi.
4. Abhi concept kya hai aao smjhe:
    1. Dekho do data structure use karege ek hoga hashmap and ek hoga doubly linkedlist.
    2. Abhi hashmap me hum store karege key and Node as value. 
    3. Also node ki class structure me hum key, value, left and right ye elements hoge.
    4. Abhi linkedlist use krne ka concept ye hai, ki hum last me most recently used
        node add karege and start side me least recently used node hogi.
    5. To jab koi new node add karni hogi to last me karege agar cache full hai to node head
        hataege. Agar beech me hai node and fetch ki gyi hai to usko utha kar last me 
        add kr dege.
    5. Abhi simple do methods hoge isme get and put.
    6. Pehle put method ka implementation dekhte hai:
        a. Sabse pehla check to hum ye lagaege ki agar cache me same key se value already 
            present hai to do case ho skte hai:
            i. Ya to value different hai, to operations hoge:
                a. Update value of that already existing node.
                b. Move that node to the end of doubly linkedList.
            ii. Ya to value same hai, to sirf ek operation hoga:
                a. Move that node to the end of doubly linkedlist.
        b. Dusra else condition me aaege ki key already present ni hai, to abhi do conditions
            hogi, kyuki key add krni hogi:
            i. Pehli condition hai ki agar cache ki capacity full ho gyi hai to fir 
                ye check krna hai ki capacity kitni hai:
                a. Agar capacity 1 hai to iska mtlb hai head bhi yahi hai node bhi yahi hai.
                b. Jiska mtlb hum ye node ko hashmap se remove kar dege and new node banaege
                    with new value and left right as null and head and tail me is new node
                    ko point karege and hashmap me ye nayi node ki entry kar dege and yaha 
                    se return.
                c. Else capacity 1 se zada hai to simple head ko head.right se point krva dege.
                d. Then new head.left ko null kar dege and tail.right me new node ko point kar 
                    dege.
                e. node.left me tail point kar dege and last me tail ko new node se point krva dege.
            ii. Dusri condition hai else me aa jati hai ki cache khali hai abhi, isme bhi do condition
                hai:
                a. Pehli ye ki agar cache empty hai to new node banao and head tail ko same node se
                    point karvao and same node ki entry hashmap me karo with key.
                b. Dusri ye ki cache empty ni hai, to msst tail.right me new node add kar do, new node
                    ke left me tail kar do. Then tail ko point kar do new node se. Also hashmap me entry
                    kar do.
    7. Ab bat krte hai get method ki, isme do conditions hai:
        a. Pehli ki agar cache me key present hai, to simple hashmap se key ke through
            node fetch kar lo. Then jo node fetch hui hai, usko end me move kar do.
        b. Dusri condition ye hai ki agar entry ni hai hashmap me, that means value
            not present to return -1.
    8. Abhi component reh gaya jo put and get dono me use hota hai and that is "moveToEnd".
        a. jab put method me key already present hoti hai tab hum move to end krte hai and
            get me jab key present hai to usko fetch krte time bhi move to end krte hai.
        b. To humne iska ek method bana diya, aoo dekhe:
            a. Method parameter hoga key and value. Value kyu aage batata hu.
            b. Isme sabse pehle hum key ke through node fetch kar lege.
            c. Dusri statement hua ki us node ki value ko method me passed value se update kr
                do.
            d. Abhi scene ye hai ki aisa kyu, kyuki kayi bar same key ke sath different value
                bhi pass ho skti hai to value change ho jati hai isliye rest agar same hai
                to override ho jaegi.
            e. Abhi teen conditions aati hai:
                i. Hamara main goal hai end me move krna node ko, to pehli conditon yahi hogi
                    ki agar fetched node already tail hai to same return kar do. Man lo value
                    update ki need hai to humne pehle hi kar diya hai.
                ii. Abhi dusri condition hui ki fetched node jo end me move krni hai vo head hai
                    , is case me hum head ko next node par point kr dege and us next node ka
                    left ya prev null kar dege as it has become new head now. And then alag hui
                    node ko tail me add kar dege.
                iii. Last condition hai ki node beech me hai to uske liye hum beech se node todege
                    beech vali node ke left and right node ko connect karege pehle. Then tooti
                    node ko tail me add karege.
                iv. at last dont forget to update tail to newly added node at the end.

Bas itni kahani hai, hai easy but condition wise lengthy hai.

*/

class LRUCache {

    Map<Integer, Node> cache;
    int capacity;
    Node head = null;
    Node tail = null;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap();
    }
    
    public int get(int key) {
        if(cache.containsKey(key)){
            int val = cache.get(key).val;
            moveToEnd(key, val);
            return val;
        }else{
            return -1;
        }
    }

    private void moveToEnd(int key, int value){
        Node temp = cache.get(key);
        temp.val = value;

        if(temp == tail){
            return;
        }

        if(temp == head){
            head = head.right;
            head.left = null;
        }else{
            temp.left.right = temp.right;
            temp.right.left = temp.left;
        }
        temp.left = tail;
        tail.right = temp;
        temp.right = null;
        tail = temp;
    }
    
    public void put(int key, int value) {
        if(cache.containsKey(key)){
            moveToEnd(key, value);
        }else{
            if(cache.size() == capacity){
                int keyTorRemove = head.key;
                cache.remove(keyTorRemove);
                if(capacity == 1){
                    Node node = new Node(key, value, null, null);
                    head = node;
                    tail = node;
                    cache.put(key, node);
                    return;
                }

                Node node = new Node(key, value, tail, null);
                head = head.right;
                head.left = null;

                cache.put(key, node);
                tail.right = node;

                tail = node;
            }else{
                if(cache.isEmpty()){
                    Node node = new Node(key, value, null, null);
                    head = node;
                    tail = node;

                    cache.put(key, node);
                }else{
                    Node node = new Node(key, value, tail, null);
                    tail.right = node;
                    tail = node;
                    cache.put(key, node);
                }
            }
        }
    }
}

class Node{
    int val;
    int key;
    Node left;
    Node right;


    public Node(int key, int val, Node left, Node right){
        this.key = key;
        this.val = val;
        this.right = right;
        this.left = left;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */