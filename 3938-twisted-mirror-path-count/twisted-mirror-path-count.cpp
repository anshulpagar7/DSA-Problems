#include <vector>

using namespace std;

class Solution {
    int MOD = 1e9 + 7;

    pair<int, int> simulate(int r, int c, int dir, const vector<vector<int>>& grid, int m, int n) {
        while (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
            if (dir == 0) {
                r++;
                dir = 1;
            } else {
                c++;
                dir = 0;
            }
        }
        if (r < 0 || r >= m || c < 0 || c >= n) {
            return {-1, -1};
        }
        return {r, c};
    }

public:
    int uniquePaths(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();

        vector<vector<pair<int, int>>> next_right(m, vector<pair<int, int>>(n));
        vector<vector<pair<int, int>>> next_down(m, vector<pair<int, int>>(n));

        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                if (grid[r][c] == 0) {
                    next_right[r][c] = simulate(r, c + 1, 0, grid, m, n);
                    next_down[r][c] = simulate(r + 1, c, 1, grid, m, n);
                }
            }
        }

        vector<vector<int>> dp(m, vector<int>(n, 0));
        dp[0][0] = 1;

        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                if (dp[r][c] == 0 || grid[r][c] == 1) {
                    continue;
                }

                int ways = dp[r][c];

                auto [nr_r, nr_c] = next_right[r][c];
                if (nr_r != -1) {
                    dp[nr_r][nr_c] = (dp[nr_r][nr_c] + ways) % MOD;
                }

                auto [nd_r, nd_c] = next_down[r][c];
                if (nd_r != -1) {
                    dp[nd_r][nd_c] = (dp[nd_r][nd_c] + ways) % MOD;
                }
            }
        }

        return dp[m - 1][n - 1];
    }
};