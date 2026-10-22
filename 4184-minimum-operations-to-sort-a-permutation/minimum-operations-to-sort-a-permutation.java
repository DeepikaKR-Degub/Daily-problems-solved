class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int p = 0; // index of 0
        for (int i = 0; i < n; i++) if (nums[i] == 0) p = i;

        boolean inc = true, dec = true;
        for (int i = 0; i < n; i++) {
            int next = nums[(i + 1) % n];
            if (next != (nums[i] + 1) % n) inc = false;
            if (next != (nums[i] - 1 + n) % n) dec = false;
        }

        int ans = Integer.MAX_VALUE;

        // Cyclically increasing: rotate left p times, or reverse, rotate right (n-p) times via 2 reverses
        if (inc) {
            ans = (p == 0) ? 0 : Math.min(p, n - p + 2);
        }

        // Cyclically decreasing: needs exactly one reverse plus rotations before/after
        if (dec) {
            int d = (p + 1) % n;
            ans = Math.min(ans, Math.min(d, n - d) + 1);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}