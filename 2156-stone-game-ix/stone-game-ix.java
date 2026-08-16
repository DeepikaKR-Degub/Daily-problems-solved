import java.util.*;

class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];

        for (int x : stones) {
            count[x % 3]++;
        }

        // If there are no stones with remainder 1 or 2,
        // Alice must eventually lose.
        if (count[1] == 0 && count[2] == 0) {
            return false;
        }

        // Known characterization of Stone Game IX:
        // Alice wins if:
        // 1. count[0] is even and both remainder types exist, OR
        // 2. count[0] is odd and the difference between count[1]
        //    and count[2] is greater than 2.
        if (count[0] % 2 == 0) {
            return count[1] > 0 && count[2] > 0;
        } else {
            return Math.abs(count[1] - count[2]) > 2;
        }
    }
}