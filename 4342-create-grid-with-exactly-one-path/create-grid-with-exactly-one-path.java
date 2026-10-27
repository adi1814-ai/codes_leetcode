class Solution {
    public String[] createGrid(int m, int n) {
        // Initialize the grid with '#' (obstacles)
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = '#';
            }
        }
        
        // Mark the first row as free cells '.'
        for (int j = 0; j < n; j++) {
            grid[0][j] = '.';
        }
        
        // Mark the last column as free cells '.'
        for (int i = 0; i < m; i++) {
            grid[i][n - 1] = '.';
        }
        
        // Convert the char[][] grid to String[]
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = new String(grid[i]);
        }
        
        return result;
    }
}