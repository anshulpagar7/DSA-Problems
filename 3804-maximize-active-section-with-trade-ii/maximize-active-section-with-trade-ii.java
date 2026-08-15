import java.util.*;

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int totalOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }

        List<Integer> lList = new ArrayList<>();
        List<Integer> rList = new ArrayList<>();
        int idx = 0;
        while (idx < n) {
            if (s.charAt(idx) == '0') {
                int start = idx;
                while (idx < n && s.charAt(idx) == '0') {
                    idx++;
                }
                lList.add(start);
                rList.add(idx - 1);
            } else {
                idx++;
            }
        }

        int m = lList.size();
        int[] L = new int[m];
        int[] R = new int[m];
        int[] len = new int[m];
        for (int i = 0; i < m; i++) {
            L[i] = lList.get(i);
            R[i] = rList.get(i);
            len[i] = R[i] - L[i] + 1;
        }

        int[] firstBlockWithRge = new int[n];
        int p = 0;
        for (int i = 0; i < n; i++) {
            while (p < m && R[p] < i) {
                p++;
            }
            firstBlockWithRge[i] = p;
        }

        int[] lastBlockWithLle = new int[n];
        p = -1;
        for (int i = 0; i < n; i++) {
            while (p + 1 < m && L[p + 1] <= i) {
                p++;
            }
            lastBlockWithLle[i] = p;
        }

        int numPairs = m - 1;
        int[][] st = null;
        if (numPairs > 0) {
            int maxLog = 32 - Integer.numberOfLeadingZeros(numPairs);
            st = new int[maxLog][numPairs];
            for (int i = 0; i < numPairs; i++) {
                st[0][i] = len[i] + len[i + 1];
            }
            for (int j = 1; j < maxLog; j++) {
                int lenPrev = 1 << (j - 1);
                for (int i = 0; i + (1 << j) <= numPairs; i++) {
                    st[j][i] = Math.max(st[j - 1][i], st[j - 1][i + lenPrev]);
                }
            }
        }

        List<Integer> answer = new ArrayList<>(queries.length);

        for (int[] q : queries) {
            int l = q[0], r = q[1];
            int pStart = firstBlockWithRge[l];
            int pEnd = lastBlockWithLle[r];

            if (pStart >= m || pEnd < 0 || pStart >= pEnd) {
                answer.add(totalOnes);
                continue;
            }

            int count = pEnd - pStart + 1;
            int bFirst = R[pStart] - Math.max(L[pStart], l) + 1;
            int bLast = Math.min(R[pEnd], r) - L[pEnd] + 1;

            if (count == 2) {
                int maxGain = bFirst + bLast;
                answer.add(totalOnes + maxGain);
            } else {
                int maxGain = Math.max(bFirst + len[pStart + 1], len[pEnd - 1] + bLast);

                int ql = pStart + 1;
                int qr = pEnd - 2;
                if (ql <= qr && st != null) {
                    int k = 31 - Integer.numberOfLeadingZeros(qr - ql + 1);
                    int internalMax = Math.max(st[k][ql], st[k][qr - (1 << k) + 1]);
                    maxGain = Math.max(maxGain, internalMax);
                }

                answer.add(totalOnes + maxGain);
            }
        }

        return answer;
    }
}