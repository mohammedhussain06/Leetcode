
class Solution {
    static final int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        long[] sum = new long[4 * n];
        long[] sq = new long[4 * n];
        long[] lazy = new long[4 * n];

        int[] last = new int[100001];
        long ans = 0;

        for (int r = 0; r < n; r++) {
            int x = nums[r];
            int prev = last[x] - 1;

            update(1, 0, n - 1, prev + 1, r, 1,
                   sum, sq, lazy);

            last[x] = r + 1;

            ans = (ans + sq[1]) % MOD;
        }

        return (int) ans;
    }

    private void apply(int node, int l, int r, long v,
                       long[] sum, long[] sq, long[] lazy) {
        long len = r - l + 1L;
        long oldSum = sum[node];

        sq[node] = (sq[node] + 2 * v % MOD * oldSum
                    + v * v % MOD * len) % MOD;

        sum[node] = (oldSum + v * len) % MOD;
        lazy[node] = (lazy[node] + v) % MOD;
    }

    private void push(int node, int l, int r,
                      long[] sum, long[] sq, long[] lazy) {
        if (lazy[node] == 0 || l == r) return;

        int mid = l + (r - l) / 2;
        apply(node * 2, l, mid, lazy[node], sum, sq, lazy);
        apply(node * 2 + 1, mid + 1, r, lazy[node],
              sum, sq, lazy);

        lazy[node] = 0;
    }

    private void update(int node, int l, int r, int ql, int qr,
                        long v, long[] sum, long[] sq,
                        long[] lazy) {
        if (ql > r || qr < l) return;

        if (ql <= l && r <= qr) {
            apply(node, l, r, v, sum, sq, lazy);
            return;
        }

        push(node, l, r, sum, sq, lazy);

        int mid = l + (r - l) / 2;
        update(node * 2, l, mid, ql, qr, v, sum, sq, lazy);
        update(node * 2 + 1, mid + 1, r, ql, qr, v,
               sum, sq, lazy);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }
}
