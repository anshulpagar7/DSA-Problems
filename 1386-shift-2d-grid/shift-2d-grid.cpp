#include <vector>

using namespace std;

class Solution {
public:
    vector<vector<int>> shiftGrid(vector<vector<int>>& grid, int k) {
        int m = grid.size();
        int n = grid[0].size();
        int total = m * n;
        
        k %= total;
        if (k == 0) return grid;

        vector<vector<int>> result(m, vector<int>(n));

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int old1D = i * n + j;
                int new1D = (old1D + k) % total;
                int newRow = new1D / n;
                int newCol = new1D % n;
                result[newRow][newCol] = grid[i][j];
            }
        }

        return result;
    }
};