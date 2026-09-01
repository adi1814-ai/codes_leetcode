import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startX = -1, startY = -1;
        List<int[]> litters = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    startX = i;
                    startY = j;
                } else if (ch == 'L') {
                    litters.add(new int[]{i, j});
                }
            }
        }
        
        int totalLitters = litters.size();
        if (totalLitters == 0) return 0;
        
        int targetMask = (1 << totalLitters) - 1;
        
        int[][] litterIndex = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(litterIndex[i], -1);
        }
        for (int i = 0; i < totalLitters; i++) {
            litterIndex[litters.get(i)[0]][litters.get(i)[1]] = i;
        }
        
        // Queue stores: {x, y, mask, current_energy, steps}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY, 0, energy, 0});
        
        // bestEnergy[x][y][mask] tracks max energy achieved for this state
        int[][][] bestEnergy = new int[m][n][1 << totalLitters];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }
        bestEnergy[startX][startY][0] = energy;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int mask = curr[2];
            int currEnergy = curr[3];
            int steps = curr[4];
            
            if (mask == targetMask) {
                return steps;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dr[i];
                int ny = y + dc[i];
                
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                char nextCell = classroom[nx].charAt(ny);
                if (nextCell == 'X') continue;
                
                int nextEnergy = currEnergy - 1;
                
                // If energy drops to 0 or below, we can only survive if we land on 'R'
                if (nextEnergy < 0) continue;
                
                int nextMask = mask;
                if (nextCell == 'L') {
                    int lIndex = litterIndex[nx][ny];
                    nextMask |= (1 << lIndex);
                }
                
                if (nextCell == 'R') {
                    nextEnergy = energy;
                } else if (nextEnergy == 0 && nextCell != 'R') {
                    // If energy is exhausted and it's not a reset cell, we can't make further moves from here
                    // Unless this step itself wasn't allowed. Handled by nextEnergy < 0 or future checks.
                }
                
                // Pruning suboptimal states
                if (bestEnergy[nx][ny][nextMask] >= nextEnergy) {
                    continue;
                }
                
                bestEnergy[nx][ny][nextMask] = nextEnergy;
                queue.offer(new int[]{nx, ny, nextMask, nextEnergy, steps + 1});
            }
        }
        
        return -1;
    }
}