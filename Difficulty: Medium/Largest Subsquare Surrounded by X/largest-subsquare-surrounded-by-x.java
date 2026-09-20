class Solution {
    public int largestSubsquare(char mat[][]) {
        int n = mat.length;

        int[][] right = new int[n][n];
        int[][] down = new int[n][n];

        // Step 1: Calculate consecutive X to the right and down
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                if (mat[i][j] == 'X') {

                    right[i][j] = 1;
                    down[i][j] = 1;

                    if (j + 1 < n)
                        right[i][j] += right[i][j + 1];

                    if (i + 1 < n)
                        down[i][j] += down[i + 1][j];
                }
            }
        }

        // Step 2: Check squares from largest to smallest
        for (int size = n; size >= 1; size--) {

            for (int i = 0; i <= n - size; i++) {
                for (int j = 0; j <= n - size; j++) {

                    int bottom = i + size - 1;
                    int rightCol = j + size - 1;

                    // Check all four boundaries
                    if (right[i][j] >= size &&
                        down[i][j] >= size &&
                        right[bottom][j] >= size &&
                        down[i][rightCol] >= size) {

                        return size;
                    }
                }
            }
        }

        return 0;
    }
}