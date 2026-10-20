#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int sortableIntegers(vector<int>& nums) {
        int n = nums.size();
        if (n == 0) return 0;

        vector<int> sorted_nums = nums;
        sort(sorted_nums.begin(), sorted_nums.end());

        int total_sum = 0;

        for (int k = 1; k <= n; ++k) {
            if (n % k != 0) {
                continue;
            }

            if (k == 1) {
                if (nums == sorted_nums) {
                    total_sum += 1;
                }
                continue;
            }

            bool possible = true;
            for (int i = 0; i < n; i += k) {
                int drop_count = 0;
                int pivot = -1;

                for (int j = 0; j < k - 1; ++j) {
                    if (nums[i + j] > nums[i + j + 1]) {
                        drop_count++;
                        pivot = j + 1;
                    }
                }

                if (nums[i + k - 1] > nums[i]) {
                    drop_count++;
                }

                if (drop_count > 1) {
                    possible = false;
                    break;
                }

                if (drop_count == 0) {
                    if (nums[i] != sorted_nums[i] || nums[i + k - 1] != sorted_nums[i + k - 1]) {
                        possible = false;
                        break;
                    }
                } else if (drop_count == 1) {
                    if (pivot == -1) {
                        if (nums[i] != sorted_nums[i] || nums[i + k - 1] != sorted_nums[i + k - 1]) {
                            possible = false;
                            break;
                        }
                    } else {
                        if (nums[i + pivot] != sorted_nums[i] || nums[i + pivot - 1] != sorted_nums[i + k - 1]) {
                            possible = false;
                            break;
                        }
                    }
                }
            }

            if (possible) {
                total_sum += k;
            }
        }

        return total_sum;
    }
};