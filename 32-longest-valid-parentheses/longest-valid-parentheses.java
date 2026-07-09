class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int maxLength = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                left ++;
            } else {
                right++;
            }
            if (right == left) {
                maxLength = Math.max(maxLength, 2 * right);
            }
            else if (right > left) {
                left = 0;
                right = 0;
            }
        }
        left = 0;
        right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == '(') {
                left ++;
            } else {
                right++;
            }
            if (right == left) {
                maxLength = Math.max(maxLength, 2 * left);
            }
            else if (left > right) {
                left = 0;
                right = 0;
            }
        }
        return maxLength;
    }
}