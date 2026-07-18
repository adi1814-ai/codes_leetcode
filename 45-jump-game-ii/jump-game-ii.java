class Solution {
    public int jump(int[] nums) {
        int jumps =0;
        int currEnd =0;
        int maxReach =0;

        int n = nums.length;
        if(n <= 1) return 0;

        for(int i=0; i<n-1; i++) {
            maxReach = Math.max(maxReach, i+ nums[i]); //At every single index i, we check: "If I jump from here, how far can I go?"
           //The math i + nums[i] represents your current position plus the maximum jump length allowed from that spot. We use Math.max to continuously update maxReach so it always holds the absolute furthest index we have discovered so far.

            if(i== currEnd) {
                jumps ++;
                currEnd = maxReach;

                if(currEnd >= n-1) break;
            }
        }
        return jumps;
    }
}