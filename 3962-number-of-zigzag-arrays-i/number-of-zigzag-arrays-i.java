class Solution {
    public int zigZagArrays(int n, int l, int r) {
        int k = r - l + 1;
        long MOD = 1_000_000_007L;

        long[] dpUp = new long[k + 1];
        long[] dpDown = new long[k + 1];

        for (int v = 1; v <= k; v++) {
            dpUp[v] = v - 1;
            dpDown[v] = k - v;
        }

        for (int len = 3; len <= n; len++) {
            long[] nextUp = new long[k + 1];
            long[] nextDown = new long[k + 1];

            long prefixSumDown = 0;
            for (int v = 1; v <= k; v++) {
                nextUp[v] = prefixSumDown;
                prefixSumDown = (prefixSumDown + dpDown[v]) % MOD;
            }

            long suffixSumUp = 0;
            for (int v = k; v >= 1; v--) {
                nextDown[v] = suffixSumUp;
                suffixSumUp = (suffixSumUp + dpUp[v]) % MOD;
            }

            dpUp = nextUp;
            dpDown = nextDown;
        }

        long total = 0;
        for (int v = 1; v <= k; v++) {
            total = (total + dpUp[v] + dpDown[v]) % MOD;
        }

        return (int) total;
    }
}