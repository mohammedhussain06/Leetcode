
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];

        long k = (long) k1 + k2;
        long total = 0;
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        if (k >= total) {
            return 0L;
        }

        // Find the minimum threshold x
        // such that reducing all differences to x takes <= k operations.
        int low = 0, high = maxDiff;

        while (low < high) {
            int mid = low + (high - low) / 2;
            long operations = 0;

            for (int d : diff) {
                if (d > mid) {
                    operations += d - mid;
                }
            }

            if (operations <= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        int x = low;
        long operations = 0;
        long answer = 0;

        for (int d : diff) {
            if (d > x) {
                operations += d - x;
                answer += (long) x * x;
            } else {
                answer += (long) d * d;
            }
        }

        // Use leftover operations to reduce some x values to x - 1.
        long remaining = k - operations;
        answer -= remaining * (2L * x - 1);

        return answer;
    }
}
