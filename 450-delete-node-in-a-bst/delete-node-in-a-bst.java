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
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) {
            return null;
        }
        if(root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if(key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            // node with the key found
            //Case 1 and 2 : node has 0 or 1 child
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;
            // Case 3: Node has 2 children
            // Find the successor (smallest in the right subtree)
            TreeNode curr = root.right;
            while( curr.left != null) {
                curr = curr.left;
            }
            // Replace root's value with the successor's value
            root.val = curr.val;
            
            // Delete the successor from the right subtree
            root.right = deleteNode(root.right, root.val);
        }
        
        return root;
        
    }
}