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
    public ListNode removeNodes(ListNode head) {
        ListNode reverseHead = reverseList(head); // reversed the linked list

        ListNode curr = reverseHead;
        int max = curr.val;

        while(curr.next != null) {
            if(curr.next.val < max) {
                curr.next = curr.next.next; // Delete curr.next by skipping over it
            } else {
                curr = curr.next;
                max = curr.val;
            }
        }
        return reverseList(reverseHead);
    }
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }
}