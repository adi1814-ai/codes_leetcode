

 import java.util.*;

class Solution {
    
    // Dual-heap structure to maintain sliding window median and calculate cost in O(log x)
    static class SlidingWindowMedian {
        PriorityQueue<Long> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        Map<Long, Integer> delayedRemoval = new HashMap<>();
        long sumLeft = 0, sumRight = 0;
        int sizeLeft = 0, sizeRight = 0;

        void add(long val) {
            if (maxHeap.isEmpty() || val <= maxHeap.peek()) {
                maxHeap.offer(val);
                sumLeft += val;
                sizeLeft++;
            } else {
                minHeap.offer(val);
                sumRight += val;
                sizeRight++;
            }
            balance();
        }

        void remove(long val) {
            delayedRemoval.put(val, delayedRemoval.getOrDefault(val, 0) + 1);
            if (!maxHeap.isEmpty() && val <= maxHeap.peek()) {
                sumLeft -= val;
                sizeLeft--;
            } else {
                sumRight -= val;
                sizeRight--;
            }
            balance();
        }

        void balance() {
            while (sizeLeft > sizeRight + 1) {
                long v = maxHeap.poll();
                sumLeft -= v; sizeLeft--;
                minHeap.offer(v);
                sumRight += v; sizeRight++;
                cleanup();
            }
            while (sizeRight > sizeLeft) {
                long v = minHeap.poll();
                sumRight -= v; sizeRight--;
                maxHeap.offer(v);
                sumLeft += v; sizeLeft++;
                cleanup();
            }
            cleanup();
        }

        void cleanup() {
            while (!maxHeap.isEmpty() && delayedRemoval.containsKey(maxHeap.peek())) {
                long top = maxHeap.peek();
                if (delayedRemoval.put(top, delayedRemoval.get(top) - 1) == 1) {
                    delayedRemoval.remove(top);
                }
                maxHeap.poll();
            }
            while (!minHeap.isEmpty() && delayedRemoval.containsKey(minHeap.peek())) {
                long top = minHeap.peek();
                if (delayedRemoval.put(top, delayedRemoval.get(top) - 1) == 1) {
                    delayedRemoval.remove(top);
                }
                minHeap.poll();
            }
        }

        long getCost() {
            long median = maxHeap.peek();
            return (median * sizeLeft - sumLeft) + (sumRight - median * sizeRight);
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int totalWindows = n - x + 1;
        long[] cost = new long[totalWindows];
        SlidingWindowMedian sw = new SlidingWindowMedian();

        // Step 1: Precalculate cost for each window of size x
        for (int i = 0; i < n; i++) {
            sw.add(nums[i]);
            if (i >= x - 1) {
                cost[i - x + 1] = sw.getCost();
                sw.remove(nums[i - x + 1]);
            }
        }

        long INF = (long) 1e18;
        // dp[j][i] = min cost to form j windows using window indices up to i
        long[] prevDp = new long[totalWindows];
        long[] currDp = new long[totalWindows];
        Arrays.fill(prevDp, INF);

        // Base case for 1 window: cost is just the window cost itself, with prefix min
        long minSoFar = INF;
        for (int i = 0; i < totalWindows; i++) {
            minSoFar = Math.min(minSoFar, cost[i]);
            prevDp[i] = minSoFar;
        }

        // Step 2: DP for remaining k-1 windows
        for (int j = 2; j <= k; j++) {
            Arrays.fill(currDp, INF);
            minSoFar = INF;
            
            for (int i = 0; i < totalWindows; i++) {
                // To place the current window at index i, the previous window must end 
                // at least x indices before, meaning it must start at index <= i - x.
                int validPrevEnd = i - x;
                if (validPrevEnd >= 0) {
                    minSoFar = Math.min(minSoFar, prevDp[validPrevEnd]);
                }
                
                if (minSoFar != INF) {
                    currDp[i] = minSoFar + cost[i];
                }
            }
            
            // Maintain prefix minimum for currDp so lookups work correctly
            long runningMin = INF;
            for (int i = 0; i < totalWindows; i++) {
                runningMin = Math.min(runningMin, currDp[i]);
                currDp[i] = runningMin;
            }
            
            prevDp = currDp.clone();
        }

        return prevDp[totalWindows - 1];
    }
}