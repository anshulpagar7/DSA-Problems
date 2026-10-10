class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int maxDiff = 0;
        int[] diffs = new int[n];

        for (int i = 0; i < n; i++) {
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            if (diffs[i] > maxDiff) {
                maxDiff = diffs[i];
            }
        }

        int[] count = new int[maxDiff + 1];
        for (int d : diffs) {
            count[d]++;
        }

        long k = (long) k1 + k2;

        for (int i = maxDiff; i > 0 && k > 0; i--) {
            if (count[i] == 0) {
                continue;
            }

            long canReduce = Math.min((long) count[i], k);
            count[i] -= (int) canReduce;
            count[i - 1] += (int) canReduce;
            k -= canReduce;

            if (count[i] > 0) {
                break;
            }
        }

        long result = 0;
        for (int i = 1; i <= maxDiff; i++) {
            if (count[i] > 0) {
                result += (long) count[i] * (long) i * i;
            }
        }

        return result;
    }
}