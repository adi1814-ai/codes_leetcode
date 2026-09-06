class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // dp[j] will store the number of ways to form t[0...j-1]
        // Using a 1D array optimization (since row i only depends on row i-1)
        double[] dp = new double[n + 1];
        
        // Base case: empty string t can always be formed 1 way
        dp[0] = 1;
        
        for (int i = 1; i <= m; i++) {
            // Traverse backwards to avoid overwriting values needed from the previous row
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        return (int) dp[n];
    }
}