class Solution {
    public int partyHouse(ArrayList<ArrayList<Integer>> adj) {
        // Find the farthest node from node 1
        int[] first = bfs(1, adj);
        int farthestNode = first[0];

        // Find the diameter of the tree
        int[] second = bfs(farthestNode, adj);
        int diameter = second[1];

        // Radius = ceil(diameter / 2)
        return (diameter + 1) / 2;
    }

    private int[] bfs(int start, ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size();

        boolean[] visited = new boolean[n + 1];
        int[] distance = new int[n + 1];

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        visited[start] = true;

        int farthestNode = start;
        int maxDistance = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : adj.get(current - 1)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distance[neighbor] = distance[current] + 1;

                    queue.offer(neighbor);

                    if (distance[neighbor] > maxDistance) {
                        maxDistance = distance[neighbor];
                        farthestNode = neighbor;
                    }
                }
            }
        }

        return new int[]{farthestNode, maxDistance};
    }
}