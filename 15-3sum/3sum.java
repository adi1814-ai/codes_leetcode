class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // WHY: Sorting places elements in order so we can use directional two-pointers (left/right)
        // and easily detect duplicate values by comparing adjacent elements.
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++) {
            // WHY: If the smallest number in the current search space is > 0, three positive numbers
            // can never sum up to 0 in a sorted array, so we can stop early.
            if(nums[i] > 0) {
                break;
            }
            if( i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;

            while( left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if(sum == 0) { // found a valid triplet
                result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                left ++;
                right --;

                while(left < right &&  nums[left] == nums[left - 1]) {
                    left ++;
                }

                while(left < right && nums[right] == nums[right + 1]) {
                    right --;
                }
            }
            // WHY: The total sum is too negative, so we increment 'left' to get a larger value from the sorted array
            else if (sum < 0) { left ++;}
            // WHY: The total sum is too positive, so we decrement 'right' to get a smaller value from the sorted array
            else { right --;}
        }
    }

return result;
} 
}