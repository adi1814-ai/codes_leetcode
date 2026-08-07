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
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1 ;
       
    }
    private int checkHeight(TreeNode node) {
        if(node == null) {return 0;}

        int leftHeight = checkHeight(node.left);
        int rightHeight = checkHeight(node.right);
        if(leftHeight == -1 || rightHeight == -1) { // subtrees already unbalanced
            return -1;
        }
        if(Math.abs(leftHeight - rightHeight) > 1) { // current node is unbalanced
            return -1;
        }
        return Math.max(leftHeight, rightHeight) +1;
    }
}