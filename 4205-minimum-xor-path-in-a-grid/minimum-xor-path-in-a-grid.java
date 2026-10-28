import java.util.HashSet;
import java.util.Set;

class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // dp[j] will store the set of all possible XOR values to reach column j in the current row
        Set<Integer>[] dp = new HashSet[n];
        for (int j = 0; j < n; j++) {
            dp[j] = new HashSet<>();
        }
        
        // Base case for top-left cell
        dp[0].add(grid[0][0]);
        
        // Fill first row
        for (int j = 1; j < n; j++) {
            for (int val : dp[j - 1]) {
                dp[j].add(val ^ grid[0][j]);
            }
        }
        
        // Fill the rest of the grid
        for (int i = 1; i < m; i++) {
            // Update first column of row i
            Set<Integer> nextRowFirst = new HashSet<>();
            for (int val : dp[0]) {
                nextRowFirst.add(val ^ grid[i][0]);
            }
            dp[0] = nextRowFirst;
            
            // Update remaining columns of row i
            for (int j = 1; j < n; j++) {
                Set<Integer> currentCellXors = new HashSet<>();
                // From left
                for (int val : dp[j]) {
                    currentCellXors.add(val ^ grid[i][j]);
                }
                // From top
                for (int val : dp[j - 1]) {
                    currentCellXors.add(val ^ grid[i][j]);
                }
                dp[j] = currentCellXors;
            }
        }
        
        // Find the minimum XOR value at the bottom-right cell (m - 1, n - 1)
        int minXor = Integer.MAX_VALUE;
        for (int val : dp[n - 1]) {
            minXor = Math.min(minXor, val);
        }
        
        return minXor;
    }
}