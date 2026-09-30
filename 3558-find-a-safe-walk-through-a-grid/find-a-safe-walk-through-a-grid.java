import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int[][] minDamage = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(minDamage[i], Integer.MAX_VALUE);
        }

        Deque<int[]> deque = new ArrayDeque<>();
        int startCost = grid.get(0).get(0);
        minDamage[0][0] = startCost;
        deque.offerFirst(new int[]{0, 0, startCost});

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0];
            int c = curr[1];
            int d = curr[2];

            if (d > minDamage[r][c]) {
                continue;
            }

            if (r == m - 1 && c == n - 1) {
                return health - d >= 1;
            }

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int weight = grid.get(nr).get(nc);
                    if (d + weight < minDamage[nr][nc]) {
                        minDamage[nr][nc] = d + weight;
                        if (weight == 0) {
                            deque.offerFirst(new int[]{nr, nc, d + weight});
                        } else {
                            deque.offerLast(new int[]{nr, nc, d + weight});
                        }
                    }
                }
            }
        }

        return health - minDamage[m - 1][n - 1] >= 1;
    }
}