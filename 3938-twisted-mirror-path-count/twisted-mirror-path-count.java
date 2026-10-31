class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;
        
        // dp[c][0] -> ways entering column c moving right
        // dp[c][1] -> ways entering column c moving down
        int[][] dp = new int[n + 1][2];
        dp[1][0] = 1;
        dp[1][1] = 1;

        for (int r = 0; r < m; r++) {
            int[][] nextDp = new int[n + 1][2];
            for (int c = 0; c < n; c++) {
                // If it's a mirror or empty cell, update states accordingly
                if (grid[r][c] == 1) {
                    // Mirror reflection logic
                    nextDp[c + 1] = new int[] { nextDp[c][1], dp[c + 1][0] };
                } else {
                    // Empty cell
                    int val = (nextDp[c][1] + dp[c + 1][0]) % MOD;
                    nextDp[c + 1] = new int[] { val, val };
                }
            }
            dp = nextDp;
        }
        
        return dp[n][0];
    }
}