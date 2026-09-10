class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        if (n <= 1) {
            return true;
        }

        int oddCount = 0;
        int evenCount = 0;

        for (int x : nums1) {
            if ((x & 1) == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        if (oddCount == n || evenCount == n) {
            return true;
        }

        return oddCount >= 1;
    }
}