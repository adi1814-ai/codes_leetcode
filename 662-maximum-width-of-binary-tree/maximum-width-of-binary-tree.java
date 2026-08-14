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
    // Helper class to store a node alongside its positional index
    private static class Pair {
        TreeNode node;
        int index;

        Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        int maxWidth = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();
            // Subtract minIndex to normalize indices at each level and prevent overflow
            int minIndex = queue.peek().index; 
            int first = 0, last = 0;

            for (int i = 0; i < size; i++) {
                Pair curr = queue.poll();
                int currIndex = curr.index - minIndex;

                if (i == 0) first = currIndex;
                if (i == size - 1) last = currIndex;

                if (curr.node.left != null) {
                    queue.offer(new Pair(curr.node.left, 2 * currIndex + 1));
                }
                if (curr.node.right != null) {
                    queue.offer(new Pair(curr.node.right, 2 * currIndex + 2));
                }
            }

            maxWidth = Math.max(maxWidth, last - first + 1);
        }

        return maxWidth;
    }
}