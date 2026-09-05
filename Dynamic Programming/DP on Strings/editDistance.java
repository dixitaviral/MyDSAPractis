/*
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character
 

Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')

Intutution:

1. Bhai ye ques hai to seedha seedha tu, jab agli bar dekhega to click ho jaega.
2. And karna bhi vaise hi hai. Bas tu sochega ki word1 par operation perform kar do.
3. Par vo karega to fas jaega tu, dikkat ho jaegi usme and code kafi complex ho jaega, dp ki state badh jaegi
    from only i and j to i, j and word1. Kyuki us case me ye tino change ho re hoge.
4. Aao bhai dekhe intution iske peeche ka:
    a. To ham apna format chalu rakhege ki pehle solve it via recursion at last
        apply dp.
    b. To bhai ab bat aati hai ki base condition kya rahegi:
        i. Dekho bhai agar tum word1 ko change krne ka sochoge to zahir hai ek base condition ye hogi
            ki agar word1 equal ho jae word2 ke.
        ii. But vo condition ki vajah se recrusion kafi complex ho jaega.
        iii. Aao mai tumko 2 base condition batata hu, or sirf ye do kyu i vali kyu ni aage batauga:
            a. Pehle base condition hogi ki i == word1.length() is case tum return karoge s2.length()-i. 
                i. Fati na bhai, koi ni aao smjhau:
                    a. Man lo word1 = "abc" and word2 = "abcde" abhi jab i and j hoge c par to word1 iterator i
                        exhaust ho gaya, but ques keh raha hai ki num of operations return karo, jitne lagege 
                        word1 ko word2 banane ke liye.
                    b. Par tumne c tak match kar diya hai lekin word2 me d and e bhi hai jo tumko add karna hoga
                        word1 me, to d and e ko word1 me add krne ke liye kitne operations lagege, for this example
                        2 and generally s2.length() - 1;
            b. Similary agar word1 me jada char hue word 2 se and j == word2.length() ho gaya tab return karege
                word1.length() - j. Kyuki abhi tumko word1.length - j char remove krne hoge word 1 se. To same num
                of operations lagege, to ye hui dusri base condition.
        iv. Ab bat krte hai recursion ki:
            a. Sabse pehla recursion lagega jab dono string ke characters match ho jae, is case me jab character match
                ho gye to koi operation kyu hi krna hai to isko skip kar do. Skip krne ka recursion will be same as
                not take vala.
            b. Abhi else block me aae mtlb mismatch hua, isme teen case banege as per ques:
                i. Insert: Abhi insert ke liye recursion chalaoge:
                    a. Ab tum sochoge ki word1 me insert krna hai i index par j index vala char. But socho
                        iss operation ko karna kitna time taking hoga.
                    b. Instead tum socho ki bina word1 me insert kiye bina kam chal jae to. Aao dekhe ek example:
                        word1 = abc, word2 = aef. Abhi a and a match hue to i and j hua b and e.
                    c. Abhi insert ke according tumko word1 ko aise banana hai ki a nd b ke beech e aa jae, to
                        string kuch aisi dikhegi: aebc.
                    d. Abhi socho insert karna ke bad tum i and j badha doge, i kispar aaya b par and j kis par aaya
                        f par.
                    e. Ab smjho tum asli me insert na krke insert me word1, word2, i, j+1 pass kar do to. Kyuki
                        insert krne ke bad j to badhega but agar tum sirf jhooth mooth ka insert krte ho to bhi i
                        b par rhega naturally socho and asli ka krte ho to i ko +1 karoge to bhi b par hi aaega.
                    f. Smjhe lala, to insert me pas karoge i, j+1;
                ii. Replace: Abhi replace ke case me recursion chalaoge:
                    a. Replace ka case bhi insert vale ke jese imagine karo ki agar tum i and j ko replace karoge,
                        to i+1, j+1 karoge right jab asli me replace karoge to bhi.
                    b. To hum ye keh skte hai na ki humne jhoot mooth ka replace kar diya i ko j se and i+1 and j+1
                        kar diya. ultimately to operations count krne hai humko.
                iii. Delete: Abhi delete ko bhi same case me smjho:
                    a. Humne kaha ki hum word1 se ith char delete kar dege and delete krke aage badhege. To bhai
                    jhooth mooth ka delete krke aage badho na kisne roka hai.
        v. Bas fir tumko insert, replace, delete me se min value nikalni hai and isko ek variable take me store kar
            lena.
        vi. Then skip and take me se min nikal lo.
        vii. Abhi 2 bate hai pehli to dp kispr lagagege kya state,hogi.
        viii. Since change dekha jae to sirf i and j ho re to unhi par dp laga dege and memo[i][j] me min of take, skip
            store kar dege and jaha upar kahi return kar dege.
        ix. Dusri bat ye ki socho bhai agar tum asli me word1 me change krte to tumhre dp me ek or state badh jati
            jo ki hota word1. Kitni zada bhadak hoti isliye sirf i and j ko badhaya word1 ko same rakha.
            
*/


class Solution {
    public int minDistance(String word1, String word2) {
        int memo[][] = new int[word1.length()][word2.length()];

        for(int arr[] : memo){
            Arrays.fill(arr, -1);
        }
        int n = helper(word1, word2, 0, 0, memo);

        return n;
    }

    public int helper(String s1, String s2, int i, int j, int memo[][]){
        // agar s2 ki len s1 se badi hui and humne
        // jese neeche same condition aane par return kar diya tha
        // Int max to issue ye hoga ki s2 hai man lo abcde
        // and s1 hai abc to jese hi i == 2 hoga return int max hoga
        // as per neeche commented vali base condition
        // but neche vali base condition basically s2.len - i karke
        // ye bata rahi hai ki bhai s2.len - i itne chars abhi bache hai
        // s1 me add karne ko to agar delete replace ya insert kuch bhi
        // opertation krege hum vo count s2.len-i ke barabar hi hoga na
        if(i == s1.length()){
            return s2.length() - j;
        }

        // same explaination upar vala hai, but ye case hai jab s1 ki len 
        // s2 se zada hai, to humko extra char s1 me se hatane hoge
        // to utne hi operation lagege.
        if(j == s2.length()){
            return s1.length() - i;
        }


        // neeche vali base condition galat hai
        // upar bataya hai mene kyu galat hai
        // if(i == s1.length() || j == s2.length()){
        //     return Integer.MAX_VALUE;
        // }

        if(memo[i][j] != -1) return memo[i][j];
        
        int skip = Integer.MAX_VALUE;
        int take = Integer.MAX_VALUE;
 
        if(s1.charAt(i) == s2.charAt(j)){
            skip = helper(s1, s2, i+1, j+1, memo);
        }else{
            int insert = helper(s1, s2, i, j+1, memo); // insert
            
            int replace = helper(s1, s2, i+1, j+1, memo); // replace

            int delete = helper(s1, s2, i+1, j, memo); // delete
                    
            take = Math.min(insert, Math.min(delete, replace));

            take += 1;
        }

        return memo[i][j] = Math.min(take, skip);
    }
}