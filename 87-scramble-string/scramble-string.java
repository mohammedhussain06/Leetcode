import java.util.*;

class Solution {

    private Map<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {

        if (s1.equals(s2))
            return true;

        if (s1.length() != s2.length())
            return false;

        String key = s1 + "#" + s2;

        if (memo.containsKey(key))
            return memo.get(key);

        // Prune: Check if both strings have the same character frequencies
        int[] freq = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            freq[s1.charAt(i) - 'a']++;
            freq[s2.charAt(i) - 'a']--;
        }

        for (int count : freq) {
            if (count != 0) {
                memo.put(key, false);
                return false;
            }
        }

        int n = s1.length();

        for (int i = 1; i < n; i++) {

            // Case 1: No Swap
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                isScramble(s1.substring(i), s2.substring(i))) {

                memo.put(key, true);
                return true;
            }

            // Case 2: Swap
            if (isScramble(s1.substring(0, i), s2.substring(n - i)) &&
                isScramble(s1.substring(i), s2.substring(0, n - i))) {

                memo.put(key, true);
                return true;
            }
        }

        memo.put(key, false);
        return false;
    }
}