/*
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.

 

Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]

Intution: Dutch National Flag problem 

 1. idea ye hai ki two pointer to hai tmhre pas
 2. i or j keh lo ya fir low and high keh lo
 3. low par tumko 0 assign karna hai
 4. high par tumko 2 assign karna hai
 5. bacha 1 uske liye bhi ek variable lelo mid
 6. par dikkat ye hai pata ni kitne 0 and 2 hai to mid
 7. kya hi le
 8. to hum kehte hai mid ko start karo 0 se but mid mtlb hai 1
 9. store hoga mid index par
 10. fir idea ye hai ki hum mid ko check krte hue chalte hai
 11. agar mid par 1 aata hai, to humne kaha mid par 1 hi hona chahiye 
 12. and aaya bhi 1 hai to aage chlo
 13. abhi agar mid par 0 aaya to iska mtlb hai iss 0 ko low par jana chahiye
 14. to mid and low me swapping kar do.
 15. Ab ques aata hai ki man lo low par 2 ho, to koi ni uska bhi check lagaya hau
 16. abhi ye swap hone do.
 17. fir ek else condition hai agar mid == 2 hai to mid and high ko swap kar do
 18. kyuki mid to 2 ni hona chahiye high par hona chahiye
 19. bas yahi intution hai.

*/

class Solution {
    public void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length-1;

        while(mid <= high){
            if(nums[mid] == 0){
                swap(nums, low, mid);
                low++;
                mid++;
            }
            else if(nums[mid] == 1){
                mid++;
            }else{
                swap(nums, mid, high);
                high--;
            }
        }
    }

    public void swap(int nums[], int start, int end){
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }
}