class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long powerOf10 = 1000L;

        while (n >= powerOf10) {
            totalCommas += (n - powerOf10 + 1);
            if (powerOf10 > Long.MAX_VALUE / 1000) {
                break;
            }
            powerOf10 *= 1000L;
        }

        return totalCommas;
    }
}