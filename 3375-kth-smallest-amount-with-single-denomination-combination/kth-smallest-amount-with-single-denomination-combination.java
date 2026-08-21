class Solution {

    long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    // Count distinct amounts <= x that are divisible by at least one coin
    long count(long x, int[] coins) {
        int n = coins.length;
        long result = 0;

        // Inclusion-exclusion over all subsets
        for (int mask = 1; mask < (1 << n); mask++) {

            long L = 1;
            int bits = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;

                    L = lcm(L, coins[i]);

                    // L is already larger than x, so this subset
                    // contributes nothing.
                    if (L > x) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) continue;

            long multiples = x / L;

            if (bits % 2 == 1) {
                result += multiples;
            } else {
                result -= multiples;
            }
        }

        return result;
    }

    public long findKthSmallest(int[] coins, int k) {

        long low = 1;
        long high = (long) coins[0] * k;

        // Since coins[0] alone can produce k amounts,
        // the kth answer cannot be greater than coins[0] * k.
        for (int coin : coins) {
            high = Math.min(high, (long) coin * k);
        }

        while (low < high) {
            long mid = low + (high - low) / 2;

            if (count(mid, coins) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}