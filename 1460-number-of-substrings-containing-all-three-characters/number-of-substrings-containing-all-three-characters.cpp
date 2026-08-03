class Solution {
public:
    int numberOfSubstrings(string s) {
        int last[3] = {-1, -1, -1};
        int count = 0;
        int n = s.length();

        for (int i = 0; i < n; ++i) {
            last[s[i] - 'a'] = i;
            int min_idx = min({last[0], last[1], last[2]});
            if (min_idx != -1) {
                count += min_idx + 1;
            }
        }

        return count;
    }
};