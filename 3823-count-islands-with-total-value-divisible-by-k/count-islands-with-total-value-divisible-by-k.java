class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] > 0) {
                    long totalSum = dfs(grid, r, c, m, n);
                    if (totalSum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private long dfs(int[][] grid, int r, int c, int m, int n) {
        if (r < 0 || r >= m || c < 0 || c >= n || grid[r][c] == 0) {
            return 0;
        }

        long sum = grid[r][c];
        grid[r][c] = 0;

        sum += dfs(grid, r + 1, c, m, n);
        sum += dfs(grid, r - 1, c, m, n);
        sum += dfs(grid, r, c + 1, m, n);
        sum += dfs(grid, r, c - 1, m, n);

        return sum;
    }
}