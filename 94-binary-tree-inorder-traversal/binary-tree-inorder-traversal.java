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
    public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    // base case  
    if(root == null) { return result; }

    Stack<TreeNode> stack = new Stack<>();

    TreeNode currentNode = root; // it is a pointer to traverse the tree's nodes
    while(currentNode != null || !stack.isEmpty()) {
        while( currentNode != null) {
            stack.push(currentNode); // pushing the current node onto the stack before moving down
            currentNode = currentNode.left; // moves this pointer to the left child
        }
            currentNode = stack.pop(); //Once currentNode hits null (end of left path), we pop the top node from stack. This popped node is the deepest unprocessed Root/Parent node.
            result.add(currentNode.val);
            currentNode = currentNode.right;
        
    }
      return result;
    }
}