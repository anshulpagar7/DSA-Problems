class Solution {
public:
    int maxJumps(vector<int>& arr, int d) {
        int n = arr.size();
        vector<int> dp(n, 0);

        auto solve = [&](auto& self, int i) -> int {
            if (dp[i] != 0) return dp[i];

            int res = 1;

            for (int x = 1; x <= d && i + x < n; ++x) {
                if (arr[i + x] >= arr[i]) break;
                res = max(res, 1 + self(self, i + x));
            }

            for (int x = 1; x <= d && i - x >= 0; ++x) {
                if (arr[i - x] >= arr[i]) break;
                res = max(res, 1 + self(self, i - x));
            }

            return dp[i] = res;
        };

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = max(ans, solve(solve, i));
        }

        return ans;
    }
};