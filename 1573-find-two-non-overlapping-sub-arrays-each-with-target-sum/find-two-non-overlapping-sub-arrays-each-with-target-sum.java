class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        
        // Fill with a value larger than any possible answer
        int INF = Integer.MAX_VALUE / 2;
        for (int i = 0; i < n; i++) {
            minLen[i] = INF;
        }
        
        int left = 0;
        int currSum = 0;
        int minSumLengths = INF;
        int currentMin = INF;
        
        for (int right = 0; right < n; right++) {
            currSum += arr[right];
            
            // Shrink window if sum exceeds target
            while (currSum > target && left <= right) {
                currSum -= arr[left];
                left++;
            }
            
            // Valid subarray found
            if (currSum == target) {
                int currentLen = right - left + 1;
                
                // If a non-overlapping subarray exists before 'left', check combined length
                if (left > 0 && minLen[left - 1] != INF) {
                    minSumLengths = Math.min(minSumLengths, currentLen + minLen[left - 1]);
                }
                
                // Update minimum length found so far
                currentMin = Math.min(currentMin, currentLen);
            }
            
            // Store the best length seen up to current 'right' index
            minLen[right] = currentMin;
        }
        
        return minSumLengths >= INF ? -1 : minSumLengths;
    }
}