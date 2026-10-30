import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        // Priority queue stores: [weight, node, count]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        // minDistance[u][count] stores the minimum weight to reach node u with a given consecutive count
        int[][] minDistance = new int[n][k + 1];
        for (int[] row : minDistance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Starting at node 0 with count 1
        pq.offer(new int[]{0, 0, 1});
        minDistance[0][1] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0], u = curr[1], count = curr[2];

            if (u == n - 1) {
                return d;
            }

            if (d > minDistance[u][count]) {
                continue;
            }

            for (int[] neighbor : adj[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];

                int nextCount = 1;
                if (labels.charAt(u) == labels.charAt(v)) {
                    nextCount = count + 1;
                }

                if (nextCount <= k && d + weight < minDistance[v][nextCount]) {
                    minDistance[v][nextCount] = d + weight;
                    pq.offer(new int[]{d + weight, v, nextCount});
                }
            }
        }

        return -1;
    }
}