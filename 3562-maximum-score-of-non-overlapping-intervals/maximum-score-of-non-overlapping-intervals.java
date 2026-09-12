class Solution {

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        // [left, right, weight, originalIndex]
        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        // Sort by right endpoint
        Arrays.sort(a, (x, y) -> {
            if (x[1] != y[1])
                return Integer.compare(x[1], y[1]);
            return Integer.compare(x[3], y[3]);
        });

        // ends[i] = right endpoint of sorted interval i
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            ends[i] = a[i][1];
        }

        /*
         * dp[k][i] = maximum score using exactly k intervals
         * among the first i sorted intervals.
         */
        long[][] dp = new long[5][n + 1];

        // chosen[k][i * 4 ... i * 4 + 3]
        // Stores the original indices of the chosen intervals,
        // already sorted lexicographically.
        int[][] chosen = new int[5][(n + 1) * 4];

        // -1 means this position is unused.
        for (int k = 0; k <= 4; k++) {
            Arrays.fill(chosen[k], -1);
        }

        // A state is impossible initially except k = 0.
        for (int k = 1; k <= 4; k++) {
            Arrays.fill(dp[k], Long.MIN_VALUE / 4);
        }

        /*
         * Process intervals one by one.
         */
        for (int i = 1; i <= n; i++) {

            int left = a[i - 1][0];
            int weight = a[i - 1][2];
            int originalIndex = a[i - 1][3];

            // Number of intervals whose right < current left.
            // This is exactly the prefix size we can combine with.
            int p = lowerBound(ends, left);

            for (int k = 0; k <= 4; k++) {

                // Option 1: don't take current interval
                dp[k][i] = dp[k][i - 1];

                copy(chosen[k], i, chosen[k], i - 1);

                // Option 2: take current interval
                if (k > 0 && dp[k - 1][p] > Long.MIN_VALUE / 8) {

                    long candidateScore =
                            dp[k - 1][p] + weight;

                    int[] candidate = new int[4];
                    getState(chosen[k - 1], p, candidate);

                    // Insert current original index so that
                    // the resulting array is sorted.
                    insertSorted(candidate, originalIndex, k);

                    if (candidateScore > dp[k][i] ||
                        (candidateScore == dp[k][i]
                         && lexicographicallySmaller(
                                candidate,
                                chosen[k],
                                i))) {

                        dp[k][i] = candidateScore;
                        setState(chosen[k], i, candidate);
                    }
                }
            }
        }

        /*
         * We may choose up to 4 intervals, so compare
         * answers for k = 0,1,2,3,4.
         */
        long bestScore = 0;
        int bestK = 0;

        for (int k = 1; k <= 4; k++) {
            if (dp[k][n] > bestScore) {
                bestScore = dp[k][n];
                bestK = k;
            } else if (dp[k][n] == bestScore) {
                if (lexicographicallySmaller(
                        chosen[k], n,
                        chosen[bestK], n)) {
                    bestK = k;
                }
            }
        }

        int[] answer = new int[bestK];
        for (int j = 0; j < bestK; j++) {
            answer[j] = chosen[bestK][n * 4 + j];
        }

        return answer;
    }

    // First position whose value >= target.
    // Therefore all positions before it have value < target.
    private int lowerBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] < target)
                lo = mid + 1;
            else
                hi = mid;
        }

        return lo;
    }

    /*
     * Copy state from position 'from' to position 'to'.
     */
    private void copy(int[] arr, int to, int[] source, int from) {
        for (int j = 0; j < 4; j++) {
            arr[to * 4 + j] = source[from * 4 + j];
        }
    }

    private void getState(int[] arr, int pos, int[] result) {
        for (int j = 0; j < 4; j++) {
            result[j] = arr[pos * 4 + j];
        }
    }

    private void setState(int[] arr, int pos, int[] state) {
        for (int j = 0; j < 4; j++) {
            arr[pos * 4 + j] = state[j];
        }
    }

    /*
     * Insert index while keeping the selected indices sorted.
     */
    private void insertSorted(int[] arr, int value, int size) {
        int pos = size - 1;

        while (pos > 0 && arr[pos - 1] > value) {
            arr[pos] = arr[pos - 1];
            pos--;
        }

        arr[pos] = value;
    }

    /*
     * Compare candidate against the state at position pos.
     */
    private boolean lexicographicallySmaller(
            int[] candidate,
            int[] states,
            int pos) {

        for (int i = 0; i < 4; i++) {
            int x = candidate[i];
            int y = states[pos * 4 + i];

            if (x == -1 && y == -1)
                return false;

            if (x == -1)
                return true;

            if (y == -1)
                return false;

            if (x != y)
                return x < y;
        }

        return false;
    }

    /*
     * Compare two complete states.
     */
    private boolean lexicographicallySmaller(
            int[] a, int posA,
            int[] b, int posB) {

        int baseA = posA * 4;
        int baseB = posB * 4;

        for (int i = 0; i < 4; i++) {
            int x = a[baseA + i];
            int y = b[baseB + i];

            if (x == -1 && y == -1)
                return false;

            if (x == -1)
                return true;

            if (y == -1)
                return false;

            if (x != y)
                return x < y;
        }

        return false;
    }
}