class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            long prod = 1L;
            int gcdVal = 0;
            long lcmVal = 1L;

            for (int j = i; j < n; j++) {
                int curr = nums[j];
                prod *= curr;
                gcdVal = (j == i) ? curr : gcd(gcdVal, curr);
                lcmVal = (j == i) ? curr : lcm(lcmVal, curr);

                if (prod == lcmVal * (long) gcdVal) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd((int) a, (int) b)) * b;
    }
}