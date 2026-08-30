class Solution {
    public int minimumDeletions(int[] nums) {
        int minIdx = 0;
        int maxIdx = 0;
        int n = nums.length;

        for(int i =0; i < n; i ++) {
            if(nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if(nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        int left = Math.min(minIdx, maxIdx);
        int right = Math.max(minIdx, maxIdx);

       // Scenario 1: Remove both from the FRONT (deletes up to the further index 'right')
           int option1 = right + 1;
        
        // Scenario 2: Remove both from the BACK (deletes from 'left' to the end)
           int option2 = n- left;
        
        // Scenario 3: Remove one from FRONT (up to 'left') and one from BACK (from 'right' to end)
           int option3 = (left + 1) + (n - right);
        

        
        return Math.min(option1, Math.min(option2 , option3));
    }
}