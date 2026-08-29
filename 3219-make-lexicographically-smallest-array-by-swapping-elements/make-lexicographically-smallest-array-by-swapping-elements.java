
class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // 1. Create a sorted copy of nums
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);

        // Map value -> group index
        Map<Integer, Integer> numToGroup = new HashMap<>();
        // Group index -> list of sorted elements in that group
        List<Deque<Integer>> groupToList = new ArrayList<>();

        int groupIndex = 0;
        groupToList.add(new ArrayDeque<>());
        groupToList.get(0).add(sortedNums[0]);
        numToGroup.put(sortedNums[0], 0);

        // 2. Group adjacent elements whose difference <= limit
        for (int i = 1; i < n; i++) {
            if (sortedNums[i] - sortedNums[i - 1] > limit) {
                groupIndex++;
                groupToList.add(new ArrayDeque<>());
            }
            numToGroup.put(sortedNums[i], groupIndex);
            groupToList.get(groupIndex).add(sortedNums[i]);
        }

        // 3. Reconstruct the output array
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int gi = numToGroup.get(nums[i]);
            // Pop the current smallest element in this group
            result[i] = groupToList.get(gi).pollFirst();
        }

        return result;
    }
}
    