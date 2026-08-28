import java.util.Arrays;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }

        // A palindrome is only possible if odd count matches the parity of n
        if ((n % 2 == 0 && oddCount != 0) || (n % 2 != 0 && oddCount != 1)) {
            return "";
        }

        int m = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        // Find how many prefix characters of target[0..m-1] can be matched
        int[] tempCount = halfCount.clone();
        int maxPrefix = 0;
        while (maxPrefix < m) {
            int c = target.charAt(maxPrefix) - 'a';
            if (tempCount[c] > 0) {
                tempCount[c]--;
                maxPrefix++;
            } else {
                break;
            }
        }

        // Scenario 1: First half matches target[0..m-1] exactly
        if (maxPrefix == m) {
            char[] p = new char[n];
            for (int i = 0; i < m; i++) {
                p[i] = target.charAt(i);
                p[n - 1 - i] = target.charAt(i);
            }
            if (n % 2 != 0) {
                p[m] = midChar;
            }
            String cand = new String(p);
            if (cand.compareTo(target) > 0) {
                return cand;
            }
        }

        // Scenario 2: Diverge at the largest possible index i < m
        for (int i = Math.min(m - 1, maxPrefix); i >= 0; i--) {
            // Count remaining characters after matching target[0..i-1]
            int[] remCount = halfCount.clone();
            for (int k = 0; k < i; k++) {
                remCount[target.charAt(k) - 'a']--;
            }

            int targetChar = target.charAt(i) - 'a';
            // Find the smallest character > target[i]
            for (int c = targetChar + 1; c < 26; c++) {
                if (remCount[c] > 0) {
                    char[] p = new char[n];
                    
                    // Match prefix up to i - 1
                    for (int k = 0; k < i; k++) {
                        p[k] = target.charAt(k);
                    }
                    
                    // Set divergence character at i
                    p[i] = (char) ('a' + c);
                    remCount[c]--;

                    // Fill positions i + 1 to m - 1 greedily with smallest chars
                    int fillIdx = i + 1;
                    for (int ch = 0; ch < 26; ch++) {
                        while (remCount[ch] > 0) {
                            p[fillIdx++] = (char) ('a' + ch);
                            remCount[ch]--;
                        }
                    }

                    // Set middle character if odd length
                    if (n % 2 != 0) {
                        p[m] = midChar;
                    }

                    // Mirror to complete the second half
                    for (int k = 0; k < m; k++) {
                        p[n - 1 - k] = p[k];
                    }

                    return new String(p);
                }
            }
        }

        return "";
    }
}