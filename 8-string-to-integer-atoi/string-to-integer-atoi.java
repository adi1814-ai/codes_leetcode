class Solution {
    public int myAtoi(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int i =0;
        int n= s.length();
        int sign =1;
        long result =0;
         // discard leading whitespace
        while(i < n && s.charAt(i) == ' ') {
            i++;
        }
        if(i < n && (s.charAt(i) == '+' || s.charAt(i)== '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0'; // Convert char to actual integer
            result = result * 10 + digit;

            // Step 4: Check overflow immediately on every digit addition
            if (sign == 1 && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && -result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            i++; //Move the pointer to the next character to repeat the digit loop.
        }

        // Return the final signed integer result
        return (int) (result * sign);
    }
}