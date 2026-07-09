import java.util.ArrayList;
import java.util.List;
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        backtrack(candidates, target, 0, currentPath, result);     
        return result;
    } 
    private void backtrack(int[] candidates, int remainingTarget, int startIdx, 
                           List<Integer> currentPath, List<List<Integer>> result) {
        if (remainingTarget == 0) {
            result.add(new ArrayList<>(currentPath)); 
            return;
        }
        if (remainingTarget < 0) {
            return;
        }
        for (int i = startIdx; i < candidates.length; i++) {
            currentPath.add(candidates[i]);
            backtrack(candidates, remainingTarget - candidates[i], i, currentPath, result);
            currentPath.remove(currentPath.size() - 1);
        }
    }
}