class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        backtrack(nums, currentPath, result);
        return result;
    }
    private void backtrack(int[] nums, List<Integer> currentPath, List<List<Integer>> result) {
        if (currentPath.size() == nums.length) {
            result.add(new ArrayList<>(currentPath));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (currentPath.contains(nums[i])) {
                continue;
            }
            currentPath.add(nums[i]);
            backtrack(nums, currentPath, result);
            currentPath.remove(currentPath.size() - 1);
        }
    }
}