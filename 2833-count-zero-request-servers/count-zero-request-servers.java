class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[][] indexedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            indexedQueries[i][0] = queries[i];
            indexedQueries[i][1] = i;
        }

        // Sort queries by their time values in ascending order
       Arrays.sort(indexedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        // Sort logs by their timestamp in ascending order
       Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int[] ans = new int[m];
        int[] serverCount = new int[n + 1];
        int activeServers = 0;
        int logIndex = 0;
        int left = 0, right = 0;
        int logsLen = logs.length;

        for (int i = 0; i < m; i++) {
            int qTime = indexedQueries[i][0];
            int originalIndex = indexedQueries[i][1];
            int windowStart = qTime - x;
            int windowEnd = qTime;

            // Add logs that fall within or before the windowEnd
            while (right < logsLen && logs[right][1] <= windowEnd) {
                int sId = logs[right][0];
                if (serverCount[sId] == 0) {
                    activeServers++;
                }
                serverCount[sId]++;
                right++;
            }

            // Remove logs that fall outside the windowStart (i.e., strictly before windowStart)
            while (left < logsLen && logs[left][1] < windowStart) {
                int sId = logs[left][0];
                serverCount[sId]--;
                if (serverCount[sId] == 0) {
                    activeServers--;
                }
                left++;
            }

            // Total servers minus servers that received at least one request in the window
            ans[originalIndex] = n - activeServers;
        }

        return ans;
    }
}