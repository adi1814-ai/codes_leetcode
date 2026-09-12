

class Solution {
    private static class IntervalData implements Comparable<IntervalData> {
        int end, start, weight, originalIndex;

        public IntervalData(int end, int start, int weight, int originalIndex) {
            this.end = end;
            this.start = start;
            this.weight = weight;
            this.originalIndex = originalIndex;
        }

        @Override
        public int compareTo(IntervalData other) {
            return Integer.compare(this.end, other.end);
        }
    }

    private static class State implements Comparable<State> {
        long weightSum;
        List<Integer> indices;

        public State(long weightSum, List<Integer> indices) {
            this.weightSum = weightSum;
            this.indices = indices;
        }

        @Override
        public int compareTo(State other) {
            // We want to maximize total weight, which means minimizing (-weightSum)
            if (this.weightSum != other.weightSum) {
                return Long.compare(other.weightSum, this.weightSum); // Larger weight comes first
            }
            // Tie-breaker: Lexicographically smallest list of indices
            int size = Math.min(this.indices.size(), other.indices.size());
            for (int i = 0; i < size; i++) {
                int cmp = Integer.compare(this.indices.get(i), other.indices.get(i));
                if (cmp != 0) return cmp;
            }
            return Integer.compare(this.indices.size(), other.indices.size());
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        List<IntervalData> sortedIntervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            sortedIntervals.add(new IntervalData(interval.get(1), interval.get(0), interval.get(2), i));
        }

        Collections.sort(sortedIntervals);

        // dp[i][j] stores the optimal State after considering the first i intervals with j picked
        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= 4; j++) {
                dp[i][j] = new State(0, new ArrayList<>());
            }
        }

        for (int i = 0; i < n; i++) {
            int start = sortedIntervals.get(i).start;
            int weight = sortedIntervals.get(i).weight;
            int originalIndex = sortedIntervals.get(i).originalIndex;

            // Binary search to find the latest non-overlapping interval
            int k = binarySearch(sortedIntervals, i, start);

            for (int j = 1; j <= 4; j++) {
                State skip = dp[i][j];

                // Take current interval
                State prev = dp[k][j - 1];
                long takeWeight = prev.weightSum + weight;
                List<Integer> takeIndices = new ArrayList<>(prev.indices);
                takeIndices.add(originalIndex);
                Collections.sort(takeIndices);
                State take = new State(takeWeight, takeIndices);

                // Choose the best state based on our custom comparison (max weight, then lexicographically smallest)
                if (take.compareTo(skip) < 0) {
                    dp[i + 1][j] = take;
                } else {
                    dp[i + 1][j] = skip;
                }
            }
        }

        List<Integer> bestIndices = dp[n][4].indices;
        int[] result = new int[bestIndices.size()];
        for (int i = 0; i < bestIndices.size(); i++) {
            result[i] = bestIndices.get(i);
        }
        return result;
    }

    private int binarySearch(List<IntervalData> sortedIntervals, int high, int targetStart) {
        int low = 0;
        int ans = 0;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (sortedIntervals.get(mid).end < targetStart) {
                ans = mid + 1;
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return ans;
    }
}