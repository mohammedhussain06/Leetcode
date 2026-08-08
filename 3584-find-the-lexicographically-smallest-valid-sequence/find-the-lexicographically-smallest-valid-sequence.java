class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[] suffix = new int[n + 1];
        int j = m - 1;
        int matched = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
                matched++;
            }
            suffix[i] = matched;
        }
        int[] ans = new int[m];
        j = 0;
        boolean usedMismatch = false;
        int k = 0;
        for (int i = 0; i < n && j < m; i++) {
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[k++] = i;
                j++;
            }
            else if (!usedMismatch) {
                int remaining = m - j - 1;
                if (suffix[i + 1] >= remaining) {
                    ans[k++] = i;
                    j++;
                    usedMismatch = true;
                }
            }
        }
        if (k != m) {
            return new int[0];
        }
        return ans;
    }
}