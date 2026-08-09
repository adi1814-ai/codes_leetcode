class Solution {
    public int stoneGameII(int[] piles) {
       
        int n = piles.length;
        
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        int[][] memo = new int[n][n + 1];
        
        return dfs(piles, 0, 1, suffixSum, memo);
    }
    
    private int dfs(int[] piles, int i, int M, int[] suffixSum, int[][] memo) {
        if (i == piles.length) {
            return 0;
        }
        
        if (i + 2 * M >= piles.length) {
            return suffixSum[i];
        }
        
        if (memo[i][M] != 0) {
            return memo[i][M];
        }
        
        int maxStones = 0;
        
        for (int x = 1; x <= 2 * M; x++) {
            int opponentBest = dfs(piles, i + x, Math.max(M, x), suffixSum, memo);
            int myStones = suffixSum[i] - opponentBest;
            maxStones = Math.max(maxStones, myStones);
        }
        
        memo[i][M] = maxStones;
        return maxStones;
    }
}