class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        
        // dp[i] will be true if the current player can win with i stones
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k * k <= i; k++) {
                // If transitioning to a state where the next player loses (dp[i - k*k] == false),
                // then the current player can win.
                if (!dp[i - k * k]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n];
    }
}