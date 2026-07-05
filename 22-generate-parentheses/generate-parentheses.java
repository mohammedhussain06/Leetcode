import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        // Kick off our recursive generator starting with 0 open and 0 close brackets
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {

        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }

        if (open < max) {
            current.append('('); // Make choice
            backtrack(result, current, open + 1, close, max); // Explore
            current.deleteCharAt(current.length() - 1); // Backtrack (Undo choice)
        }

        if (close < open) {
            current.append(')'); // Make choice
            backtrack(result, current, open, close + 1, max); // Explore
            current.deleteCharAt(current.length() - 1); // Backtrack (Undo choice)
        }
    }
}