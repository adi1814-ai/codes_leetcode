class Solution {
    public int search(int[] nums, int target) {
        //there is a golden rule with rotated sorted arrays: If you cut the array in half at any point, at least ONE of the halves will always be perfectly sorted.

        //goal is simply to figure out which half is sorted, and then check if your target belongs in that sorted half.
                int left =0;
                int right = nums.length-1;
                while (left <= right) {
                    int mid = left + (right - left) / 2;

                    if(nums[mid] == target) {
                        return mid;
                    }

                    // which half is sorted , Example: [4, 5, 6, 7]... 4 is smaller than 7, so this side is perfectly normal.
                    if(nums[left] <= nums[mid]) {
                        //check whether target is there in left portion
                        if(target >= nums[left] && target < nums[mid]) {// for example:- target - 0 fits between the range 4(nums[left] and 7(nums[mid])) {
                            right = mid -1; // pulling right pointer in
                        } else { left = mid +1;}
                    } else {
                        // right half sorted
                        if(target <= nums[right] && target > nums[mid]) {
                            left = mid +1;
                        } else { right = mid -1;}
                    }
                }
               return -1;
    }
}