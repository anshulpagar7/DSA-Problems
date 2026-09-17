class Solution {
    public int countCommas(int n) {
        if (n < 1000) {
            return 0;
        }

        long totalCommas = 0;
        long threshold = 1000L;

        while (n >= threshold) {
            totalCommas += (n - threshold + 1);
            threshold *= 1000L;
        }

        return (int) totalCommas;
    }
}