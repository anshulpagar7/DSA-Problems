class Solution {
    public int mirrorDistance(int n) {
        long original = n;
        long reversed = 0;
        long temp = n;

        while (temp > 0) {
            reversed = reversed * 10 + (temp % 10);
            temp /= 10;
        }

        return (int) Math.abs(original - reversed);
    }
}