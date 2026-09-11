class Solution {
    public ArrayList<Integer> pattern(int n) {

        ArrayList<Integer> result = new ArrayList<>();

        solve(n, result, n);

        return result;
    }

    private void solve(int n, ArrayList<Integer> result, int original) {

        result.add(n);

        // Stop when n becomes <= 0
        if (n <= 0) {
            return;
        }

        // Decrease by 5
        solve(n - 5, result, original);

        // Add back while returning from recursion
        result.add(n);
    }
}