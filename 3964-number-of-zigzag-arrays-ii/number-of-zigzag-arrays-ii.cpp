class Solution {
private:
    static constexpr int MOD = 1000000007;

    vector<vector<long long>> multiply(const vector<vector<long long>>& A, const vector<vector<long long>>& B, int sz) {
        vector<vector<long long>> C(sz, vector<long long>(sz, 0));
        for (int i = 0; i < sz; ++i) {
            for (int k = 0; k < sz; ++k) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < sz; ++j) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    vector<vector<long long>> power(vector<vector<long long>> A, int exp, int sz) {
        vector<vector<long long>> res(sz, vector<long long>(sz, 0));
        for (int i = 0; i < sz; ++i) res[i][i] = 1;

        while (exp > 0) {
            if (exp & 1) res = multiply(res, A, sz);
            A = multiply(A, A, sz);
            exp >>= 1;
        }
        return res;
    }

public:
    int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int sz = 2 * m;

        vector<vector<long long>> T(sz, vector<long long>(sz, 0));

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < i; ++j) {
                T[i + m][j] = 1;
            }
            for (int j = i + 1; j < m; ++j) {
                T[i][j + m] = 1;
            }
        }

        T = power(T, n - 2, sz);

        long long ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                if (i == j) continue;
                int start_state = (i < j) ? i : (i + m);
                
                for (int end_state = 0; end_state < sz; ++end_state) {
                    ans = (ans + T[start_state][end_state]) % MOD;
                }
            }
        }

        return ans;
    }
};