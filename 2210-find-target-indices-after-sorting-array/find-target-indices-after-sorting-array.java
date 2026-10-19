class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int count = 0;
        int lessThanTarget = 0;

        for(int num : nums) {
            if(num < target) {
                lessThanTarget++;
            } else if(num == target) {
                count++;
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i =0; i< count; i++) {
            result.add(lessThanTarget + i);
        }
        return result;
    }
}