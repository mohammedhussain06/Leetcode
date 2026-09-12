class Solution {
    static class State {
        long score;
        int[] indices;

        State(long score, int[] indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    private int n;
    private int[][] arr;
    private int[][] next;
    private State[][] memo;

    public int[] maximumWeight(List<List<Integer>> intervals) {
        n = intervals.size();

        arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0); // left
            arr[i][1] = intervals.get(i).get(1); // right
            arr[i][2] = intervals.get(i).get(2); // weight
            arr[i][3] = i;                       // original index
        }

        // Sort by start time, then original index
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]);
            return Integer.compare(a[3], b[3]);
        });

        // Find next interval with start > current end
        next = new int[n][1];

        for (int i = 0; i < n; i++) {
            next[i][0] = findNext(arr[i][1]);
        }

        memo = new State[n + 1][5];

        State ans = solve(0, 4);

        return ans.indices;
    }

    private int findNext(int end) {
        int lo = 0;
        int hi = n;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            // Must be strictly greater than end
            if (arr[mid][0] > end) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private State solve(int pos, int remaining) {
        if (pos >= n || remaining == 0) {
            return new State(0, new int[0]);
        }

        if (memo[pos][remaining] != null) {
            return memo[pos][remaining];
        }

        // Option 1: skip current interval
        State skip = solve(pos + 1, remaining);

        // Option 2: take current interval
        State takeNext = solve(next[pos][0], remaining - 1);

        int[] takeIndices = new int[takeNext.indices.length + 1];
        takeIndices[0] = arr[pos][3];

        System.arraycopy(
            takeNext.indices,
            0,
            takeIndices,
            1,
            takeNext.indices.length
        );

        // Sort chosen original indices so lexicographical comparison
        // is based on the required answer format.
        Arrays.sort(takeIndices);

        State take = new State(
            (long) arr[pos][2] + takeNext.score,
            takeIndices
        );

        memo[pos][remaining] = better(skip, take);

        return memo[pos][remaining];
    }

    private State better(State a, State b) {
        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        // Same score -> lexicographically smaller array
        return lexicographicallySmaller(a.indices, b.indices) ? a : b;
    }

    private boolean lexicographicallySmaller(int[] a, int[] b) {
        int len = Math.min(a.length, b.length);

        for (int i = 0; i < len; i++) {
            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }
        return a.length < b.length;
    }
}