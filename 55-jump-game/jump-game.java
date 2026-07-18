class Solution {
    public boolean canJump(int[] nums) {
        int maxReach =0;
        for(int i =0; i<nums.length;i++) {
            // if our current position is further than our maxReach
            if(i > maxReach) {
                return false;
            }
            // update maxReach to be furthest we can jump
            maxReach = Math.max(maxReach, i + nums[i]); // greedy step
             //if we can already reach our last index 
             if(maxReach >= nums.length - 1) {
             return true;
             }
        }
        return true;
    }
}