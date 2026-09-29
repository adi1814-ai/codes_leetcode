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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Check if current node is part of a duplicate sequence
        if (head.val == head.next.val) {
            // Skip all nodes with the duplicate value
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            // Exclude the last duplicate node as well by moving to head.next
            return deleteDuplicates(head.next);
        } else {
            // No duplicate, recursively solve for the rest of the list
            head.next = deleteDuplicates(head.next);
            return head;
        }
    }
}