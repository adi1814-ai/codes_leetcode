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
    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = null;
    public void inorder(TreeNode root) {
        if (root == null) return;
    
          inorder(root.left);
    
    // Find violation
    if (prev != null && root.val < prev.val) {
        if (first == null) {
            first = prev;      // First violation: 'prev' is always the incorrect larger node
            second = root;     // Tentatively set 'second' to current
        } else {
            second = root;     // Second violation: update 'second' to current smaller node
        }
    }
    prev = root;
    
   inorder(root.right);
}
    public void recoverTree(TreeNode root) {
        
        first = null;
        second = null;
        prev = null;
        
        // 1. Perform inorder traversal to find the two misplaced nodes
        inorder(root);
        
        // 2. Swap their values to recover the BST
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }
}