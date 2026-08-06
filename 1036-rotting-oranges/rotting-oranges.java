class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        Queue<int[]> queue = new LinkedList<>();
          int rows = grid.length;
          int cols = grid[0].length;
          int fresh =0;

          for(int r =0; r<rows; r++) {
             for(int c =0; c< cols; c++) {
                if(grid[r][c] == 2) {
                    queue.offer(new int []{r, c}); // add rotten oranges's indexes into the queue
                } else if (grid[r][c] == 1) {
                    fresh++; // count fresh oranges
                }
             }
          }
     // edge case 
     if(fresh == 0) return 0;

     int minutes =0;
     // directions for up , right , down , left
     int [][] directions ={{-1, 0}, {1, 0}, {0, -1},{0, 1}};
     //Multisource BFS
     while(!queue.isEmpty() && fresh > 0) {
        int size = queue.size();
        //process all oranges thta are rotting at the current time 
        for(int i = 0; i < size; i++) {
            int [] current = queue.poll();
            int row = current[0];
            int col = current[1];

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == 1) {
                    grid[newRow][newCol] = 2; // Make it rotten
                    fresh--;           // Decrement fresh count
                    queue.offer(new int[]{newRow, newCol}); // Push to queue to spread rot in the next minute
                }
            }
        }
        //After processing the current level, increment the time
        minutes ++;
     }
     
        // If there are still fresh oranges left, they are unreachable
        return fresh == 0 ? minutes : -1;
            
    }
}