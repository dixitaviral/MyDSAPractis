/*
Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.

A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.

 

Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
Example 2:

Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
Output: "aaaaaaaa"

Intution:

1. Bhai dekho ye ques accha hai kafi, agar smjh gye acche se to kar le jaoge koi issue ni hai.
2. To Ques basically ye keh ra hai ki do strings hai inki ek parent string banao, jiski
    length minimal ho and vo parent ke subsequence me se given two string ho. Kind of superset.
3. Abhi isko krne ka similar logic hai jese hum longest common subsequence nikalne ke liye lagate hai
    aao dekhe:
    a. Dekho bhai sabse pehle recall krte hai ki lcs kese nikalte the apan, usi me se shortest common
        supersequence (SCS) derive kar dege.
    b. LCS me hamare pas me teen choices thi:
        1. Agar dono string ke current element ith and jth element equal hai means, vo lcs ka part hoga
            right, to humko us character ko consider kar lege and since dono string se character consider
            kiya hai to i and j dono incremnet kar dege and next recursion call kar dege.
        2. Dusra ye tha ki agar match ni hote hai to bhai do raste hai:
            i. ith element par khade raho and j ko badhao kya pata j aage jakr ith element se match ho jae.
            ii. Ya fir ith element ko badhao and j ko stable karo kya pata i aage jakr jth element se match
                ho jae.
            iii. Also in dono cases me hum current character ko consider ni krte the kyuki humko common/equal
                character chahiye.
        3. Abhi hum in teeno ka maximum nikal lete the, jo branch sabse badha subsequence length degi vohi ans
            hai.
        4. Base condition ki bat karu to overflow par 0 return kr dete the in case of both string overflow.
    c. Abhi bat krte hai SCS ki, scs me keh raha hai ki shortest common supersequence nikalna hai humko jo ki
        shortest hona chahiye, to aao dekhe lcs me kya change hoga jo scs ban jaega:
        Also note krne vali bat hai lcs me humne length nikali thi exact lcs string ni nikali thi,
        similarly isme bhi hum scs length nikalege. 

        Actually scs string ya lcs string dp matrix ko backtrack karke nikalte hai, vo aage dekhege, abhi
        length nikalna seekho for scs.

        1. Sabse pehle base condition ki bat krte hai:
            a. LCS me base condition me overflow hona par return krte the 0, abhi 0 isliye return krte the
                kyuki koi character ni bacha hai in either of string, mtlb kisi ek me character bache bhi hoge
                to humse kya humko common chahiye and jo bache hue characters hai vo common ni hai.
            b. But in case of scs humko vo bache hue characters bhi chahiye, kyuki supersequence kehta hai ki 
                bhai mere ander dono seuqnce hoge jo str1 and str2 se match karega and mai shortest houga.
            c. To man lo str1 hai abac and str2 hai ab, abhi dekho start me hi ab common sequence mil gaya hai
                str2 overflow hogi abhi, and humne dekha hai ki remaining characters of both string bhi matter krte hai
                to base condition me overflow ke time par humko bache hue characters ki length return krni hogi right.
            d. Mtlb jitne bhi bache hue characters hai jo consider ni hue because of overflow unko bhi consider karo.
            e. Hence overflow me return karo (str1.length-i) + (str2.length()-j);
        2. Dusra abhi recursion implementation ki bat krte hai:
            a. Sabse pehle humne lcs me dekha ki agar equal hai to consider karo means 1 + recursion call and i+1 and j+1.
                and equal ni hai to koi ek index badhao but consider ni krna hai, kyuki humko common chahiye.
            b. But super sequence me humko jo not matching characters hai unko bhi consider krna pdega, kyuki superset
                bana rahe hai jisme dono string ke sare matching and not matching characters hone chahiye.
            c. To isme matching vali condition me to hum 1 + recursive call karege hi but non matching else
                condition me bhi i+1, j me 1+ and i, j+1 1+ krke min nikalna hai.
            d.  bas abhi chaho to if and else condition me hi return kar do and if else condition me se values nikal lo 
                and last me min return kar do.
        3. Abhi dp ka hal ye hai ki jaha jaha return kar rahe ho vaha vaha dp me store karo even in base condition, vo kyu
            aage batauga. And dp check laga do if already calcualted return that.
        4. To bas itna hai scs length nikalna.
    
    d. Abhi dekho bhai, aage karna ye hai ki length nikalne ke bad hamare pas me dp array bana hua milega, isi ko backtrack krke
        humko scs string mil jaegi, aao dekhe kese:
        i. kuch ni krna hai bas humko recursion me jo steps hai unko hi follow krna hai.
        ii. Sabse pehli condition humne check ki thi, ki agar str1 ith char and str2 jth char equal hai, to consider kar lo character.
            to same karo while loop chala do and i and j char equal h to stringbuilder me append kar do i++ and j++. same as recursion.
        iii. Then recursion me hum check kar rahe hai agar equal ni hai to do branc jaegi ek me i badhega and j will stay and hum i 
            char ko consider karege, and i rok ke j badhaege to j consider karege and unka min store hoga.
        iv. Iska ulta karo to ye hoga ki and ye else if condition me hoga ki dp[i+1][j] <= dp[i][j+1] so consider ith char in stringBuilder
            and i++.
        v. Then else condition me j consider and j++.
    e. Abhi jo mene bola tha ki dp me jaha jaha return kar rahe ho vo vo store krna hai, means return ke ander vale ko bhi dp me store karna hai.
    j. Also dp me ek extra space bana kar dp bananai hai, kyuki jo hum i+1 and j+1 else if me check karege vo overflow hog jaega 
        jab i == str1.length()-1 and j == str2.length()-1 hoga to i+1 and j+1 krne par length ke equal hoga and overflow ho jaega.
    k. And return base vali state isliye store karai kyuki agar vo ni karate to hum dp ko -1 se initialize krte hai bactrac krte time
        -1 consider karega jisse ans galat niklega.

Bas itna karna hai scs ke liye.
*/


class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int dp[][] = new int[str1.length()+1][str2.length()+1];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }

        int len = helper(str1, str2, 0, 0, dp);

        int i = 0;
        int j = 0;

        StringBuilder sb = new StringBuilder();

        while(i < str1.length() && j < str2.length()){
            if(str1.charAt(i) == str2.charAt(j)){
                sb.append(str1.charAt(i));
                i++;
                j++;
            }else if(dp[i+1][j] <= dp[i][j+1]){
                sb.append(str1.charAt(i));
                i++;
            }else{
                sb.append(str2.charAt(j));
                j++;
            }
        }

        while(i < str1.length()){
            sb.append(str1.charAt(i++));
        }

        while(j < str2.length()){
            sb.append(str2.charAt(j++));
        }

        return sb.toString();
    }


    public int helper(String s1, String s2, int i, int j, int dp[][]){
       if(i == s1.length() || j == s2.length()) return dp[i][j] = (s1.length()-i)+(s2.length()-j);

       if(dp[i][j] != -1) return dp[i][j];

       int take = Integer.MAX_VALUE;
       int notTake = Integer.MAX_VALUE;
       
       if(s1.charAt(i) == s2.charAt(j)){
             return dp[i][j] = 1 + helper(s1, s2, i+1, j+1, dp);
       }else{
             return dp[i][j] = Math.min(1 + helper(s1, s2, i+1, j, dp), 
                                    1 + helper(s1, s2, i, j+1, dp));
       }
    }
}