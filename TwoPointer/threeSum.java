/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.

Intution:

1. Sorting bohot help karti hai two pointers pattern me, humko ye pata chalta hai ki:
    a. agar result expected target se bada aaya hai to right pointer move krna hai so that result chota ho jae.
    b. and if result expected target se chota aaya hai to left pointer move krna hai taki result bada ho jae.
    c. result bada chota aise hota hai kyuki sorted array me left side small values hoti hai and right side
        badi values.
2. To isme sorting lagegi, also ye Two sum question ka extended version hai and isko bhi two sum ki tarah
    solve krna hai.
3. Uske bad main logic hai triplets combination banan, vo kese banege simple hai:
    a. for loop chalao or ek element pakad lo.
    b. then us element ko pakad kar i and j define karo along with while loop. i = a+1 se and j = nums.length-1 
        se, kyuki two pointer me bhi aise hi krte the. 
    c. then sum karo and check if equal to zero, add all three to list. else check result 0 se bada hai to:
        i. move j.
        ii. else i.
4. Abhi aata hai ki triplets ko unique kese rakhege, uske liye humko teen cheeze krni hai:
    a. for loop me jis element ko pakda hai, vo jab index 0 se aage aa jae, tb check karo ki nums[a] == nums[a-1]
        if yes, then skip(continue;)
    b. similarly for i and j, after storing result into list jab sum zero mil jae, while loop chala do dono par
        and check their previous value if same then i++ and j--.

Bonus tip:
1. Shyd tum bhul jao ki j pointer ke liye prev element j-1 par ni j+1 par milega.
2. Ab tumhra followup ques ho skta hai ki, j+1 karege to out of bound error aa jaegi kyuki, j = nums.length-1 hai.
3. Si soch rahe ho but while loop se pehle hum already j-- kar chuke hai, so ni aaegi, kyuki j already ek 
    position pehle move ho chuka hai.
        

*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> li = new ArrayList();

        Arrays.sort(nums);
        int i = 0;
        int j = nums.length-1;

        for(int a = 0; a < nums.length-2; a++){
            i = a+1;
            j = nums.length - 1;

            if(a > 0 && nums[a] == nums[a-1]) continue;

            while(i < j){
                int sum = nums[a]+nums[i]+nums[j];
                if(sum == 0){
                    List<Integer> temp = List.of(nums[a], nums[i], nums[j]);
                    li.add(temp);
                    i++;
                    j--;

                    while(i < j && nums[i] == nums[i-1]) i++;
                    while(i < j && nums[j] == nums[j+1]) j--;
                }else if(sum > 0){
                    j--;
                }else{
                    i++;
                }
            }
        }

        return li;
        
    }
}