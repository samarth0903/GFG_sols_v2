class Solution {
    public int maxProduct(int[] arr, int k) {

        long[][] dp = new long[k + 1][2];

        // dp[i][0] = minimum product using i elements
        // dp[i][1] = maximum product using i elements

        for (int i = 0; i <= k; i++) {
            dp[i][0] = Long.MAX_VALUE;
            dp[i][1] = Long.MIN_VALUE;
        }

        dp[0][0] = 1;
        dp[0][1] = 1;

        for (int num : arr) {

            // Go backwards so that one element is used only once
            for (int j = k; j >= 1; j--) {

                if (dp[j - 1][1] == Long.MIN_VALUE) {
                    continue;
                }

                long a = dp[j - 1][0] * num;
                long b = dp[j - 1][1] * num;

                // Take minimum of both possibilities
                dp[j][0] = Math.min(dp[j][0], Math.min(a, b));

                // Take maximum of both possibilities
                dp[j][1] = Math.max(dp[j][1], Math.max(a, b));
            }
        }

        return (int) dp[k][1];
    }
}