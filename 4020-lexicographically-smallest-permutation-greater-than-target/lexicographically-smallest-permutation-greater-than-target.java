class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        int[] freq = new int[26];

        // Count characters in s
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Try changing the rightmost possible position
        for (int i = n - 1; i >= 0; i--) {

            int[] remaining = freq.clone();

            // Match target[0 ... i-1]
            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int x = target.charAt(j) - 'a';

                if (remaining[x] == 0) {
                    possible = false;
                    break;
                }

                remaining[x]--;
            }

            if (!possible) {
                continue;
            }

            int current = target.charAt(i) - 'a';

            // Find the smallest character greater than target[i]
            for (int c = current + 1; c < 26; c++) {

                if (remaining[c] > 0) {

                    StringBuilder ans = new StringBuilder();

                    // Keep prefix same as target
                    for (int j = 0; j < i; j++) {
                        ans.append(target.charAt(j));
                    }

                    // Make string strictly greater
                    ans.append((char) ('a' + c));
                    remaining[c]--;

                    // Add remaining characters in sorted order
                    for (int x = 0; x < 26; x++) {
                        while (remaining[x] > 0) {
                            ans.append((char) ('a' + x));
                            remaining[x]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}