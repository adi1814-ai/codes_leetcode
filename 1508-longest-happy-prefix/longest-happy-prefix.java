class Solution {
    public String longestPrefix(String s) {
        int n = s.length();
        int [] lps = new int[n];
        int len =0;
        int i=1;

        //constructing the pi table

        while(i < n) {
            if(s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if(len != 0) {
                // Backtrack to the previous longest prefix suffix length
                    len = lps[len-1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }// The last element contains the length of the longest happy prefix

        int longestLength = lps[n - 1];
        return s.substring(0, longestLength);
    }
}