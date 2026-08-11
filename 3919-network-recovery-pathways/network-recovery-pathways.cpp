class Solution {
public:
    int findMaxPathScore(vector<vector<int>>& edges, vector<bool>& online, long long k) {
        int n = online.size();

        vector<int> candidate_costs;
        candidate_costs.reserve(edges.size());
        for (const auto& e : edges) {
            candidate_costs.push_back(e[2]);
        }
        sort(candidate_costs.begin(), candidate_costs.end());
        candidate_costs.erase(unique(candidate_costs.begin(), candidate_costs.end()), candidate_costs.end());

        vector<vector<pair<int, int>>> adj(n);
        vector<int> in_degree(n, 0);
        for (const auto& e : edges) {
            int u = e[0], v = e[1], cost = e[2];
            adj[u].push_back({v, cost});
            in_degree[v]++;
        }

        queue<int> q;
        for (int i = 0; i < n; ++i) {
            if (in_degree[i] == 0) {
                q.push(i);
            }
        }

        vector<int> topo_order;
        topo_order.reserve(n);
        while (!q.empty()) {
            int u = q.front();
            q.pop();
            topo_order.push_back(u);
            for (const auto& edge : adj[u]) {
                int v = edge.first;
                if (--in_degree[v] == 0) {
                    q.push(v);
                }
            }
        }

        const long long INF = 1e18;

        auto check = [&](int min_cost) -> bool {
            vector<long long> dp(n, INF);
            dp[0] = 0;

            for (int u : topo_order) {
                if (dp[u] == INF) continue;
                if (u != 0 && u != n - 1 && !online[u]) continue;

                for (const auto& edge : adj[u]) {
                    int v = edge.first;
                    int cost = edge.second;

                    if (cost >= min_cost) {
                        dp[v] = min(dp[v], dp[u] + cost);
                    }
                }
            }

            return dp[n - 1] <= k;
        };

        int low = 0, high = (int)candidate_costs.size() - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(candidate_costs[mid])) {
                ans = candidate_costs[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }
};