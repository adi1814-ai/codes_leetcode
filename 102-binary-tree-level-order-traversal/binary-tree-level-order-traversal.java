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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
   List<Integer> currentLevel = new ArrayList<>();
        while(!q.isEmpty()) {
           
            TreeNode currnode = q.remove();
            if(currnode == null) {
                result.add(new ArrayList<>(currentLevel));
                currentLevel.clear();
                if(q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
             } else {
                currentLevel.add(currnode.val) ;
                if(currnode.left != null) {
                    q.add(currnode.left);
                } 
                if(currnode.right != null) {
                    q.add(currnode.right);
                }
             }
           
         }
        
        return result;
    }
}