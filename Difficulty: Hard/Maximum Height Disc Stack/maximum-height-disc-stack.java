import java.util.*;

class Solution {
    public int maxStackHeight(int[] r, int[] h) {
        int n = r.length;

        int[][] discs = new int[n][2];

        for (int i = 0; i < n; i++) {
            discs[i][0] = r[i];
            discs[i][1] = h[i];
        }

        // Sort by radius
        Arrays.sort(discs, (a, b) -> Integer.compare(a[0], b[0]));

        // Coordinate compression of heights
        int[] heights = h.clone();
        Arrays.sort(heights);

        int m = 0;
        for (int x : heights) {
            if (m == 0 || heights[m - 1] != x) {
                heights[m++] = x;
            }
        }

        int[] bit = new int[m + 1];
        int ans = 0;

        int i = 0;

        while (i < n) {
            int j = i;

            // Find all discs having same radius
            while (j < n && discs[j][0] == discs[i][0]) {
                j++;
            }

            int[] best = new int[j - i];

            // Query first, don't update yet.
            // This prevents same-radius discs from stacking.
            for (int k = i; k < j; k++) {
                int height = discs[k][1];

                int pos = lowerBound(heights, m, height);

                // Query heights strictly smaller than current height
                best[k - i] = query(bit, pos);
            }

            // Now update BIT
            for (int k = i; k < j; k++) {
                int height = discs[k][1];

                int pos = lowerBound(heights, m, height) + 1;

                int value = best[k - i] + height;

                update(bit, pos, value);

                ans = Math.max(ans, value);
            }

            i = j;
        }

        return ans;
    }

    // Maximum value in [1 ... index]
    private int query(int[] bit, int index) {
        int max = 0;

        while (index > 0) {
            max = Math.max(max, bit[index]);
            index -= index & -index;
        }

        return max;
    }

    // Update position
    private void update(int[] bit, int index, int value) {
        while (index < bit.length) {
            bit[index] = Math.max(bit[index], value);
            index += index & -index;
        }
    }

    // First index where arr[index] >= target
    private int lowerBound(int[] arr, int n, int target) {
        int low = 0;
        int high = n;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] < target)
                low = mid + 1;
            else
                high = mid;
        }

        return low;
    }
}