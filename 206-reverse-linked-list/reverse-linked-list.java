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
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            ListNode nextNode = curr.next; // 1. Save the next node
            curr.next = prev;              // 2. Reverse the link
            prev = curr;                   // 3. Move prev forward
            curr = nextNode;               // 4. Move curr forward
        }
        
        return prev;
    }
}