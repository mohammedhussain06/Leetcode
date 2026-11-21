
class Solution {
    int[] newParent;
    int[] last;
    int[] size;
    List<Integer>[] children;
    List<Integer>[] original;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        newParent = parent.clone();
        last = new int[26];
        Arrays.fill(last, -1);

        original = new ArrayList[n];
        children = new ArrayList[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            original[i] = new ArrayList<>();
            children[i] = new ArrayList<>();
        }

        // Build original tree
        for (int i = 1; i < n; i++) {
            original[parent[i]].add(i);
        }

        // Find new parent for every node
        dfs1(0, s);

        // Build modified tree
        for (int i = 1; i < n; i++) {
            children[newParent[i]].add(i);
        }

        // Calculate subtree sizes
        dfs2(0);

        return size;
    }

    void dfs1(int node, String s) {
        int ch = s.charAt(node) - 'a';

        // Save previous ancestor with same character
        int previous = last[ch];

        // Update parent if matching ancestor exists
        if (previous != -1) {
            newParent[node] = previous;
        }

        // Current node becomes latest ancestor
        last[ch] = node;

        // Visit original children
        for (int child : original[node]) {
            dfs1(child, s);
        }

        // Restore previous value during backtracking
        last[ch] = previous;
    }

    int dfs2(int node) {
        size[node] = 1;

        for (int child : children[node]) {
            size[node] += dfs2(child);
        }

        return size[node];
    }
}
