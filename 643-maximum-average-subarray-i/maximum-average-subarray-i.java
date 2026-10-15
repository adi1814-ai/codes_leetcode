class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double currentSum = 0;

        for(int i =0; i< k; i++) {
            currentSum += nums[i];
        }
        double maxSum = currentSum;

        // Slide the window from index k to the end of the array
        for (int i = k; i < nums.length; i++) {
            currentSum = currentSum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, currentSum);
        }
        
        // Return the maximum average
        return maxSum / k;
    }
}