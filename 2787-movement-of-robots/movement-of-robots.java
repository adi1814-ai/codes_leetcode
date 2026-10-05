import java.util.Arrays;

class Solution {
    public int sumDistance(int[] nums, String s, int d) {
        int n = nums.length;
        long[] positions = new long[n];
        
        // 1. Calculate final position of each robot ignoring collisions
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'R') {
                positions[i] = (long) nums[i] + d;
            } else {
                positions[i] = (long) nums[i] - d;
            }
        }
        
        // 2. Sort final positions to easily compute pairwise distances
        Arrays.sort(positions);
        
        // 3. Compute the sum of pairwise distances modulo 1,000,000,007
        long MOD = 1_000_000_007;
        long totalDistance = 0;
        long prefixSum = 0;
        
        for (int i = 0; i < n; i++) {
            // Contribution of positions[i] to all previous elements positions[j] (j < i)
            long currentContribution = ((long) i * positions[i] - prefixSum) % MOD;
            totalDistance = (totalDistance + currentContribution) % MOD;
            
            prefixSum = (prefixSum + positions[i]) % MOD;
        }
        
        return (int) ((totalDistance + MOD) % MOD);
    }
}