class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        // Check Type 0: Rotations of the original array
        // We look for index i where nums[i] == 0 and check if the array is cyclically increasing.
        int firstZeroIndex = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                firstZeroIndex = i;
                break;
            }
        }

        boolean isCyclicInc = true;
        for (int i = 0; i < n; i++) {
            if (nums[(firstZeroIndex + i) % n] != i) {
                isCyclicInc = false;
                break;
            }
        }

        if (isCyclicInc) {
            // Option A: Rotate left 'firstZeroIndex' times
            ans = Math.min(ans, firstZeroIndex);
            // Option B: Reverse -> Rotate left -> Reverse
            ans = Math.min(ans, 1 + ((n - firstZeroIndex) % n) + 1);
        }

        // Check Type 1: Rotations of the reversed array
        // Reverse the array first to check against decreasing/reversed cyclic patterns
        int[] rev = new int[n];
        for (int i = 0; i < n; i++) {
            rev[i] = nums[n - 1 - i];
        }

        int revZeroIndex = -1;
        for (int i = 0; i < n; i++) {
            if (rev[i] == 0) {
                revZeroIndex = i;
                break;
            }
        }

        boolean isRevCyclicInc = true;
        for (int i = 0; i < n; i++) {
            if (rev[(revZeroIndex + i) % n] != i) {
                isRevCyclicInc = false;
                break;
            }
        }

        if (isRevCyclicInc) {
            // Option C: Reverse first, then rotate left
            ans = Math.min(ans, 1 + revZeroIndex);
            // Option D: Rotate left first, then reverse
            ans = Math.min(ans, ((n - revZeroIndex) % n) + 1);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}