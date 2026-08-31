class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1;
        int prevCritical = -1;
        int minDistance = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;
        int maxDistance = -1;

        while (curr.next != null) {
            ListNode next = curr.next;

            // Check if curr is a local maximum or minimum
            if ((curr.val > prev.val && curr.val > next.val) ||
                (curr.val < prev.val && curr.val < next.val)) {

                // First critical point
                if (first == -1) {
                    first = index;
                } else {
                    // Distance from previous critical point
                    minDistance = Math.min(minDistance, index - prevCritical);

                    // Distance from first critical point
                    maxDistance = index - first;
                }

                prevCritical = index;
            }

            prev = curr;
            curr = next;
            index++;
        }

        // Fewer than two critical points
        if (first == -1 || prevCritical == first) {
            return new int[]{-1, -1};
        }

        return new int[]{minDistance, maxDistance};
    }
}