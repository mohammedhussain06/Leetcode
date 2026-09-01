import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startR = -1, startC = -1;
        int litterCount = 0;
        int[][] litterIdx = new int[m][n];
        for (int[] row : litterIdx) {
            Arrays.fill(row, -1);
        }
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    litterIdx[r][c] = litterCount++;
                }
            }
        }
        
        // If there is no litter to collect
        if (litterCount == 0) return 0;
        
        int targetMask = (1 << litterCount) - 1;
        
        // maxEnergy[r][c][mask] stores the maximum remaining energy seen for state (r, c, mask)
        int[][][] maxEnergy = new int[m][n][1 << litterCount];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                Arrays.fill(maxEnergy[r][c], -1);
            }
        }
        
        // Queue stores: [r, c, mask, remaining_energy]
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC, 0, energy});
        maxEnergy[startR][startC][0] = energy;
        
        int moves = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                int mask = curr[2];
                int remEnergy = curr[3];
                
                if (mask == targetMask) {
                    return moves;
                }
                
                if (remEnergy <= 0) continue;
                
                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    char cell = classroom[nr].charAt(nc);
                    if (cell == 'X') continue;
                    
                    int nextEnergy = remEnergy - 1;
                    int nextMask = mask;
                    
                    if (cell == 'R') {
                        nextEnergy = energy;
                    } else if (cell == 'L') {
                        int idx = litterIdx[nr][nc];
                        nextMask |= (1 << idx);
                    }
                    
                    if (nextEnergy > maxEnergy[nr][nc][nextMask]) {
                        maxEnergy[nr][nc][nextMask] = nextEnergy;
                        queue.offer(new int[]{nr, nc, nextMask, nextEnergy});
                    }
                }
            }
            moves++;
        }
        
        return -1;
    }
}