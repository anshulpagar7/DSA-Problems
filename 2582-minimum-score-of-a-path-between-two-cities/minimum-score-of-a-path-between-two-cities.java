import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int minScore(int n, int[][] roads) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int d = road[2];
            adj.get(u).add(new int[]{v, d});
            adj.get(v).add(new int[]{u, d});
        }

        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        visited[1] = true;

        int minScore = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int[] edge : adj.get(curr)) {
                int neighbor = edge[0];
                int weight = edge[1];

                minScore = Math.min(minScore, weight);

                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        return minScore;
    }
}