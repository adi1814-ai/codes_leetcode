class Solution {
    public long subArrayRanges(int[] nums) {
        return getSum(nums, true) - getSum(nums, false);
    }

    private long getSum(int [] nums, boolean isMax) {
        int n = nums.length;
        long sum =0;
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i =0; i <=n;i++) {
            while(!stack.isEmpty() &&(i == n || (isMax ? nums[stack.peek()] < nums[i] : nums[stack.peek()] > nums[i]))) {
                int mid = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                sum += (long) nums[mid] * (mid - left) * (i - mid);
            }
            stack.push(i);
        }
        return sum;
    }
}