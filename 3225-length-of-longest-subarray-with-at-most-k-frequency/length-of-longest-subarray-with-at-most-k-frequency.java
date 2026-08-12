class Solution {
    public int maxSubarrayLength(int[] nums, int k) {

        Map<Integer, Integer> countMap = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);

            // If frequency exceeds k, shrink the window from the left
            while (countMap.get(num) > k) {
                int leftNum = nums[left];
                countMap.put(leftNum, countMap.get(leftNum) - 1);
                left++;
            }

            // Update the maximum length found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}