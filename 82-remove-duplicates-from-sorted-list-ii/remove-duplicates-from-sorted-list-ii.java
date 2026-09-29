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
        // Dummy node pointing to head to easily handle head removal
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null) {
            // Check if curr is the start of a duplicate sequence
            if (curr.next != null && curr.val == curr.next.val) {
                // Move curr to the end of the duplicate sequence
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }
                // Link prev past all duplicates
                prev.next = curr.next;
            } else {
                // No duplicate found for curr.val, safe to move prev forward
                prev = prev.next;
            }
            // Move curr to the next unexamined node
            curr = curr.next;
        }

        return dummy.next;
    }
}