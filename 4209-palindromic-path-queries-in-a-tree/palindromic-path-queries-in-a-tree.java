
           import java.util.*;

class Solution {
    int n;
    List<List<Integer>> adj;
    int[] parent, depth, heavy, head, pos, revPos;
    int curPos;
    int[] tree;
    char[] chars;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        this.chars = s.toCharArray();
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        Arrays.fill(heavy, -1);
        head = new int[n];
        pos = new int[n];
        revPos = new int[n];
        curPos = 0;

        dfs1(0, 0, 0);
        dfs2(0, 0);

        tree = new int[4 * n];
        build(1, 0, n - 1);

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                chars[u] = c;
                update(1, 0, n - 1, pos[u], 1 << (c - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int mask = queryPath(u, v);
                ans.add((mask & (mask - 1)) == 0);
            }
        }
        return ans;
    }

    private int dfs1(int u, int p, int d) {
        parent[u] = p;
        depth[u] = d;
        int size = 1;
        int maxSubtree = 0;
        for (int v : adj.get(u)) {
            if (v != p) {
                int sub = dfs1(v, u, d + 1);
                size += sub;
                if (sub > maxSubtree) {
                    maxSubtree = sub;
                    heavy[u] = v;
                }
            }
        }
        return size;
    }

    private void dfs2(int u, int h) {
        head[u] = h;
        pos[u] = curPos;
        revPos[curPos] = u;
        curPos++;
        if (heavy[u] != -1) {
            dfs2(heavy[u], h);
        }
        for (int v : adj.get(u)) {
            if (v != parent[u] && v != heavy[u]) {
                dfs2(v, v);
            }
        }
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            int u = revPos[start];
            tree[node] = 1 << (chars[u] - 'a');
            return;
        }
        int mid = (start + end) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = (start + end) / 2;
        if (idx <= mid) update(2 * node, start, mid, idx, val);
        else update(2 * node + 1, mid + 1, end, idx, val);
        tree[node] = tree[2 * node] ^ tree[2 * node + 1];
    }

    private int queryTree(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return 0;
        if (l <= start && end <= r) return tree[node];
        int mid = (start + end) / 2;
        return queryTree(2 * node, start, mid, l, r) ^ queryTree(2 * node + 1, mid + 1, end, l, r);
    }

    private int queryPath(int u, int v) {
        int res = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] > depth[head[v]]) {
                int t = u; u = v; v = t;
            }
            res ^= queryTree(1, 0, n - 1, pos[head[v]], pos[v]);
            v = parent[head[v]];
        }
        if (depth[u] > depth[v]) {
            int t = u; u = v; v = t;
        }
        res ^= queryTree(1, 0, n - 1, pos[u], pos[v]);
        return res;
    }
}