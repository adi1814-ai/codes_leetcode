class Solution {
    public int maximalRectangle(char[][] matrix) {
        
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        int [] heights = new int[cols];
        int maxArea = 0;

     //Dynamically updates vertical bar heights row by row, representing consecutive '1's resting on row i.
        for(int i=0;i < matrix.length; i++ ) {
            for(int j =0;j <cols; j++) {
                if(matrix[i][j] == '1') {
                    heights[j] += 1;
                } else  {
                    heights[j] = 0;
                }
            }
            //Ensures we evaluate every possible row bottom and capture the overall largest rectangle in the entire grid.
            maxArea = Math.max(maxArea , largestRectangleArea(heights));
        }
        return maxArea;
    }
    private int largestRectangleArea(int [] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea =0;
        int n = heights.length;

        for(int i =0;i<=n;i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            while(!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()]; //While the stack isn't empty and the current bar is shorter than the height of the index at stack.peek(), it pops the top index from the stack and stores its corresponding height.   A shorter bar blocks any taller bar in the stack from extending further to the right. The popped bar has reached its rightmost boundary, so its max rectangle must be calculated now.

        //Determines the exact range of columns over which the popped height can stretch.
                int width;
                if(stack.isEmpty()) {
                    width = i;
                } else  {
                    width = i - stack.peek() - 1;
                }

                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;

    }
}