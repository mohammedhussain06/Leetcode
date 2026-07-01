import java.util.Arrays;

class Solution {
    // 4D array for memoization: [index][k][lastChar][lastCharCount]
    // String length <= 100, k <= 100, 26 lowercase letters + 1 empty state, count max 100
    private int[][][][] memo;

    public int getLengthOfOptimalCompression(String s, int k) {
        int n = s.length();
        // lastChar ranges from 0 to 26 (26 represents an empty/dummy character state)
        // lastCharCount caps at 100 (or n)
        memo = new int[n][k + 1][27][101];
        
        // Initialize memoization array with -1 (unvisited)
        for (int[][][] d3 : memo) {
            for (int[][] d2 : d3) {
                for (int[] d1 : d2) {
                    Arrays.fill(d1, -1);
                }
            }
        }
        
        return dp(s, 0, k, (char) ('a' + 26), 0);
    }

    private int dp(String s, int i, int k, char lastChar, int lastCharCount) {
        // Base Case: If we reach the end of the string, no more characters to compress
        if (i == s.length()) {
            return 0;
        }

        int lastCharIdx = lastChar - 'a';
        // Check if this subproblem has already been computed
        if (memo[i][k][lastCharIdx][lastCharCount] != -1) {
            return memo[i][k][lastCharIdx][lastCharCount];
        }

        // Option 1: Delete the current character (if we still have deletions left)
        int deleteChar = Integer.MAX_VALUE;
        if (k > 0) {
            deleteChar = dp(s, i + 1, k - 1, lastChar, lastCharCount);
        }

        // Option 2: Keep the current character
        int keepChar = 0;
        if (s.charAt(i) == lastChar) {
            // It matches the last character, so it extends the current run
            // We calculate if changing the count expands the string length (e.g., from count 1->2, 9->10, 99->100)
            int extraLength = 0;
            if (lastCharCount == 1 || lastCharCount == 9 || lastCharCount == 99) {
                extraLength = 1;
            }
            keepChar = extraLength + dp(s, i + 1, k, lastChar, lastCharCount + 1);
        } else {
            // It's a new character! It takes 1 slot for the character itself.
            keepChar = 1 + dp(s, i + 1, k, s.charAt(i), 1);
        }

        // Store and return the minimum length path found
        return memo[i][k][lastCharIdx][lastCharCount] = Math.min(deleteChar, keepChar);
    }
}