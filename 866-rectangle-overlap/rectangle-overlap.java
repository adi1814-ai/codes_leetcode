class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // Check if one rectangle is to the left, right, above, or below the other
        boolean isLeft = rec1[2] <= rec2[0];
        boolean isRight = rec1[0] >= rec2[2];
        boolean isBottom = rec1[3] <= rec2[1];
        boolean isTop = rec1[1] >= rec2[3];
        
        // If any of these are true, they do not overlap
        return !(isLeft || isRight || isBottom || isTop);
    }
}