import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        // Find start and number each litter cell
        int sr = 0, sc = 0;
        int litterCount = 0;

        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);

                if (c == 'S') {
                    sr = i;
                    sc = j;
                } else if (c == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        // No litter to collect
        if (litterCount == 0) {
            return 0;
        }

        int allCollected = (1 << litterCount) - 1;

        /*
         * visited[r][c][energy][mask]
         *
         * energy can be 0..energy
         * mask can be 0..(2^litterCount - 1)
         */
        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << litterCount];

        // Queue stores: row, col, remaining energy, mask, distance
        Queue<int[]> queue = new LinkedList<>();

        visited[sr][sc][energy][0] = true;
        queue.offer(new int[]{sr, sc, energy, 0, 0});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int r = cur[0];
            int c = cur[1];
            int e = cur[2];
            int mask = cur[3];
            int dist = cur[4];

            if (mask == allCollected) {
                return dist;
            }

            // If energy is 0, we cannot make another move.
            // We can continue only if we are currently on R,
            // but R resets energy immediately when entered.
            if (e == 0) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                char cell = classroom[nr].charAt(nc);

                if (cell == 'X') {
                    continue;
                }

                // One move costs one energy
                int ne = e - 1;
                int nmask = mask;

                // Collect litter
                if (cell == 'L') {
                    int id = litterId[nr][nc];
                    nmask |= (1 << id);
                }

                // Reset energy on R
                if (cell == 'R') {
                    ne = energy;
                }

                if (!visited[nr][nc][ne][nmask]) {
                    visited[nr][nc][ne][nmask] = true;
                    queue.offer(new int[]{
                        nr, nc, ne, nmask, dist + 1
                    });
                }
            }
        }

        return -1;
    }
}

