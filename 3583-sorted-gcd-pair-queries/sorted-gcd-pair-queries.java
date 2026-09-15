class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int x : nums) {
            if (x > maxVal) {
                maxVal = x;
            }
        }

        int[] count = new int[maxVal + 1];
        for (int x : nums) {
            count[x]++;
        }

        long[] gcdCount = new long[maxVal + 1];

        for (int g = maxVal; g >= 1; g--) {
            long c = 0;
            for (int mult = g; mult <= maxVal; mult += g) {
                c += count[mult];
            }

            long totalPairs = c * (c - 1) / 2;
            for (int mult = 2 * g; mult <= maxVal; mult += g) {
                totalPairs -= gcdCount[mult];
            }

            gcdCount[g] = totalPairs;
        }

        long[] pref = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            pref[i] = pref[i - 1] + gcdCount[i];
        }

        int qLen = queries.length;
        int[] ans = new int[qLen];

        for (int i = 0; i < qLen; i++) {
            long target = queries[i] + 1;
            int low = 1;
            int high = maxVal;
            int res = maxVal;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (pref[mid] >= target) {
                    res = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            ans[i] = res;
        }

        return ans;
    }
}