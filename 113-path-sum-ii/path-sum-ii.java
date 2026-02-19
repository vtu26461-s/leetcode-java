import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

class Solution {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, targetSum, path, result);
        return result;
    }

    void dfs(TreeNode node, int sum, List<Integer> path,
             List<List<Integer>> result) {

        if (node == null)
            return;

        path.add(node.val);

        if (node.left == null && node.right == null && sum == node.val) {
            result.add(new ArrayList<>(path));
        } else {
            dfs(node.left, sum - node.val, path, result);
            dfs(node.right, sum - node.val, path, result);
        }

        path.remove(path.size() - 1); // backtrack
    }
}
