/*
Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.

Return a list of all possible strings we could create. Return the output in any order.

 

Example 1:

Input: s = "a1b2"
Output: ["a1b2","a1B2","A1b2","A1B2"]
Example 2:

Input: s = "3z4"
Output: ["3z4","3Z4"]

Intution:
1. Bhai sabse pehli baat ye ques ne sikhai hai:
    a. Backtracking me kuch bhi ho, jese ye ques me keh ra tha:
        i. Ek bar lowercase me add karo and ek bar upper case me.
        ii. Mujhe laga backtracking ka undo jo lowercase me add kiya tha usko upper case me add
            karna hai.
        iii. Ni ye galat hai, upper lower jo bhi karo ek iteration complete hone ke bad 
            jo element ans me add kiya hai vo remove karo.
2. Abhi ques simple hai:
    a. Ek string di gyi hai, jese a1b2 isko letter lower and uppercase me karke permutation
        banane hai jese ki [a1b2, A1b2, a1B2, A1B2]. 
    b. iski complexity letter par depend karti hai jitne letter utni branches, so complexity hui:
        2^n.
    c. Abhi simple hai, har step par humko dekhna hai ki character letter hai ya number.
    d. if letter then:
        i. ans string me char append kar do.
        ii. then next branch traverse karo, means next recursion call karo.
        iii. then wapas aane par jo current char add kiya tha usko remove krke upperCase banao
                and wapas string me add kar do.
        iv. then again branch traverse karo, means next recursion call karo.
        v. Abhi ye tip jo mene 1 point me batayi thi, yaha tumhri ek recursion iteration
            khtm ho ri hai, abhi jo bhi last char tumne string me add kiya hai
            usko remove karo, backtracking ka sbse important rule.
        vi. bss ye case yaha khtm hua.
    e. abhi else number hai then:
        i. Yaha koi check ni lagega, simple string me append kar do, kyuki number bhi ans string
            ka part hai.
        ii. And yaha se recursion vapas call kar do.
        iii. again important thing, ki wapas aakr jo number last me append kiya tha usko remove
                karo.
3. Bas itna hi hai, ho jaega.

*/


class Solution {
    public List<String> letterCasePermutation(String s) {
        List<String> list = new ArrayList();

        create(list, new StringBuilder(""), s, 0);

        return list;
    }

    public void create(List<String> result, StringBuilder sb, String s, int i){
        if(sb.length() == s.length()){
            result.add(sb.toString());
            return;
        }

        char temp = s.charAt(i);//

        if(Character.isLetter(temp)){
            sb.append(Character.toLowerCase(temp));//a1b

            create(result, sb, s, i+1);

            char c = sb.charAt(sb.length()-1);

            sb.deleteCharAt(sb.length()-1);

            sb.append(Character.toUpperCase(c));

            create(result, sb, s, i+1);

            sb.deleteCharAt(sb.length()-1);

        }
        else{
            sb.append(temp);
            create(result, sb, s, i+1);
            sb.deleteCharAt(sb.length()-1);
        }
        
    }
}