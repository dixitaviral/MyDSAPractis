/*
Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

 

Example 1:

Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Example 2:

Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]

Intution:
1. Bhai dekho ye ques hai to easy DSU hi lagna hai. But isme jo input diya hai usko tumko acche
    se model karna padega.
2. Ques simply yahi keh raha hai ki tumko accounts diye hai, jisme nested list me first number par
    name and rest string email hai.
3. Agar 2 accounts me name same hai and unke emails common hai to dono accounts ko
    merge kar do, ek nayi list me add kar do, agar ni hai to list as it is add kar do and
    return kar do.
4. Abhi isko karna kese hai with DSU, aao dekhe:
    a. Sabse pehle ek map bana lo, jisme key rakho emails and value rakho list of accounts index
        jis jis account me vo email hai.
    b. Bas abhi tumko simply isi map par loop chalana hai and for each key(email) ke liye
        list of index ke liye union kar do.
    c. Abhi union karna kese hai ye ni batauga, as ye ques krte time tumko union krna acche se
        aata hai, to agar tum bhul gye to tumne practice krni band kar di hai
        baki isi folder me ek md file hai usme dekh lo.
    d. Abhi union krne ke bad tumhre pas hoga parent array, jisme tmhre pas information hogi
        ki jis index ka jo parent hai, us parent me index ke sare emails add kar do.
    e. Abhi ye karoge kese:
        a. Sabse pehle parent array par for lop chala do then then find the parent for
            each index of that for loop.
        b. Then jab parent mil jae, so ek map bana lo Integer, TreeSet ka usme tum parent ko
            as key store kara do, and jo curr index hai jo child hai uski list ko accounts array se
            fetch kar lo, and tree set me from index 1 kyuki 0 par name hai, index 1 se mails start
            hote hai, treeset me add karte jao.
        c. Now upar vala loop khtm hone par ek or loop chala do and isme tum jo upar map banaya tha,
            uski ko traverse krte jao, since upar vale map me tmhre pas per index sorted mails
            pade hai.
        d. Tumko sirf index mtlb key ka use krke name fetch krna hai accounts array se and rest jo value hai
            jo ki all merged mails ki list hai, usko add kar do and finally is list ko parent list me
            add kr do
        e. Simple abhi ye list return kar do.
        Khatam
*/

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        Map<String, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < accounts.size(); i++) {
            List<String> list = accounts.get(i);
            for(int j = 1; j < list.size(); j++){
                map.computeIfAbsent(list.get(j), k -> new ArrayList()).add(i);
            }
        }

        int len = accounts.size();

        int parent[] = new int[len];
        int height[] = new int[len];

        for (int i = 0; i < len; i++) {
            parent[i] = i;
            height[i] = 1;
        }

        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) { 
            List<Integer> nodes = entry.getValue();

            int start = nodes.get(0);

            for(int i = 1; i < nodes.size(); i++){

                int pu = find(start, parent);
                int pv = find(nodes.get(i), parent);

                if (pu == pv)
                    continue;

                if (height[pu] > height[pv]) {
                    parent[pv] = pu;
                } else if (height[pv] > height[pu]) {
                    parent[pu] = pv;
                } else {
                    parent[pv] = pu;
                    height[pu]++;
                }

                start = nodes.get(i);
            }
        }

        Map<Integer, TreeSet<String>> merged = new HashMap<>();

        for (int i = 0; i < len; i++) {
            int root = find(i, parent);

            merged.computeIfAbsent(root, k -> new TreeSet<>());

            List<String> acc = accounts.get(i);

            for (int j = 1; j < acc.size(); j++) {
                merged.get(root).add(acc.get(j));
            }
        }

        List<List<String>> ans = new ArrayList<>();

        for (Map.Entry<Integer, TreeSet<String>> entry : merged.entrySet()) {

            List<String> curr = new ArrayList<>();

            curr.add(accounts.get(entry.getKey()).get(0));

            curr.addAll(entry.getValue());

            ans.add(curr);
        }

        return ans;
    }

    public int find(int x, int parent[]) {
        if (x != parent[x]) {
            parent[x] = find(parent[x], parent);
        }

        return parent[x];
    }
}

// little optimised solution

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int len = accounts.size();

        int parent[] = new int[len];
        int height[] = new int[len];

        for (int i = 0; i < len; i++) {
            parent[i] = i;
            height[i] = 1;
        }

        Map<String, Integer> map = new HashMap<>();

        // A little optimal approach:
        // Bhai upar jo map hai unko hum map of String, Integer kar de instead of String, List<Integer>
        // ABhi dekho scene ye hai, ki humne ek account nikala accounts se
        // usme humne bola ki account fetch kar lo accounts list se
        // then account nested list par chalao for loop and from 1 kyuko 0th par
        // to name rhega. Then map me entry kar do, email -> i ki. Iska mtlb hua, ki current account
        // list me ye email, is number ke account se map hota hai. Ab suno ye logic isliye, ki man lo pehli account nested
        // list me jitne emails the vo i se map ho gye ya keh lo 0 se map ho gye ab aaya 2 ya 3 account
        // jisme vapas koi aisa mail aa gaya jo already exist karta hai, abhi humko union find kar dena hai
        // between current i and since ye email pehle bhi aaya hai to hum map.get(email) karke ye pata laga lege ki 
        // pehli bar email kis account index par aaya tha. See that int u and int v line. Bas fir kya union kar dege.
        

        // Abhi ek confusion ho skti hai tumko man lo 0th account me duplicate email aa gaya fir. To koi ni 
        // u bhi 0 hoga and v bhi 0 and 0 se 0 ka union ho jaega.

        // Second confusion ye hogi tumko ki bhai us pehli bar aae email ka kya hoga, vo to map me hi pada raha, uska union hua hi ni
        // to bhai shyd tum bhul rahe ho ki hum emails ka ni accounts ka union kar rahe hai
        // if you will see below code, to hum jo map bana rahe hai usko kahi use hi ni kar rahe. Bas further parent array ko use krke 
        // ans banate hai.
        for (int i = 0; i < accounts.size(); i++) { 

            List<String> account = accounts.get(i);

            for(int j = 1; j < account.size(); j++){

                String email = account.get(j);

                if(!map.containsKey(email)){
                    map.put(email, i);
                }else{
                    int u = map.get(email);
                    int v = i;

                    int pu = find(u, parent);
                    int pv = find(v, parent);

                    if (pu == pv)
                        continue;

                    if (height[pu] > height[pv]) {
                        parent[pv] = pu;
                    } else if (height[pv] > height[pu]) {
                        parent[pu] = pv;
                    } else {
                        parent[pv] = pu;
                        height[pu]++;
                    }
                }
            }
        }

        Map<Integer, TreeSet<String>> merged = new HashMap<>();

        for (int i = 0; i < len; i++) {
            int root = find(i, parent);

            merged.computeIfAbsent(root, k -> new TreeSet<>());

            List<String> acc = accounts.get(i);

            for (int j = 1; j < acc.size(); j++) {
                merged.get(root).add(acc.get(j));
            }
        }

        List<List<String>> ans = new ArrayList<>();

        for (Map.Entry<Integer, TreeSet<String>> entry : merged.entrySet()) {

            List<String> curr = new ArrayList<>();

            curr.add(accounts.get(entry.getKey()).get(0));

            curr.addAll(entry.getValue());

            ans.add(curr);
        }

        return ans;
    }

    public int find(int x, int parent[]) {
        if (x != parent[x]) {
            parent[x] = find(parent[x], parent);
        }

        return parent[x];
    }
}