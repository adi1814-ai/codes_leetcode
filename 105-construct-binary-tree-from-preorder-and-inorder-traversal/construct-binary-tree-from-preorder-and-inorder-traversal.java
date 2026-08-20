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
  private int preIdx = 0;
    private int search(int [] inorder, int left , int right, int val) {
        for(int i = left; i <= right; i++) {
               if(inorder[i] == val) {
                return i;
               }
             }
        return -1;
    }
    private TreeNode helper(int[] preorder, int[] inorder, int leftB, int rightB) {

         if(leftB > rightB) {
            return null;
         }

        TreeNode root = new TreeNode(preorder[preIdx++]);
        

       int  inIdx = search(inorder , leftB, rightB, root.val );
       

        root.left = helper(preorder, inorder,  leftB, inIdx - 1);
        root.right = helper(preorder, inorder,   inIdx + 1, rightB);

        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        
       return helper(preorder, inorder,  0, inorder.length-1);
    }
}