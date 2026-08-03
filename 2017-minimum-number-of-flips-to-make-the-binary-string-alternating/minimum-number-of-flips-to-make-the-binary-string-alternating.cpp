class Solution {
public:
    int minFlips(string s) {
        int n = s.length();
        string s_doubled = s + s;
        
        string alt1 = "", alt2 = "";
        for (int i = 0; i < 2 * n; ++i) {
            alt1 += (i % 2 == 0 ? '0' : '1');
            alt2 += (i % 2 == 0 ? '1' : '0');
        }

        int diff1 = 0, diff2 = 0;
        int min_flips = 2 * n;

        for (int i = 0; i < 2 * n; ++i) {
            if (s_doubled[i] != alt1[i]) diff1++;
            if (s_doubled[i] != alt2[i]) diff2++;

            if (i >= n) {
                if (s_doubled[i - n] != alt1[i - n]) diff1--;
                if (s_doubled[i - n] != alt2[i - n]) diff2--;
            }

            if (i >= n - 1) {
                min_flips = min({min_flips, diff1, diff2});
            }
        }

        return min_flips;
    }
};