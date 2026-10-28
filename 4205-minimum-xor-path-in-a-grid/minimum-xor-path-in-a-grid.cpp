#include <vector>
#include <bitset>
#include <algorithm>

using namespace std;

class Solution {
public:
    int minCost(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();

        vector<bitset<1024>> dp(n);

        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                bitset<1024> from_prev;

                if (r == 0 && c == 0) {
                    from_prev.set(0);
                } else {
                    if (r > 0) {
                        from_prev |= dp[c];
                    }
                    if (c > 0) {
                        from_prev |= dp[c - 1];
                    }
                }

                int val = grid[r][c];
                dp[c].reset();

                for (int x = 0; x < 1024; ++x) {
                    if (from_prev.test(x)) {
                        dp[c].set(x ^ val);
                    }
                }
            }
        }

        for (int v = 0; v < 1024; ++v) {
            if (dp[n - 1].test(v)) {
                return v;
            }
        }

        return 0;
    }
};