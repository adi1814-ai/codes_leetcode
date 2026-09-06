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
    public boolean findTarget(TreeNode root, int k) {
        
       // ABOUT :-  int complement = k - node.val;

       // if we are currently visiting a node with a value of node.val, we are trying to find if there is another number already stored in our HashSet that satisfies this equation:


       //When our DFS visits a node, it calculates what its pairing partner (complement) needs to be. For example, if target $k = 9$ and the current node value is $3$, the complement is $9 - 3 = 6$.
       
       //It checks: "Have we already seen a node with the value 6?" (set.contains(complement)).

       //If yes: We found our pair! We can immediately return true.If no: We haven't seen $6$ yet. So, we add the current node's value ($3$) to our HashSet so that future nodes can check against it, and we continue searching.
       
     
     //  ------TIME COMPLEXITY SAVIOUR------

       //This clever approach saves us from having to compare every single node against every other node (which would take $O(N^2)$ time). Instead, by looking for the complement, we solve the problem in a fast $O(N)$ time complexity!

       HashSet<Integer> set = new HashSet<>();
        return dfs(root, k, set);
    }
    private boolean dfs(TreeNode node, int k, HashSet<Integer> set) {
        if (node == null) {
            return false;
        }
        
        // Check if the complement (k - current node value) exists in our set
        int complement = k - node.val;
        if (set.contains(complement)) {
            return true;
        }
        
        // Add the current node's value to the set
        set.add(node.val);
        
        // Recursively search the left and right subtrees
        return dfs(node.left, k, set) || dfs(node.right, k, set);
    }
}