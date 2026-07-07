class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        // Search Range: The absolute minimum days we could wait is 1. The maximum days we would ever wait is the maximum value in the bloomDay array (the day the very last flower blooms).

        //Midpoint: We pick a day exactly in the middle of our range and test it.

        //range adjustment: on the day chosen "mid"
        //"Can we make $m$ bouquets on this day?"

        //If yes, this day works! But we want the minimum day, so we record this as a potential answer and discard the right half of our search range to look for an even earlier day.

        //If no, this day doesn't work. We need to wait longer, so we discard the left half and search the higher days.
        int n = bloomDay.length;
        if((long) m * k > n) {
            return -1;
        }

        // find the maximum day 
        int low = 1, high =0;
        for(int day : bloomDay) {
            high = Math.max(high, day);
        }
        int ans =-1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(BouquetMaker(bloomDay, mid , m , k)) {
                ans = mid; // This days works , save it 
                high = mid -1; // Try to find an earlier day
                //If the day works, it might be the answer! We save it. But because we want the minimum possible day, we throw away the higher half of the search space and keep looking in the lower half (high = mid - 1).
            } else { //If the day does not work, we know we didn't wait long enough. We throw away the lower half and look only at the higher days (low = mid + 1).
                low = mid + 1; // Dosent work, wait more days
            }
        }

        return ans;
    }
    private boolean BouquetMaker(int [] bloomDay , int currentDay , int m , int k) {
        //We need to keep track of how many complete ("bouquets") we have made so far, and how many valid ("adjacentFlowers") we are currently stringing together to build the next bouquet.
        int bouquets =0;
        int adjacentFlowers =0;

        for (int i = 0; i < bloomDay.length; i++) {
            // To verify if the flower has actually blossomed by our test day. If it has, it counts toward our current bouquet.
            if (bloomDay[i] <= currentDay) {
                adjacentFlowers++;
                // If we collected enough adjacent flowers for a bouquet
                if (adjacentFlowers == k) { //Once we have enough flowers for a single bouquet, we bundle them up (bouquets++). We must reset the counter because a flower can only be used once; we have to start fresh for the next bouquet.
                    bouquets++;
                    adjacentFlowers = 0; // Reset to start a new bouquet
                }
            } else {
                // This enforces the strict "adjacent" rule. If you encounter a flower that hasn't bloomed, the chain of consecutive flowers is broken, and you must start counting from zero again after passing it.
                adjacentFlowers = 0;
            }
        }
        
        // Return true if we made enough bouquets, false otherwise
        return bouquets >= m; // m is the target
    }
}