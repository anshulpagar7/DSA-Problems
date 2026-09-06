class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            char sc = s.charAt(i);
            for (int j = n - 1; j >= 0; j--) {
                if (sc == t.charAt(j)) {
                    dp[j + 1] += dp[j];
                }
            }
        }

        return dp[n];
    }
}