/*
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

 

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
Example 3:

Input: intervals = [[4,7],[1,4]]
Output: [[1,7]]
Explanation: Intervals [1,4] and [4,7] are considered overlapping.

Intution:
1. Bhai simple array ka ques hai ki intervals diye hai jese 1,2 and 2,3 to ye overlap karte hai
2. To isko ek interval me kar do jese ho gaya 1,3.
3. Abhi isko krna kese hai aao dekhe:
    a. Sabse pehle ek bat smjh lo, hamesha jab ek interval diya hai hoga like 1,2. To jo first
        digit hogi vo hamesha choti hogi second se.
    b. Abhi ek magic dikhata hu, tumse pas intervals hai man lo 1,2 and 3,4. Ye ek valid alag
        alag intervals hai, jo ki overlap ni ho re. 
    c. Abhi ye hame dekhne me pata chal ra, but hamara mind aisi kya calculation kar raha hai
        ki humko ye pata lag ra hai ki ye overlap ni ho re.
    d. See hume pata hai ki ek interval me first digit will always less than second digit.
    e. Then overlapping kese pata karege, bhai man lo do intervals diye hai, abhi tumne dekhna hai
        overlap ho re ya ni, to tum ye dekhoge na ki jo pehle interval ki second digit hai,
        vo second interval ki first digit se badi hai kya, for example 1,3 and 2,4. ye saaf overlap
        ka case hai jisme 3 > 2 and equal ka bhi case ho skta hai like 1,2, and 2,3.
    f. Bas yahi intution code me likh deni hai.
    g. But humko isme sorting ki need bhi hai, socho ki interval diya hai 4,7 and 1,4. Abhi
        dekhne me to lag ra hi ki merged interval 1,7 hoga, but agar isko implement
        karoge, then uss case me tumko alag se array lena padega, then usme to 4 to 7 fill karoge
        then next interval 1,4 fill karoge, usko fill krte time tumko pata chalega ki bhai
        4 to already visited hai, it means overlap hai. 
    h. But ab mushkil ye aaegi ki 4 overlap hai ye to mil gaya but tumko start kisse krna hai
        and end kaha karna hai ye sab nikalne me kafi logic jaega.
    i. Best approach hai intervals ko sort kar do, based on start number of every intervals.
    j. isse humko jo intervals pehle occur ho re hai vo mil jaege and usse humko aage ke intervals
        ki boundar easily pata lag jaegi.
    k. Abhi Sorting ke liye tum likh skte ho Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]))
    l. Bas to intution kuch aisi jaegi abhi:
        i. Sabse pehle sorting.
        ii. Then res array bana lo jisme merged intervals ko store karegoge.
        iii. do variable bana lo start and end, jisme 0th interval ka start and end store kar lo.
        iv. For loop chalao 1 index se and usme check karo ki current end jo hai is that >= current
            loop index ka start, if yes then update the end to max of current end or current loop
            index end. example 1,3 and 3,6. Abhi jiska end bada hoga merge to uski interval ka hoga na
            to is condition se end reset hua to 6.
        v. Abhi else me aa jao agar chota hai for example 1,3 and 4, 6. Abhi ye overlap ni kar ra
            to isme start and end ko res me add kar do.
        vi. Abhi ek or cheez since tumhra loop chal ra 1 se, but tumse start and end store kiya, 0th
            index ka, to jab loop khatam hoga uske bad me tumko res me start and end karna hoga.
        vii. Uske bad since tume res array banaya hai interval ke size ka, but tumko
            exact size return karna hai jitne merged intervals tumhre pas bache hai.
        viii. Uske liye tum karoge Arrays.copyOf(res, index+1), since index tumhra res array me
            insertion ke liye kaam aaega, to jo bhi last index par tumne store kiya hoga,
            usse plus 1 size tumko return karna hai, kyuki array to 0 indexed hota hai na and
            copyOf me exclusive size diya jata hai.
*/


class Solution{

    public int[][] merge(int[][] intervals) {
        // step 1. sort the invervals based on 1 number of every interval
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));

        int res[][] = new int[intervals.length][2];

        int start = intervals[0][0];
        int end = intervals[0][1];

        int idx = 0;

        
        for(int i = 1; i < intervals.length; i++){
            // check if prev interval end is greater than current interval start, that means it's overlap
            // in this case update the end with max end between prev and current interval
            // since max end will have both intervals covered.
            // start we will take always previous one, as we have already sorted it so prev start will always be lesser than next one.
            if(end >= intervals[i][0]){
                end = Math.max(end, intervals[i][1]);
            }else{
                res[idx++] = new int[]{start, end};
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }

        // since we started from 1 in above array, the last invetervals got skipped.
        // so add them here
        res[idx] = new int[]{start, end};


        // we have to return exactly size which have valid intervals no null or empty rows should be returned.
        return Arrays.copyOf(res, idx+1);

        // another way of returning exactly 2d array without any null or empty values, take List<int[]> res = new ArrayList.
        // add it like llist.add(new int[]{start,end});

        // then do 
        /*
            int newRes[][] = new int[list.size()][2];

            for(int i = 0; i < list.size(); i++){
                newRes[i] = list.get(i);
            }

            return newRes;
        */
 
    }
}