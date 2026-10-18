#include <vector>

using namespace std;

class Solution {
    long long mod = 1e9 + 7;
    vector<vector<long long>> comb;

public:
    int numOfWays(vector<int>& nums) {
        int n = nums.size();
        comb.assign(n + 1, vector<long long>(n + 1, 0));

        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % mod;
            }
        }

        return (dfs(nums) - 1 + mod) % mod;
    }

private:
    long long dfs(vector<int>& nums) {
        int n = nums.size();
        if (n <= 2) {
            return 1;
        }

        int root = nums[0];
        vector<int> left, right;

        for (int i = 1; i < n; i++) {
            if (nums[i] < root) {
                left.push_back(nums[i]);
            } else {
                right.push_back(nums[i]);
            }
        }

        long long leftWays = dfs(left);
        long long rightWays = dfs(right);

        long long ways = (comb[left.size() + right.size()][left.size()] * leftWays) % mod;
        return (ways * rightWays) % mod;
    }
};