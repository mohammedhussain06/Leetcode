class Solution {
    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        // Store positions and values of non-zero digits
        int[] pos = new int[n];
        int[] digit = new int[n + 1]; // 1-based indexing
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                pos[cnt++] = i;
                digit[cnt] = d;
            }
        }

        // Powers of 10 modulo MOD
        long[] pow10 = new long[cnt + 1];
        pow10[0] = 1;
        for (int i = 1; i <= cnt; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // Prefix hash for concatenated number
        long[] prefHash = new long[cnt + 1];
        // Prefix digit sums
        long[] prefSum = new long[cnt + 1];

        for (int i = 1; i <= cnt; i++) {
            prefHash[i] = (prefHash[i - 1] * 10 + digit[i]) % MOD;
            prefSum[i] = prefSum[i - 1] + digit[i];
        }

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int l = queries[q][0];
            int r = queries[q][1];

            int left = lowerBound(pos, cnt, l);
            int right = upperBound(pos, cnt, r) - 1;

            if (left > right) {
                ans[q] = 0;
                continue;
            }

            int i = left + 1;
            int j = right + 1;
            int len = j - i + 1;

            long x = (prefHash[j] - (prefHash[i - 1] * pow10[len]) % MOD + MOD) % MOD;
            long sum = prefSum[j] - prefSum[i - 1];

            ans[q] = (int) ((x * (sum % MOD)) % MOD);
        }

        return ans;
    }

    // First index with value >= target
    private int lowerBound(int[] arr, int size, int target) {
        int l = 0, r = size;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    // First index with value > target
    private int upperBound(int[] arr, int size, int target) {
        int l = 0, r = size;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] > target)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}