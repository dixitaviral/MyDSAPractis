/*

Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.

 

Example 1:

Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]
Example 2:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Intution:

1. Bhai iss question se bohot kuch seekha. Chalo dekhte hai.
2. Sabse pehle to ye condition, jo ki humko duplicate branches banne se bachati hai->
    if(i > 0 && nums[i] == nums[i-1]) ye sorted array par kam krti hai.
3. Abhi upar ki condition me humne is ques me ek extra check lagaya hai, visited[i-1] == 0;
    a. Abhi ye extra check kyu, pehle ques me humne to ni lagya tha.
    b. Vo isliye kyuki pehle ke ques me hum ek index traverse krne ke bad aage badh jate the,
        means left elements par vapas ni jate the.
    c. But isme hum ja re hai and dusra ye ki isme duplicates bhi hai, jinko hume depth me allow 
        krna hai but horizontally allow ni krna, kyuki duplicate branch ban jaegi.
        Aao dekhe kese, for example 1,1,2 hai, lets say 0th index 1 is 1a and 1st index 1 is 1b.
        to array bana [1a,1b,2];
        abhi 1a ne branch banai-> [1a] then do choice neeche
                           [1a,1b]    [1a,2]
                        [1a,1b,2]       [1a,2,1b]

        abhi 1b ne branch banai-> [1b] then do choice neeche
                           [1b,1a]    [1b,2]
                        [1b,1a,2]       [1b,2,1a]

    d. abhi agar a and b hata do, to dono ne same branch banai. To ye faltu time waste hai. Humko
        isse rokna hoga.
    e. Abhi rokege kese, hum kahege if(i > 0 && nums[i] == nums[i-1]) continue. Mtlb jab ek 
        same element already branch bana chuke hai tab dusra same bhi same branch banaega. to skip.
    f. But abhi ek twist or bhi hai, agar tum sirf point e vala check lagate ho usse hoga kya,
        ki jese 1a ke bad vali branch dekho jisme usne 1a, 1b liya hai, abhi yaha par, point
        e vali condition true ho jaegi, and 1b skip ho jaega, jo ki galat hai. Humko Depth me 
        duplicates allow krne hoge.
    g. Abhi vo krne ka simple tareeka hai, hum dekhe ki current path me prev 1 hai ya ni, agar
        hai iska mtlb prev 1 ki branch chal ri hai, to allow.
    h. agar ni hai, to mtlb ye dusra 1 hai vo nyi same branch banane vala hai, jo already ban chuki
        hai, to skip.
    i. abhi isko likhege kese, bss point e me ye condition add kar do: visited[i-1] == 0;
    khatam.
4. Abhi ye main condition thi, bs baki recurse undo. yhi krna hai.

5. Ek important baat, isme humne for(i = 0) likha hai na ki i = start. Vo isliye kyuki humko
    peeche vale elements bhi consider krne hai and start hamesha forward chalta hai. isliye ni liya
6. Agar simple permutation karoge jisme duplicates ni hai, usme msst visited ka use karo sirf
    and ho jaega. No duplicate condition there.

                                      

*/


class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList();

        Arrays.sort(nums);
        create(nums, result, new ArrayList(), new int[nums.length]);

        return result;
    }

    public void create(int nums[], List<List<Integer>> result, List<Integer> li, int idx[]){
        if(li.size() == nums.length){
            result.add(new ArrayList(li));
            return;
        }

        // we will consider [1,1,2] for dry run in below explaination.
        // here horizontal traversal loop se hoga and vertical recursion se
        for(int i = 0; i < nums.length; i++){
            // horizontally used index branch na bane like 1,1,2, so 1st 1 se dobara branch na bane
            // uske liye
            if(idx[i] == 1) continue;

            // now ye isliye kar re hai ki simple and straight bolu to:
            // agar hum depth me ja re hai to duplicates allowed hai
            // and if hum horizontal ja re hai means new branch bana rahe hai, to skip.
            // Abhi socho agar depth me jaege to pehle 1 ke bad dusra 1 aaega, right?
            // now pehla 1 visited me mark hona chahiye, right?
            // to condition false ho gyi visited[i-1] == 0, and current duplicate 1 taken in depth.
            // Abhi socho prev 1 visited me marked ni, and hum dusre 1 par khade hai
            // to iska mtlb ye hua ki jo dusra 1 ek naya apna path start kr ra hai
            // since visited me prev 1 marked ni hai, jo ishara hai duplicate branch ka 
            // vahi same branch jisko hum prev 1 se already traverse kr chuke hai,
            // tabhi visited[i-1] == 0 hua true and current 1 skip.
            if(i > 0 && nums[i] == nums[i-1] && idx[i-1] == 0) continue;

            li.add(nums[i]);

            idx[i] = 1;

            create(nums, result, li, idx);

            li.remove(li.size()-1);

            idx[i] = 0;
        }
    }
}