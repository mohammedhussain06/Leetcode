import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char digit = board[r][c];
                if (digit != '.') {
                    String rowKey = digit + " in row " + r;
                    String colKey = digit + " in col " + c;
                    String blockKey = digit + " in block " + (r / 3) + "-" + (c / 3);
                    if (!seen.add(rowKey) || !seen.add(colKey) || !seen.add(blockKey)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}