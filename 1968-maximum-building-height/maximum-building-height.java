import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 0});
        for (int[] r : restrictions) {
            list.add(new int[]{r[0], r[1]});
        }

        Collections.sort(list, (a, b) -> Integer.compare(a[0], b[0]));

        if (list.get(list.size() - 1)[0] != n) {
            list.add(new int[]{n, n - 1});
        }

        int m = list.size();

        for (int i = 1; i < m; i++) {
            int[] prev = list.get(i - 1);
            int[] curr = list.get(i);
            curr[1] = Math.min(curr[1], prev[1] + (curr[0] - prev[0]));
        }

        for (int i = m - 2; i >= 0; i--) {
            int[] next = list.get(i + 1);
            int[] curr = list.get(i);
            curr[1] = Math.min(curr[1], next[1] + (next[0] - curr[0]));
        }

        int maxHeight = 0;
        for (int i = 1; i < m; i++) {
            int[] prev = list.get(i - 1);
            int[] curr = list.get(i);
            int id1 = prev[0], h1 = prev[1];
            int id2 = curr[0], h2 = curr[1];

            int peak = (h1 + h2 + (id2 - id1)) / 2;
            maxHeight = Math.max(maxHeight, peak);
        }

        return maxHeight;
    }
}