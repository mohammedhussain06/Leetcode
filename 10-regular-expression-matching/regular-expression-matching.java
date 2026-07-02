class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;

        int m = s.length();
        int n = p.length();

        // dp[i][j] will be true if s[0..i-1] matches p[0..j-1]
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Base case: Empty string matches empty pattern
        dp[0][0] = true;

        // Base case initialization for patterns like a*, a*b*, a*b*c* matching an empty string
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char currentPatternChar = p.charAt(j - 1);

                // If it's a plain character match or a '.' wildcard
                if (currentPatternChar == s.charAt(i - 1) || currentPatternChar == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } 
                // If we encounter a '*' wildcard
                else if (currentPatternChar == '*') {
                    // Option 1: Count the '*' and its preceding character as 0 repetitions
                    dp[i][j] = dp[i][j - 2];

                    // Option 2: If the preceding pattern character matches the current string character,
                    // we can safely "consume" the string character and stay on the same pattern state.
                    char precedingChar = p.charAt(j - 2);
                    if (precedingChar == s.charAt(i - 1) || precedingChar == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        // The final cell holds the result for the entire string and pattern
        return dp[m][n];
    }
}