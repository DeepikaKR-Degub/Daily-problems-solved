class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1000000007;
        int n = s.length();

        // dp[i] = number of distinct subsequences
        // including the empty subsequence using first i characters
        long[] dp = new long[n + 1];
        dp[0] = 1; // empty subsequence

        // Store the last position of each character
        int[] last = new int[26];

        for (int i = 1; i <= n; i++) {
            char ch = s.charAt(i - 1);
            int index = ch - 'a';

            // Every existing subsequence can either take or not take ch
            dp[i] = (2 * dp[i - 1]) % MOD;

            // Remove duplicate subsequences created by previous occurrence
            if (last[index] != 0) {
                dp[i] = (dp[i] - dp[last[index] - 1] + MOD) % MOD;
            }

            last[index] = i;
        }

        // Remove the empty subsequence
        return (int) ((dp[n] - 1 + MOD) % MOD);
    }
}