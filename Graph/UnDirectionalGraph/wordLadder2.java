/*
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].

 

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

Intution:
1. Bhai ye ques bhi same as word ladder hai. 
2. Agar ques samjhna hai to neeche padho:
    a. Ques simply ye keh ra hai ki tmko ek begin word diya hai and ek end word diya hai.
    b. And sath me ek word list di hai jisme kuch or words hai. Tumko ek path return karna hai
    shortest path, jisme hoga ye ki beginWord ke har letter ko change karege every possible alphabet
    se and match karna hai ki kya vo word word list me hai ya ni.
    c. Agar hai vo word to usko vapas se fir change kar har letter ko every alphabet se.
    for example str = cat, so sabse pehle c ko change karege a to z to banega
    aat, bat, cat,...  then change karege mid letter ko to banega
    cat, cbt, cct,... then change karege last letter ko to banega
    caa, cab, cac,... and check karna hai ki kya ye word word list me
    d. Abhi isko aisa smjho ki ye jo valid words hoge, valid words mtlb jo ki word list me present hai.
    e. Vo hai nodes, and hum un words se dusre valid words me transition kar rahe hai,
    so that transition is nothing but edges.
    f. Plus humko shortest transition path chaiye, and jaha bat shortest path ki aati hai
    hum seedha BFS lagate hai.
3. To aao fir intution dekhte hai:
    a. word ladder me keh ra tha shortest transformation path ka count batao.
    b. But isme keh ra hai sare shortest transformation path batao.
    c. Abhi ye karne ke liye pehle to humko ebginword ke all possible combinations or words banane
        padege and then un words me check karna hoga konsa hamare pas hai word list me.
    d. Jo present hoga uski list ya set bana kar return kar dege bfe ke ander.
    e. Abhi yaha se main baat start hoti hai ki humko jitne possible words the jo ki word list me present the vo mil gaye hai.
    f. Abhi all possible shortest transformation path nikaloge jab to aise niklega ki:
        i. man lo word hai cat, usse tumko mila bat and dat jo ki word list present hai man lo.
        ii. Abhi abhi jo bat word se aage jo chain banegi ye ek branch ho gyi jisse humko endword mil sakta hai.
        iii. and then dat word vali jo branch hogi usse humko ek or branch milegi jo dusra path ho sakta hai.
        iv. Aise hi or bhi paths ban sakte hai isko humko list me store karana hai, and if endword mil jae to 
            to result list me store kar do.
    g. Abhi main baat aati hai ques ka main logic to tumne bana diya hai, but result banane ka logic kese banaoge.
*/