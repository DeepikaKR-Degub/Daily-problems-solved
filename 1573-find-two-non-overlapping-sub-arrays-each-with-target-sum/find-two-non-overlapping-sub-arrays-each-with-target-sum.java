class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;

        int[] best = new int[n];
        int INF = Integer.MAX_VALUE / 2;

        for (int i = 0; i < n; i++) {
            best[i] = INF;
        }

        int left = 0;
        long sum = 0;
        int answer = INF;
        int minLength = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            // Shrink window if sum becomes too large
            while (sum > target && left <= right) {
                sum -= arr[left];
                left++;
            }

            // Found a subarray with sum = target
            if (sum == target) {
                int currentLength = right - left + 1;

                // Check if there is a previous non-overlapping subarray
                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(
                        answer,
                        best[left - 1] + currentLength
                    );
                }

                // Keep the shortest valid subarray seen so far
                minLength = Math.min(minLength, currentLength);
            }

            // Store best answer up to this index
            if (right == 0) {
                best[right] = minLength;
            } else {
                best[right] = Math.min(best[right - 1], minLength);
            }
        }

        return answer == INF ? -1 : answer;
    }
}