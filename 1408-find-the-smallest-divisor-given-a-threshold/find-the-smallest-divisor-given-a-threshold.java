class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int low =1;
        int high = 0;
        
        for(int num : nums) {
            high = Math.max(high, num);
        }
        
        int ans = high;

        while (low <= high) {
            int mid = low + (high -low) / 2; // find the middle divisor
            if(getSum(nums , mid) <= threshold) {
                ans = mid;
                high = mid - 1; // TRy searching even smaller one by searching the left half
            } else {
                low = mid +1; // mid is too small, look on the right
            }
        }
        return ans;
    }
    private int getSum(int [] nums , int divisor) {
        int sum =0;
        for(int num : nums) {
            sum += (num + divisor -1) / divisor;
        }
        return sum;
    }
}