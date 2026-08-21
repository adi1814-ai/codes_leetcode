

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        List<Long> lcms = new ArrayList<>();
        List<Integer> signs = new ArrayList<>();
        
        // Precompute LCMs for all subsets to optimize the binary search
        for (int i = 1; i < (1 << n); i++) {
            long currentLcm = 1;
            int bits = Integer.bitCount(i);
            
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    currentLcm = lcm(currentLcm, coins[j]);
                }
            }
            lcms.add(currentLcm);
            signs.add(bits % 2 == 1 ? 1 : -1);
        }
        
        // Binary search boundaries
        long minCoin = coins[0];
        for (int c : coins) {
            minCoin = Math.min(minCoin, c);
        }
        
        long left = 1;
        long right = minCoin * k; // The max possible amount if we only used the smallest coin
        long ans = right;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long count = 0;
            
            // Apply Inclusion-Exclusion Principle
            for (int i = 0; i < lcms.size(); i++) {
                count += signs.get(i) * (mid / lcms.get(i));
            }
            
            if (count >= k) {
                ans = mid;
                right = mid - 1; // Try to find a smaller valid amount
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
    
    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    
    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}
