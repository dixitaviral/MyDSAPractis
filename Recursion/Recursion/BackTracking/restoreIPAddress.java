/*

A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.

For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.

 

Example 1:

Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]
Example 2:

Input: s = "0000"
Output: ["0.0.0.0"]
Example 3:

Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]

(solution dekh kar darna ni, solution me comment zada likhe hai tbhi bada lag ra hai, hai easy)
(and intution bhi easy hai bss explain kiya hai acche se tbhi itna bada ho gaya.)
Intution:
Basic intution without detail:
    1. Ek Ip address ke char part hoge. To ek part variable lelo
    2. Then uss variable me number add krte jao and validation lagate jao, part <= 255, no leading
        zeros and part length should be max 3.
    3. Ab part ko validate krne ke bad sb me add kar do with following a dot.
    4. sb update krne se pehle ek string me uski current length lelo, jo bad me undo krne ke kaam aaegi.
    5. Abhi bonus tip lelo, kyuki ye easily dimag me ni aaegi, ki isko jo condition hai
        part.length() <= 3. Isko alag se check ni karna, instead for loop chalao usi me 
        1->3 loop chalao.
    6. Or ek lelo start variable jiska index 0 se start hoga, abhi substring banegi kese
        aise string part = s.substring(start, start+len). Len simply loop ka variable hai
        for(int len = 1; len <= 3; len++).
    7. bas abhi recursion me updated start tumko pass krna hai ki abhi start kitna chala hai
        vo tum smjhdar ho nikalo khudse.
    8. Base condition khud socho, agar aa gyi to badhiya ni to neeche likhi hai.
1. bhai ye question bactracking ka abhi tak ka sabse tough question laga or hai bhi.
2. Chalo simple bhasha me intution smjhte hai.
3. Sabse pehle IP address smjho max IP address hum bana skte hai 255.255.255.255.
4. Mtlb har IP address ke char 4 parts hote hai -> p1.p2.p3.p4 where p stands for part.
    a. Abhi parts ki condition smjhte hai.
        i. Part hamesha 255 se chota ya barabar rehna chahiye.
        ii. Part kabhi bhi 0 se start ni hona chahiye. But iska mtlb ye ni hai ki string me 
            zero hai usko eliminate karna hai, instead usko aise place krna hai taki vo leading na
            bane. For example string = 101023, abhi isko hum aise divide kr skte hai
            10.10.2.3 which is valid, but 10.1.02.3 ya eliminate hi kar diya 10.1.2.3 ye galat hai.
        iii. Part hamesha 3 length ka hi ho skta hai.
    b. Abhi intution smjhte hai:
        i. Sabse pehle baat backtracking function ke parameters smjhte hai:
            *. List<String> result = isme hum sare valid IP bana kar store karege, then main function
                                  me return karege.
            *. String s = ye input string hai jo, backtrack function me pass karege.
            *. StringBuilder sb = ye variable valid IP answer hold karega, and base condition aane par
                                  result list me add hoga.
            *. int start = ye start variable hai, hum isko as start point use krege, ki abhi konsa
                           number choose ho ra string se and base condition me bhi kaam aaega
                           kyuki yhi index hoga jo input string ko traverse krega. so base condition me
                           iska bhi kaam lagega for start == s.length(). to base condition me total
                           2 expressions hogi part = s.substring(start, start+len) aise. Is code ka meaning neeche smjhaya
                           hai.
            * int part = ye variable base condition me kaam aaega, jab hum char part bana lege, tab
                           base condition true ho jaega. Ek baat or part ko hum alag se bhi if me check
                           karege base condition ke alava, kyuki aisa bhi ho skta hai ki part == 4 ho jae
                           but valid IP address na bana ho like input = 101023 abhi since hum 
                           exclude/include code likh rahe hai to kya pata combination bane 1.1.2.3 joki
                           invalid hai but part == 4 hoge. aisa hone par hum return kr dege.
        ii. Abhi baat aati hai base condition ki:
            *. Pehli base condition hogi part == 4 && start == s.length(), isme answer store krke
                return.
            * Dusri base condition hogi part == 4 and isme sirf return. Aisa kyu, iska reason mene
                i point ke 4 point me bataya hai.
        ii. Abhi krte hai for loop ki baat, to bhai isme humko sare number ke ek tarah se dekha jae
            to combinations banane hai, to zahir hai loop lagega, hamne pehle bhi dekha hai.
            ki subset aae to do recursion and combination aae to loop. But iske loop me tweak hai 
            thora sa, aao dekhte hai:
            *. For loop basically hum 1->3 chalaege kyuki humko ek max 3 element store krne hai ek part
               me. and store kese hoga, string part = s.substring(start, start+len);
               Abhi len 1 and start 0, to substring(0,1) man lo string hai 25525511135. to pehla part bana
               2, then next recursive call me len 1, start 1, to substring bani 5. Abhi sochoge 2 alag 5
               alag. Neeche padho smjh aaega.
            *. Abhi hum karege validation:
                a.sbse pehla validation hoga ki bhai tum start+len likh rhe ho
                  len max 3 ho skta hai and start max string length -1 tak ja skta hai, to start+len krne par
                  outofbound error aaegi, to bhai hum kahege ki start+len > s.length() then break. Break isliye
                  kyuki, string khtm or aage kaha jaoge bhai.
                b. Dusra validation hai, ki agar part ko integer me convert krke agar value 255 se badi hai
                   to continue. Abhi kahoge continue kyu, kyuki pehle line me reset ho ra hai na
                   part dekho ii ke first point me.
                c. Agla validation hoga, leading zeros to ni hai, jiski baat humne upar ki thi
                    to bhai agar part ki length 1 se zada hai and part ka oth index 0 hai to break kar do.
                d. abhi final ans store krna hai sb me, usse pehle ek prev variable me sb ki current length
                    store kar lo bad me undo krne me kaam aaegi.
                e. then sb.append(part).append('.');
                d. then recursion. abhi recursion me khel ye hai ki jo start hoga usko kese badhaege
                    abhi socho kitni max string traverse kr loge ek iteration me acc to substring(start, 
                    start+len). ans hai start+len. to bs vhi pass krdo. vhi next start hoga.
                f. Abhi part+1 krke bhejna hoga, kyuki 1 part mil gaya hai. and ha ek String part variable
                    hum part store krne ke liye banaegi, but ye parameter part variable integer hai
                    jo ki part count karega.
                g. abhi prev variable yaad hai, undo kardo sb ko, sb.setLength(prev);


                KHATAM bhai!!!.
             
*/

class Solution {

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        create(result, s, new StringBuilder(), 0, 0);
        return result;
    }

    void create(List<String> result,
                String s,
                StringBuilder sb,
                int start,
                int parts) {

        // Base Condition: 
        // IP will have 4 dot separated parts, so count parts == 4
        // then you should also check if start == s.length(), because start is the index we 
        //  are using to traverse the string.
        if(parts == 4 && start == s.length()) {
            result.add(sb.substring(0, sb.length()-1));
            return;
        }

        // this check is here because, from above condition start could also not equal to s.length()
        // that means we have made a invalid string, which we should break.
        if(parts == 4) return;

        // the reason for loop from 1->3 is because every part of IP will atmost have 3 chars.
        for(int len = 1; len <= 3; len++) {
            
            // we are creating substring from starting index till the len of this loop
            // there could be few conditions where start+len can go out of s.length() range. so we have 
            // to break here, to avoid any IndexOutOfBoundsException 
            if(start + len > s.length()) break;

            // Since we know there are four parts in the string, we have to create each part separately
            // and then perform validations
            String part = s.substring(start, start+len);

            //validation for leading zeros like 1.02 which is wrong.
            if(part.length()>1 && part.charAt(0)=='0')
                break;

            // this one is to check if the part is greater than 255 then we have to skip the loop.
            if(Integer.parseInt(part) > 255)
                continue;

            // this we are storing, so that we can backtrack/undo the final string. let's say sb = "255."
            // so prev will be 4 and then when we will undo we will say sb.setLength(prev) this will remove 
            // all extra chars which are appended after len prev
            int prev = sb.length();

            // appending result to final string
            sb.append(part).append('.');

            // since we are passing start+len, Let me tell you. har bar loop chalne par
            // hum s.substring(start, start+len) kar rahe mtlb hum start+len - 1 index tak
            // string use kar chuke hai, abhi -1 isliye kiya kyuki substring ke time par second arguement
            // exclusive hota hai, to jo next index hum jis par traverse karege vo hoga start+len
            // and parts+1 isliye kyuki humko parts count krne hai
            create(result, s, sb,
                   start+len,
                   parts+1);

            // undo/backtrack
            sb.setLength(prev);
        }
    }
}