import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Base case: empty string has a length of 0
        if (s == null || s.length() == 0) {
            return 0;
        }

        // Key: Character, Value: The most recent index where we saw it
        HashMap<Character, Integer> map = new HashMap<>();
        
        int maxLength = 0;
        int left = 0; // Left boundary of our sliding window

        // Right boundary of our sliding window moves across the string
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            // If the character is a duplicate and is inside our current window
            if (map.containsKey(ch) && map.get(ch) >= left) {
                // Shrink the window by jumping 'left' past the old character's position
                left = map.get(ch) + 1;
            }

            // Record or update the character's newest index
            map.put(ch, right);

            // Calculate the current window size (right - left + 1) and update maxLength
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}