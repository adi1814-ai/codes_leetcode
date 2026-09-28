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
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k==0 ) {
            return head;
        }
        ListNode tail = head;
        int length =1;
        while(tail.next != null) {
            tail = tail.next;
            length++;
        }
        int rotation = k% length;
        if(rotation == 0){ // break the cycle
            return head;
        }
        tail.next = head; // form a circular linked list

        // 4. Find the new tail: (length - effectiveK) steps from head
        int stepsToNewTail = length - rotation;
        ListNode newTail = head;
        for (int i = 1; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        // 5. Break the circular connection and set new head
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}