class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // handles k larger than array length
        reverse(nums, 0 , n - 1); // reverses the whole array
        reverse(nums, 0, k - 1); // reverses the first k- elements
        reverse(nums, k, n - 1); // reverses the rest of the array
    }

    public void reverse(int nums[] , int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start ++;
            end --;
        }
    }
}