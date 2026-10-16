class Solution {
    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '*') {
                // Remove the closest non-star character to its left
                sb.setLength(sb.length() - 1);
            } else {
                // Push the character onto our stack-like StringBuilder
                sb.append(c);
            }
        }
        return sb.toString();
    }
}