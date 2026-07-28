class Solution {
public:
    string smallestPalindrome(string s) {
        int n = s.length();
        int mid = n / 2;
        
        sort(s.begin(), s.begin() + mid);
        
        for (int i = 0; i < mid; ++i) {
            s[n - 1 - i] = s[i];
        }
        
        return s;
    }
};