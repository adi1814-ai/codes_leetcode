class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int result [] = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
    for(int i = 0 ; i< 2 *n;i++) { // loop twice to simulate  a circular traversal
    int currentNum = nums[i % n];
    
    // Pop smaller elements and assign their next greater element
            while (!stack.isEmpty() && nums[stack.peek()] < currentNum) {
                result[stack.pop()] = currentNum;
            }
            // Push index only during the first pass (i < n) 
            // so we don't process duplicate index entries endlessly
            if (i < n) {
                stack.push(i);
            }
        }
       return result;
    }
}