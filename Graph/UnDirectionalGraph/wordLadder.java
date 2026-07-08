/*
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

 

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

Intution:
1. Bhai sabse pehle to ye ques simple hai dekhne me thora tricky lagta hai.
2. Ques simply ye keh ra hai ki tmko ek begin word diya hai and ek end word diya hai.
3. And sath me ek word list di hai jisme kuch or words hai. Tumko ek path return karna hai
    shortest path, jisme hoga ye ki beginWord ke har letter ko change karege every possible alphabet
    se and match karna hai ki kya vo word word list me hai ya ni.
4. Agar hai vo word to usko vapas se fir change kar har letter ko every alphabet se.
    for example str = cat, so sabse pehle c ko change karege a to z to banega
    aat, bat, cat,...  then change karege mid letter ko to banega
    cat, cbt, cct,... then change karege last letter ko to banega
    caa, cab, cac,... and check karna hai ki kya ye word word list me
5. Abhi isko aisa smjho ki ye jo valid words hoge, valid words mtlb jo ki word list me present hai.
6. Vo hai nodes, and hum un words se dusre valid words me transition kar rahe hai,
    so that transition is nothing but edges.
7. Plus humko shortest transition path chaiye, and jaha bat shortest path ki aati hai
    hum seedha BFS lagate hai.
8. To aao fir intution dekhte hai:
    a. Sabse pehla kaam humko word list ko set me convert krna hai, taki o(1) me check kar sake
        ki koi word present hai ya ni.
    b. Then ek queue banaege, jisme beginWord add kar dege.
    c. Also humko ek visited set bhi chahiye, ki jo word hum already use kar chuke hai,
        vo dobara use na kare.
    d. then humko ek function chaihiye jo basiaclly humko set of all possible words return krega.
    e. Ab vo function khud se banana basic coding hai vo ni batauga mai.
    f. then vo set of all possible words me se jo words list me present hai, unko queue, me add kar
        do.
    g. Abhi humko ek count variable chahiye jo ki level count karega. Mtlb ki ek level me 
       queue me jitne bhi words hoge vo process ho jae then count badhaege.
    h. And jis level par humko endWord mil jae, to level count +1 return kar dege.
    i. LevelCount+1 isliye kyuki bfs se pehle hi hum return karege, but jo endWord hoga vo uss BFS 
        traversal me aaega then count badhege, isse accha pehle count badha do and
        return kar do.
Bonus tips:
1. Isme ek scene hai ki jab create function banaoge jo set of all possible words banaega.
2. Usme se tum sirf return vahi karoge jo word list me present hai, jo ni hai vo ignore kar do.
3. and then queue me direct add kar do unhi ko.
*/

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new ArrayDeque();
        
        Set<String> set = new HashSet(wordList);

        if(!set.contains(endWord)) return 0;

        Set<String> visited = new HashSet();

        queue.add(beginWord);
        visited.add(beginWord);

        int count = 0;

        while(!queue.isEmpty()){

            int size = queue.size();

            while(size-- > 0){
                String str = queue.poll();

                if(str.equals(endWord)){
                    count++;
                    return count;
                }

                Set<String> newSet = create(str, set);

                for(String word : newSet){
                    if(!visited.contains(word)){
                        visited.add(word);
                        queue.add(word);
                    }
                }
            }
            count++;
        }

        return 0;

    }

    public Set<String> create(String word, Set<String> hashSet){
        Set<String> set = new HashSet();

        for(int i = 0; i < word.length(); i++){
            for(char j = 'a'; j <= 'z'; j++){
                char arr[] = word.toCharArray();
                arr[i] = j;

                String str = new String(arr);
                
                if(hashSet.contains(str))
                    set.add(new String(arr));
            }
        }

        return set;
    }


}