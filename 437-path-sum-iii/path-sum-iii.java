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
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0L, 1);
        return dfs(root, 0L, targetSum, prefixSumMap);
    }

    private int dfs(TreeNode node, long currentSum , int targetSum, Map<Long, Integer>prefixSumMap) {
            if(node == null) {
                return 0;
            }
            currentSum += node.val;

            int count = prefixSumMap.getOrDefault(currentSum - targetSum, 0);

            // Update prefix sum count in map for child nodes
        prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);
        count += dfs(node.left, currentSum, targetSum, prefixSumMap);
        count += dfs(node.right, currentSum, targetSum, prefixSumMap); 
        //backtract
        prefixSumMap.put(currentSum, prefixSumMap.get(currentSum) - 1);
        
        return count;
    }
}