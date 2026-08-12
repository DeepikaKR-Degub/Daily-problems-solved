import java.util.*;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();

        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {

            // Increase frequency of nums[right]
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

            // Shrink window if frequency exceeds k
            while (freq.get(nums[right]) > k) {
                freq.put(nums[left], freq.get(nums[left]) - 1);
                left++;
            }

            // Current window is good
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}