class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // Step 1: Create prefMax array
        int[] prefMax = new int[n];
        prefMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefMax[i] = Math.max(prefMax[i - 1], nums[i]);
        }
        
        // Step 2: Create suffMin array
        int[] suffMin = new int[n];
        suffMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffMin[i] = Math.min(suffMin[i + 1], nums[i]);
        }
        
        // Step 3: Check each index to find the first stable index
        for (int i = 0; i < n; i++) {
            int score = prefMax[i] - suffMin[i];
            if (score <= k) {
                return i; // Found the smallest stable index
            }
        }
        
        // If no index satisfies the condition
        return -1;
    }
}

