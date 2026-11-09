
import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        // Available servers (sorted by ID)
        TreeSet<Integer> available = new TreeSet<>();

        // Stores {finishTime, serverID}
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        // Request count handled by each server
        int[] count = new int[k];

        // Initially all servers are available
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        for (int i = 0; i < arrival.length; i++) {

            int currentTime = arrival[i];

            // Release servers whose requests have completed
            while (!busy.isEmpty() &&
                   busy.peek()[0] <= currentTime) {

                int[] completed = busy.poll();
                available.add(completed[1]);
            }

            // If all servers are busy, drop the request
            if (available.isEmpty()) {
                continue;
            }

            // Preferred server
            int preferred = i % k;

            // Find the next available server
            Integer server = available.ceiling(preferred);

            // Wrap around if no server exists after preferred
            if (server == null) {
                server = available.first();
            }

            // Assign request to server
            available.remove(server);

            count[server]++;

            // Store its completion time
            busy.offer(new int[]{
                currentTime + load[i],
                server
            });
        }

        // Find maximum number of requests handled
        int maxRequests = 0;

        for (int c : count) {
            maxRequests = Math.max(maxRequests, c);
        }

        // Collect all busiest servers
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}
