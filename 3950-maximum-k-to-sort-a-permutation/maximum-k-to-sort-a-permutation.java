class Solution {
    public int sortPermutation(int[] nums) {
        int k = -1;
        
        for (int i = 0; i < nums.length; i++) {
            // If the element is not in its correct position
            if (nums[i] != i) {
                if (k == -1) {
                    k = nums[i];
                } else {
                    k &= nums[i];
                }
            }
        }
        
        // If k is still -1, the array is already sorted
        return (k == -1) ? 0 : k;
    }
}
    