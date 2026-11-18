
class Solution {
    public int minChanges(String s) {
        int count = 0;
        // Check characters in pairs (index i and i+1)
        for (int i = 0; i < s.length(); i += 2) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                count++;
            }
        }
        return count;
    }
}