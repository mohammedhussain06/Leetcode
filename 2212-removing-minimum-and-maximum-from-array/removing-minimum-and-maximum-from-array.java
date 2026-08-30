class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIndex = 0;
        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        int left = Math.min(minIndex, maxIndex);
        int right = Math.max(minIndex, maxIndex);
        int removeFromFront = right + 1;
        int removeFromBack = n - left;
        int removeBothEnds = (left + 1) + (n - right);
        return Math.min(
            removeFromFront,
            Math.min(removeFromBack, removeBothEnds)
        );
    }
}