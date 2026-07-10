import java.util.*;

class Solution {

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        int[] values = new int[n];
        int[] pos = new int[n];
        int[] comp = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = arr[i][0];
            pos[arr[i][1]] = i;
        }

        int id = 0;
        comp[0] = 0;
        for (int i = 1; i < n; i++) {
            if (values[i] - values[i - 1] > maxDiff) {
                id++;
            }
            comp[i] = id;
        }

        int[] nextFar = new int[n];
        int r = 0;
        for (int i = 0; i < n; i++) {
            while (r + 1 < n && values[r + 1] - values[i] <= maxDiff) {
                r++;
            }
            nextFar[i] = r;
        }

        int[] prevFar = new int[n];
        int l = 0;
        for (int i = 0; i < n; i++) {
            while (values[i] - values[l] > maxDiff) {
                l++;
            }
            prevFar[i] = l;
        }

        int LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        int[][] up = new int[LOG][n];
        int[][] down = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            up[0][i] = nextFar[i];
            down[0][i] = prevFar[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
                down[k][i] = down[k - 1][down[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            if (u == v) {
                ans[i] = 0;
                continue;
            }

            int pu = pos[u];
            int pv = pos[v];

            if (comp[pu] != comp[pv]) {
                ans[i] = -1;
                continue;
            }

            if (pu < pv) {
                ans[i] = jumpsRight(pu, pv, up, LOG);
            } else {
                ans[i] = jumpsLeft(pu, pv, down, LOG);
            }
        }

        return ans;
    }

    private int jumpsRight(int from, int target, int[][] up, int LOG) {
        int cur = from;
        int steps = 0;

        for (int k = LOG - 1; k >= 0; k--) {
            int nxt = up[k][cur];
            if (nxt < target) {
                cur = nxt;
                steps += 1 << k;
            }
        }

        if (cur < target) {
            steps++;
        }

        return steps;
    }

    private int jumpsLeft(int from, int target, int[][] down, int LOG) {
        int cur = from;
        int steps = 0;

        for (int k = LOG - 1; k >= 0; k--) {
            int nxt = down[k][cur];
            if (nxt > target) {
                cur = nxt;
                steps += 1 << k;
            }
        }

        if (cur > target) {
            steps++;
        }

        return steps;
    }
}