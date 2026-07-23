/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode curr = head; // a pointer starting at head

        // Filling the stack
        while(curr != null) {
            stack.push(curr.val);
            curr = curr.next; // moving pointer to the next node
        }

        // Comparing Values
        curr = head; // reset the pointer
        while(curr != null) {
            int top = stack.pop(); // poping the top vale from the stack
           if( curr.val != top) { // comparing the popped value with the curr(head)
            return false;
           }
            curr = curr.next;
           
        }
        return true;
    }
}