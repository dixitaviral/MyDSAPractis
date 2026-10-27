/*
Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.

Return the kth positive integer that is missing from this array.

 

Example 1:

Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
Example 2:

Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 
Intution:

1. Bhai ye question easy hai yrr.. bas thora dimag lagana hai.
2. Bat smjho ques keh ra hai tumko kuch numbers diye hai sorted order me usme se tumko missing number dhundna hai.
3. But kth missing number smjhe. 
4. Ab iski intution agar smjho to kuch aisi hogi ki:
    a. Agar tum ye pata kar le gye ki missing kth number kidhar aaega ya kin numbers ke beech aaega to kaam ban gaya
    b. Abhi scene ye hai ki isko pata kese karoge, binary search bhai. 
    c. Dekho bhai man lo array diye hai 2,3,4,7,11. and k = 5 diya hai.
        abhi agar tum dekho to 5th missing number nikalna hai and since number sequence me aaege
        to missing numbers hue: 1,5,6,8,9,10. Right abhi 5th missing 9 hai.
    d. Logic kya lagaya mene ki array length se i = 0, and j = arr.length-1 nikalo and then mid nikalo
    e. Abhi mid se left check karo ki kitne numbers missing hai. Ye kese pata chalega. dekho bhai smjhata hu
        mid = 2 hua and that is 4 number in array. Abhi agar tum 4 se left number count karo, since array sorted hai
        to 4 se left me sare chote numbers hoge and 4 se left me total kitne numbers hoge agar hum 1 se start karo
        3 right?
    f. Bas to formula bana arr[mid] - mid - 1. Abhi karo 4-2 = 2. But 4 se left me to ek hi number missing hai
        to fir 2 kyu aa ra hai, vo isliye aa ra hai kyuki array 0 indexed hai na to -1 or karo to aaya hamare pas 
        1.
    g. Fir kya agar k missing number humko left me mil gye to hum j = mid-1 kar dege else i = mid+1.
    h. Ab tum kahoge bhai theek isse position il jaegi but missing number kese milega. 
    i. Mene kaha badhiya ques hai, dekh bhai jab pura binary search khtm hoga to i ek aisi position par
        jakr rukega jaha par us number ko aana chahiye but bhai since array length kam hai to out of bound to
        jaega ni, that means i us index par rukega remember not the given number I said index. 
    j. To i us index par rukega jaha se aage agar hum k add kar de to humko missing number mil jaega.
    k. To return i+k.

Khatam.

*/

class Solution {
    public int findKthPositive(int[] arr, int k) {
        int j = arr.length-1;

        int i = 0;

        while(i <= j){
            int mid = (i+j)/2;

            if(arr[mid] - mid - 1 < k){
                i = mid+1;
            }else{
                j = mid-1;
            }
        }

        return i+k;
    }
}