class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean sorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) sorted = false;
        }
        if (sorted) return 0;
        if (n == 2) return -1;
        
        char first = s.charAt(0), last = s.charAt(n - 1);
        char lo = 'z', hi = 'a';
        for (int i = 1; i < n - 1; i++) {
            lo = (char) Math.min(lo, s.charAt(i));
            hi = (char) Math.max(hi, s.charAt(i));
        }
        
        if (first <= Math.min(lo, last) || Math.max(first, hi) <= last) return 1;
        return (last < lo && hi < first) ? 3 : 2;
    }
}