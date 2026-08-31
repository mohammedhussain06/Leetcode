class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int minDistance = Integer.MAX_VALUE;
        int maxDistance = -1;
        int firstCritical = -1;
        int prevCritical = -1;
        int position = 1;
        ListNode prev = head;
        ListNode curr = head.next;
        while (curr.next != null) {
            ListNode next = curr.next;
            boolean isCritical =
                    (curr.val > prev.val && curr.val > next.val) ||
                    (curr.val < prev.val && curr.val < next.val);
            if (isCritical) {
                if (firstCritical == -1) {
                    firstCritical = position;
                } else {
                    minDistance = Math.min(
                            minDistance,
                            position - prevCritical
                    );
                    maxDistance = position - firstCritical;
                }
                prevCritical = position;
            }
            prev = curr;
            curr = next;
            position++;
        }
        if (minDistance == Integer.MAX_VALUE) {
            return new int[]{-1, -1};
        }
        return new int[]{minDistance, maxDistance};
    }
}