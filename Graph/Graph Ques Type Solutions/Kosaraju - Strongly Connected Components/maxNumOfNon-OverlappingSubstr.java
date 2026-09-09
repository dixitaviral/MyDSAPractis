/*
Given a string s of lowercase letters, you need to find the maximum number of non-empty substrings of s that meet the following conditions:

The substrings do not overlap, that is for any two substrings s[i..j] and s[x..y], either j < x or i > y is true.
A substring that contains a certain character c must also contain all occurrences of c.
Find the maximum number of substrings that meet the above conditions. If there are multiple solutions with the same number of substrings, return the one with minimum total length. It can be shown that there exists a unique solution of minimum total length.

Notice that you can return the substrings in any order.

Example 1:

Input: s = "adefaddaccc"
Output: ["e","f","ccc"]
Explanation: The following are all the possible substrings that meet the conditions:
[
  "adefaddaccc"
  "adefadda",
  "ef",
  "e",
  "f",
  "ccc",
]
If we choose the first string, we cannot choose anything else and we'd get only 1. If we choose "adefadda", we are left with "ccc" which is the only one that doesn't overlap, thus obtaining 2 substrings. Notice also, that it's not optimal to choose "ef" since it can be split into two. Therefore, the optimal way is to choose ["e","f","ccc"] which gives us 3 substrings. No other solution of the same number of substrings exist.
Example 2:

Input: s = "abbaccd"
Output: ["d","bb","cc"]
Explanation: Notice that while the set of substrings ["d","abba","cc"] also has length 3, it's considered incorrect since it has larger total length.

Intution:

*/


class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int[][] firstNLastOcc = new int[26][2];
        int len = s.length();

        for(int i = 0; i < 26; i++){
            Arrays.fill(firstNLastOcc[i] , -1);
        }

        for(int i = 0; i < len; i++){
            if(firstNLastOcc[s.charAt(i) - 'a'][0] != -1){
                firstNLastOcc[s.charAt(i) - 'a'][1] = i;
            }else{
                firstNLastOcc[s.charAt(i) - 'a'][0] = i;
                firstNLastOcc[s.charAt(i) - 'a'][1] = i;
            }
        }

        Map<Integer, List<Integer>> map = new HashMap();

        // create a graph
        for(int i = 0; i < 26; i++){
            if(firstNLastOcc[i][0] == -1) continue;

            int start = firstNLastOcc[i][0];
            int end = firstNLastOcc[i][1];

            while(start <= end){
                int nextChar = s.charAt(start) - 'a';

                if(nextChar != i){
                    map.computeIfAbsent(i, k -> new ArrayList()).add(nextChar);
                }

                start++;
            }
        }

        Stack<Integer> stack = new Stack();
        boolean visited[] = new boolean[26];
        Arrays.fill(visited, false);
        for(int i = 0; i < 26; i++){
            if(firstNLastOcc[i][0] != -1 && !visited[i]){ // condition could be if(map.containsKey(i) && !visited[i])
                dfsTopoSort(i, map, visited, stack);
            }
        }

        Map<Integer, List<Integer>> transposedGraph = new HashMap();

        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()){
            for(int n : entry.getValue()){
                transposedGraph.computeIfAbsent(n, k -> new ArrayList()).add(entry.getKey());
            }
        }

        // this below list will contains all strongly connected components.
        List<List<Integer>> scc = new ArrayList();
        Arrays.fill(visited, false);
        while(!stack.isEmpty()){
            int n = stack.pop();

            if(!visited[n]){
                List<Integer> list = new ArrayList();
                dfsKosaraju(n, transposedGraph, visited, list);
                scc.add(list);
            }
        }

        Map<String, int[]> validStrings = new HashMap();
        for(List<Integer> list : scc){
            int finalS = Integer.MAX_VALUE;
            int finalE = 0;
            for(int i = 0; i < list.size(); i++){
                int s1 = firstNLastOcc[list.get(i)][0];
                int e1 = firstNLastOcc[list.get(i)][1];

                finalS = Math.min(s1, finalS);
                finalE = Math.max(e1, finalE);
            }

            validStrings.put(s.substring(finalS, finalE+1), new int[]{finalS, finalE});
        }

        validStrings =  validStrings.entrySet().stream()
                .sorted((entry1, entry2) -> Integer.compare(entry1.getValue()[1], entry2.getValue()[1]))
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue(),
                        (oldValue, newValue) -> oldValue, 
                        LinkedHashMap::new
                ));

        int prev = -1;
        List<String> res = new ArrayList();
        for(Map.Entry<String, int[]> entry : validStrings.entrySet()){
            if(prev < entry.getValue()[0]){
                prev = entry.getValue()[1];

                res.add(entry.getKey());
            }
        }

        return res;

    }

    public void dfsKosaraju(int start, Map<Integer, List<Integer>> map, boolean visited[], List<Integer> scc){
        visited[start] = true;

        scc.add(start);

        for(int n : map.getOrDefault(start, List.of())){
            if(visited[n]) continue;

            dfsKosaraju(n, map, visited, scc);
            
        }
    }

    public void dfsTopoSort(int start, Map<Integer, List<Integer>> map, boolean visited[], Stack<Integer> stack){
        visited[start] = true;

        for(int n : map.getOrDefault(start, List.of())){
            if(visited[n]) continue;

            dfsTopoSort(n, map, visited, stack);
        }

        stack.push(start);
    }
}