/*
You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

The lock initially starts at '0000', a string representing the state of the 4 wheels.

You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

Example 1:

Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation: 
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".
Example 2:

Input: deadends = ["8888"], target = "0009"
Output: 1
Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".
Example 3:

Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
Output: -1
Explanation: We cannot reach the target without getting stuck.


Intution:
    1. Bhai ques easy hai staright forward hai, but BC hai char ko add krna and ans aae integer me.
    2. Ques keh ra hai ki:
        a. 4 wheels hai, joki set hai 0,0,0,0 par.
        b. Ek deadend array diya hai, jisme number bata rahe hai ki agar lock ko ghumane par deadends ki value me se koi aaya
            to lock stuck ho jaega and then return -1.
        c. Ek target diya hai ki agar wheels move krte krte ye number aa gaya to kitni bar move kiya hai jo aaya hai ye batao.
        d. Abhi scene ye hai ki 0 ko aage +1 karoge to 1 par jaega but ulta ghumaoge -1 karoge to 9 par aana chahie.
        e. Similarly, 9 ko aage move karoge to 0 par aana chahiye.
    3. Abhi intution dekhte hai:
        a. Sabse pehle ek set lege and usme deadends daal dege kyuki set me get O(1) me hota hai.
        b. Then queue lenge usme char array dalege. Abhi char array me kya kya hoga aao batata hu.
            i. ye array 5 length ka hoga.
            ii. from 0 to 3 rhegi string, kyuki string 4 number ki hogi since 4 wheels hai.
            iii. and 4 index par hoga steps, ki abhi tak kitni bar wheel ghuma hai.
        c. Ek visited set bhi lenge, but usse pehle ye dekhte hai kyu lege.
            i. Bhai dekho man lo 0000 hai isme tum har zero ko +1 kar skte ho to hoga 1111.
            ii. And agar 0000 ko -1 karoge to banega 9999.
            iii. Abhi +1 and -1 krte krte bohot chances hai ki same string vapas mile to loop ban jaega
            iv. Isliye humko visited chahiye.
        d. abhi queue me add kar dege starting string 0000 and visited me bhi add kar dege.
        e. Abhi BFS chalate hai but usse pehle dekho idea kya hai:
            i. Bhai seedha seedha idea hai. For loop chalao char bar kyuki wheels char hai and
                per wheel do number benege +1 and -1 krke.
            ii. ki starting string hai 0000 and hamare par per character means 0th index par 0 character
                1 par again 0 and so on till 3 tak again 0.
            iii. Abhi 4 character me se hamare pas per character 2 choice hai, either +1 or -1. 
            iv. means make 0->1 or 0->9;
            v. then check if new number which got created let's say for the first time we will have two numbers.
            vi. 0000 se humko do number milege 0th index par either 1000 or 9000.
            vii. Abhi humko check karna hai ki kahi ye number deadends set me ni nahi and visited to nahi hai.
            viii. and ye check hoga do alag alag if blocks me first will be number 1000 and 
                second will be 9000.
            ix. inside if block add to visited set and then increement the 4th index by 1 kyuki wheel ghuma
                since character changed.
            x. Then add inside the queue, same with number 2 as well.
        f. Queue se poll krne ke bad check if deadends set already have that number return -1;
        g. Check if target == polled number return 4 index of character.
        h. Bas ho gaya.

Important Notes:
1. Bhai as per I said, ques straight hai, bs isme conversion me bohot time jata hai.
2. Sabse pehle ye seekho ki koi character ko '0' se minus karoge to integer return hoga.
3. Ye kyu bata ra hu, kyuki humko "0000" string ko char array me hi convert kar paoge.
4. Then ek ek char ko integer me convert kroge to time complexity bohot badhegi.
5. To humko character me hi khelna pdega.
6. Abhi kaorge kese sabse pehle char - '0' isse int milega, then +1, since int me convert ho gaya
    to +1 kar hai ek bar to kar dege. Now %10 kyu, kyuki 9+1 karoge to 10 aaega but humko chahiye 0.
7. to 9+1 = 10%10 bana 0.
8. Abhi socho bhai, jab -1 karna hoga. tumne kiya char-'0', man lo char hai 0, so bana '0'-'0' = int 0.
9. abhi int 0 me -1 karoge to bana -1, bhaisab humko to 9 chahiye tha, ye kese hoga.
10. To bhai 9 add karna pdega na ki -1, abhi 0+9%10 = kya mila 9. Or kar lo 2+9%10 = 1 and so on.
11. Chalo abhi baat krte hai steps ki.
12. Bhai humne kya dekha tha ki queue me char array me hi 4 index par steps store krte rhege.
13. Par ek baat socho jab tum deadend me string verify karoge man lo string hai 0202, and ye deadend me hai
    but tumne queue se poll kiya hai 02025 jisme 5 to steps hai kitni bar wheel ghuma.
14. Abhi 02025 will not match with 0202 in deadend. to humko queue se poll krne ke bad
    string alag krni pdegi then match krna hai.
15. Ek or baat jo tmhre 4 index par 5(steps) rhega, vo bhi to char form me hoga isko return krege
    int ke form me by returning char-'0';
16. Abhi bhai same inside for loop bhi, visited check and set check karoge, to jab do naye number
    banaoge +1 and +9 krke, to us time bhi 5 index ko hatakar krna hoga.
17. then jab compare kar lena to inside if block usko vapas array me add kar dena.


*/

class Solution {

    public int openLock(String[] deadends, String target) {
        Set<String> set = new HashSet(Arrays.asList(deadends));

        Queue<char[]> queue = new ArrayDeque();

        queue.add(new char[]{'0','0','0','0','0'});

        Set<String> visited = new HashSet();

        visited.add("0000");

        while(!queue.isEmpty()){
            char letters[] = queue.poll();

            String input =""+letters[0]+letters[1]+letters[2]+letters[3];

            if(set.contains(input)) return -1;

            if(input.equals(target)){
                return letters[4]-'0';
            }

            input += letters[4];

            for(int i = 0; i < 4; i++){
                char t1 = (char)((((letters[i]-'0')+1)%10)+'0');
                char t2 = (char)((((letters[i]-'0')+9)%10)+'0');

                char t1Arr[] = input.toCharArray();
                char t2Arr[] = input.toCharArray();

                t1Arr[i] = t1;
                t2Arr[i] = t2;

                String s1 = String.valueOf(t1Arr).substring(0,4);
                String s2 = String.valueOf(t2Arr).substring(0,4);

                if(!set.contains(s1) && ! visited.contains(s1)){
                    visited.add(s1);
                    t1Arr[4] = (char)((((letters[4]-'0')+1))+'0');
                    queue.add(t1Arr);

                }

                if(!set.contains(s2) && !visited.contains(s2)){
                    visited.add(s2);
                    t2Arr[4] = (char)((((letters[4]-'0')+1))+'0');
                    queue.add(t2Arr);
                }
                
            }
        }

        return -1;
    }
}