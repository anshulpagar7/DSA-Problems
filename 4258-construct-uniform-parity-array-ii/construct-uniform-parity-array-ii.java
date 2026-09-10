class Solution {
    public boolean uniformArray(int[] nums1) {
        int minVal = Integer.MAX_VALUE;
        int oddCount = 0;

        for (int x : nums1) {
            if (x < minVal) {
                minVal = x;
            }
            if ((x & 1) == 1) {
                oddCount++;
            }
        }

        if (oddCount == 0 || (minVal & 1) == 1) {
            return true;
        }

        return false;
    }
}