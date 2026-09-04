class Solution {
    public int firstStableIndex(int[] nums, int k) {
        // Store the length of the input array to avoid multiple .length calls and streamline boundary loops
        int n = nums.length;
        
        // Allocate a suffix minimum array of size n to store the minimum value from index i to n-1 for each position i
        int[] minRight = new int[n];
        
        // Initialize the last element of minRight to the last element of nums, since the suffix from the last index contains only itself
        minRight[n - 1] = nums[n - 1];
        
        // Iterate backwards from the second-to-last index (n-2) down to 0 to precompute suffix minimums in O(n) time
        for (int i = n - 2; i >= 0; i--) {
            // Compare the current element with the minimum of the already computed suffix on its right (i + 1)
            minRight[i] = Math.min(nums[i], minRight[i + 1]);
        }
        
        // Initialize maxLeft to 0 to keep track of the running maximum value from index 0 up to the current index i
        int maxLeft = 0;
        
        // Iterate forward from index 0 to n-1 to evaluate the stability condition at each position efficiently
        for (int i = 0; i < n; i++) {
            // Update the running prefix maximum by comparing the previous maxLeft with the current element nums[i]
            maxLeft = Math.max(maxLeft, nums[i]);
            
            // Check if the instability score (max_left - min_right[i]) satisfies the stable condition (<= k)
            if (maxLeft - minRight[i] <= k) {
                // Return index i immediately because we iterate left-to-right, guaranteeing the first found index is the smallest
                return i; 
            }
        }
        
        // Return -1 if the loop finishes without finding any index that meets the stability condition
        return -1;
    }
}