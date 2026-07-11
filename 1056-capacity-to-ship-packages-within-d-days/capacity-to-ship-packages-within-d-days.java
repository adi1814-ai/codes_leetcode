class Solution {
    public int shipWithinDays(int[] weights, int days) {
                                      // NOTES 

    // Absolute minimum capacity = the heaviest package
    // Absolute maximum capacity = if we had to ship everything on a single day. The capacity would need to be the sum of all packages.

    int low =0;
    int high =0;
    for(int wt: weights) {
        low = Math.max(low, wt); //Absolute minimum capacity stored
        high += wt; // Absolute maximum capacity stored
    }
    int ans = high;
    while(low <= high) {
        int mid = low + (high -low) / 2;
        if(isValid(weights, days, mid)) {
            ans = mid; // record the valid capacity found so far
            high = mid -1; // Try to find a smaller value
        } else {
            low = mid +1; // capacity too small, look for larger
        }
    }
   return ans;
 }
 private boolean isValid(int []weights, int days , int capacity) {
    int requiredDays = 1; // start counting the days  needed and at least one day is crucial.
    int currentLoad = 0;

    for(int w : weights) {
        if(currentLoad + w > capacity) {// checking if the currentload can sum up and can exceed the ship's capacity
        requiredDays++; //(not adding the item) if condition true --> current ship is full , sailing it 
        currentLoad = w; // new day , empty ship , the last item which was not able to be added(as iit was exceeding the capacity) is now added on new day
    } else {
        currentLoad += w; // adding the current item on the ship , if condition false
    }
 }
 return requiredDays <= days;
}
}