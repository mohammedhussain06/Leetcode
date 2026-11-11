
class Solution {
    public int climbStairs(int n, int[] costs) {

        int[] dp = new int[n + 1];

        // Base case
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {

            dp[i] = Integer.MAX_VALUE;

            // Try jumps of 1, 2 and 3 steps
            for (int j = 1; j <= 3; j++) {

                if (i - j >= 0) {

                    int jumpCost = j * j;

                    dp[i] = Math.min(
                        dp[i],
                        dp[i - j] + costs[i - 1] + jumpCost
                    );
                }
            }
        }

        return dp[n];
    }
}
