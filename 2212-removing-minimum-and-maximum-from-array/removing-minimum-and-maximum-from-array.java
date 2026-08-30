class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find indices of minimum and maximum
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Make sure first = smaller index, second = larger index
        int first = Math.min(minIndex, maxIndex);
        int second = Math.max(minIndex, maxIndex);

        // Option 1: Remove both from the front
        int front = second + 1;

        // Option 2: Remove both from the back
        int back = n - first;

        // Option 3: Remove first from front, second from back
        int mixed = (first + 1) + (n - second);

        return Math.min(front, Math.min(back, mixed));
    }
}