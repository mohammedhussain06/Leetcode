class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] ans = new int[m * n];
        int row = 0;
        int col = 0;
        int dir = 1; 
        for (int i = 0; i < m * n; i++) {
            ans[i] = mat[row][col];
            if (dir == 1) {
                row--;
                col++;
                if (col == n) {
                    col--;
                    row += 2;
                    dir = -1;
                } else if (row < 0) {
                    row = 0;
                    dir = -1;
                }
            } else {
                row++;
                col--;
                if (row == m) {
                    row--;
                    col += 2;
                    dir = 1;
                } else if (col < 0) {
                    col = 0;
                    dir = 1;
                }
            }
        }
        return ans;
    }
}