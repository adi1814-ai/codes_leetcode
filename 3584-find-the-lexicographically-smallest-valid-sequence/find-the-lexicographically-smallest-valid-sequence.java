class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int [] dp = new int[n + 1]; // Suffix array --> dp[i] will tell us "How many characters of word2 can I perfectly match using the remaining characters from index i to the end of word1?"
        int j = m -1; // pointer which is set to start at the very last character of word 2

        for(int i = n - 1; i >= 0; i--) {
            if( j>=0 && word1.charAt(i) == word2.charAt(j)) {
                dp[i] = dp[i + 1] + 1;
                j -- ;
            } else {
                dp[i] = dp[i + 1];
            }
        }
        // greedy search
        int [] ans = new int[m]; //ans: The array where we will store the winning sequence of indices
        j = 0;
        boolean usedChange = false; 
        for (int i = 0; i < n && j < m; i++) {  
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }
            else if (!usedChange && dp[i + 1] >= m - j - 1) {
                ans[j] = i;
                j++;
                usedChange = true;
            }
        if (j == m) {
            return ans;
        }
            
    }
    return new int[0];
}
}