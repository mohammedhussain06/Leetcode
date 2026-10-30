
import java.util.*;

class Solution {

    int LOG;
    int[][] up;
    int[] depth, tin, tout;
    int[] initialRootMask;

    // Fenwick Tree supporting XOR operations
    class Fenwick {
        int[] bit;
        int n;

        Fenwick(int n) {
            this.n = n;
            bit = new int[n + 2];
        }

        void update(int i, int val) {
            while (i <= n) {
                bit[i] ^= val;
                i += i & -i;
            }
        }

        int query(int i) {
            int result = 0;

            while (i > 0) {
                result ^= bit[i];
                i -= i & -i;
            }

            return result;
        }

        // Range XOR update [l, r]
        void rangeUpdate(int l, int r, int val) {
            update(l, val);
            update(r + 1, val);
        }
    }

    // Find Lowest Common Ancestor
    int lca(int u, int v) {

        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // Bring u to the same depth as v
        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) {
            return u;
        }

        // Lift both nodes simultaneously
        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }

    public List<Boolean> palindromePath(
        int n,
        int[][] edges,
        String s,
        String[] queries
    ) {

        // 1. Build adjacency list
        List<Integer>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj[u].add(v);
            adj[v].add(u);
        }

        // 2. Initialize arrays
        LOG = 1;

        while ((1 << LOG) <= n) {
            LOG++;
        }

        up = new int[LOG][n];

        depth = new int[n];
        tin = new int[n];
        tout = new int[n];

        initialRootMask = new int[n];

        // 3. Iterative DFS for Euler Tour
        // Avoid recursive DFS stack overflow.

        int[] parent = new int[n];
        int[] order = new int[n];

        Arrays.fill(parent, -1);

        int[] stack = new int[n];
        int top = 0;
        int timer = 0;

        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {

            int u = stack[--top];

            order[timer] = u;
            tin[u] = timer++;

            initialRootMask[u] =
                (u == 0 ? 0 : initialRootMask[parent[u]])
                ^ (1 << (s.charAt(u) - 'a'));

            up[0][u] = parent[u];

            for (int v : adj[u]) {

                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;

                stack[top++] = v;
            }
        }

        // 4. Calculate binary lifting table
        for (int j = 1; j < LOG; j++) {

            for (int u = 0; u < n; u++) {

                up[j][u] =
                    up[j - 1][up[j - 1][u]];
            }
        }

        // 5. Calculate subtree sizes
        int[] subtreeSize = new int[n];

        Arrays.fill(subtreeSize, 1);

        for (int i = n - 1; i > 0; i--) {

            int u = order[i];

            subtreeSize[parent[u]] += subtreeSize[u];
        }

        for (int u = 0; u < n; u++) {
            tout[u] = tin[u] + subtreeSize[u] - 1;
        }

        // 6. Initialize Fenwick Tree
        Fenwick bit = new Fenwick(n);

        char[] current = s.toCharArray();

        List<Boolean> answer = new ArrayList<>();

        // 7. Process queries
        for (String q : queries) {

            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {

                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int oldMask =
                    1 << (current[u] - 'a');

                int newMask =
                    1 << (c - 'a');

                int delta = oldMask ^ newMask;

                // Update entire subtree
                bit.rangeUpdate(
                    tin[u] + 1,
                    tout[u] + 1,
                    delta
                );

                current[u] = c;
            }

            else {

                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int ancestor = lca(u, v);

                // Updated root-to-node masks
                int maskU =
                    initialRootMask[u]
                    ^ bit.query(tin[u] + 1);

                int maskV =
                    initialRootMask[v]
                    ^ bit.query(tin[v] + 1);

                int ancestorMask =
                    1 << (current[ancestor] - 'a');

                // XOR path mask
                int pathMask =
                    maskU ^ maskV ^ ancestorMask;

                // At most one odd frequency
                boolean valid =
                    (pathMask & (pathMask - 1)) == 0;

                answer.add(valid);
            }
        }

        return answer;
    }
}
