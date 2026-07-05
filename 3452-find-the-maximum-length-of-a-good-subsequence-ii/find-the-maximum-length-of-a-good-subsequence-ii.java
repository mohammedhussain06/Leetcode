class Solution {
    public int maximumLength(int[] nums, int k) {
        Map<Integer, int[]> dp = new HashMap<>();

        int[] best = new int[k + 1];
        int[] secondBest = new int[k + 1];
        int[] bestVal = new int[k + 1];

        int ans = 0;

        for (int x : nums) {
            int[] cur = dp.getOrDefault(x, new int[k + 1]);

            for (int j = k; j >= 0; j--) {
                // Extend subsequence ending with same value
                cur[j]++;

                // Extend subsequence ending with different value
                if (j > 0) {
                    int candidate;
                    if (bestVal[j - 1] != x) {
                        candidate = best[j - 1] + 1;
                    } else {
                        candidate = secondBest[j - 1] + 1;
                    }
                    cur[j] = Math.max(cur[j], candidate);
                }

                // Update best and secondBest
                if (cur[j] > best[j]) {
                    if (bestVal[j] != x) {
                        secondBest[j] = best[j];
                    }
                    best[j] = cur[j];
                    bestVal[j] = x;
                } else if (bestVal[j] != x && cur[j] > secondBest[j]) {
                    secondBest[j] = cur[j];
                }

                ans = Math.max(ans, cur[j]);
            }

            dp.put(x, cur);
        }

        return ans;
    }
}