import java.util.*;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        Map<Integer, Integer> dp = new HashMap<>();

        int ans = 0;

        for (int x : nums) {

            // Use x as it is
            int keep = dp.getOrDefault(x - 1, 0) + 1;

            // Increase x by 1
            int increase = dp.getOrDefault(x, 0) + 1;

            // Update both possible ending values
            dp.put(x, Math.max(dp.getOrDefault(x, 0), keep));

            dp.put(x + 1,
                    Math.max(dp.getOrDefault(x + 1, 0), increase));

            ans = Math.max(ans,
                    Math.max(dp.get(x), dp.get(x + 1)));
        }

        return ans;
    }
}