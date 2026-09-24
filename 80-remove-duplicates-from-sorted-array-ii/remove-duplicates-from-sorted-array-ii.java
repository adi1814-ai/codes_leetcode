class Solution {
    public int removeDuplicates(int[] nums) {
        if( nums.length <= 2) {
            return nums.length;
        }
        int insert = 2; // No matter what the first two numbers in a sorted array are—whether they are different like [1, 2] or identical like [1, 1]—they will never break the rule of having at most two duplicates

        for (int scan = 2; scan < nums.length; scan++) {
            // Compare the current element with the element 2 positions back in our processed array
            if (nums[scan] != nums[insert - 2]) { // nums[insert - 2] two position back , according to the question
                nums[insert] = nums[scan];
                insert++;
            }
        }

        return insert;
    }
}