class Solution {
    public int distinctSubseqII(String s) {
        long[] dp = new long[26];
        long mod = 1_000_000_007;
        
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            
            // Calculate total subsequences we can form up to this point
            long total = 1;
            for (int i = 0; i < 26; i++) {
                total = (total + dp[i]) % mod;
            }
            
            // Update the count for the current character
            dp[idx] = total;
        }
        
        // Sum all ending counts to get the final answer
        long ans = 0;
        for (int i = 0; i < 26; i++) {
            ans = (ans + dp[i]) % mod;
        }
        
        return (int) ans;
    }
}
