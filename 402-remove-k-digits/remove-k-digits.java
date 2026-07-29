class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for(char current : num.toCharArray()) {

            while(!stack.isEmpty() &&  k > 0 && current < stack.peek()) {
                   stack.pop();
                   k--;
            }
              stack.push(current);
        }
// if numbers are already in non-decreasing order
            while(!stack.isEmpty() && k > 0) {
                stack.pop();
                k--;
            }

            StringBuilder sb = new StringBuilder();
            while(!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            sb.reverse();
// Skip leading zeroes
            while(sb.length() > 0 && sb.charAt(0) == '0') {
                sb.deleteCharAt(0);
            }

            return sb.length()==0 ? "0" : sb.toString();
        
    }
}