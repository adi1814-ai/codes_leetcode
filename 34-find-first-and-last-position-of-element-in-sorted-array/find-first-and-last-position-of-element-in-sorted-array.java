class Solution {
    public int[] searchRange(int[] nums, int target) {
       int [] result =  new int[2];
       result[0] = findfirst(nums , target);
       result[1] = findlast(nums , target);
       return result;
    }
    private int findfirst(int [] nums , int target)  {
        int lb =0;
        int ub = nums.length-1;
        int idx =-1;
        while (lb <= ub) {
            int mid = lb +(ub - lb) / 2 ;
            if(nums[mid] >= target) {
                if(nums[mid] == target) { idx = mid;} 
                ub = mid -1;
            } else {
                lb = mid +1;
            }
        }
        return idx;
    }


    private int findlast(int [] nums , int target) {
        int lb =0;
        int ub = nums.length-1;
        int idx =-1;

        while(lb <= ub) {
            int mid = lb + ( ub - lb) / 2;
            if (nums[mid] <= target) {
                if(nums[mid] == target) {
                    idx = mid ;}
                    lb = mid +1; 
                } else {
                    ub = mid-1;
                }
            }
            return idx;
        
    }
}