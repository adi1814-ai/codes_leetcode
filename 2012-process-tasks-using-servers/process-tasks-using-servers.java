class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Priority queue for available servers: ordered by weight, then index
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // Priority queue for busy servers: ordered by free time, then weight, then index
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[2], b[2]);
        });

        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[] { servers[i], i }); // {weight, index}
        }

        long currentTime = 0;
        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);

            // Free up servers that have finished their tasks by currentTime
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                int[] server = busyServers.poll();
                freeServers.offer(new int[] { server[1], server[2] }); // {weight, index}
            }

            // If no servers are free, fast-forward time to when the next server becomes free
            if (freeServers.isEmpty()) {
                currentTime = busyServers.peek()[0];
                while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                    int[] server = busyServers.poll();
                    freeServers.offer(new int[] { server[1], server[2] });
                }
            }

            // Assign the task to the best available server
            int[] bestServer = freeServers.poll();
            int weight = bestServer[0];
            int index = bestServer[1];

            ans[j] = index;
            busyServers.offer(new int[] { (int) (currentTime + tasks[j]), weight, index });
        }

        return ans;
    }
}