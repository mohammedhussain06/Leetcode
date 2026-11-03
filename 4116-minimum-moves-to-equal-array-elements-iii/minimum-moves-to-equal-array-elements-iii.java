
class Solution {
    public int minMoves(int[] nums) {
        int max = nums[0];

        // Find the maximum element
        for (int num : nums) {
            max = Math.max(max, num);
        }

        int moves = 0;

        // Calculate total increments required
        for (int num : nums) {
            moves += max - num;
        }

        return moves;
    }
}
