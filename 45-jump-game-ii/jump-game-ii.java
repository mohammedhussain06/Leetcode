class Solution {
    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int jump = 0;
        int currentWindow = 0;
        int farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == currentWindow) {
                jump++;
                currentWindow = farthest;
            }
        }
        return jump;
    }
}