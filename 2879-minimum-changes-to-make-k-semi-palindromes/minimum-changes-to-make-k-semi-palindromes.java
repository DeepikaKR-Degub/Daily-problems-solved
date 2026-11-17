class Solution {

    public int minimumChanges(String s, int k) {
        int n = s.length();

        // cost[i][j] = minimum changes to make s[i..j]
        // a semi-palindrome.
        int[][] cost = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                cost[i][j] = getSemiPalindromeCost(s, i, j);
            }
        }

        // dp[p][i] = minimum cost to split first i characters
        // into p semi-palindromic substrings.
        int[][] dp = new int[k + 1][n + 1];

        int INF = 1_000_000;

        for (int p = 0; p <= k; p++) {
            for (int i = 0; i <= n; i++) {
                dp[p][i] = INF;
            }
        }

        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {

            // Need at least p characters and leave at least
            // one character for every remaining part.
            for (int i = p; i <= n; i++) {

                // Last substring is s[j ... i-1]
                for (int j = p - 1; j < i; j++) {

                    // Length 1 is not a valid semi-palindrome.
                    if (i - j < 2) {
                        continue;
                    }

                    if (dp[p - 1][j] != INF) {
                        dp[p][i] = Math.min(
                            dp[p][i],
                            dp[p - 1][j] + cost[j][i - 1]
                        );
                    }
                }
            }
        }

        return dp[k][n];
    }

    // Minimum changes required to make s[left..right]
    // a semi-palindrome.
    private int getSemiPalindromeCost(String s, int left, int right) {

        int len = right - left + 1;
        int answer = Integer.MAX_VALUE;

        // d must be a proper divisor of len.
        for (int d = 1; d < len; d++) {

            if (len % d != 0) {
                continue;
            }

            int changes = 0;

            // There are d groups.
            for (int start = 0; start < d; start++) {

                int l = start;
                int r = len - d + start;

                // Compare characters symmetrically
                // inside this group.
                while (l < r) {

                    char c1 = s.charAt(left + l);
                    char c2 = s.charAt(left + r);

                    if (c1 != c2) {
                        changes++;
                    }

                    l += d;
                    r -= d;
                }
            }

            answer = Math.min(answer, changes);
        }

        return answer;
    }
}