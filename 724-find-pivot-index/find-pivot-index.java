class Solution {
    public int pivotIndex(int[] nums) {
        int totalSum = 0;
        int leftSum = 0;

        // step 1 : calculate the total sum of all elements in the array
        for(int num : nums) {
            totalSum += num;
        }
        // iterate through the array to find the pivot index
        for(int i = 0; i < nums.length; i++) {
            int rightSum = totalSum - leftSum - nums[i];

            // If left sum equals right sum, we found our pivot index
            if(leftSum == rightSum) {
                return i;
            }
         // Add current element to leftSum for the next iteration
            leftSum += nums[i];
        }

        return -1;
    }
}