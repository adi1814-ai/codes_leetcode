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
    public List<List<Integer>> verticalTraversal(TreeNode root) {
  
        // TreeMap to handle the sorting: Column -> Row -> Min-Heap of Values
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        
        // Your two queues!
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> colQueue = new LinkedList<>();
        
        if (root != null) {
            nodeQueue.add(root);
            colQueue.add(0); // Root is at column 0
        }

        int row = 0; // Track the depth level explicitly

        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size(); // Number of nodes at the current row
            
            // Process the current level entirely before moving to the next row
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.poll();
                int col = colQueue.poll();

                // Initialize maps/queues if they don't exist yet
                if (!map.containsKey(col)) {
                    map.put(col, new TreeMap<>());
                }
                if (!map.get(col).containsKey(row)) {
                    map.get(col).put(row, new PriorityQueue<>());
                }
                
                // Add the current node's value into the PriorityQueue
                map.get(col).get(row).offer(node.val);

                // Add children to queues with their updated columns
                if (node.left != null) {
                    nodeQueue.add(node.left);
                    colQueue.add(col - 1);
                }
                if (node.right != null) {
                    nodeQueue.add(node.right);
                    colQueue.add(col + 1);
                }
            }
            
            // Increment row after finishing all nodes at the current level
            row++;
        }

        // Construct the final result
        List<List<Integer>> result = new ArrayList<>();
        
        for (TreeMap<Integer, PriorityQueue<Integer>> colMap : map.values()) {
            List<Integer> columnList = new ArrayList<>();
            for (PriorityQueue<Integer> pq : colMap.values()) {
                while (!pq.isEmpty()) {
                    columnList.add(pq.poll());
                }
            }
            result.add(columnList);
        }

        return result;
    }
}
     