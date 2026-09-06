class Solution {
    public int subsequencePairCount(int[] nums) {
        int MOD = 1_000_000_007;
        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        int[][] gcdTable = new int[maxVal + 1][maxVal + 1];
        for (int i = 0; i <= maxVal; i++) {
            for (int j = 0; j <= maxVal; j++) {
                gcdTable[i][j] = gcd(i, j);
            }
        }

        int[][] dp = new int[maxVal + 1][maxVal + 1];
        dp[0][0] = 1;

        for (int x : nums) {
            int[][] nextDp = new int[maxVal + 1][maxVal + 1];

            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    long ways = dp[g1][g2];
                    if (ways == 0) continue;

                    nextDp[g1][g2] = (int) ((nextDp[g1][g2] + ways) % MOD);

                    int ng1 = gcdTable[g1][x];
                    nextDp[ng1][g2] = (int) ((nextDp[ng1][g2] + ways) % MOD);

                    int ng2 = gcdTable[g2][x];
                    nextDp[g1][ng2] = (int) ((nextDp[g1][ng2] + ways) % MOD);
                }
            }

            dp = nextDp;
        }

        long ans = 0;
        for (int g = 1; g <= maxVal; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }

        return (int) ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}