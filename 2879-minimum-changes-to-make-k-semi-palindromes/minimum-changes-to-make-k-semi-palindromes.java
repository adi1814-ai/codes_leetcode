class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];

        // Precompute cost for every substring s[i...j] to be a semi-palindrome
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int minChanges = len; 

                // Check all proper divisors d of len
                for (int d = 1; d < len; d++) {
                    if (len % d == 0) {
                        int currentCost = 0;
                        for (int g = 0; g < d; g++) {
                            int left = i + g;
                            int right = i + len - d + g;
                            while (left < right) {
                                if (s.charAt(left) != s.charAt(right)) {
                                    currentCost++;
                                }
                                left += d;
                                right -= d;
                            }
                        }
                        minChanges = Math.min(minChanges, currentCost);
                    }
                }
                cost[i][j] = minChanges;
            }
        }

        // DP table initialization
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = 1000000; // infinity
            }
        }
        dp[0][0] = 0;

        // Fill DP table
        for (int parts = 1; parts <= k; parts++) {
            for (int i = 0; i < n; i++) {
                // Ensure substring length is at least 2 and we don't exceed string bounds
                for (int j = i + 1; j < n; j++) {
                    if (dp[i][parts - 1] != 1000000) {
                        dp[j + 1][parts] = Math.min(dp[j + 1][parts], dp[i][parts - 1] + cost[i][j]);
                    }
                }
            }
        }

        return dp[n][k];
    }
}