class Solution {

    List<Integer> ans = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode node, int level) {
        if (node == null)
            return;

        if (level == ans.size())
            ans.add(node.val);

        dfs(node.right, level + 1);
        dfs(node.left, level + 1);
    }
}