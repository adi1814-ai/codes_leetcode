class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        //Property: Integers in each row are sorted left to right, and integers in each column are sorted top to bottom. However, the end of one row is not guaranteed to be smaller than the start of the next row, like it was in--- Search in 2D Matrix (I)---
        //Optimal Solution: Treating it as a flat 1D array won't work here. Instead, the optimal approach runs in O(m + n) time using a "step-wise" or pointer reduction method.
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row =0;
        int col = matrix[0].length - 1;
        while(row < matrix.length && col >=0) {
            if(matrix[row][col] == target) {
                return true;
            } else if(matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }
}