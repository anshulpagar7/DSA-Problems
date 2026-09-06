class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] comp = new int[n];
        int compId = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                compId++;
            }
            comp[i] = compId;
        }

        int qLen = queries.length;
        boolean[] ans = new boolean[qLen];

        for (int i = 0; i < qLen; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            ans[i] = comp[u] == comp[v];
        }

        return ans;
    }
}