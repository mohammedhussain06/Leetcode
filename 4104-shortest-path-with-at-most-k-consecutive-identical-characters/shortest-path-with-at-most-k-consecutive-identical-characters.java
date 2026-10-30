
import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {

        // Build adjacency list
        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            adj[u].add(new int[]{v, w});
        }

        // dist[node][count]
        // count = consecutive occurrences of labels[node]
        long[][] dist = new long[n][k + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        // PriorityQueue: {distance, node, count}
        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        // Starting node contributes its label
        dist[0][1] = 0;

        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {

            long[] curr = pq.poll();

            long d = curr[0];
            int u = (int) curr[1];
            int count = (int) curr[2];

            // Ignore outdated states
            if (d != dist[u][count]) {
                continue;
            }

            // Destination reached
            if (u == n - 1) {
                return (int) d;
            }

            // Explore outgoing edges
            for (int[] edge : adj[u]) {

                int v = edge[0];
                int w = edge[1];

                int newCount;

                if (labels.charAt(u) == labels.charAt(v)) {
                    newCount = count + 1;
                } else {
                    newCount = 1;
                }

                // Invalid consecutive sequence
                if (newCount > k) {
                    continue;
                }

                long newDist = d + w;

                if (newDist < dist[v][newCount]) {

                    dist[v][newCount] = newDist;

                    pq.offer(new long[]{
                        newDist, v, newCount
                    });
                }
            }
        }

        return -1;
    }
}
