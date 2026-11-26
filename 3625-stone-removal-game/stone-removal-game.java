class Solution {
    public boolean canAliceWin(int n) {
        int remove = 10;
        boolean aliceTurn = true;

        while (n >= remove) {
            n -= remove;
            remove--;
            aliceTurn = !aliceTurn;
        }

        // The player whose turn it is now cannot make a move,
        // so the other player wins.
        return !aliceTurn;
    }
}