class Solution {
    public int largestInteger(int[] nums, int k) {
       //Data Structures Used

       //HashSet<Integer> (Local Scope): Prevents double-counting if a number appears multiple times within the same subarray. Inserting an element into a Set automatically discards duplicates.
       
       //HashMap<Integer, Integer> (Global Scope): Maps each number x to the total number of distinct size-$k$ subarrays in which x appears.
       
       
        Map<Integer, Integer> subarrayCount = new HashMap<>();
        for(int i =0; i<=nums.length - k; i++) {
            Set<Integer> window =new HashSet<>();

            for(int j = i; j < i+ k; j++) {
                window.add(nums[j]);
            }

            for(int num : window) {
                subarrayCount.put(num, subarrayCount.getOrDefault(num, 0) + 1);
            }
        }
        // 2. Find the maximum key with a frequency of exactly 1
        int ans = -1;
        for (Map.Entry<Integer, Integer> entry : subarrayCount.entrySet()) {
            if (entry.getValue() == 1) {
                ans = Math.max(ans, entry.getKey());
            }
        }
       return ans;
    }
}