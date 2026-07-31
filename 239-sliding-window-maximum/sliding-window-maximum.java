class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int [] result = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();
        int resultIdx = 0;

        for(int i =0;i < n;i++) {
            // Remove indices out of current window
            if(!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }
            //Remove indices smaller than current from the back
            while(!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }
            dq.offerLast(i);
        
             if (i >= k - 1) {
                result[resultIdx++] = nums[dq.peekFirst()];
            }
        }
           return result;

    }
}

