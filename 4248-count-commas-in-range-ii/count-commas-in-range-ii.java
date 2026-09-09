class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long threshold = 1000; // First threshold where commas start appearing
        
        // Loop through powers of 1000 (10^3, 10^6, 10^9, 10^12, 10^15)
        while (threshold <= n) {
            // Add the count of numbers from 1 to n that are >= threshold
            totalCommas += (n - threshold + 1);
            
            // Prevent overflow if threshold * 1000 exceeds Long.MAX_VALUE
            if (threshold > Long.MAX_VALUE / 1000) {
                break;
            }
            threshold *= 1000;
        }
        
        return totalCommas;
    }
}
    