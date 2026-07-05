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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        // Step 1: Give 'fast' an 'n' step head start
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // Step 2: Move both forward until 'fast' runs off the edge of the list
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        // Step 3: Skip over the target node to break its link!
        slow.next = slow.next.next;
        
        // Return the actual head (which is safely attached to dummy.next)
        return dummy.next;
    }
}