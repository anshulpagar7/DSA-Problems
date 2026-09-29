class Solution {
    public int minElement(int[] nums) {
        int minVal = Integer.MAX_VALUE;

        for (int num : nums) {
            int sum = 0;
            int n = num;
            while (n > 0) {
                sum += n % 10;
                n /= 10;
            }
            if (sum < minVal) {
                minVal = sum;
            }
        }

        return minVal;
    }
}