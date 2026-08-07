class Solution {
public:
    int numberWays(vector<vector<int>>& hats) {
        int n = hats.size();
        int done = (1 << n) - 1;
        const int MOD = 1e9 + 7;

        vector<vector<int>> hatsToPeople(41);
        for (int i = 0; i < n; ++i) {
            for (int hat : hats[i]) {
                hatsToPeople[hat].push_back(i);
            }
        }

        vector<vector<int>> dp(42, vector<int>(done + 1, 0));

        for (int hat = 1; hat <= 41; ++hat) {
            dp[hat][done] = 1;
        }

        for (int hat = 40; hat >= 1; --hat) {
            for (int mask = done - 1; mask >= 0; --mask) {
                long long ans = dp[hat + 1][mask];

                for (int person : hatsToPeople[hat]) {
                    if (!(mask & (1 << person))) {
                        ans = (ans + dp[hat + 1][mask | (1 << person)]) % MOD;
                    }
                }

                dp[hat][mask] = ans;
            }
        }

        return dp[1][0];
    }
};