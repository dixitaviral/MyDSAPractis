/*
    Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

 

Example 1:

Input: nums = [3,2,3]
Output: 3
Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2

Intution:

* jo neeche algo lagayi hai isko Boy's More algorithm bolte hai
* Jo ki kehta hai ki agar tumko kabhi majority kind of ques aae
* to bhai do group bana do majority vs non-majority
* Isko karege kese, aao dekhe
* sabse pehle count variable lelo
* inutuion simple hai agar same element aata rahe to count++ 
* agar diff element aae to count --;
* jab count 0 ho jae to vapas se current element se compare krna start kar do
* isko aise smjh ki BJP ko char vote mile and Congress ko 3 mile
* abhi tumko computer se nikalvana hai majority kidhr hai
* vo O(1) space and linear time complexity me
* to bhai upar se dekho to BJP ke 3 vote and Congress ke 3 vote ho gye cancel
* then bacha BJP ka 1 vote, jo ki majority hai
* same logic yaha aaya ki same number aata rahe to count++;
* diff aae to count --;
* 0 ho jae to bhai new banda assign karo

*/

class Solution {
    public int majorityElement(int[] nums) {
        int num = 0;

        int count = 0;

        for(int n : nums){
             if(count == 0){
                num = n;
            }

            if(n == num){
                count++;
            }else{
                count--;
            }
        }

        return num;
    }
}