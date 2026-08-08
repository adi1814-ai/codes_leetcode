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
    int maxSum;
    private int solve(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = solve(root.left);
        int right = solve(root.right);

        int bottomAnswer = left + right + root.val;
        int anyone = Math.max(left , right) +root.val;
        int onlyRoot = root.val;

        int currentMax = Math.max(Math.max(bottomAnswer, anyone), Math.max(onlyRoot, maxSum));
        maxSum = Math.max(maxSum, currentMax);

        return Math.max(anyone , onlyRoot);
    }
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        solve(root);

        return maxSum;
    }
}