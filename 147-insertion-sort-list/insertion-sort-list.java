/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        // Edge case: an empty list or a list with only one element is already sorted
        if (head == null || head.next == null) {
            return head;
        }

        // Dummy node acts as the start boundary of our new sorted list
        ListNode dummy = new ListNode(0);
        ListNode curr = head; // Pointer to traverse the unsorted input list

        while (curr != null) {
            // Securely save the next unsorted node before breaking links
            ListNode nextToProcess = curr.next;

            // Start scanning the sorted list from the beginning (dummy)
            ListNode prev = dummy;
            
            // Move prev forward until we find the node whose value is larger than curr.val
            while (prev.next != null && prev.next.val < curr.val) {
                prev = prev.next;
            }

            // Insert 'curr' between 'prev' and 'prev.next'
            curr.next = prev.next;
            prev.next = curr;

            // Advance to the next unsorted element we saved earlier
            curr = nextToProcess;
        }

        // The real sorted head is sitting right after the dummy node
        return dummy.next;
    }
}