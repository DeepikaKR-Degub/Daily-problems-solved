import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        // First and last occurrence of each character
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        // Store all possible smallest valid intervals
        List<int[]> intervals = new ArrayList<>();

        for (int c = 0; c < 26; c++) {

            if (first[c] == n)
                continue;

            int l = first[c];
            int r = last[c];

            boolean valid = true;

            // Expand interval if we find another character
            // whose occurrences extend outside [l, r]
            for (int i = l; i <= r; i++) {

                int x = s.charAt(i) - 'a';

                if (first[x] < l) {
                    valid = false;
                    break;
                }

                r = Math.max(r, last[x]);
            }

            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> ans = new ArrayList<>();

        int prevEnd = -1;

        for (int[] interval : intervals) {

            int l = interval[0];
            int r = interval[1];

            // Non-overlapping
            if (l > prevEnd) {
                ans.add(s.substring(l, r + 1));
                prevEnd = r;
            }
        }

        return ans;
    }
}