class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        
        // RULE :
       //No two flowers can be planted in adjacent plots. That means we can never have two 1's right next to each other.
       
        for (int i = 0; i < flowerbed.length; i++) {
            // Is current plot empty?
            if (flowerbed[i] == 0) {
                // chceking left and right neighbors empty (or out of bounds)?
                boolean emptyLeft = (i == 0 || flowerbed[i - 1] == 0);
                boolean emptyRight = (i == flowerbed.length - 1 || flowerbed[i + 1] == 0);
                
                // Make the greedy choice
                if (emptyLeft && emptyRight) {
                    flowerbed[i] = 1; // Plant flower here
                    n--;              // One less flower needed
                    
                    if (n <= 0) {   // Early termination optimization
                         return true;
                        } 
                }
            }
        }
        return n <= 0;
    }
}
