import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMasks = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            if (col >= 2 && col <= 9) {
                rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << (col - 1)));
            }
        }

        int maxFamilies = 2 * (n - rowMasks.size());

        int leftBlock = (1 << 1) | (1 << 2) | (1 << 3) | (1 << 4);    // seats 2, 3, 4, 5
        int rightBlock = (1 << 5) | (1 << 6) | (1 << 7) | (1 << 8);   // seats 6, 7, 8, 9
        int middleBlock = (1 << 3) | (1 << 4) | (1 << 5) | (1 << 6);  // seats 4, 5, 6, 7

        for (int mask : rowMasks.values()) {
            boolean canLeft = (mask & leftBlock) == 0;
            boolean canRight = (mask & rightBlock) == 0;
            boolean canMiddle = (mask & middleBlock) == 0;

            if (canLeft && canRight) {
                maxFamilies += 2;
            } else if (canLeft || canRight || canMiddle) {
                maxFamilies += 1;
            }
        }

        return maxFamilies;
    }
}