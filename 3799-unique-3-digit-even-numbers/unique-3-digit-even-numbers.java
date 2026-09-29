class Solution {
    public int totalNumbers(int[] digits) {
        int[] count = new int[10];
        for (int d : digits) {
            count[d]++;
        }

        int distinctCount = 0;

        for (int num = 100; num < 1000; num += 2) {
            int d1 = num / 100;
            int d2 = (num / 10) % 10;
            int d3 = num % 10;

            int[] needed = new int[10];
            needed[d1]++;
            needed[d2]++;
            needed[d3]++;

            boolean canForm = true;
            for (int i = 0; i < 10; i++) {
                if (needed[i] > count[i]) {
                    canForm = false;
                    break;
                }
            }

            if (canForm) {
                distinctCount++;
            }
        }

        return distinctCount;
    }
}