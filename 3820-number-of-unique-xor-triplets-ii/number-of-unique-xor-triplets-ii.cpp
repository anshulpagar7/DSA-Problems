class Solution {
public:
    int uniqueXorTriplets(vector<int>& nums) {
        int max_val = 0;
        for (int v : nums) {
            max_val = max(max_val, v);
        }

        int U = 1;
        while (U <= max_val) {
            U <<= 1;
        }

        vector<bool> one(U, false);
        vector<bool> two(U, false);

        for (int v : nums) {
            one[v] = true;
            for (int x = 0; x < U; ++x) {
                if (one[x]) {
                    two[x ^ v] = true;
                }
            }
        }

        vector<bool> three(U, false);
        for (int v : nums) {
            for (int x = 0; x < U; ++x) {
                if (two[x]) {
                    three[x ^ v] = true;
                }
            }
        }

        int count = 0;
        for (int x = 0; x < U; ++x) {
            if (three[x]) {
                count++;
            }
        }

        return count;
    }
};