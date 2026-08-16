/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> currentPath = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, targetSum, currentPath, result);
        return result;
    }
    private void dfs(TreeNode root, int targetSum , List<Integer> currentPath, List<List<Integer>> result) {
        if(root == null) {
            return;
        }
        currentPath.add(root.val);

        if(root.left == null && root.right == null && root.val == targetSum) {
            result.add(new ArrayList<>(currentPath));
        } else {
        int newTarget = targetSum - root.val;
        dfs(root.left, newTarget, currentPath, result);
        dfs(root.right, newTarget, currentPath, result);
        }

        //Backtrack 
        currentPath.remove(currentPath.size() - 1);
      
    }
}