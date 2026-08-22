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

    private int postIdx;
    private int search(int [] inorder, int left , int right, int val) {
        for(int i = left; i <= right; i++) {
               if(inorder[i] == val) {
                return i;
               }
             }
        return -1;
    }
    private TreeNode helper(int[] postorder, int[] inorder, int leftB, int rightB) {

         if(leftB > rightB) {
            return null;
         }

        TreeNode root = new TreeNode(postorder[postIdx--]);
        

       int  inIdx = search(inorder , leftB, rightB, root.val );
       

        root.right = helper(postorder, inorder,  inIdx + 1, rightB);
        root.left = helper(postorder, inorder,  leftB, inIdx - 1);

        return root;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
       // postorder traversal -- left, right , Root
       // inorder traversal -- left, root , right
       postIdx= postorder.length - 1;
       return helper(postorder, inorder,  0, inorder.length-1);
    }
}