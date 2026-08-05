class Solution {
    public int trap(int[] height) {

        Stack<Integer> stack = new Stack<>();
        int totalWater = 0;
        int current = 0;

        while (current < height.length) {
            // If the stack isn't empty and the current bar is TALLER than the top of the stack
            // We found a "Right Wall"!
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                // 1. Pop the top to get the Bottom of the valley
                int bottomIndex = stack.pop(); 

                // 2. Check if we have a Left Wall. If not, the water spills out.
                if (stack.isEmpty()) {
                    break;
                }

                // 3. The new top of the stack is our Left Wall
                int leftWallIndex = stack.peek(); 

                // 4. Calculate Width
                int width = current - leftWallIndex - 1; 

                // 5. Calculate Height (Bounded by the shorter wall)
                int boundedHeight = Math.min(height[leftWallIndex], height[current]) - height[bottomIndex]; 

                // 6. Calculate Area and add it to the total
                totalWater += width * boundedHeight; 
            }
            
            // Push the current index onto the stack (we are either going downhill, or we just finished processing a puddle)
            stack.push(current);
            current++;
        }

        return totalWater;
    }
}
