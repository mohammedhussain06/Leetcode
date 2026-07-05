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
      ListNode dummy = new ListNode(0);
        // 'tail' will track the last node of our newly merged list
        ListNode tail = dummy;
        
        // Loop as long as BOTH lists have nodes left to compare
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;   // Link the smaller node
                list1 = list1.next;  // Step forward in list1
            } else {
                tail.next = list2;   // Link the smaller node
                list2 = list2.next;  // Step forward in list2
            }
            tail = tail.next;        // Advance our merged list runner
        }
        
        // Cleanup: Attach the remaining sorted pieces of whichever list didn't empty out
        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
        }
        
        // Return the actual starting node, skipping our dummy anchor
        return dummy.next;  
    }
}