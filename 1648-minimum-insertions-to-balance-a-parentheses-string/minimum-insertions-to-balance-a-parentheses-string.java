
class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                // If the next character is ')', use it
                // as the second closing parenthesis.
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    // Insert the missing second ')'
                    insertions++;
                }

                // Match the closing pair with an opening '('
                if (open > 0) {
                    open--;
                } else {
                    // Insert a missing opening '('
                    insertions++;
                }
            }
        }

        // Every unmatched '(' needs two closing ')'
        insertions += open * 2;

        return insertions;
    }
}
