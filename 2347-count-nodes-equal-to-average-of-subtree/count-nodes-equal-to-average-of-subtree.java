class Solution {
    int result = 0;
    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return result;
    }
    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int sum = node.val + left[0] + right[0];
        int count = 1 + left[1] + right[1];
        if (node.val == sum / count) {
            result++;
        }
        return new int[]{sum, count};
    }
}