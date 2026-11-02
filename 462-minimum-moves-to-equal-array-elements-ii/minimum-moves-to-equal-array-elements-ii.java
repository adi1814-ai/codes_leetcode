class Solution {
    public int minMoves2(int[] nums) {
        // Step 1: Sort the array
        Arrays.sort(nums);
        
        // Step 2: Find the median element
        int median = nums[nums.length / 2];
        
        // Step 3: Calculate total moves required to reach the median
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        
        return moves;
    }
}