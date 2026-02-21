/*
Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

 

Example 1:

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
Example 2:

Input: nums = [0]
Output: [[],[0]]

Intution:
1. Bhai ye ques bhi subset banane ki tarah hai jisme backtracking ke simple teen rule lagege:
    a. Ans banao include
    b. then recursion karo
    c. then jo ans me add/update kiya tha usko undo kardo.
    d. then start from point a again.
2. Abhi ye ques thora tricky hai, kyuki isme input me duplicate elements hai, and ques keh ra hai
    ki bhai duplicate subset mat banana. For example:
    Agar input hai: [1,2,2]. Iske normal subsets banege:
    [],[1],[1,2],[1,2,2],[1,2],[2],[2,2],[2]
    but isme kuch subsets repeating hai jinko humko hatana hai.
3. Abhi tree branches par aao: jese likha hai
                           [1,2,2]
                            a b c                              
                            / | \ 
                           /  |  \ 
                          /   |   \
                         /    |    \
              [1],[2,2]       |   [2],[2]
                              |
                           [2],[2]
    Upar dekhoge, to agar tum branch b and c dekho, to smjh aaega, ki bhai duplicate to humko
    lene ni hai, still same branch ban ri hai to abhi se cut kar do,
    that's why condition lagi thi:
    ki if(i > start && nums[i] == nums[i-1]) continue;
    
    jaha par start simply ye bata ra hai ki loop tumne start kaha se kiya hai, and i bata ra hai
    ki loop abhi kitna aage badha hai.

4. Baki condition same backtracking vali hi hai.

                                   

*/

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList();

        // sorting humko isliye karni hai, kyuki ek ke bad dusra element same to ni hai ye check krna hai.
        Arrays.sort(nums);
        create(nums, result, new ArrayList(),0);

        return result;
    }

    public void create(int nums[], List<List<Integer>> result, List<Integer> li, int start){

        result.add(new ArrayList(li));

        // jab bhi subset jese ques me duplicate word aae tab loop lagana.
        // i is simple a iterator, jisse humko array traverse krna hai.
        // start simple ye batata hai, ki loop infinite na ho to array ke next index se start kro jispar current khade ho.
        //Next index se start kyu karna hai, kyuki agar i = 0 rakhooge, to loop ke ander jo recursive call hai vo vapas loop trigger krne par i = 0 se start kregi, jisse infinite loop ban jaega.
        // i > start ka simple ye mtlb hai, ki jab i current element se aage ho.


        for(int i = start; i < nums.length; i++){
            // ye line tumko simply ye keh ri hai ki, jab vapas aao recursive call se, tab ye check kar lo
            // ki jis element se ek branch bana chuke ho usse hi to dusri ni bana rahe.
            if(i > start && nums[i] == nums[i-1]){
                continue;
            }

            li.add(nums[i]);

            //1. yaha par hum i+1 isliye likh rahe hai kyuki humko har call me ek index aage se start krna 
            // hai else infinite loop banega
            //2. Yaha par hum start+1 ki jagah i+1 isliye kar rahe hai, kyuki i hi vo index variable hai jo
            //   array ko traverse kar raha hai, na ki start.
            //3. Agar start+1 likhege to, at a moment hoga ki start+1 se i ki value reset ya galat ho skti 
            //   hai.
            //4. Agar socho for loop ki jagah while loop hota to i+1 hi likhte na, for accessing next element
            //5. same way yaha par bhi i+1 likh rahe hai.
            //6. It's like start tumko start index bata raha hai like left pointer, and i tumko right pointer
            //   banke array traverse krva raha hai.
            // structure kuch aisa banega, for input: [1,2,2]
            // 1: [1,2,2]
            // 2: [1,2,2]
            // 2: [1,2,2]
            create(nums, result, li, i+1);

            li.remove(li.size()-1);
        }
        

    }
}