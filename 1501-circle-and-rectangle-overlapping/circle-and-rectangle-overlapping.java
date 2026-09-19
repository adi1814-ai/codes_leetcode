class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Find closest x within rectangle bounds
        int closestX = Math.max(x1, Math.min(xCenter, x2));
        
        // Find closest y within rectangle bounds
        int closestY = Math.max(y1, Math.min(yCenter, y2));
        
        // Calculate squared distance from circle center to closest point
        int dx = xCenter - closestX;
        int dy = yCenter - closestY;
        
        return dx * dx + dy * dy <= radius * radius;
    }
}