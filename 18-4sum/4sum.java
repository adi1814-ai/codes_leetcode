class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();
        
        // Sort the array so we can use directional two pointers
        Arrays.sort(nums);

        // First loop: fixes the 1st number
        for (int i = 0; i < nums.length - 3; i++) {
            // Skip duplicates for 1st number
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Second loop: fixes the 2nd number
            for (int j = i + 1; j < nums.length - 2; j++) {
                // Skip duplicates for 2nd number
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int left = j + 1;
                int right = nums.length - 1;

                // Two pointers for 3rd and 4th numbers
                while (left < right) {
                    // Cast to long to prevent integer overflow
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;

                        // Skip duplicates for 3rd number
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        // Skip duplicates for 4th number
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }
}
    