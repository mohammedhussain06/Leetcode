class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] charCounts = new int[26];

        // Step 1: Fill frequencies using string s
        for (int i = 0; i < s.length(); i++) {
            charCounts[s.charAt(i) - 'a']++;
        }

        // Step 2: Decrement using string t and check instantly
        for (int i = 0; i < t.length(); i++) {
            int idx = t.charAt(i) - 'a';
            charCounts[idx]--;

            // If a count drops below 0, it means string t has 
            // MORE of this character than string s did.
            if (charCounts[idx] < 0) {
                return false;
            }
        }

        return true;
    }
}