/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    private List<Integer> result = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        findTarget(root, target, k);
        return result;
    }

    private int findTarget(TreeNode root, TreeNode target, int k) {
        if (root == null) 
           return -1;
        if (root == target) {
            collect(root, k);
            return 0;
        }

        int leftDist = findTarget(root.left, target, k);
        if (leftDist != -1) {
            if (leftDist + 1 == k) {
                result.add(root.val);
            }
            else {
                collect(root.right, k - leftDist - 2);
            }
            return leftDist + 1;
        }

        int rightDist = findTarget(root.right, target, k);
        if (rightDist != -1) {
            if (rightDist + 1 == k){
                 result.add(root.val);
            }
            else { collect(root.left, k - rightDist - 2);
            }
            return rightDist + 1;
        }

        return -1;
    }

    private void collect(TreeNode node, int distance) {
        if (node == null || distance < 0)
         return;
        if (distance == 0) {
            result.add(node.val);
            return;
        }
        collect(node.left, distance - 1);
        collect(node.right, distance - 1);
    }
}