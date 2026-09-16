class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        int total = n + k - 1;
        int choose = 2 * k;

        long[][] c = new long[total + 1][choose + 1];
        for (int i = 0; i <= total; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= Math.min(i, choose); j++) {
                c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % MOD;
            }
        }

        return (int) c[total][choose];
    }
}