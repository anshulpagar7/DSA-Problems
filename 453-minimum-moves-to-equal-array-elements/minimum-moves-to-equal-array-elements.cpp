#include <vector>
#include <numeric>
#include <algorithm>

using namespace std;

class Solution {
public:
    int minMoves(vector<int>& nums) {
        int min_val = *min_element(nums.begin(), nums.end());
        long long moves = 0;
        for (int x : nums) {
            moves += (x - min_val);
        }
        return static_cast<int>(moves);
    }
};