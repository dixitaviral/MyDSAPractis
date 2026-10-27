/*
Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.

 

Example 1:

Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.
Example 2:

Input: nums = [1,2,4,8]
Output: [1,2,4,8]

Intution:

1. Bhai dekho ques easy hai but tweaks se bhara hua hai jese humne longest increasing subsequence
    me kiya tha, vese hi isme bhi karna hai baki.
2. Ques kya keh ra hai vo dekhte hai sabse pehle:
    a. ques keh raha hai ki, ek vaild divisible longest subset vo hoga, jis subset ki length sabse
        badi hai.
    b. And us subset me jitne pair possible hai between number sabke liye a%b == 0 and b%a == 0
        condition satify honi chahiye.
3. Ab steps dekhe to simple hai:
    a. Sabse pehle subset numbers choose karo kon kon se hoge.
    b. Then un numbers ke beech check karo ki a%b == 0 and b % a == 0 condition true ho ri hai ya ni.
    c. Then agar ho ri hai vo take condition ho gyi.
    d. Agar ni ho ri hai to notTake condition ho gyi.
    e. Aise kar kar ke, i == nums.length jab ho jae, to jitne subset me elements bane hai unko ans
        list me store kr do.
4. But Upar likhe steps simple recursion solution ke liye hai.
5. Abhi jab jab tumko ques diya jae ki subset and subsequence return karo, tab tab tumko, ans 
    ki length nikalni hai with the help of recursion and dp solution.
6. Then jo dp banega usi ki help se tumko backtrack krna hai means jese jese krke dp fill hui hai
    recursion me vese krke tumko dp se hi susbet banana hai, aao batau kese karna hai.
7. To dekho bhai sabse pehle intution dekh lete ques ki:
    a. First of all current ques me array sort hoga, ab kyu hoga uska ans neeche diya hai mene.
        solution me hai to dobara ni likh ra, bas itna smjho ki sorting kar dege to jo ques me
        condition di hai nums[prev] % nums[i] == 0, ye ni check karna kyuki prev hamesha smaller hoga
        i se as sorted hai, jisko vajah se iska ans aaega nums[prev] % nums[i] == nums[prev] hamesha.

        Plus agar sorting ni krte ho to to tumko subset banate time and naya element add krte time
        check karna pdega ki jitne elements hai ans list me curr num un sabse divisible ho. Aisa kyu
        neeche bataya hai and sorting krne ke bad ye cheez apne aap took care ho jaegi.
    
    b. Ab bhai humko dp banani hai with the help of recursion and dp:
        i. To sabse pehle base condition i == nums.length return 0;
        ii. Then take and nottake variable. Abhi do variable pass hoge prev = -1 and i = 0.
        iii. Prev = -1 isliye kyuki subset me zaruri ni hai ki 0th element le hum, isliye prev = -1
            ye show kar ra hai ki koi element abhi consider ni kiya gaya.
        iv. Ab aati hai bat aage ki to humne already discuss kiya hai ki if block me condition lagaege
            for nums[i] % nums[prev] == 0, vice versa ni lagani hai plus ek condition or lagegi prev ==
            -1, ye isliye kyuki hamne kaha tha prev = -1 hoga.
        v. Abhi iske ander take call hogi 1+recursive call jisme i+1 and i as prev pass karege.
        vi. Iske bahr notTake call hogi jisme sirf recursive call with i+1 and prev pass karege not
            i kyuki isliye hum current consider ni kar re to jo bhi prev num select hoga us branch me
            vo hi aage jaega.
        vii. then simple max of take and nottake lagega.
        viii. Isi me i and prev ka use krke dp laga do. Jisse humko max length subset divisible mil jaega.
    c. Ab bat aati hai jab DP bana lege uske bad bactrack krke susbset banaege kese, aao dekhe:
        i. Sabse pehle bhai ek while loop lelo jo ki basically i < nums.length ka hoga, ab jese jese
            recursion likha hai vese vese likhte jao.
        ii. Sabse pehle if condition uske ander same prev == -1 || nums[i] % nums[prev] check, if true
            then inside take and  notTake dono nikalo.
        iii. Abhi take and notTake dono nikalne se pehle tum puchoge bhai ye kya recursion me
            notTake tumne if ke bahr likha hai isme tum ander likh rahe ho.
        iv. Vo isliye kyuki recursion ke time par humko max length nikalni thi to humne sari states
            compare kari thi and dp banayi thi.
        v. Is case dp hamare pas already hai, to vapas utni mehenat kyu krni hai, also ans jo
            nikala tha for every dp cell, vo humne take and notTake max se nikala tha na.
        vi. Abhi hamare pas take and notTake dono ki value cell me padi hai, and agar take ki value
            kahi bhi badi mili to hum us time par jo i index hoga usko consider karege, kyuki
            recursion me bhi take vala case consider vala tha.
        vii. Ab take kese niklega recursion me hum take nikal rahe the 1+helper(i+1, i), same way hum
            idhar bhi nikalege, but isme formula lagega 1+dp[i+1][i+1]. Ab tum kahoge i hoga na
            dusre sqaure bracket me, bilkul hoga, but yaad karo prev ko humne liya tha -1, and dp
            me store krte time prev+1 krke store kiya tha. Plus take condition me prev ko hum i
            pass karte the, tabhi.
        viii. Abhi same for notTake, isme simple dp[i+1][prev+1].
        ix. Ab take >= notTake consider ith element in ans list and assign prev = i.
        x. Last me i++; That's all

8. Last me return ans.

*/

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {

        // sorting isliye ki hai kyuki sare number ascending
        // order me aa jaege usse humko ye fayda milega ki 
        // jo condition hai nums[prev] % nums[current] == 0
        // ye kabhi true ni hogi kyuki chote numbers ko 
        // bade numbers se modulo krne par vo chota number return
        // ho jata hai jo ki 0 ke equal ni hoga.

        /*
        Plus iske alava ek or fayda hai, maan lo humne bina sorting ke
        dhunda, to humko ye condition ki check krni padegi nums[prev] % nums[current] == 0
        abhi check krne me koi issue ni hai, but isse hoga ye ki ans list me
        already jo numbers as valid pair stored hai, vo sare numbers ko
        humko nums[prev] se divisible hai ya ni check krna pdega. Kyuki ques
        keh ra hai ki jitne pair hoge, ek valid subset me, vo sare pair
        ki given ques condition true honi chahiye. for example:

        2, 3, 18, 9, 6, 27, 54 ye numbers diye hai

        isme subset lelo tum 2,18,9 isme pairs agar hum banae to banege
        2,18 18,9 2,9, abhi 2,18 ans list me add ho jaege, and 18,9 bhi 
        add ho jaega ans me abhi ans subset kya bana 2,18,9. Abhi
        batao 2,18,9 me 2,9 divisble hai kya from both side 2,9 or 
        9,2 nahi. So this is invalid. Abhi ni karoge to ye issue aaega
        and iske liye alag se check lagana pdega ki jo number hum add 
        kar rahe hai vo ans list me sabse divisible hai ya ni.

        And sort krke and above condition na laga kar kya fayda hai dekho
        vapas , now since array is sorted, to subset banega abhi
        2,9,18, humne check kiya 9%2 == 0 no, to bhai ye ans me add ni hoga
        then humne check kiya 2,18 since 18%2 == 0 ye ans me add hoga, 
        2, 18 ans me aa gaya as 2 pehle se hoga. then isi subset me se alag
        subset banega 9,18 ye alag list me add hoga same me ni.

        Ye fayda hai sorting ka.
        */
        Arrays.sort(nums);

        int dp[][] = new int[nums.length+1][nums.length+1];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }

        List<Integer> ans = new ArrayList();

        int max = helper(nums, 0, -1, dp);

        int i = 0;

        int prev = -1;

        while(i < nums.length){

            if(prev == -1 || nums[i] % nums[prev] == 0){
                int take = 1+dp[i+1][i+1];// since it should be
                                          // d[i+1][prev], but prev 
                                          // original value was -1, 
                                          // so we need increase it 
                                          // by 1 and since in take
                                          // condition we use current
                                          // i value as prev, hence 
                                          // i+1 not prev.

                int notTake = dp[i+1][prev+1]; // prev+1 because, we require
                                                // not the current i value
                                                // instead original prev value
                                                // and prev is incremented by 1
                                                // so prev + 1
                if(take >= notTake){
                    ans.add(nums[i]);
                    prev = i;
                }  
            }
            i++;            
        }

        return ans;
    }

    public int helper(int nums[], int i, int prevIndex, int dp[][]){
        if(i == nums.length) {
           return 0;
        }

        if(dp[i][prevIndex+1] != -1) return dp[i][prevIndex+1];

        int take = 0;
        int notTake = 0;

        if(prevIndex == -1 || nums[i] % nums[prevIndex] == 0){
            take = 1 + helper(nums, i+1, i, dp);
        } 

        notTake = helper(nums, i+1, prevIndex, dp);

        return dp[i][prevIndex+1] = Math.max(take, notTake);
    }
}