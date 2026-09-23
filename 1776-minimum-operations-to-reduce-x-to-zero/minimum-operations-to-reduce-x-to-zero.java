class Solution {
    public int minOperations(int[] nums, int x) {

        int n = nums.length;

        // Calculate total sum
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        // Sum of the subarray we want to keep
        int target = total - x;

        // If target is negative, impossible
        if (target < 0) {
            return -1;
        }

        // If target is 0, we remove everything
        if (target == 0) {
            return n;
        }

        int left = 0;
        int sum = 0;
        int maxLength = -1;

        // Sliding window
        for (int right = 0; right < n; right++) {

            sum += nums[right];

            // Shrink window if sum becomes too large
            while (sum > target && left <= right) {
                sum -= nums[left];
                left++;
            }

            // Found a subarray with sum == target
            if (sum == target) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        // No valid subarray
        if (maxLength == -1) {
            return -1;
        }

        // Remove everything outside the subarray
        return n - maxLength;
    }
}