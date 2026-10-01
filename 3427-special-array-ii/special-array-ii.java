class Solution {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] badPrefix = new int[n];

        for (int i = 1; i < n; i++) {
            badPrefix[i] = badPrefix[i - 1];
            if ((nums[i] % 2) == (nums[i - 1] % 2)) {
                badPrefix[i]++;
            }
        }

        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int from = queries[i][0];
            int to = queries[i][1];
            ans[i] = (badPrefix[to] - badPrefix[from] == 0);
        }

        return ans;
    }
}