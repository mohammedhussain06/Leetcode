import java.util.List;
import java.util.Arrays;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) { // Fixed name and type
        int n = grid.size();          // For Lists, use .size() instead of .length
        int m = grid.get(0).size();   // Get the inner row list length
        
        int[][] visitedHealth = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visitedHealth[i], -1);
        }
        
        return backtrack(0, 0, n, m, grid, health, visitedHealth);
    }

    private boolean backtrack(int i, int j, int n, int m, List<List<Integer>> grid, int currentHealth, int[][] visitedHealth) {
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return false;
        }

        // For Lists, replace grid[i][j] with grid.get(i).get(j)
        if (grid.get(i).get(j) == 1) {
            currentHealth--;
        }

        if (currentHealth < 1) {
            return false;
        }

        if (i == n - 1 && j == m - 1) {
            return true;
        }

        if (currentHealth <= visitedHealth[i][j]) {
            return false;
        }
        
        visitedHealth[i][j] = currentHealth;

        boolean down  = backtrack(i + 1, j, n, m, grid, currentHealth, visitedHealth);
        boolean right = backtrack(i, j + 1, n, m, grid, currentHealth, visitedHealth);
        boolean up    = backtrack(i - 1, j, n, m, grid, currentHealth, visitedHealth);
        boolean left  = backtrack(i, j - 1, n, m, grid, currentHealth, visitedHealth);

        return down || right || up || left;
    }
}