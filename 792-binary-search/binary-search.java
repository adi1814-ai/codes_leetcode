class Solution {
    public int search(int[] nums, int target) {
        int ub=nums.length-1; // upper bound
        int lb=0;   // lower bound
       
        while(lb <= ub) {

            int mid = lb + (ub - lb) / 2;
            if(nums[mid] == target) {
               return mid;
            } else if(nums[mid] < target) { // if the target is in the right half
                lb = mid + 1;
            } else if(nums[mid] > target) { // if the target is in left half 
                ub = mid -1;
            }
        }

        return -1; // if target not found 
    }
}