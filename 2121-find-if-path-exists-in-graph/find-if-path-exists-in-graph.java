class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int[] e : edges) {
            union(parent, e[0], e[1]);
        }

        return find(parent, source) == find(parent, destination);
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    private void union(int[] parent, int a, int b) {
        int pa = find(parent, a);
        int pb = find(parent, b);
        if (pa != pb) {
            parent[pa] = pb;
        }
    }
}