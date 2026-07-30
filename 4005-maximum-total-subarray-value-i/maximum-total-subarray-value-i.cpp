class Solution {
public:
    long long maxTotalValue(vector<int>& nums, int k) {
        int min_val = nums[0];
        int max_val = nums[0];

        for (int x : nums) {
            min_val = min(min_val, x);
            max_val = max(max_val, x);
        }

        return (long long)k * (max_val - min_val);
    }
};
