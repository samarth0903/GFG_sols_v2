import java.util.*;

class Solution {
    public boolean areAnagrams(Node root1, Node root2) {

        Queue<Node> q1 = new LinkedList<>();
        Queue<Node> q2 = new LinkedList<>();

        q1.add(root1);
        q2.add(root2);

        while (!q1.isEmpty() && !q2.isEmpty()) {

            int size1 = q1.size();
            int size2 = q2.size();

            if (size1 != size2) {
                return false;
            }

            HashMap<Integer, Integer> map = new HashMap<>();

            // Process current level
            for (int i = 0; i < size1; i++) {

                Node n1 = q1.poll();
                Node n2 = q2.poll();

                map.put(n1.data, map.getOrDefault(n1.data, 0) + 1);
                map.put(n2.data, map.getOrDefault(n2.data, 0) - 1);

                // Add children of first tree
                if (n1.left != null) q1.add(n1.left);
                if (n1.right != null) q1.add(n1.right);

                // Add children of second tree
                if (n2.left != null) q2.add(n2.left);
                if (n2.right != null) q2.add(n2.right);
            }

            // Check frequencies
            for (int count : map.values()) {
                if (count != 0) {
                    return false;
                }
            }
        }

        return q1.isEmpty() && q2.isEmpty();
    }
}