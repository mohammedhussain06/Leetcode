import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, currentPath, result);    
        return result;
    }
    private void backtrack(int[] nums, boolean[] used, List<Integer> currentPath, List<List<Integer>> result) {
        if (currentPath.size() == nums.length) {
            result.add(new ArrayList<>(currentPath));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            currentPath.add(nums[i]);
            backtrack(nums, used, currentPath, result);
            used[i] = false;
            currentPath.remove(currentPath.size() - 1);
        }
    }
}