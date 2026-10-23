class Solution {
    public int sortPermutation(int[] nums) {
        int ans = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                ans &= nums[i];
            }
        }
        // If ans remains -1, it means the array was already sorted.
        return ans == -1 ? 0 : ans;
    }
}