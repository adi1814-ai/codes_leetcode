class Solution {
    public boolean search(int[] nums, int target) {
        int start =0;
        int end = nums.length-1;

        while(start <= end ) {
            int mid = start +(end - start) / 2;

            if(nums[mid] == target) {
                return true;
            }
     // checking for duplicacy
            if(nums[start] == nums[mid] && nums[mid] == nums[end]) { // shrinking the search space
                start ++;
                end --;
                continue;
            }
   // which half is sorted , Example: [4, 5, 6, 7]... 4 is smaller than 7, so this side is perfectly normal.
            if(nums[start] <= nums[mid]) {
                //check whether target is there in left portion
                if(target >= nums[start] && target < nums[mid]) { // for example:- target - 0 fits between the range 4(nums[left] and 7(nums[mid]))  
                    end = mid -1;
                } else { start = mid +1;}
            } else {  // right half sorted
                if(target <=nums[end] && target > nums[mid])  {
                    start = mid +1;
                } else { end = mid -1;}
            }
        }
        return false;
    }
}