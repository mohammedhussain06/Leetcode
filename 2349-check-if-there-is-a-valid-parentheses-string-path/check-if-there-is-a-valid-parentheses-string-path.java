import java.util.*;

class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // A valid parentheses string must have even length.
        if ((m + n - 1) % 2 == 1) {
            return false;
        }

        // dp[j] stores possible balances at cell (current row, j)
        BitSet[] dp = new BitSet[n];

        for (int j = 0; j < n; j++) {
            dp[j] = new BitSet(m + n);
        }

        // Start at (0, 0)
        if (grid[0][0] == ')') {
            return false;
        }

        dp[0].set(1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                BitSet possible = new BitSet(m + n);

                // From top
                if (i > 0) {
                    possible.or(dp[j]);
                }

                // From left
                if (j > 0) {
                    possible.or(dp[j - 1]);
                }

                // Current character
                if (grid[i][j] == '(') {
                    // balance becomes balance + 1
                    BitSet shifted = new BitSet(m + n);

                    for (int b = possible.nextSetBit(0);
                         b >= 0;
                         b = possible.nextSetBit(b + 1)) {
                        shifted.set(b + 1);
                    }

                    dp[j] = shifted;
                } else {
                    // balance becomes balance - 1
                    BitSet shifted = new BitSet(m + n);

                    for (int b = possible.nextSetBit(1);
                         b >= 0;
                         b = possible.nextSetBit(b + 1)) {
                        shifted.set(b - 1);
                    }

                    dp[j] = shifted;
                }
            }
        }

        // Valid path must finish with balance 0.
        return dp[n - 1].get(0);
    }
}