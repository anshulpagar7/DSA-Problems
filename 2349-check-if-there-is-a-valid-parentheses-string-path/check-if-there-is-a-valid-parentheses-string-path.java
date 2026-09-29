class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        int maxBalance = (m + n) / 2;
        boolean[][][] visited = new boolean[m][n][maxBalance + 1];

        return dfs(grid, 0, 0, 0, m, n, maxBalance, visited);
    }

    private boolean dfs(char[][] grid, int r, int c, int balance, int m, int n, int maxBalance, boolean[][][] visited) {
        balance += (grid[r][c] == '(' ? 1 : -1);

        if (balance < 0 || balance > maxBalance) {
            return false;
        }

        int remainingSteps = (m - 1 - r) + (n - 1 - c);
        if (balance > remainingSteps) {
            return false;
        }

        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        if (visited[r][c][balance]) {
            return false;
        }
        visited[r][c][balance] = true;

        if (c + 1 < n && dfs(grid, r, c + 1, balance, m, n, maxBalance, visited)) {
            return true;
        }

        if (r + 1 < m && dfs(grid, r + 1, c, balance, m, n, maxBalance, visited)) {
            return true;
        }

        return false;
    }
}