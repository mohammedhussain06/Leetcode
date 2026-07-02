class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // Step 1: Skip non-alphanumeric characters from the left side
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            
            // Step 2: Skip non-alphanumeric characters from the right side
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // Step 3: Compare characters by converting them to lowercase on the fly
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false; // Mismatch found, not a palindrome
            }

            // Step 4: Move both pointers closer to the center
            left++;
            right--;
        }

        return true; // Pointers met without any mismatch
    }
}