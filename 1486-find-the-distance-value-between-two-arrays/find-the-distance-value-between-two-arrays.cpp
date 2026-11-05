#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

class Solution {
public:
    int findTheDistanceValue(vector<int>& arr1, vector<int>& arr2, int d) {
        sort(arr2.begin(), arr2.end());
        int count = 0;

        for (int val : arr1) {
            // Find first element in arr2 >= val - d
            auto it = lower_bound(arr2.begin(), arr2.end(), val - d);

            // If found element is <= val + d, then |val - *it| <= d
            if (it != arr2.end() && *it <= val + d) {
                continue;
            }
            count++;
        }

        return count;
    }
};