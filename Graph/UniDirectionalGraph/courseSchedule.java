/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Intution:
1. Bhai solution dekhne me bada hai bs, ques easy hi hai.
2. Abhi dekhte hai ques kya keh ra hai:
    a. Simple sa mtlb hai ki humko numCourses diya hua hai jo bata ra hai ki itne courses hai.
    b. Dusra humko ek prerequisites 2d array diya hai, jo ki dependency array hai. Simple bhasha me
        ye array hume batata hai ki konsi edge kispar dependent hai, ya ques ke context me keh lo
        ki for example hai 2d array hai[{1,0},{0,1}], to iska mtlb hua ki 1 course ko krne ke liye
        pehle 0 couse karo, and vice versa.
    c. Bas humko ye check krke batana hai hai, transistive dependency to ni hai, ki deadlock ban ra hai
        ki bhai 1->0->1.
3. Chalo abhi intution dekh lete hai.
4. But usse pehle isme ye bata du ki dfs lagega, kyuki humko har couse ki dependency check krte hue 
    aage badhna hai. BFS se bhi hoga. Jab dobara dekhna ye ques, to BFS se bhi try krna.
5. Abhi dfs intution dekh lete hai:
    a. sabse pehle to bhai is dependency array ko code readable banao.
    b. So ek map bana lo jisme humko graph like structure dikhe, ki couse 1 konse course ko
        point kar ra hai.
    c. Abhi isme do cheeze lagegi:
        i. Ek ho gaya apna visited array, jisme hum ye check karege ki current node jo hum 
            traverse karege vo already traversed to ni hai na. Kyuki agar already traversed hai
            to iska mtlb hua ki already valid path isse exist krta hai.
        ii. Dusra hoga path set. Abhi set isliye use kiya kyuki O(1) time get kr skte hai.
        iii. Abhi path set kyu? Vo isliye ki bhai man lo tum ek node par DFS chala rahe ho,
            and transistive depdendency aa gyi, to path set me check kiye bina karoge to cycle me 
            fas jaoge.
        iv Or is ques ka core logic to yhi hai bhai, ki agar transistive dependency aai, mtlb cycle aai,
        mtlb deadlock and hence return false.
    d. Abhi simple hai, for loop chala lo 0-> numCourses tak and then har i ke liye dfs chala lo.
    e. Sath me check krte raho ki visited to ni hai already.
    f. dfs ko if block ke ander rkhna hai not condition ke sath, agar false aaya to main method me return false
    g. Ab bat krte hai dfs method ki:
        i. Humne kya dekha tha ki agar path me koi node repeat hui mtlb return false, to sbse pehla check yhi hai.
        ii. Then visited check, agar already visited hai to path aage ka valid hai, then return true.
        iii. Abhi i and ii point ko cross kiya mtlb new node hai, to add to path.
        iv. Abhi list retrival from map, map.get(node).
        v. Abhi us list par check laga lo ki null hai ya ni, kyuki cases aise bhi hai ki numCourses 4 hai,
            but dependency array hai [{0,1}], to 2,3,4 ke liye koi key hogi hi ni and map null return kar dega
        vi. abhi simple for loop on list and if(!dfs()) again and return false if false aaya.
        vii. then last me loop se bahr aa kar set.remove path se current node ko remove kar do.
        viii. Abhi kahoge kyu bhai, vo isliye kyuki isko backtracking jesa smjho, ki current node 
                agar current path me valid ni hui, iska mtlb ye thori ki dusre path me bhi valid ni hogi.
                To jab dfs kar lo, then path.remove.
        ix: Abhi visited[node] = 1, ye last me isliye kyuki humne pata laga liye ki current node path valid hai.
        x. Ab kahoge kese, bhai agar invalid hota means cycle milti and vo hum upar hi false return kar dete.
        xi. Bohot badhiya, but ab ques bana visited array me se remove kyu ni.
        xii. Kyuki bhai visited array humko same path dobara traverse krne se bachaege outside dfs.
        xiii. Bas abhi last me return true, kyuki false hota to upar hi return ho jata. 
        xiv. That's all.
        


*/

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap();
        int row = prerequisites.length;

        // ye map hum sirf isliye bana rahe hai taki jo input diya hai
        // vo code radable ho, else [{0,1},{1,0}] ka mtlb kese smjhega code.
        // being human to smjh aa raha hai ki ek edge 0->1 and ek edge 1->0 hai.
        for(int i = 0; i < row; i++){
            int u = prerequisites[i][0];
            int v = prerequisites[i][1];

            if(map.containsKey(u)){
                List<Integer> list = map.get(u);
                list.add(v);
                map.put(u, list);
            }else{
                map.put(u, new ArrayList(List.of(v)));
            }
        }
        
        Set<Integer> path = new HashSet();

        int []visited = new int[numCourses];

        for(int i = 0; i < numCourses; i++){
            if(visited[i] != 1)    
                if(!dfs(map, path, visited, i))
                    return false;
                
        }

        return true;
    }


    public boolean dfs(Map<Integer, List<Integer>> map, Set<Integer> path, int []visited, int node){
        // path mtlb hota hai ki, ki current dfs tree me jo path se hum depth me gaye hai
        // vo path me koi node repeat to ni ho ri, agar ho ri hai, iska mtlb hum cycle me fasne
        // vale hai, kyuki jo node humne path me already traverse ki hai, vo node agar vapas se   
        // traverse karege to hum cycle me fas jaege, and yhi is ques ka sbse bada logic hai.
        if(path.contains(node)) return false;

        // visited yaha isliye check ho ra hai, kyuki agar koi node visited hai, iska mtlb 
        // uss node ka path hum already follow kar chuke hai, and jo ki valid 
        // tabhi hum true return kar rahe hai.
        if(visited[node] == 1) return true;
        
        path.add(node);

        List<Integer> list = map.get(node);

        if(list != null){
            for(int num : list){
                if(!dfs(map, path, visited, num))
                    return false;
            }
        }

        path.remove(node);
        visited[node] = 1;

        return true;
    } 
}