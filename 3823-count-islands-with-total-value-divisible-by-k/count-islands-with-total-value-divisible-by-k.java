import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If we find an unvisited land cell (non-zero)
                if (grid[i][j] > 0 && !visited[i][j]) {
                    long totalSum = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    
                    visited[i][j] = true;
                    queue.offer(new int[]{i, j});
                    
                    while (!queue.isEmpty()) {
                        int[] cell = queue.poll();
                        int r = cell[0];
                        int c = cell[1];
                        
                        totalSum += grid[r][c];
                        
                        for (int d = 0; d < 4; d++) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];
                            
                            if (nr >= 0 && nr < m && nc >= 0 && nc < n 
                                    && grid[nr][nc] > 0 && !visited[nr][nc]) {
                                visited[nr][nc] = true;
                                queue.offer(new int[]{nr, nc});
                            }
                        }
                    }
                    
                    // Check if the total value of the island is divisible by k
                    if (totalSum % k == 0) {
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
}