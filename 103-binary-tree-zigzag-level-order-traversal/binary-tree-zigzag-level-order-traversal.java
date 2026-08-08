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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean zigzag = true;
        while(!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for(int i = 0; i < size; i++) {
                TreeNode currNode = queue.poll();
                if(zigzag) {
                  list.addLast(currNode.val);
                } else {
                    list.addFirst(currNode.val);
                }
             if(currNode.left != null) {
                queue.add(currNode.left);
            }
             if(currNode.right != null) {
                queue.add(currNode.right);
            }
            }
            result.add(list);
            zigzag = !zigzag;

        }
        return result;
    }
}