/*
There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).

Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].

Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.

You must decrease the overall operation steps as much as possible.

 

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false

Intution:

1. Bhai ye ques bhi same tmhre search in a rotated sorted array jesa hai.
2. Bas isme scene hai ye ki isme duplicate numbers bhi diye hai.
3. Abhi aao smjhe sabse pehle duplicate numbers kya issue lekr aate hai:
    a. example lete hai 1,0,1,1,1. Abhi isme tm search in a rotated sorted array vala logic lagaeoge.
    b. 3-4 poimts me dekhte hai kya logic tha:
        1. Sabse pehle mid nikal lo if mid == target return.
        2. Abhi mid match ni hua and array rotated and sorted hai. To agar mid nikalege to
            left or right me se koi ek sorted zarur hoga.
        3. To humne pehle check kiya if else laga kar ki konsi side sorted hai left if and else
            right.
        4. Abhi if else ke ander hum check krte the ki left means i to mid ke beech target hai ya ni,
            agar hai to shrink respective pointer and if ni hai to else me dusra pointer shrink karo.
        5. Same in else block ki right me mid to j ke beech number hai ya ni agar ni hai to respective
            pointer shrink karo and agar hai to dusra pointer shrink karo.
    c. Chalo badhiya logic recall ho gaya to 1,0,1,1,1 par mid nikalo to 2 aaega means 1 number.
    d. Abhi duplicates allowed hai tumne left sorted hai ya right, iske liye condition lagayi 
        nums[i] <= nums[mid]. As per example ye true ho gaya but yrr left side to 1,0,1 hai jo ki
        sorted ni hai. Same nums[mid] <= nums[j] ye bhi true hai.
    e. Abhi socho dono taraf true aa ra hai to mere bhai sorted kidhr vala smjhe. To is condition se
        bachne ke liye hum yahi dono conditions check krte hai mid nikalne ke bad. Ki agar nums[i] == nums[mid]
        == nums[j] means ambiguity ki konsi side sorted hai is case me simple i++ and j-- krte hai.
    j. Ab isse ye hoga jo ambiguous sequences hai vo nullify ho jaege.
4. Bas isme itna extra kaam tha.

Ho gaya.

*/


class Solution {
    public boolean search(int[] nums, int target) {
        int i = 0;
        int j = nums.length-1;

        while(i <= j){
            int mid = (i+j)/2;

            if(nums[mid] == target) return true;

            // baki to same apna search in a rotated sorted array vala hai
            // Bas ye condition isme extra isliye hai kyuki tumne khud
            // smjha tha ki bhai agar numbersd duplicate hai and i == mid
            // hone par ye kese pata karu ki left sorted hai ya ni
            // kyuki left 1,0,1... bhi ho skta hai and 1,1,1 bhi.
            // Is case me agar hum nums[j] bhi check krte hai ki bhai
            // agar nums[j] bhi equal hai tab to ambiguity ho gyi yrr ki 
            // konsi side sorted hai us case me i++ and j-- krte hai so that
            // mid par target mil jae and complexity isme na chah kar bhi
            // O(n) ho jaega.
            if(nums[i] == nums[mid] && nums[mid] == nums[j]) {
                i++;
                j--;
            }
            else if(nums[i] <= nums[mid]){
                if(nums[i] <= target && target <= nums[mid]){
                    j = mid-1;
                }else{
                    i = mid+1;
                }
            }else{
                if(nums[mid] <= target && target <= nums[j]){
                    i = mid+1;
                }else{
                    j = mid-1;
                }
            }
        }

        return false;
    }
}