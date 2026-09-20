import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startX = -1;
        int startY = -1;
        int litterCount = 0;
        int[][] litterId = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(litterId[i], -1);
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        if (litterCount == 0) {
            return 0;
        }

        int targetMask = 0;
        int initialMask = (1 << litterCount) - 1;

        int[][][] maxEnergy = new int[m][n][1 << litterCount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(maxEnergy[i][j], -1);
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startX, startY, energy, initialMask});
        maxEnergy[startX][startY][initialMask] = energy;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            moves++;

            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                int x = curr[0];
                int y = curr[1];
                int e = curr[2];
                int mask = curr[3];

                if (e <= 0) {
                    continue;
                }

                for (int[] d : dirs) {
                    int nx = x + d[0];
                    int ny = y + d[1];

                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                        continue;
                    }

                    char cell = classroom[nx].charAt(ny);
                    if (cell == 'X') {
                        continue;
                    }

                    int nEnergy = (cell == 'R') ? energy : e - 1;
                    int nMask = mask;

                    if (cell == 'L' && (mask & (1 << litterId[nx][ny])) != 0) {
                        nMask &= ~(1 << litterId[nx][ny]);
                    }

                    if (nMask == targetMask) {
                        return moves;
                    }

                    if (nEnergy > maxEnergy[nx][ny][nMask]) {
                        maxEnergy[nx][ny][nMask] = nEnergy;
                        queue.offer(new int[]{nx, ny, nEnergy, nMask});
                    }
                }
            }
        }

        return -1;
    }
}