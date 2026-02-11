class Solution{

    int evenSum(int n){//3
        if(n == 0){
            return 0;
        }

        return 2*n + evenSum(n-1); 
    }
}