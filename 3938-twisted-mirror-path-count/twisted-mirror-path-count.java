class Solution {
private static final int MOD = 1_000_000_007;
private int[][][] memo;
private int[][] grid;
private int m, n;

// dir = 0: entered mirror while moving right
// dir = 1: entered mirror while moving down
private int reflect(int r, int c, int dir) {
    if (memo[r][c][dir] != -2) {
        return memo[r][c][dir];
    }

    int nr, nc;

    if (dir == 0) {
        nr = r + 1;
        nc = c;
    } else {
        nr = r;
        nc = c + 1;
    }

    // Reflection goes outside the grid
    if (nr >= m || nc >= n) {
        return memo[r][c][dir] = -1;
    }

    // The reflected destination is empty
    if (grid[nr][nc] == 0) {
        return memo[r][c][dir] = nr * n + nc;
    }

    // Consecutive mirror: direction alternates
    int nextDir = (dir == 0) ? 1 : 0;

    return memo[r][c][dir] =
            reflect(nr, nc, nextDir);
}

public int uniquePaths(int[][] grid) {
    this.grid = grid;
    m = grid.length;
    n = grid[0].length;

    memo = new int[m][n][2];

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            memo[i][j][0] = -2;
            memo[i][j][1] = -2;
        }
    }

    long[][] dp = new long[m][n];
    dp[0][0] = 1;

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (dp[i][j] == 0) {
                continue;
            }

            if (i == m - 1 && j == n - 1) {
                continue;
            }

            // Attempt to move right
            int nr = i;
            int nc = j + 1;

            if (nc < n) {
                int dest;

                if (grid[nr][nc] == 0) {
                    dest = nr * n + nc;
                } else {
                    dest = reflect(nr, nc, 0);
                }

                if (dest != -1) {
                    int x = dest / n;
                    int y = dest % n;

                    dp[x][y] =
                            (dp[x][y] + dp[i][j]) % MOD;
                }
            }

            // Attempt to move down
            nr = i + 1;
            nc = j;

            if (nr < m) {
                int dest;

                if (grid[nr][nc] == 0) {
                    dest = nr * n + nc;
                } else {
                    dest = reflect(nr, nc, 1);
                }

                if (dest != -1) {
                    int x = dest / n;
                    int y = dest % n;

                    dp[x][y] =
                            (dp[x][y] + dp[i][j]) % MOD;
                }
            }
        }
    }

    return (int) dp[m - 1][n - 1];
}

}
