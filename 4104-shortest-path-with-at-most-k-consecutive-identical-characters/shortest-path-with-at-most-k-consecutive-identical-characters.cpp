#include <vector>
#include <string>
#include <queue>
#include <tuple>

using namespace std;

class Solution {
public:
    int shortestPath(int n, vector<vector<int>>& edges, string labels, int k) {
        if (n == 1) {
            return 0;
        }

        vector<vector<pair<int, int>>> adj(n);
        for (const auto& e : edges) {
            adj[e[0]].push_back({e[1], e[2]});
        }

        const long long INF = 1e18;
        vector<vector<long long>> dist(n, vector<long long>(k + 1, INF));
        using State = tuple<long long, int, int>;
        priority_queue<State, vector<State>, greater<State>> pq;

        dist[0][1] = 0;
        pq.push({0, 0, 1});

        while (!pq.empty()) {
            auto [d, u, consecutive] = pq.top();
            pq.pop();

            if (d > dist[u][consecutive]) {
                continue;
            }

            if (u == n - 1) {
                return d;
            }

            for (const auto& [v, w] : adj[u]) {
                int next_consecutive = (labels[v] == labels[u]) ? (consecutive + 1) : 1;

                if (next_consecutive <= k) {
                    if (d + w < dist[v][next_consecutive]) {
                        dist[v][next_consecutive] = d + w;
                        pq.push({dist[v][next_consecutive], v, next_consecutive});
                    }
                }
            }
        }

        return -1;
    }
};