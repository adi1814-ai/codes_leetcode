class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;

        // check if the first row has any zeros
        for(int j = 0; j < n; j++) {
            if(matrix[0][j] == 0) {
                firstRowHasZero = true;
                break;
            }
        }

        // check if the first row has any zeroes
        for(int j = 0; j< n; j++) {
            if(matrix[0][j] == 0) {
                firstRowHasZero = true;
                break;
            }
        }
        // check if the first coloumn has any zeroes
        for(int i = 0; i < m; i++) {
            if(matrix[i][0] == 0) {
                firstColHasZero = true;
                break;
            }
        }
        // Step 3: Use first row and column as markers for the rest of the matrix
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Step 4: Zero out inner cells based on markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Step 5: Zero out first row if needed
        if (firstRowHasZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // Step 6: Zero out first column if needed
        if (firstColHasZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}