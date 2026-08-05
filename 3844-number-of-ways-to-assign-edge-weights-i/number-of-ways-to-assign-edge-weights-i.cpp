class Solution {
public:
    int assignEdgeWeights(vector<vector<int>>& edges) {
        int n = edges.size() + 1;
        vector<vector<int>> adj(n + 1);
        for (const auto& e : edges) {
            adj[e[0]].push_back(e[1]);
            adj[e[1]].push_back(e[0]);
        }

        queue<int> q;
        vector<int> dist(n + 1, -1);
        
        q.push(1);
        dist[1] = 0;
        int max_depth = 0;

        while (!q.empty()) {
            int u = q.front();
            q.pop();

            max_depth = max(max_depth, dist[u]);

            for (int v : adj[u]) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.push(v);
                }
            }
        }

        if (max_depth == 0) return 0;

        const int MOD = 1e9 + 7;
        long long ans = 1;
        for (int i = 0; i < max_depth - 1; ++i) {
            ans = (ans * 2) % MOD;
        }

        return ans;
    }
};