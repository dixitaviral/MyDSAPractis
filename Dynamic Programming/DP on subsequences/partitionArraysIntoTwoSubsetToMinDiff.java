/*
You are given an integer array nums of 2 * n integers. You need to partition nums into two arrays of length n to minimize the absolute difference of the sums of the arrays. To partition nums, put each element of nums into one of the two arrays.

Return the minimum possible absolute difference.

 

Example 1:

example-1
Input: nums = [3,9,7,3]
Output: 2
Explanation: One optimal partition is: [3,9] and [7,3].
The absolute difference between the sums of the arrays is abs((3 + 9) - (7 + 3)) = 2.
Example 2:

Input: nums = [-36,36]
Output: 72
Explanation: One optimal partition is: [-36] and [36].
The absolute difference between the sums of the arrays is abs((-36) - (36)) = 72.
Example 3:

example-3
Input: nums = [2,-1,0,4,-2,-9]
Output: 0
Explanation: One optimal partition is: [2,4,-9] and [-1,0,-2].
The absolute difference between the sums of the arrays is abs((2 + 4 + -9) - (-1 + 0 + -2)) = 0.

Intution:
1. Bhai ye samjh lo aaj tak sabse baap ques hai jo tumne khud se ni lagaya hai but smjha hai kiya hai.
2. Iska brute force pehle dekho lo, usse pehle ques kya keh raha hai smjho:
    a. Ques keh ra hai bhai tmhre pas n size ka array diya hai. Isme se tum jitne n/2 size ke
        subset soch skte ho nikal skte ho nikal lo.
    b. Then tum in sare n/2 size ke subsets ka absolute diff nikalo and sbse min abs diff return kar do.
3. Ques bohot simple hai, ye recursion and dp se easily ho skta hai no issue.
4. Aao fir intution dekhe recursion + DP ki :
    a. Sabse pehle to yrr base condition ki bat kr lete hai.
        i. Pehli base condition agar i == nums.length return Int_max. int max isliye kyuki humko
            min diff store karna hai, tum jante ho 0 karege to min sabse 0 ho jaega.
        ii. Abhi dusri condition ye ki since humko unhi subsets ko consider krna hai jinki length
            n/2 hai. To ek count variable bhi lena pdega na ki jab count of taken numbers == n/2 ho 
            tabhi consider karo. To man lo nums.length hai 6 means last index 5 and tmhra count hai 1
            and i hai 4 par, to is hisaab se tum element loge count == 3, but since tum already 4 par ho
            to 5 vala or le skte ho bas to mtlb count 3 kabhi jaega hi ni is condition me to ek check
            laga do ki if (nums.length/2 - count > nums.length - i) to return int max again.
        iii. Abhi third condition simple jab count == n/2 to return Math.abs(sum - (totalSum - sum)).
            Abhi ye jo abs vali kahani likhi hai neeche smjhata hu bas itna smjho ye valid case
            return hai and base codntion hai.
    b. Ab aati hai baat main intution ki:
        a. Dekho bhai tum sochoge ki hum do sum track kar le ek left ek right bla bla.... Choro mai
            batata hu ek short cut.
        b. Dekho bhai man lo tumne ek kisi n/2 size ke subset ka sum nikal liya, abhi tumko dusre unique
            n/2 size ke subset ka sum nikalna hai, to kya dobara se recursion karoge. Mai kehta hu ni
            dekho bhai ek subset n/2 size ka sum hai A and dusre subset n/2 size ka sum hai B.
        c. Ques bhi keh raha hai ki total array length ke half size ka subset sum ka abs min diff return
            karo.
        d. To itna mante ho na ki ek valid subset sum me hum array ke half elements ko cover kar chuke hai.
        e. To bhai agar hum pure array ka sum nikal le totalSum, and usme se jo hum subset sum banaya
            minus kar de, to ek dusre subset ka sum ni aa jaega.
        f. To formula kya bana Math.abs(sum - (total-sum)), kya bolte ho.
        g. Bas Yahi intution hai, ab iske aage msst ek take vali recursion call karo by including
            current number in the sum then return aane par min variable me Math.min krke store karo.
        h. Similarly notTake vali condition likho jisme sum me curr number include ni krna hai, isse
            jo return aaega vo and min variable me se min store karo.
        i. Last me min return kar do.
5. Ab bhai tum kahoge recursion ho gaya memo lagaye, mene bhi yahi socha tha, mene bhi i, sum and count
    par memeo lagaya, ab scene ye hua ki:
    1. Pehle to arr nums negative me bhi ho skte hai and dp memo me sum as state hai, but negative
        sum means negative index to hota ni hai array me. To yaha fase.
    2. Mene kaha koi ni Map of string and integer banaya and key me i+"-"+sum+"-"+counr rakha and 
        value me min. Tab bhi TLE aaya, ab vo isliye.
    3. Kyuki ques keh raha hai ki jo number present ho skte hai unki range hogi: -10^7 to 10^7. Means
        ek number itna bada ho skta hai. And max 15 size ka subset sum chahiye hoga.
    4. To bhai dp memo bhi isko optimise ni kar payi.
    5. Isi liye neeche simple recursive solution me mene dp ni lagaya.


Ab bat krte hai fir BC ye ques hoga kese, ek concept hai mene dekha hai usko kehte hai meet in the
middle krke.

1. Iske simple ye steps hai ki array ko do parts me divide karo agar array size hai n to half kar do
    ek n1 = n/2 size and ek n2 = n-n1. (n2 = n/2 isliye kiya kyuki agar odd size hota to n1 and n2 same
    aate jisse ek element choot jata).
2. Abhi ye jo array divide kiya hai tumne inka sabka subset sum nikal kar store kar lo. Man lo n1 array
    me 3 elements hai to inke total subset sum hue 2^n1 means 8 similary for n2 as well.
3. Abhi tmhre pas do array hai ek n1 size ka ek n2 size ka. Plus tmhre pas total sum bhi hai.
4. To high level ye dekho ki agar tum n1 subset sum array par loop chalao and for every element in
    n1 tum total-n1's element find karo in n2 subset sum array.
5. To basically ye ques ban jaega subset sum equal to zero krke.

Abhi upar jo mene bataya hai ye seedha example hai isi concept ko tweak krke humko iske solution
me use krna hai.

Solution/Intution below solution ke neeche likha hai.
*/

// simple recursive solution
class Solution {
    public int minimumDifference(int[] nums) {
        int totalSum = 0;

        for(int num : nums){
            totalSum += num;
        }

        return helper(nums, 0, 0, totalSum, 0, dp);
    }

    public int helper(int nums[], int i, int sum, int total, int count){
        if(i == nums.length) return Integer.MAX_VALUE;

        if(((nums.length)/2) - count > nums.length-i) return Integer.MAX_VALUE;

        if(count == nums.length/2) return Math.abs(sum - (total-sum));

        int min = Integer.MAX_VALUE;

        int take = helper(nums, i+1, sum+nums[i], total, count+1);

        min = Math.min(take, min);

        int notTake = helper(nums, i+1, sum, total, count);

        min = Math.min(notTake, min);

        return min;
    }
}


/*
Intution:
1. Bhai bohot cool solution hai but dhyan se smjhna okay.
2. Aao fir start kare.  

*/

class Solution {
    public int minimumDifference(int[] nums) {
        int totalSum = 0;
        int length = nums.length;
        int half = length/2;
        for(int num : nums){
            totalSum += num;
        }

        // breaking nums into two half part
        //-------------------------------
        int left[] = new int[half+1];
        int right[] = new int[half+1];

        for(int i = 0; i < half; i++){
            left[i] = nums[i];
            right[i] = nums[i+half];
        }
        //------------------------------

        // Making Array of list to store subset sum of left and
        // right halfs on the basis of count of elements 
        // for example all subset sums of subset size 2 
        // will be store on index 2 array list.
        List<Integer>[] leftSubsetSum = new List[half+1];
        List<Integer>[] rightSubsetSum = new List[half+1];

        for(int i = 0; i <= half; i++){
            leftSubsetSum[i] = new ArrayList();
            rightSubsetSum[i] = new ArrayList();
        }

        //Step 1:
        // generate subset sum of all possible subset size
        helper(left, 0, 0, 0, leftSubsetSum);
        helper(right, 0, 0, 0, rightSubsetSum);

        //Sort the rightSubsetSum list as you need to search
        // numbers 
        for(List<Integer> list : rightSubsetSum){
            Collections.sort(list);
        }

        //step 3:
        // loop over leftSubsetSum and do total-subsetSum find
        // inside remaning count index list of rightSubsetSum.
        // After you will find a subset sum from taking one from
        // left and other from right. Then you have to use this formula
        // Math.abs(subsetSum, (total-subsetSum)) and store this in min
        // varibale which will get compared till the loop ends at last 
        // you will get ans.

        int ans = Integer.MAX_VALUE;

        for(int i = 0; i <= half; i++){
            int remaining = half-i;

            for(int sum : leftSubsetSum[i]){

                // actually smjho, humne left me sare subset size
                // ka sum nikala hai abhi agar nums 6 size ka hai
                // to hamare formula ke hisaab se sum - (total - sum)
                // kehne ka mtlb hai ki humko half sum nikalna hai
                // kyuki ques keh ra hai valid subset size vahi hai
                // jo array len/2 hoga. Usi subset ka sum nikalo and baki
                // same size ke subset sum se minus krke min rteurn karo.
                // Hamare logic ke hisaab se hum agar ek subset ka sum nikal
                // le of size nums array len / 2 and totalsum me usko minus kr de
                // to ek diff mil jaega. 
                // Neeche vali line usi ke liye hai ki hum ek subset ka sum nikalna hai
                // of size n/2 man lo ek subset ka size 3 hai and humne leftSubsetSum me
                // sum liya jiska subset size tha 1 then hum right vale subset se vo subset
                // sum le sakte hai jiska subset size 2 hoga kyuki 2 + 1 = 3. So LeftSubsetSum+
                // rightSubsetSum * 2 hi to total sum ke barabar hua.
                // Hence below line.
                int target = (totalSum/2) - sum;

                int idx = binarySearchFind(target, rightSubsetSum[remaining]);

                int subsetSum = sum + rightSubsetSum[remaining].get(idx);

                ans = Math.min(ans, Math.abs(subsetSum - (totalSum - subsetSum)));
            }
        }

        return ans;
        
    }

    public void helper(int nums[], int i, int count, int sum, List<Integer>[] list){
        if(i == nums.length) return;

        list[count].add(sum);

        helper(nums, i+1, count+1, sum+nums[i], list);

        helper(nums, i+1, count, sum, list);
    }

    public int binarySearchFind(int value, List<Integer> list){
        int i = 0; 
        int j = list.size()-1;

        int minDiff = Integer.MAX_VALUE;
        int minIndex = 0;

        while(i <= j){
            int mid = (i+j)/2;
            
            //iss line ka mtlb hai ki agar value na mile, to 
            // return that index jisse value ka diff sabse kam ho
            if(minDiff > Math.abs(value-list.get(mid))){
                minDiff = Math.abs(value-list.get(mid));
                minIndex = mid;
            }

            if(list.get(mid) == value) return mid;

            if(list.get(mid) > value){
                j = mid-1;
            }else{
                i = mid+1;
            }
        }

        return minIndex;
    }
}