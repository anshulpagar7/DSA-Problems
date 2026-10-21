import java.util.Arrays;

class Solution {
    public int minOperations(String s) {
        int n = s.length();
        if (n <= 1) {
            return 0;
        }

        char[] sorted = s.toCharArray();
        Arrays.sort(sorted);

        boolean isSorted = true;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != sorted[i]) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) {
            return 0;
        }

        if (n == 2) {
            return -1;
        }

        char minChar = sorted[0];
        char maxChar = sorted[n - 1];

        if (s.charAt(0) == minChar || s.charAt(n - 1) == maxChar) {
            return 1;
        }

        boolean hasMinInside = false;
        boolean hasMaxInside = false;

        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == minChar) {
                hasMinInside = true;
            }
            if (s.charAt(i) == maxChar) {
                hasMaxInside = true;
            }
        }

        if (s.charAt(0) != maxChar || s.charAt(n - 1) != minChar || hasMinInside || hasMaxInside) {
            return 2;
        }

        return 3;
    }
}