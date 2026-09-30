/*
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

 

Example 1:

Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
Example 3:

Input: nums = [1,0,1,2]
Output: 3

Intution:

1. Bhai ques kya keh ra hai jo to tumko pata hi hoga. Abhi isko karege kese vo solution chota
    bas thori good thinking chahiye hogi.
2. To aao bhai dekhe kya logic lagega isme so that O(n) me ho jae ye.
3. Dekho bhai consecutive number vo hote hai jinke beech ka diff 1 ho like 1 2 3 4 aise. Jo ek
    ke bad ek aae.
4. Abhi tumko ek unsorted array diya hai, jisme tumko ye batana hai ki longest consecutive
    numbers length kya hai.
5. Isko karoge kese, aao ek example se dekhe:
    arr[] = [100,4,200,1,3,2]
6. Abhi upar jo array diya hai usme max length of consecutive hai 4 that is 1 2 3 4.
7. Abhi logic simple hai:
    a. Since O(n) me karna hai to humko sare elements ek bar hi scan krne hoge array ke.
    b. To chalo shuru kare 1 element 100 se. Abhi 100 ki consecutive sequence ka part hai
        ye kese pata chalega. 
    c. Agar tum 100-1 = 99 dekho, kya 99 array me hai, ni hai. To iska mtlb hua:
        i. Pehle to agar consecutive sequence exist karta hai for 100, to vo 100 se hi start hoga
            kyuki 99 present ni hai array me.
    d. to iss case me hum kar dege count++. And then usi if block ke ander chalaege while loop
    e. Abhi while loop isliye kyuki humko consecutive sequence ka pehla number if condition me mil
        gaya tha, abhi while loop chala kar ye check karege ki 100+1 = 101 exist krta hai count++;
        101+1 = 102 exist karta hai count++ and so on.
    f. Bas fir kya last me hum ek variable lenge max = Math.max(max, count). 
    g. Ek zaruri bat ye hai ki tumko pehle set bana lena hai array ka and usi set par iterate krna hai
        and usi set par check lagana hai. tabhi o(n) me tmhra kaam ho paega.
*/

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet();

        for(int n : nums){
            set.add(n);
        }
        
        int max = 0;

        for(int n : set){
            int count = 0;
            if(!set.contains(n-1)){
                int number = n;
                count++;

                while(set.contains(number+1)){
                    number += 1;
                    count++;
                }
            }

            max = Math.max(count, max);
        }

        return max;
    }
}