class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        // Already sorted
        boolean sorted = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;

        int ans = Integer.MAX_VALUE;

        // Case 1: nums is a rotation of [0, 1, 2, ..., n-1]
        int k = nums[0];
        boolean valid = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (k + i) % n) {
                valid = false;
                break;
            }
        }

        if (valid) {
            ans = Math.min(n - k, k + 2);
        }

        // Case 2: nums is a rotation of [n-1, n-2, ..., 0]
        k = (n - 1 - nums[0] + n) % n;
        valid = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (n - 1 - k - i + 2 * n) % n) {
                valid = false;
                break;
            }
        }

        if (valid) {
            ans = Math.min(ans, Math.min(k + 1, n - k + 1));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}