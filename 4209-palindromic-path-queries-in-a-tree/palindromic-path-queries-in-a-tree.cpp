#include <vector>
#include <string>
#include <sstream>

using namespace std;

class Solution {
    struct Fenwick {
        int n;
        vector<int> tree;
        Fenwick(int n) : n(n), tree(n + 1, 0) {}

        void update(int i, int delta) {
            for (; i <= n; i += i & -i) {
                tree[i] ^= delta;
            }
        }

        int query(int i) {
            int sum = 0;
            for (; i > 0; i -= i & -i) {
                sum ^= tree[i];
            }
            return sum;
        }
    };

    vector<vector<int>> adj;
    vector<int> tin, tout;
    vector<vector<int>> up;
    int timer = 0;
    int LOG = 17;

    void dfs(int u, int p) {
        tin[u] = ++timer;
        up[u][0] = p;
        for (int i = 1; i < LOG; ++i) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }

        for (int v : adj[u]) {
            if (v != p) {
                dfs(v, u);
            }
        }
        tout[u] = timer;
    }

    bool is_ancestor(int u, int v) {
        return tin[u] <= tin[v] && tout[u] >= tout[v];
    }

    int get_lca(int u, int v) {
        if (is_ancestor(u, v)) return u;
        if (is_ancestor(v, u)) return v;

        for (int i = LOG - 1; i >= 0; --i) {
            if (!is_ancestor(up[u][i], v)) {
                u = up[u][i];
            }
        }
        return up[u][0];
    }

public:
    vector<bool> palindromePath(int n, vector<vector<int>>& edges, string s, vector<string>& queries) {
        adj.assign(n, vector<int>());
        for (const auto& e : edges) {
            adj[e[0]].push_back(e[1]);
            adj[e[1]].push_back(e[0]);
        }

        tin.resize(n);
        tout.resize(n);
        up.assign(n, vector<int>(LOG, 0));
        timer = 0;

        dfs(0, 0);

        Fenwick bit(n);
        for (int i = 0; i < n; ++i) {
            int mask = 1 << (s[i] - 'a');
            bit.update(tin[i], mask);
            bit.update(tout[i] + 1, mask);
        }

        vector<bool> ans;

        for (const string& q : queries) {
            stringstream ss(q);
            string type;
            ss >> type;

            if (type == "update") {
                int u;
                char c;
                ss >> u >> c;

                int old_mask = 1 << (s[u] - 'a');
                int new_mask = 1 << (c - 'a');
                int change = old_mask ^ new_mask;

                s[u] = c;
                bit.update(tin[u], change);
                bit.update(tout[u] + 1, change);
            } else {
                int u, v;
                ss >> u >> v;

                int lca = get_lca(u, v);
                int mask_u = bit.query(tin[u]);
                int mask_v = bit.query(tin[v]);
                int mask_lca = bit.query(tin[lca]);
                int node_lca_mask = 1 << (s[lca] - 'a');

                int path_mask = mask_u ^ mask_v ^ node_lca_mask;

                ans.push_back((path_mask & (path_mask - 1)) == 0);
            }
        }

        return ans;
    }
};