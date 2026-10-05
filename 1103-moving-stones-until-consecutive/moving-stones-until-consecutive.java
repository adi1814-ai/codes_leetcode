import java.util.Arrays;

class Solution {
    public int[] numMovesStones(int a, int b, int c) {
        int[] stones = new int[]{a, b, c};
        Arrays.sort(stones);
        
        int x = stones[0];
        int y = stones[1];
        int z = stones[2];
        
        // Minimum Moves
        int minMoves;
        if (z - x == 2) {
            // Already consecutive (e.g., 1, 2, 3)
            minMoves = 0;
        } else if (y - x <= 2 || z - y <= 2) {
            // If either gap is 1 or 2, we can make them consecutive in 1 move.
            // e.g., (1, 2, 5) -> move 5 to 3 => (1, 2, 3)
            // e.g., (1, 3, 5) -> move 5 to 2 => (1, 2, 3)
            minMoves = 1;
        } else {
            // Otherwise, move one outer stone next to middle, then the other next to it.
            minMoves = 2;
        }
        
        // Maximum Moves
        // We move outer stones 1 step at a time into the empty slots.
        int maxMoves = (y - x - 1) + (z - y - 1);
        
        return new int[]{minMoves, maxMoves};
    }
}