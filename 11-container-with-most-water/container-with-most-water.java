class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;
        
        while (left < right) {
            // Width between the two pointers
            int width = right - left;
            
            // Container height is bounded by the shorter line
            int currentHeight = Math.min(height[left], height[right]);
            
            // Calculate area and update maxWater
            int currentArea = width * currentHeight;
            maxWater = Math.max(maxWater, currentArea);
            
            // Move the pointer pointing to the shorter line
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxWater;
    }
}