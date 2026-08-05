class DSU {
public:
    vector<int> parent;
    int components;
    DSU(int n) : parent(n), components(n) {
        iota(parent.begin(), parent.end(), 0);
    }
    int find(int i) {
        if (parent[i] == i)
            return i;
        return parent[i] = find(parent[i]);
    }
    bool unite(int i, int j) {
        int root_i = find(i);
        int root_j = find(j);
        if (root_i != root_j) {
            parent[root_i] = root_j;
            components--;
            return true;
        }
        return false;
    }
};

class Solution {
public:
    int maxStability(int n, vector<vector<int>>& edges, int k) {
        DSU must_dsu(n);
        int must_count = 0;
        int min_must_strength = INT_MAX;

        for (const auto& edge : edges) {
            if (edge[3] == 1) {
                if (!must_dsu.unite(edge[0], edge[1])) {
                    return -1; // Cycle among mandatory edges
                }
                must_count++;
                min_must_strength = min(min_must_strength, edge[2]);
            }
        }

        auto check = [&](int target) -> bool {
            if (min_must_strength < target) return false;

            DSU dsu = must_dsu;
            int added_optional = 0;
            int upgrades_used = 0;

            // Phase 1: Try adding optional edges without upgrades
            for (const auto& edge : edges) {
                if (edge[3] == 0 && edge[2] >= target) {
                    if (dsu.unite(edge[0], edge[1])) {
                        added_optional++;
                    }
                }
            }

            // Phase 2: Try adding optional edges with upgrades
            for (const auto& edge : edges) {
                if (edge[3] == 0 && edge[2] < target && edge[2] * 2 >= target) {
                    if (upgrades_used < k && dsu.unite(edge[0], edge[1])) {
                        upgrades_used++;
                        added_optional++;
                    }
                }
            }

            return dsu.components == 1;
        };

        int low = 1, high = 200000;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }
};