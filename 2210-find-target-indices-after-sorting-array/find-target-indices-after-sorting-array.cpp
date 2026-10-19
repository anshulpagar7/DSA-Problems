#include <vector>

using namespace std;

class Solution {
public:
    vector<int> targetIndices(vector<int>& nums, int target) {
        int smaller = 0;
        int count = 0;

        for (int num : nums) {
            if (num < target) {
                smaller++;
            } else if (num == target) {
                count++;
            }
        }

        vector<int> result;
        for (int i = 0; i < count; i++) {
            result.push_back(smaller + i);
        }

        return result;
    }
};