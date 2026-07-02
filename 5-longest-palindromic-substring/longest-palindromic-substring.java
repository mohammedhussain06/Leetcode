class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);     // Odd length center
            int len2 = expandAroundCenter(s, i, i + 1); // Even length center
            
            int maxLen = Math.max(len1, len2);
            
            if (maxLen > (end - start)) {
                start = i - (maxLen - 1) / 2;
                end = i + maxLen / 2; // Fixed formula
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (true) {
            if (left < 0 || right >= s.length() || s.charAt(left) != s.charAt(right)) {
                break;
            }
            left--; 
            right++;
        }
        return right - left - 1; // Fixed placement inside the method scope
    }
}