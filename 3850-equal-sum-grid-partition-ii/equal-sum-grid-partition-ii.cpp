class Solution {
private:
    bool checkHorCuts(const vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        
        long long totalSum = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                totalSum += grid[i][j];
            }
        }

        unordered_set<long long> seen;
        long long topSum = 0;

        for (int i = 0; i < m - 1; ++i) {
            for (int j = 0; j < n; ++j) {
                seen.insert(grid[i][j]);
                topSum += grid[i][j];
            }

            long long bottomSum = totalSum - topSum;
            long long diff = topSum - bottomSum;

            if (diff == 0) return true;

            if (diff > 0) {
                if (i == 0) {
                    if (n == 1) {
                        if (diff == grid[0][0]) return true;
                    } else {
                        if (diff == grid[0][0] || diff == grid[0][n - 1]) return true;
                    }
                } else {
                    if (n == 1) {
                        if (diff == grid[0][0] || diff == grid[i][0]) return true;
                    } else {
                        if (seen.count(diff)) return true;
                    }
                }
            }
        }

        return false;
    }

    void reverseRows(vector<vector<int>>& grid) {
        reverse(grid.begin(), grid.end());
    }

    vector<vector<int>> transpose(const vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        vector<vector<int>> res(n, vector<int>(m));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res[j][i] = grid[i][j];
            }
        }
        return res;
    }

public:
    bool canPartitionGrid(vector<vector<int>>& grid) {
        if (checkHorCuts(grid)) return true;

        reverseRows(grid);
        if (checkHorCuts(grid)) return true;
        reverseRows(grid);

        vector<vector<int>> gridT = transpose(grid);

        if (checkHorCuts(gridT)) return true;

        reverseRows(gridT);
        if (checkHorCuts(gridT)) return true;

        return false;
    }
};