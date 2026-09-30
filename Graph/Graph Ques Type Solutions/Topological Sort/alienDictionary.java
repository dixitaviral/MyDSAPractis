/*

A new alien language uses the English alphabet, but the order of letters is unknown. You are given a list of words[] from the alien language’s dictionary, where the words are claimed to be sorted lexicographically according to the language’s rules.

Your task is to determine the correct order of letters in this alien language based on the given words. If the order is valid, return a string containing the unique letters in lexicographically increasing order as per the new language's rules. If there are multiple valid orders, return any one of them.

However, if the given arrangement of words is inconsistent with any possible letter ordering, return an empty string ("").

A string a is lexicographically smaller than a string b if, at the first position where they differ, the character in a appears earlier in the alien language than the corresponding character in b. If all characters in the shorter word match the beginning of the longer word, the shorter word is considered smaller.

Note: Your implementation will be tested using a driver code. It will print true if your returned order correctly follows the alien language’s lexicographic rules; otherwise, it will print false.

Examples:

Input: words[] = ["baa", "abcd", "abca", "cab", "cad"]
Output: true
Explanation: A possible correct order of letters in the alien dictionary is "bdac".
The pair "baa" and "abcd" suggests 'b' appears before 'a' in the alien dictionary.
The pair "abcd" and "abca" suggests 'd' appears before 'a' in the alien dictionary.
The pair "abca" and "cab" suggests 'a' appears before 'c' in the alien dictionary.
The pair "cab" and "cad" suggests 'b' appears before 'd' in the alien dictionary.
So, 'b' → 'd' → 'a' → 'c' is a valid ordering.
Input: words[] = ["caa", "aaa", "aab"]
Output: true
Explanation: A possible correct order of letters in the alien dictionary is "cab".
The pair "caa" and "aaa" suggests 'c' appears before 'a'.
The pair "aaa" and "aab" suggests 'a' appear before 'b' in the alien dictionary. 
So, 'c' → 'a' → 'b' is a valid ordering.
Input: words[] = ["ab", "cd", "ef", "ad"]
Output: ""
Explanation: No valid ordering of letters is possible.
The pair "ab" and "ef" suggests "a" appears before "e".
The pair "ef" and "ad" suggests "e" appears before "a", which contradicts the ordering rules.

Intution:

1. Bhai ye ques hai to seedha seedha but isko tumko graph ke form me solve krna thora tricky padega.
2. Aao pehle dekhte hai ques kya keh ra hai:
    a. Ques keh ra hai ki ek tumko word list di hai.
    b. Ye words aliens ne likhe hai kyuki vo english seekh rahe hai.
    c. Abhi unki english hamare jesi same ni hai, unhone kuch apne hisaab se hi alphabet sequence bana
        rakhe hai.
    d. mtlb a b c d ... aise ni kuch alag hi sequence ho skta hai, example d b e f a.. aise kuch bhi.
    e. Abhi ek word list di hai jo ques hai keh ra hai lexicographically sorted hai.
    f. Hmm hmm smjh gaya aao lexicographically sorted ka mtlb smjhata hu:
        i. Lexicographically sorted hone ka mtlb hai ki character sequence ek word ka jitna ho sakega
            utna ascending order me hoga.
        ii. Mtlb agar likha hai apple arnab atif - abhi ye lexicographically sort hai
        iii. Tum atif arnab ni likh skte hai, kyu?
        iv. Kyuki dekho dono a se start ho re hai theek hai, but next char kya hai atif me 't' and
            arnab me 'r'.
        v. Abhi r jo hai alphabets me t se pehle aata hai means r < t.
        vi. to si order hoga arnab atif.
        vii. Abhi tum kahoge aise to r and t me r < t ka relation smjh aaya but uske bad jo n and i hai
                uska same relation ni bana kyuki n is not < i.
        viii. Mene kaha ha ekdm ni keh re ho aisa kyu ni. Aao smjhte hai.
        ix. Isko likh kar ni smjha skta mai, bas itna smjho ki agar tumko asli dictionary me
            atif and arnab dhundna hoga. To tum kese dhundoge.
        x. Sabse pehle a wale words, then ar and at vale words. Abhi ar vale jo words hai
            vo hamesha at se pehle aaege. Ye confirmed hai.
        xi. Isse ye bhi prove hua ki r < t hai. 
        xii. Abhi r ke aage jo bhi aaega and t ke aage jo bhi aaega usko tum prove ni kr skte na.
        xiii. Kyuki hame ye pata hai ki ar and at me ar vale hamesha pehle aaege, to ar ke bad kuch bhi
            aae and at ke bad kuch bhi aa jae, hamesha ar vale words at se pehle hi aaege.
        xiv. Isse humko ye pata chala ki char comparison me jo pehla letter mismatch hoga,
            usse hum relation bana skte hai less than ka.
    g. To lexicographically smjh aaya hoga abhi. Abhi lexicographically vala hi logic lagana hai
        iss ques me bhi and pata karna hai ki jo alien words hai unme se konsa letter pehle  
        and konsa bad me aata hai.
    h. and jitne words diye hai unme se hi char sequence nikalo ki ye isse chota hai ya yu keh lo
        pehle aata hai fir ye isse pehle aata hai....
    i. Abhi agar aisa kuch sequence na mile ya man lo dependency ya cycle mil jae ki e < w and w < e
        aisa kuch aae to iska mtlb koi sequence exist ni krta empty return kar do.
3. To abhi ques smjh aa gaya hoga, abhi dekhte hai intution:
    a. Bhai ye ques kahn's algo / topological sorting se lagega.
    b. dekho humko ultimately ek sequence return krna hai ki ye pehle aaega bad me aaega.
    c. And kahn's algorithm bhi yahi nikalne me help krti hai and isse cycle vale ques bhi solve
        hote hai.
    d. Abhi aise ques aae jisme sequence nikalne ko bole ya cycle detect krne ko bole, to ek bar
        graph kahn's algo bhi soch lena.
    e. chalo aage dekhte hai intution.
    f. Intution ekdm simple hai:
        i. Sabse pehle ek bana lo jo ki tumko ek graph kind of bana dega.
        ii. Map banana hai character and set of characters ka, hum list bhi use kr sakte the iske
            ander but set isliye use kar rahe hai ki characters repeat na ho jae.
        iii. Abhi simple ek compare method bana lo usme string ko compare kar lo, kese krna hai
            aao dekhe:
            a. Bhai upar humne dekha tha ki jab tak strings me same char aae tab tak move karo
            b. Jab mismatch mile to smjh jana ki ek relation mil gaya hai.
            c. same logic yaha lagega, string par for loop chala do and jab mismatch mile
                to map me entry kara do currentchar, set of {greater char}
            d. Abhi humne ye bhi dekha tha ki humko first mismatch character tak hi dekhna tha
                uske aage ka hum kuch prove ni sk skte.
            e. to jab mismatch mil return kar do true.
            f. else false return kar do.
            g. Accha ek or cheez ki man lo string 1, 3 length ki hui and string 2 hui 4 length ki.
            h. Abhi length change krne se character ki ordering thori change ho jaegi, to for loop
                chala for comparison till min length of both strings.
            i. Also kahn's algo ki bat kar rahe or indegree array na bane aise kese, to indegree array
                bana lo and use entry kar do only if map.computeIfAbsent true return kare. Aisa isliye 
                kar rahe hai ki man lo do bar w < r w < r aa gaya to indegree 2 ya zada bar same
                sequence par increment na kare.
            j. True return karne ka mtlb hai ki entry hui hai map and tabhi indegree[ch - 'a'] kar do.
            k. bas ye entry hone bad true return kar do
            l. abhi man lo loop pura chal gaya uske bad bhi mismatch ni mila tab do condition banti hai.
            m. Ek to s1 length > s2 length jese aaab and aaa to ye case me lexicographical order
                hi gadbad hai, to return false.
            n. Abhi s1 length <= s2 length to return true
        iv. Abhi indegree array to banega hi kahn's algo me, to indegree array tum 26 length ka banege
            kyuki alphabets ki indegree banani hogi tumko.
        v. fir ye bhi to dekho ki sare letters ni hoge word list me to tum pehle indegree ko -1 se
            fill kar do.
        vi. Then loop chala do word list par and jo char aa ra hai sirf usko 0 kar do and ye cheez 
            compare se pehle karni hai.
        vii. Abhi indgree se queue me add krna hai jinki indegree 0 hai vo tum jante hi ho
        viii. Abhi kya indegree hai map hai, laga do bfs:
            a. Poll karo sequence me add karo polled char ko.
            b. Then associated char nikalo map and unki indegree kam karo and if indegree == 0
                add it to queue.
            c. Last me check kar lo agar indegree me koi 0 se number to ni hai agar hai means
                cycle hai like w < r < w to return "";
            d. else return sequence.
    Khatam.

 */


//firse solve kar diyo, proper smjh ni aaya 
class Solution {
    public String findOrder(String[] words) {

        Map<Character, Set<Character>> map = new HashMap<>();
        Queue<Character> queue = new ArrayDeque<>();

        int indegree[] = new int[26];
        Arrays.fill(indegree, -1);

        // ye isliye kar rahe hai kyuki indegree me check karke hum queue me dalte hai jab
        // to jo 0 indegree vale hote hai unko hi dalte hai.
        // abhi kahoge to 0 hi rehne do -1 kyu mark kar rahe
        // -1 isliye mark kar rahe kyuki indegree array 26 length ka ban raha hai jitne character
        // hote hai, abhi sare character aane to possible ni hai word list input me
        // to jo present hai word list me unko vapas 0 mark kar do, taki indegree++
        // karte time si se increment ho pae.
        // and jo present ni hai unko -1 hi rehne do, vo process ka part hi ni ban paege.
        // Mark all characters present in dictionary
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                if (indegree[ch - 'a'] == -1)
                    indegree[ch - 'a'] = 0;
            }
        }

        String start = words[0];

        // false hum tabhi return kar rahe compare se jab s1 length > s2 length.
        // jo ki correctly lexicographically sorted ni hai.
        // aisa ni hai ki ho ni skta tha, jese words hai aabbbb and aabc
        // ye valid hai agar aisa kuch hota to return true hoga kyuki pehle hum mistamch hi check ka
        // rahe hai agar mismatch ni mila to s1 length > s2 length check hoga and return false.
        // iske alava ek or condition hai last me return true ki
        // words hai aaa aaab, to ye valid sequence hai to true aaega.
        for (int i = 1; i < words.length; i++) {
            if (!compare(start, words[i], map, indegree))
                return "";

            start = words[i];
        }

        for (int i = 0; i < 26; i++) {
            if (indegree[i] == 0)
                queue.add((char) ('a' + i));
        }

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {

            char c = queue.poll();
            sb.append(c);

            for (char ch : map.getOrDefault(c, Set.of())) {

                indegree[ch - 'a']--;

                if (indegree[ch - 'a'] == 0)
                    queue.add(ch);
            }
        }

        // agar kisi ki bhi indegree 0 se bade mili iska mtlb ko letter process
        // ni hua that means cycle mil gyi apne ko and retrun "".
        // cycle milne ka mtlb kuch aisa mil gaya hoga w < r < w
        // Cycle check
        for (int i = 0; i < 26; i++) {
            if (indegree[i] > 0)
                return "";
        }

        return sb.toString();
    }

    public boolean compare(String s1, String s2,
                           Map<Character, Set<Character>> map,
                           int indegree[]) {

        int length = Math.min(s1.length(), s2.length());

        for (int i = 0; i < length; i++) {

            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            if (c1 != c2) {

                // agar man lo ek bar entry ho gyi w < r ki and doabara same sequence aaya
                // to map me koi issue ni hoga kyuki value ko set me store kar rahe hai
                // par indegree array ko same w < r ke liye do bar update kr dege
                // agar ye if check ni lagaege to. 
                // tabhi ye if check laga hai.
                if (map.computeIfAbsent(c1, k -> new HashSet<>()).add(c2)) {
                    indegree[c2 - 'a']++;
                }

                return true;
            }
        }

        // agar upar krne ke bad aisa kuch aata hai s1 = aaab and s2 = aaa
        // tab bhai tum last vale b ko kisse compare karoge to ye case false ka ho gaya
        // Prefix case
        if (s1.length() > s2.length())
            return false;

        // else man lo s1 hai s1 = aab and s2 hai s2 = aabc. To ye lexicographically si order me
        // sorted hai, but iski entry ni hogi map me tabhi true return kar rahe hai.
        return true;
    }
}