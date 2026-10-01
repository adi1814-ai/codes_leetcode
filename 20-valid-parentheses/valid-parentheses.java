class Solution {
    public boolean isValid(String s) {
        // using a stack data structure
        Stack<Character> stack = new Stack<>();
        for(char c: s.toCharArray()) {
            if(c== '(') {
                stack.push(')');
            } 
            else if(c=='[') {
                stack.push(']');
            }
            else if(c=='{') {
                stack.push('}');
            }
            else if(stack.isEmpty() || stack.pop()!=c ) // if the stack is empty or the popped element dosen't matches with the current closing character which we pushed , then return false in that case.
            return false;
        }
        return stack.isEmpty();//checks the stack, sees it still contains any of the closed brackets [']', ')'],  will evaluates to false, and correctly tells the string as invalid.
}
}