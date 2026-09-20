class Solution {
    public boolean rotateString(String s, String goal) {
        

        // abcde + abcde => abcdeabcde => // substrings-->  abcde, bcdea , cdeab
        if(s.length() != goal.length()) {
            return false;
        }

        return (s + s).contains(goal);
    }
}