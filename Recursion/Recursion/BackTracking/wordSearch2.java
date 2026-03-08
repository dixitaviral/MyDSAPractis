/*
    Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

Example 1:


Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
Example 2:


Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []

Intution:

1. Bhai ye ques same word search ki tarah hai, aao uske steps dekh lete hai:
    a. Ek word hai, usko iterate krte hai.
    b. Then word ke har character ke liye, ek cell pakadte hai matrix ka.
    c. Then 4 direction me flow karege up down left and right.
    d. Bas last ne word ke iterator ka index word ki length ke equal ho jae to bss true return.
2. Abhi iss ques me same hi krna hai but isme ek word ki jagah ek word array diya hai, abhi 
    humko vo sare word return krne hai jo present hai board me.
3. Isko hum same kr skte hai, ek outer loop or badha kar jo ki word array iterate karega and har
    word ko pass karega dfs recursion call ke ander then same process jo point 1 ke subpoints me 
    dekha hai.
4. But scene ye hai ki bhai socho teen loop har word ke liye hum same process krege time complexity
    bohot badh jaegi.
5. Toh hum is ques me Trie data srructure use krege, ye built in data structure ni hai, but kafi jagah use hota hai
    like google search autosuggestions.
6. Abhi thora trie ko dekh lete hai. Trie simple do variable ki class/object hota hai. Apne hisaab se or variable
    add kr skte ho acc to .
7. Chalo trie class dekhte hai neeche banai hai, but smjhte hai abhi:

idea dekho pehle

man words hai -> eat and each.
    inko aise store kr skte hai:
        e, null
          a, null
            t, eat
            c, null
              h, each

isko TrieNode Object like dekho to aisa dikhega

  0(a) 1(b) 2(c) 3(d) 4(e) ....25
[ null null null null obj1 ...   ], word = null
                         |
                        \/
  0(a) 1(b) 2(c) 3(d) 4(e) ....25
[ obj2 null null null null ...   ], word = null
    |
   \/
  0(a) 1(b) 2(c) 3(d) 4(e) .... 19(t)...25
[ null null obj3 null null ...  obj4....], word = eat
             |
            \/
  0(a) 1(b) 2(c) 3(d) 4(e) .... 7(h)...25
[ null null obj3 null null ...  obj5....], word = each


Smjh rahe ho, jo common unko dobara store ni kiya, and ek tree like structure bana diya.

Idea yahi hai, ki pehle trie dictionary bana lete hai, then har cell lege and check karege ki current cell path 
means current se up ya down ya left ya right, karte karte agar kisi TrieNode character object par word != null. Bs 
mil gaya word and store kar lege.

Abhi baat ye ki word kese add karege, agar path match ho gaya, simple hai. TrieNode me 2 variable hai, ek array[26]
kyuki 26 characters hote hai alphabets me. and dusra variable hai word, jaha humko pata chalega trie me word store
krte time ki word store ho gaya hai, to last character ke sath word variable me vo word store kr dege, jese upar 
dikhaya hai.

Abhi Chalo trie smjhte hai.



class TrieNode{
    
    // current char mark karo with new TrieNode() object
    TrieNode trie[];
    String word;// if word ended, then last character ke sath word store kar li.

    public TrieNode(){
        trie = new TrieNode[26];// initialize with 26 space as there can be 26 characters which can come
        word = null;// initialize with null first.
    }

    public void insert(TrieNode root, String value){
        TrieNode temp = root;// take temp var instead of editing root.

        for(char c : value.toCharArray()){// traverse the word
            if(temp.trie[c-'a'] == null){// if current character has not appeared before
                temp.trie[c-'a'] = new TrieNode();// then mark that character index with new object
            }

            temp = temp.trie[c-'a'];// always re initialize the temp with current character object, so that
                                        next character can be skipped if same or a new object will be added if not.
        }

        temp.word = value; // when whole word is stored in Trie, then in last character object store the word.
    }
}

8. Abhi sirf itna karna hai:
    a. DFS chalaege, with params, board, i, j, result List, root trienode
    b. first check if i and j are not out or in flowing.
    c. then temp me board character store kar lo.
    d. then check if the character is already not visited by checking temp == '#' return;
    e. then check if TrieNode next store kar lo, kyuki root ke Trie me jo character index par object fill hoga
        vo humko information dega. So TrieNode next = root.trie[curr char - 'a'];
    f. then check if next == null then return.
    g. then check if next.word != null then res list. add(next.word) and next.word = null. Kyuki ghum kar hum fir isi
        path par aa skte hai, tab hum vapas se word store kar dege, to ek bar word store kar diya, then dobara na
        store ho isliye next.word = null.
    h. then mark the current cell visited, so that vapas vo cell current horizotal me use na ho. so board[i][j] = '#';
    h. abhi check in all four direction by recusion dfs() .... .
    i. then undo board[i][j] = temp(curr char)
*/

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        List<String> res = new ArrayList();
        for(String str : words)
            root.insert(root, str);

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                dfs(board, i, j, res, root);
            }
        }
        return res;
    }

    public void dfs(char [][] board, int i, int j, List<String> res, TrieNode root){
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length) return;

        if(board[i][j] == '#') return;

        char temp = board[i][j];

        TrieNode next = root.trie[temp-'a'];

        if(next == null) return;

        if(next.word != null){
            res.add(next.word);
            next.word = null;
        }

        board[i][j] = '#';

        dfs(board, i+1, j, res, root.trie[temp-'a']);
        dfs(board, i-1, j, res, root.trie[temp-'a']);
        dfs(board, i, j+1, res, root.trie[temp-'a']);
        dfs(board, i, j-1, res, root.trie[temp-'a']);

        board[i][j] = temp;
    }

}

class TrieNode{
    TrieNode trie[];
    String word;

    public TrieNode(){
        trie = new TrieNode[26];
        word = null;
    }

    public void insert(TrieNode root, String value){
        TrieNode temp = root;

        for(char c : value.toCharArray()){
            if(temp.trie[c-'a'] == null){
                temp.trie[c-'a'] = new TrieNode();
            }

            temp = temp.trie[c-'a'];
        }

        temp.word = value;
    }
}