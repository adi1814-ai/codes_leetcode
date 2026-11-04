class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map =  new HashMap<>();
        int times =  nums.length / 3;

        //Count frequencies
        for(int num : nums) {
            map.put(num , map.getOrDefault(num, 0) + 1);
        }
        // Iterate through each key-value pair in the map
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            // entry.getKey() gives the number from the array
            // entry.getValue() gives how many times that number appeared
            if (entry.getValue() > times) {
                result.add(entry.getKey());
    }
}
    return result;
    }
}