#include <vector>
#include <unordered_map>
#include <unordered_set>
#include <algorithm>

using namespace std;

class Solution {
public:
    int largestInteger(vector<int>& nums, int k) {
        int n = nums.size();
        unordered_map<int, int> subarrayCount;

        for (int i = 0; i <= n - k; i++) {
            unordered_set<int> seen;
            for (int j = i; j < i + k; j++) {
                seen.insert(nums[j]);
            }
            for (int val : seen) {
                subarrayCount[val]++;
            }
        }

        int maxVal = -1;
        for (const auto& [val, count] : subarrayCount) {
            if (count == 1) {
                maxVal = max(maxVal, val);
            }
        }

        return maxVal;
    }
};