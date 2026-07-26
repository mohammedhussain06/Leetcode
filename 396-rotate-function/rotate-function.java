class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        long sum = 0;
        long curr = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            curr += (long) i * nums[i];
        }
        long ans = curr;
        for (int k = 1; k < n; k++) {
            curr = curr + sum - (long) n * nums[n - k];
            ans = Math.max(ans, curr);
        }
        return (int) ans;
    }
}