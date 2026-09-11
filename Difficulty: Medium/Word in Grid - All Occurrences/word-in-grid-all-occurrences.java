class Solution {
    public ArrayList<ArrayList<Integer>> searchWord(char[][] mat, String word) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        int n = mat.length;
        int m = mat[0].length;
        int len = word.length();

        // 8 possible directions
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (mat[i][j] != word.charAt(0))
                    continue;

                // Try all 8 directions
                for (int d = 0; d < 8; d++) {
                    int r = i;
                    int c = j;
                    boolean found = true;

                    for (int k = 1; k < len; k++) {
                        r += dr[d];
                        c += dc[d];

                        if (r < 0 || r >= n || c < 0 || c >= m ||
                            mat[r][c] != word.charAt(k)) {
                            found = false;
                            break;
                        }
                    }

                    if (found) {
                        ArrayList<Integer> position = new ArrayList<>();
                        position.add(i);
                        position.add(j);
                        ans.add(position);

                        // Same starting position should be added only once
                        break;
                    }
                }
            }
        }

        return ans;
    }
}