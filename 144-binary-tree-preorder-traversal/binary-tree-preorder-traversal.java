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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>(); // to store the output
        //base case 
        if(root == null) {return result ;}

        // preorder traversal  --> root node -- left node-- right node

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root); // to initiate the traversal , root node will be pushed first    
        //   |       |
    //       |       |
  //         |       |
  //         | root  |



        while(!stack.isEmpty()) {
            TreeNode node = stack.pop(); // popped the root node first
            result.add(node.val); // adding it in resultArrayList

            if(node.right != null) {             // |       |
                stack.push(node.right);         //  |       |
            }                                  //   | left  |  // top of the stack(will pop next after root is popped)
            if(node.left != null) {           //    | right | // bottom of the stack
                stack.push(node.left);
            }
        }
        return result;
    }
}