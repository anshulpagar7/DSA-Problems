#include <vector>
#include <set>
#include <algorithm>

using namespace std;

class Solution {
    class MedianSlidingWindow {
        multiset<int> low, high;
        long long sum_low = 0, sum_high = 0;
        int k;

        void balance() {
            int target_low = (k + 1) / 2;
            while (low.size() < target_low && !high.empty()) {
                auto it = high.begin();
                int val = *it;
                high.erase(it);
                sum_high -= val;
                low.insert(val);
                sum_low += val;
            }
            while (low.size() > target_low) {
                auto it = prev(low.end());
                int val = *it;
                low.erase(it);
                sum_low -= val;
                high.insert(val);
                sum_high += val;
            }
        }

    public:
        MedianSlidingWindow(int window_size) : k(window_size) {}

        void insert(int val) {
            if (low.empty() || val <= *low.rbegin()) {
                low.insert(val);
                sum_low += val;
            } else {
                high.insert(val);
                sum_high += val;
            }
            balance();
        }

        void erase(int val) {
            auto it = low.find(val);
            if (it != low.end()) {
                low.erase(it);
                sum_low -= val;
            } else {
                it = high.find(val);
                if (it != high.end()) {
                    high.erase(it);
                    sum_high -= val;
                }
            }
            balance();
        }

        long long getCost() {
            long long median = *low.rbegin();
            return (median * low.size() - sum_low) + (sum_high - median * high.size());
        }
    };

public:
    long long minOperations(vector<int>& nums, int x, int k) {
        int n = nums.size();
        int num_windows = n - x + 1;
        vector<long long> cost(num_windows);

        MedianSlidingWindow window(x);
        for (int i = 0; i < x; ++i) {
            window.insert(nums[i]);
        }
        cost[0] = window.getCost();

        for (int i = x; i < n; ++i) {
            window.erase(nums[i - x]);
            window.insert(nums[i]);
            cost[i - x + 1] = window.getCost();
        }

        const long long INF = 1e18;
        vector<long long> dp(n + 1, 0);

        for (int j = 1; j <= k; ++j) {
            vector<long long> next_dp(n + 1, INF);
            for (int i = j * x; i <= n; ++i) {
                next_dp[i] = min(next_dp[i - 1], dp[i - x] + cost[i - x]);
            }
            dp = move(next_dp);
        }

        return dp[n];
    }
};