class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Build adjacency list graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] inv : invocations) {
            graph.get(inv[0]).add(inv[1]);
        }

        //Find all suspicious methods starting from k using DFS/BFS
        boolean[] suspicious = new boolean[n];
        dfs(k, graph, suspicious);

        // Check if any non-suspicious method calls a suspicious method
        for (int[] inv : invocations) {
            int u = inv[0];
            int v = inv[1];
            if (!suspicious[u] && suspicious[v]) {
                // Return all methods if removal rule is violated
                List<Integer> allMethods = new ArrayList<>();
                for (int i = 0; i < n; i++) allMethods.add(i);
                return allMethods;
            }
        }

        //Collect remaining non-suspicious methods
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                result.add(i);
            }
        }
        return result;
    }

    private void dfs(int node, List<List<Integer>> graph, boolean[] suspicious) {
        suspicious[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!suspicious[neighbor]) {
                dfs(neighbor, graph, suspicious);
            }
        }
    }
}