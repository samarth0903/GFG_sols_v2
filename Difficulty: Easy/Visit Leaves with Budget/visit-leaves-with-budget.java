import java.util.*;

class Solution {
    public int getCount(Node root, int k) {
        if (root == null || k <= 0) {
            return 0;
        }

        ArrayList<Integer> costs = new ArrayList<>();

        Queue<Node> queue = new LinkedList<>();
        Queue<Integer> level = new LinkedList<>();

        queue.add(root);
        level.add(1);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            int currLevel = level.poll();

            // Check if current node is a leaf
            if (curr.left == null && curr.right == null) {
                costs.add(currLevel);
            }

            // Add left child
            if (curr.left != null) {
                queue.add(curr.left);
                level.add(currLevel + 1);
            }

            // Add right child
            if (curr.right != null) {
                queue.add(curr.right);
                level.add(currLevel + 1);
            }
        }

        // Visit cheapest leaves first
        Collections.sort(costs);

        int count = 0;
        int totalCost = 0;

        for (int cost : costs) {
            if (totalCost + cost > k) {
                break;
            }

            totalCost += cost;
            count++;
        }

        return count;
    }
}