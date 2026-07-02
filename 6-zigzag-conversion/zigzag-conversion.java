import java.util.ArrayList;
import java.util.List;

class Solution {
    public String convert(String s, int numRows) {
        // Edge Case: If there's only 1 row, or the string is too short,
        // no zigzagging can happen. Return the string as-is.
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        // Create our buckets (one StringBuilder for each row)
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        int currRow = 0;
        int direction = 1; // 1 means moving down, -1 means moving up

        // Loop through the input string sequentially
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            // Append the character to our current oscillating row bucket
            rows.get(currRow).append(ch);

            // If we hit the top row (0), we must change direction to go down (1)
            // If we hit the bottom row (numRows - 1), we must change direction to go up (-1)
            if (currRow == 0) {
                direction = 1;
            } else if (currRow == numRows - 1) {
                direction = -1;
            }

            // Move the index to the next position in the oscillation
            currRow += direction;
        }

        // Combine all the row buckets straight across, from row 0 to numRows-1
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}