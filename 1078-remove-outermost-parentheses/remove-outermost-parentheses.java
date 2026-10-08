class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If count > 0, 'c' is inside a primitive block (not the outer opening '(')
                if (count > 0) {
                    sb.append(c);
                }
                count++;
            } else {
                // Decrement count first for closing brackets
                count--;
                // If count > 0, 'c' is inside a primitive block (not the outer closing ')')
                if (count > 0) {
                    sb.append(c);
                }
            }
        }
        
        return sb.toString();
    }
}