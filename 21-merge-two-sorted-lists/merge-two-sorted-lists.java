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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Create a dummy node to act as the start of our merged list
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // While both lists have nodes remaining
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1; // Splice in list1's node
                list1 = list1.next;   // Advance list1 pointer
            } else {
                current.next = list2; // Splice in list2's node
                list2 = list2.next;   // Advance list2 pointer
            }
            current = current.next;   // Advance the tail pointer
        }

        // Attach whatever is left over from whichever list isn't empty
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        // Return the actual head, which is right after the dummy node
        return dummy.next;
    }
}
