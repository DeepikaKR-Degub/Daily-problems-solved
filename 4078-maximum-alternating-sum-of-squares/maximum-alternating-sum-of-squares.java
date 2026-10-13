import java.util.Arrays;

class Solution {
public long maxAlternatingSum(int[] nums) {
long[] squares = new long[nums.length];

    for (int i = 0; i < nums.length; i++) {
        squares[i] = (long) nums[i] * nums[i];
    }

    Arrays.sort(squares);

    int n = squares.length;
    long score = 0;

    for (int i = 0; i < n; i++) {
        if (i < n / 2) {
            score -= squares[i];
        } else {
            score += squares[i];
        }
    }

    return score;
}

}
