class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sumLeft = 0, sumRight = 0;
        int qLeft = 0, qRight = 0;

        for (int i = 0; i < n / 2; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                qLeft++;
            } else {
                sumLeft += c - '0';
            }
        }

        for (int i = n / 2; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                qRight++;
            } else {
                sumRight += c - '0';
            }
        }

        if ((qLeft + qRight) % 2 != 0) {
            return true;
        }

        int diffSum = sumLeft - sumRight;
        int diffQ = qRight - qLeft;

        return diffSum * 2 != diffQ * 9;
    }
}