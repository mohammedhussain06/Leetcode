class Solution {
    private int[][] dp;

    public int getLengthOfOptimalCompression(String s, int k) {
        int n = s.length();
        dp = new int[n][k + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solve(s, 0, k);
    }

    private int solve(String s, int index, int k) {
        if (k < 0) return Integer.MAX_VALUE;
        if (index >= s.length() || s.length() - index <= k) return 0;

        if (dp[index][k] != -1) return dp[index][k];

        int ans = solve(s, index + 1, k - 1);

        int same = 0, deleted = 0;

        for (int i = index; i < s.length() && deleted <= k; i++) {
            if (s.charAt(i) == s.charAt(index)) {
                same++;
                int len = 1;
                if (same >= 100) len = 4;
                else if (same >= 10) len = 3;
                else if (same >= 2) len = 2;

                ans = Math.min(ans, len + solve(s, i + 1, k - deleted));
            } else {
                deleted++;
            }
        }

        return dp[index][k] = ans;
    }
}