class Solution {
    public boolean uniformArray(int[] nums1) {
        int mn = Integer.MAX_VALUE;
        
        // Find the minimum odd number in the array
        for (int x : nums1) {
            if (x % 2 == 1) {
                mn = Math.min(mn, x);
            }
        }
        
        // If there is any even number smaller than the minimum odd number, 
        // it's impossible to transform it.
        for (int x : nums1) {
            if (x % 2 == 0 && mn != Integer.MAX_VALUE && x < mn) {
                return false;
            }
        }
        
        return true;
    }
}
        
        
    
