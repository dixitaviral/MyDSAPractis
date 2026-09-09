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
    a. Bhai iski intution agar mai yaha explain karuga to kafi complex ho jaegi.
    b. Mai bas steps bata de raha hu, implementation agar yaad na aae to neeche soltuion me refer kar dena.
    c. Aao steps dekhe:
        i. Sabse pehle bfs chalao, valid words create karo and min distance nikalo and map banao.
        ii. Map ka kya scene hai, ki words transition me kon kon se words valid path se belong krte hai
            ye store karta hai.
        iii. Abhi map banane ke do tareeke hau neeche explain kiya hai, but in short agar tum, child to parent
            map banate ho to jada fayda hai, bajae parent to child ke.
        iv. Then tumko minDistance and map dfs me pass kar dena hai.
        v. Then DFS chala kar path store kar lo list me.
        vi. bas itna hi hai. Lekin implementation thora dhyan dekr karne vala hai.

*/



// BFS + DFS solution (Not optimised)

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet(wordList);

        if(!wordSet.contains(endWord)) return List.of();

        Queue<String> queue = new ArrayDeque();

        Set<String> outerVisited = new HashSet();

        Map<String, List<String>> map = new HashMap();

        List<List<String>> result = new ArrayList();

        queue.add(beginWord);

        outerVisited.add(beginWord);

        int minDistance = 1;
        // ye flag isliye use kar rahe taki inner level while loop ko jab break karege
        // tab ander vala hi break hoga, bahr vala bhi break karna hai
        // tabhi ander while loop ko break krte time, flag true kar dege, taki
        // bahr vala bhi break ho jae.
        boolean flag = false;


        // run BFS to create a map of valid next words and take out shortest distance
        // map why? kyuki map ban raha hai hit -> hot, then hot -> dot and lot
        // isko hum dfs me pass karege taki dfs sirf inhi words par scan kare.
        while(!queue.isEmpty()){
            int size = queue.size();

            // innervisited isliye lagaya hai, taki jab hume koi aisa case milta hai
            // ki man lo do words hai dog and log, abhi ye dono se humko endword cog mil jaega
            // but tum dog ke bad cog milne par cog ko visited mark kar doge.
            // then log process hoga, vapas se cog aaega, uss time par tum cog ko vapas
            // queue me add nahi karoge, jisse ye log vala path explore ni hoga and 
            // map me ye log vali entry nahi ho paegi.
            // Isliye hum ek level par jitne bhi words process krne hai, hum usko ek alag
            // visited set me manage karege, taki agar dog and log same level par aaege
            // and vo cog banaege, to internal visited cog ko visited mark karke queue me add 
            // kar dega, and usse pehle dog and log joki valid path se belong krte hai
            // vo unki entry bhi ho jaegi kuch aise
            // dog -> cog, log -> cog.
            // fir ek level khatam hone ke bad, ye innerVisited ko hum, outer visited me
            // add kar dege, ki agar next level par same word aate hai to skip kar de.
            Set<String> innerVisited = new HashSet();
            
            // par ab tumhre man me ek ques aaega ki yar jab dog se cog bana tab
            // innervisited me add ho gaya, then uske bad log se cog bana vo to innervisited check 
            // me skip ho jaega. To mene kaha ha koi bat ni queue me repeated entry ni hui
            // but usse pehle ki line to dekho jisme humne kaha hai ki map me log-> cog ki entry kar do.
            // humko us entry se mtlb hai, ki jab later me dfs chale to log -> cog vali entry bhi
            // consider ho.



            while(size-- > 0){
                String str = queue.poll();

                if(str.equals(endWord)){
                    flag = true;
                    break;
                }

                List<String> validWords = createComb(str, wordSet);

                for(String valid : validWords){
                    if(outerVisited.contains(valid)) continue;
                    map.computeIfAbsent(str, k -> new ArrayList()).add(valid);
                    if(innerVisited.contains(valid)) continue;
                    innerVisited.add(valid);
                    queue.add(valid);
                }
            }

            // bhai jo words process kar chuke ho unko dobara nahi banana isliye wordSet se vo words hata do
            // take dobara jab createComb function call ho tab same words vapas na bane
            // agar confusion ye hai ki endword bhi hat jaega jan iinerVisited me endword aa jaega
            // to uska ans ye hai ki hum bfs ka vo level puri tarah explore kar chuke hai
            // jo endword bana raha hai. That means humko shortest path already mil gaya hai.
            wordSet.removeAll(innerVisited);

            if(flag) break;

            // agar ye na smjh aae to inner visited vala explaination padh lena upar smjh aa jaega.
            outerVisited.addAll(innerVisited);

            minDistance++;
        }

        dfs(minDistance, beginWord, endWord, map, result, new LinkedHashSet());

        return result;
        
    }

    public void dfs(int minDistance, String startWord, String endWord, Map<String, List<String>> map, List<List<String>> result, Set<String> visited){
        if(visited.size() > minDistance-1) return;

        if(endWord.equals(startWord)){
            visited.add(startWord);

            result.add(new ArrayList(visited));

            visited.remove(startWord);

            return;
        }

        visited.add(startWord);

        for(String str : map.getOrDefault(startWord, List.of())){
            if(visited.contains(str)) continue;

            dfs(minDistance, str, endWord, map, result, visited);
        }

        visited.remove(startWord);
    }

    public List<String> createComb(String word, Set<String> set){
        List<String> validWords = new ArrayList();

        for(int i = 0; i < word.length(); i++){
            for(char j = 'a'; j <= 'z'; j++){
                char arr[] = word.toCharArray();
                if(arr[i] == j) continue;
                arr[i] = j;
                String newWord = new String(arr);
                if(set.contains(newWord)){
                    validWords.add(newWord);
                }
            }
        }

        return validWords;
    }
}


// BFS + DFS solution Optimised:
// neeche ka solution same hai upar se, bs optimised ye hai ki:

// upar vale me map ban raha hai aise:
// beginword = hit
// to map bana {hit -> {hot}, hot->{dot, lot}, dot->{dog}, lot->{log}, dog->{cog}, log->{cog}}
// means parent to child banaya
// to dfs chalega from startword to endword.

// but neeche hum bana rahe hai child to parent relationship
// for example:
// endword cog and beginword hit
// {cog -> {log, dog}, log->{lot}, dog->{dot}, dot->{hot}, lot->{hot}, hot->{hit}}
// isse solution jaldi milega
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet(wordList);

        if(!wordSet.contains(endWord)) return List.of();

        Queue<String> queue = new ArrayDeque();

        Set<String> outerVisited = new HashSet();

        Map<String, List<String>> map = new HashMap();

        List<List<String>> result = new ArrayList();

        queue.add(beginWord);

        outerVisited.add(beginWord);

        int minDistance = 1;
        boolean flag = false;

        // run BFS to create a map of valid next words and take out shortest distance
        // map why? kyuki map ban raha hai hit -> hot, then hot -> dot and lot
        // isko hum dfs me pass karege taki dfs sirf inhi words par scan kare.
        while(!queue.isEmpty()){
            int size = queue.size();

            // innervisited isliye lagaya hai, taki jab hume koi aisa case milta hai
            // ki man lo do words hai dog and log, abhi ye dono se humko endword cog mil jaega
            // but tum dog ke bad cog milne par cog ko visited mark kar doge.
            // then log process hoga, vapas se cog aaega, uss time par tum cog ko vapas
            // queue me add nahi karoge, jisse ye log vala path explore ni hoga and 
            // map me ye log vali entry nahi ho paegi.
            // Isliye hum ek level par jitne bhi words process krne hai, hum usko ek alag
            // visited set me manage karege, taki agar dog and log same level par aaege
            // and vo cog banaege, to internal visited cog ko visited mark karke queue me add 
            // kar dega, and usse pehle dog and log joki valid path se belong krte hai
            // vo unki entry bhi ho jaegi kuch aise
            // dog -> cog, log -> cog.
            // fir ek level khatam hone ke bad, ye innerVisited ko hum, outer visited me
            // add kar dege, ki agar next level par same word aate hai to skip kar de.
            Set<String> innerVisited = new HashSet();
            
            // par ab tumhre man me ek ques aaega ki yar jab dog se cog bana tab
            // innervisited me add ho gaya, then uske bad log se cog bana vo to innervisited check 
            // me skip ho jaega. To mene kaha ha koi bat ni queue me repeated entry ni hui
            // but usse pehle ki line to dekho jisme humne kaha hai ki map me log-> cog ki entry kar do.
            // humko us entry se mtlb hai, ki jab later me dfs chale to log -> cog vali entry bhi
            // consider ho.

            while(size-- > 0){
                String str = queue.poll();

                if(str.equals(endWord)){
                    flag = true;
                    break;
                }

                List<String> validWords = createComb(str, wordSet);

                for(String valid : validWords){
                    if(outerVisited.contains(valid)) continue;
                    map.computeIfAbsent(valid, k -> new ArrayList()).add(str);
                    if(innerVisited.contains(valid)) continue;
                    innerVisited.add(valid);
                    queue.add(valid);
                }
            }

            // bhai jo words process kar chuke ho unko dobara nahi banana isliye wordSet se vo words hata do
            // take dobara jab createComb function call ho tab same words vapas na bane
            // agar confusion ye hai ki endword bhi hat jaega jan iinerVisited me endword aa jaega
            // to uska ans ye hai ki hum bfs ka vo level puri tarah explore kar chuke hai
            // jo endword bana raha hai. That means humko shortest path already mil gaya hai.
            wordSet.removeAll(innerVisited);
            if(flag) break;
            outerVisited.addAll(innerVisited);

            minDistance++;
        }

        dfs(minDistance, beginWord, endWord, map, result, new LinkedHashSet());

        return result;
        
    }

    public void dfs(int minDistance, String startWord, String endWord, Map<String, List<String>> map, List<List<String>> result, Set<String> visited){
        if(visited.size() > minDistance-1) return;

        if(endWord.equals(startWord)){
            visited.add(endWord);

            List<String> list = new ArrayList(visited);
            Collections.reverse(list);

            result.add(new ArrayList(list));

            visited.remove(endWord);

            return;
        }

        visited.add(endWord);

        for(String str : map.getOrDefault(endWord, List.of())){
            if(visited.contains(str)) continue;

            dfs(minDistance, startWord, str, map, result, visited);
        }

        visited.remove(endWord);
    }

    public List<String> createComb(String word, Set<String> set){
        List<String> validWords = new ArrayList();

        for(int i = 0; i < word.length(); i++){
            for(char j = 'a'; j <= 'z'; j++){
                char arr[] = word.toCharArray();
                if(arr[i] == j) continue;
                arr[i] = j;
                String newWord = new String(arr);
                if(set.contains(newWord)){
                    validWords.add(newWord);
                }
            }
        }

        return validWords;
    }
}

// A bit more optimal approach

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet(wordList);

        if(!wordSet.contains(endWord)) return List.of();

        Queue<String> queue = new ArrayDeque();

        Set<String> outerVisited = new HashSet();

        Map<String, List<String>> map = new HashMap();

        List<List<String>> result = new ArrayList();

        Map<String, List<String>> relatedWords = new HashMap();

        createWildCardMap(wordSet, relatedWords);

        queue.add(beginWord);

        outerVisited.add(beginWord);

        int minDistance = 1;
        boolean flag = false;

        while(!queue.isEmpty()){
            int size = queue.size();

            Set<String> innerVisited = new HashSet();

            while(size-- > 0){
                String str = queue.poll();

                if(str.equals(endWord)){
                    flag = true;
                    break;
                }

                // this approach create all possible combinations of a word. if a word is hit the it will create
                // hit's length that is 3 * 26 alphabets combinations
                // which is vast
                // List<String> validWords = createComb(str, wordSet);

                // this approach basically works over wildcard strings. We pre-compute wild cards.
                // let's say in test case one we have hot, so in map we will store
                // *ot -> hot, h*t -> hot, ho* -> hot.
                // Now while creating combinations we will create these wildcards, if any wildcard matches it will
                // return all words present in wordList
                // This approach is too optimal and it has complexity of equal to string length
                List<String> validWords = createCombViaMap(str, relatedWords);


                for(String valid : validWords){
                    if(outerVisited.contains(valid)) continue;
                    map.computeIfAbsent(valid, k -> new ArrayList()).add(str);
                    if(innerVisited.contains(valid)) continue;
                    innerVisited.add(valid);
                    queue.add(valid);
                }
            }

            // bhai jo words process kar chuke ho unko dobara nahi banana isliye wordSet se vo words hata do
            // take dobara jab createComb function call ho tab same words vapas na bane
            // agar confusion ye hai ki endword bhi hat jaega jan iinerVisited me endword aa jaega
            // to uska ans ye hai ki hum bfs ka vo level puri tarah explore kar chuke hai
            // jo endword bana raha hai. That means humko shortest path already mil gaya hai.
            wordSet.removeAll(innerVisited);
            if(flag) break;
            outerVisited.addAll(innerVisited);

            minDistance++;
        }

        dfs(minDistance, beginWord, endWord, map, result, new LinkedHashSet());

        return result;
        
    }

    public void dfs(int minDistance, String startWord, String endWord, Map<String, List<String>> map, List<List<String>> result, Set<String> visited){
        if(visited.size() > minDistance-1) return;

        if(endWord.equals(startWord)){
            visited.add(endWord);

            List<String> list = new ArrayList(visited);
            Collections.reverse(list);

            result.add(new ArrayList(list));

            visited.remove(endWord);

            return;
        }

        visited.add(endWord);

        for(String str : map.getOrDefault(endWord, List.of())){
            if(visited.contains(str)) continue;

            dfs(minDistance, startWord, str, map, result, visited);
        }

        visited.remove(endWord);
    }

    // public List<String> createComb(String word, Set<String> set){
    //     List<String> validWords = new ArrayList();

    //     for(int i = 0; i < word.length(); i++){
    //         for(char j = 'a'; j <= 'z'; j++){
    //             char arr[] = word.toCharArray();
    //             if(arr[i] == j) continue;
    //             arr[i] = j;
    //             String newWord = new String(arr);
    //             if(set.contains(newWord)){
    //                 validWords.add(newWord);
    //             }
    //         }
    //     }

    //     return validWords;
    // }

    public List<String> createCombViaMap(String word, Map<String, List<String>> map){
        List<String> validWords = new ArrayList();

        for(int i = 0; i < word.length(); i++){
            char arr[] = word.toCharArray();

            arr[i] = '*';

            validWords.addAll(map.getOrDefault(new String(arr), List.of()));
        } 

        return validWords;
    }

    public void createWildCardMap(Set<String> set, Map<String, List<String>> map){
        for(String s : set){
            for(int i = 0; i < s.length(); i++){
                char arr[] = s.toCharArray();
                arr[i] = '*';
                map.computeIfAbsent(new String(arr), k -> new ArrayList()).add(s);
            }
        }
    }
}