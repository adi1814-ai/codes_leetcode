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
    int maxSum = 0;

    // Helper class to store: [isBST, minVal, maxVal, sum]
    private class NodeInfo {
        boolean isBST;
        int minVal;
        int maxVal;
        int sum;

        NodeInfo(boolean isBST, int minVal, int maxVal, int sum) {
            this.isBST = isBST;
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.sum = sum;
        }
    }
    public int maxSumBST(TreeNode root) {
        maxSum = 0;
        postOrder(root);
        return maxSum;
    }

    private NodeInfo postOrder(TreeNode node) {
        // Base case: An empty tree is a valid BST with a sum of 0
        if (node == null) {
            return new NodeInfo(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        // Recursively get info from left and right subtrees
        NodeInfo left = postOrder(node.left);
        NodeInfo right = postOrder(node.right);

        // Check if the current node forms a valid BST
        if (left.isBST && right.isBST && node.val > left.maxVal && node.val < right.minVal) {
            int currentSum = node.val + left.sum + right.sum;
            maxSum = Math.max(maxSum, currentSum);

            int currentMin = Math.min(node.val, left.minVal);
            int currentMax = Math.max(node.val, right.maxVal);

            return new NodeInfo(true, currentMin, currentMax, currentSum);
        }

        // If it's not a BST, mark isBST as false
        return new NodeInfo(false, 0, 0, 0);
    }
}
