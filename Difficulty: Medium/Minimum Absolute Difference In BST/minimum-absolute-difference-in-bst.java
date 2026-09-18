class Solution {
    int prev = -1;
    int ans = Integer.MAX_VALUE;

    public int absDiff(Node root) {
        inorder(root);
        return ans;
    }

    void inorder(Node root) {
        if (root == null) return;

        inorder(root.left);

        if (prev != -1) {
            ans = Math.min(ans, root.data - prev);
        }

        prev = root.data;

        inorder(root.right);
    }
}