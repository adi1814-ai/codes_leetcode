class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;

        // Test every valid window length k from l to r
        for (int k = l; k <= r; k++) {
            int windowSum = 0;

            // Calculate the sum of the first window of size k
            for (int i = 0; i < k; i++) {
                windowSum += nums.get(i);
            }
            if (windowSum > 0) {
                minSum = Math.min(minSum, windowSum);
            }

            // Slide the window across the rest of the array
            for (int i = k; i < n; i++) {
                windowSum += nums.get(i) - nums.get(i - k);
                if (windowSum > 0) {
                    minSum = Math.min(minSum, windowSum);
                }
            }
        }

        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}