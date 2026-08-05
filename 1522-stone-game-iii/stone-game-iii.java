class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int bestOption = stoneValue[i] - dp[i + 1];

            if (i + 1 < n) {
                bestOption = Math.max(bestOption, stoneValue[i] + stoneValue[i + 1] - dp[i + 2]);
            }

            if (i + 2 < n) {
                bestOption = Math.max(bestOption, stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - dp[i + 3]);
            }

            dp[i] = bestOption;
        }

        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}
