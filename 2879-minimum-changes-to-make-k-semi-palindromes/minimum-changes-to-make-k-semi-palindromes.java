
class Solution {
    public int minimumChanges(String s, int k) {

        int n = s.length();

        // Step 1: Precompute cost of every substring
        int[][] cost = new int[n][n];

        for (int l = 0; l < n; l++) {

            for (int r = l + 1; r < n; r++) {

                int len = r - l + 1;
                int best = Integer.MAX_VALUE;

                // Try every proper divisor
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Check each group
                    for (int offset = 0; offset < d; offset++) {

                        int left = l + offset;

                        int right = left +
                                ((r - left) / d) * d;

                        // Compare symmetric characters
                        while (left < right) {

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    best = Math.min(best, changes);
                }

                cost[l][r] = best;
            }
        }

        // Step 2: DP
        int INF = 1000000;

        int[][] dp = new int[k + 1][n + 1];

        for (int i = 0; i <= k; i++) {
            java.util.Arrays.fill(dp[i], INF);
        }

        dp[0][0] = 0;

        // p = number of partitions
        for (int p = 1; p <= k; p++) {

            // i = number of characters considered
            for (int i = 2 * p; i <= n; i++) {

                // j = starting index of last substring
                for (int j = 2 * (p - 1); j <= i - 2; j++) {

                    dp[p][i] = Math.min(
                        dp[p][i],
                        dp[p - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}
