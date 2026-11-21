import java.util.*;

class Solution {
    private List<Integer>[] adj;
    private int[] newParent;
    private int[] ans;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        newParent = new int[n];
        newParent[0] = -1;
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        findNewParents(0, parent, lastSeen);

        // Rebuild adjacency list for the modified tree
        List<Integer>[] newAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            newAdj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            newAdj[newParent[i]].add(i);
        }

        ans = new int[n];
        computeSubtreeSizes(0, newAdj);
        return ans;
    }

    private void findNewParents(int u, int[] origParent, int[] lastSeen) {
        int c = s.charAt(u) - 'a';
        newParent[u] = (lastSeen[c] != -1) ? lastSeen[c] : origParent[u];

        int prev = lastSeen[c];
        lastSeen[c] = u;

        for (int v : adj[u]) {
            findNewParents(v, origParent, lastSeen);
        }

        lastSeen[c] = prev;
    }

    private int computeSubtreeSizes(int u, List<Integer>[] newAdj) {
        int size = 1;
        for (int v : newAdj[u]) {
            size += computeSubtreeSizes(v, newAdj);
        }
        ans[u] = size;
        return size;
    }
}