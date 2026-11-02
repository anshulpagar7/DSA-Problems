#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

class Solution {
public:
    int minMoves2(vector<int>& nums) {
        int n = nums.size();
        auto mid = nums.begin() + n / 2;
        nth_element(nums.begin(), mid, nums.end());
        int median = *mid;

        long long moves = 0;
        for (int x : nums) {
            moves += abs(x - median);
        }

        return static_cast<int>(moves);
    }
};