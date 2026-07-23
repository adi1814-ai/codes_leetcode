class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();
        for(String op :operations) {

            if(op.equals("+")) {
             int top = stack.pop();
             int newScore = top + stack.peek();
             stack.push(top);
             stack.push(newScore);
            } else if(op.equals("D")) {
                // double the previous score
                stack.push(2 * stack.peek());
            } else if(op.equals("C")) {
                // remove / invalidate the previous score
                stack.pop();
            } else {
                // integer score string
                stack.push(Integer.parseInt(op));
            }
        }

        // sum up all the elements left in the stack
        int totalSum =0;
        for(int score: stack) {
            totalSum += score;
        }
        return totalSum;
    }
}