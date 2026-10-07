/*
You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.

If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.

Return the maximum coins you can collect by bursting the balloons wisely.

 

Example 1:

Input: nums = [3,1,5,8]
Output: 167
Explanation:
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
Example 2:

Input: nums = [1,5]
Output: 10

Intution:

1. Bhai ye ques hai easy agar tumko partition dp aati hai, jo ki ye ques lagane ke liye tum smjh
    chuke ho.
2. Basically ques keh ra hai ki array diya hai jisme tumko arr.size quatity me balloons diye hai
    and jo number hai har index par us balloon par utna likha hai and agar tum vo balloon 
    fodte ho to tumko nums[i-1] * nums[i] * nums[i+1] balloon milege.
3. Ek ques or aaega ki agar i overflow hua to dono side se, to us case me 1 multiply karna hai 
    i-1 ya i+1 krne par.
4. Abhi multiple combinations ban skte hai pehla fodo ya pehle chor do bad me fodo, and tmko
    sare combinations me se max coins nikalne hai. Ki aise balloon fodo ki coins max mile.
5. Aao abhi intution dekhte hai:
    1. Isme zada kuch ni hai 3 steps hai bas:
        a. recursion function banao in form of range i and j. 
            i. Jisme i = 0 pass kar do and j = nums.length-1 pass kar do.
            ii. Abhi samjho ki i and j liye hai to isko lene ka reason ye hai ki left and right 
                as alag subproblem solve krni hai to window shrink karke left bana sake and 
                right bana sake.
            iii. Abhi scene ye hai ki i and j shrink ho re hai to base condition kya hogi isko
                kar. vo hogi i > j to return 0.
        b. Abhi dusra ye hai ki combinations banao and sare try karo:
            i. Abhi combinations kese banaege vo dekhte hai. 
            ii. Dekho bhai hum sabse pehla call karege i = 0 se lekr j = nums.length - 1 par.
            iii. Then agar tum ek for loop laga do jisme k = i -> k <= j chala do and k ko current
                element lelo.
            iv. To tmhre pas teen variable ho gye i, k, j. Abhi inka mtlb smjhte hai pehle.
            v. Abhi baba main logic smjho kya hai, main logic ye hai ki humko jo current balloon
                hai, vo sabse last me fodna hai. Fir tum kahoge ya par ho skta vo balloon sirf last
                me consider hoga.
            vi. Bhai smjho baat ko for loop chal raha hai k vala tumko current balloon ko chora jo ki
                first vala tha to baki sare right me fod diye then current foda, coins aae store kiye.
            vii. Then k++ hua us time par tumne 0 vala foda and 1 vala last ke liye rakha and baki
                    right ke fod diye... dekho bhai sare combinations check ho re hai.
            viii. Itna sab smjhane ka mtlb ye hai ki tum last vala balloon jab fodoge tab tum 
                ye likhoge nums[i-1] * nums[k] * nums[j+1] and puchoge bhai k-1 and k+1 kyu ni kiya
                .
            ix. To itna isi liye smjhaya hai kyu ni kiya mene k-1 and k+1. Vo isliye mere bhai kyuki
                current balloon jab footega usse pehle uske left and right vale balloon foot chuke 
                hoge, means i to k-1 and k+1 to j ki range me jitne balloon hoge vo foot gye hoge.
                Agree?
            x. And as per ques humko vahi balloon fodne hai jo already foote na ho, and aise balloon
                humko i to k-1 and k+1 to j ki range ke bahr milege and vo range kya hogi i-1 and j+1.
            xi. Abhi mere bhai tum kahoge but overflow hoge, maine kaha ha bhai uske liye tum 
                condition laga if not overflow then consider i-1 into current balloon burst multiplication
            xii. Similarly for j+1 if not overflow then consider j+1 into current balloon burst multiplication.
        c. Last me aata hai har iteration of for loop me tumko coins milege uska max maintain krna hai
            and vahi return kar dena hai.
    2. Abhi isi ko dp me set kar do, to i and j state and 2d matrix bana lo and store karo and return 
        kar do if already calculated.

*/

class Solution {
    public int maxCoins(int[] nums) {
        int dp[][] = new int[nums.length][nums.length];

        for(int arr[] : dp){
            Arrays.fill(arr, -1);
        }

        return helper(nums, 0, nums.length-1, dp);
    }

    public int helper(int[] nums, int i, int j, int dp[][]){
        
        if(i > j) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        int max = 0;

        for(int k = i; k <= j; k++){

            // current recursion me as per ques humko vo
            // balloons burst krne hai jo already burst na hue ho
            // Abhi agar hum ye kare ki jo leftmost hai unko burst 
            // kar de and max coins le aae and right vale burst kar
            // and unke max coins le aae, then hamare pas bacha current
            // vala usko bhi burst kar de. To humko ans mil jaega.
            // But since hum current balloon sabse last me burst kar
            // rahe hai to uske pehle baki balloons burst ho chuke hoge
            // to humko prev and next vo balloon choose krna hai
            // jo burst na hue ho, and vo balloon hum agar k-1 and k+1
            // par jaege to immediate next balloon hoge, to humko
            // aise balloon chahiye to current passed i and j range ke
            // bahr hai means left me i-1 and right me j+1. To same
            // vahi kar rahe hai hum neeche by doing i-1 and j+1
            // not k-1 and k+1

            int currCoins = 1;
            if(i-1 >= 0){
                currCoins *= nums[i-1];
            }

            if(j+1 < nums.length){
                currCoins *= nums[j+1];
            }

            int coins = helper(nums, i, k-1, dp) + 
                        helper(nums, k+1, j, dp) +
                        (currCoins * nums[k]);// burst current balloon at last

            max = Math.max(coins, max);
                        
        }

        return dp[i][j] = max;
    }
}