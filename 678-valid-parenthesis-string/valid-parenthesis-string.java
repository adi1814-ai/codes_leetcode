class Solution {
    public boolean checkValidString(String s) {
        // 'low' is the minimum number of open parentheses we could have.
        // 'high' is the maximum number of open parentheses we could have.
        int low =0;
        int high =0;
        for(char c :s.toCharArray()) {
            if(c =='(') {
                low++;
                high++;
            } else if (c == ')') {
                low --;
                high--;
            } else { //c  == '*'
                low--;
                high++;
            }
            // the greedy check
            // If high < 0, it means we have more ')' than we could possibly match,
            // even if all '*' were '('. The string is invalid.
            if(high < 0) return false;

            // If low < 0, we treat the current '*' as empty or '(',
            // so we reset low to 0. We can't have fewer than 0 open brackets.
            if(low < 0)  low = 0;
        }
        return low ==0;

        
    }
}