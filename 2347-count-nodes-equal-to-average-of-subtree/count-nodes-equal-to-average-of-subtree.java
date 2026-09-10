class Solution {
    int count = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return count;
    }

    // returns {sum, number of nodes}
    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int sum = node.val + left[0] + right[0];
        int nodes = 1 + left[1] + right[1];

        int average = sum / nodes;

        if (average == node.val) {
            count++;
        }

        return new int[]{sum, nodes};
    }
}