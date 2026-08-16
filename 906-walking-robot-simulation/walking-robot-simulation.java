import java.util.HashSet;
import java.util.Set;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // Directions: North, East, South, West
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int d = 0; // Starts facing North

        // Store obstacles in a HashSet using a 64-bit combined key
        Set<Long> obstacleSet = new HashSet<>();
        for (int[] obs : obstacles) {
            long key = (((long) obs[0] + 30000) << 32) | ((long) obs[1] + 30000);
            obstacleSet.add(key);
        }

        int x = 0, y = 0;
        int maxDistSq = 0;

        for (int cmd : commands) {
            if (cmd == -1) {
                d = (d + 1) % 4; // Turn right 90 degrees
            } else if (cmd == -2) {
                d = (d + 3) % 4; // Turn left 90 degrees
            } else {
                for (int step = 0; step < cmd; step++) {
                    int nextX = x + dirs[d][0];
                    int nextY = y + dirs[d][1];
                    long nextKey = (((long) nextX + 30000) << 32) | ((long) nextY + 30000);

                    if (obstacleSet.contains(nextKey)) {
                        break; 
                    }

                    x = nextX;
                    y = nextY;
                    maxDistSq = Math.max(maxDistSq, x * x + y * y);
                }
            }
        }

        return maxDistSq;
    }
}