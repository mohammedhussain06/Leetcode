class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int howFar = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > howFar) {
                return false;
            }
            howFar = Math.max(howFar, i + nums[i]);
            if (howFar >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}