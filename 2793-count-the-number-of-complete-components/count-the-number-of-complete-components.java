import java.util.ArrayList;
import java.util.List;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponentCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                dfs(i, adj, visited, component);

                int vCount = component.size();
                int totalEdges = 0;

                for (int u : component) {
                    totalEdges += adj.get(u).size();
                }

                // In an undirected graph, sum of degrees = 2 * totalEdges in the component
                // A complete graph with k vertices has exactly k * (k - 1) / 2 edges
                if (totalEdges == vCount * (vCount - 1)) {
                    completeComponentCount++;
                }
            }
        }

        return completeComponentCount;
    }

    private void dfs(int node, List<List<Integer>> adj, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, component);
            }
        }
    }
}