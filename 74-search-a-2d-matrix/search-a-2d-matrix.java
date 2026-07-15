class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n =matrix[0].length;
        int low =0;
        int high =(m * n) -1;

        while(low <= high) {
            int mid = low + (high - low) / 2;
             //maping 1D index to 2D matrix coordinates
             int row = mid / n;
             int col = mid %n;

             int midValue = matrix[row][col];
             if(midValue == target) {
                return true;
             } else if(midValue < target) {
                low = mid +1; // search in the left half
             } else {
                high = mid -1;
             }
    }
    return false; // target not found
    }
}
