import java.util.*;

class Solution {
    public int dominantPairs(int[] arr) {
        int n = arr.length;
        int half = n / 2;

        int[] first = new int[half];
        int[] second = new int[half];

        for (int i = 0; i < half; i++) {
            first[i] = arr[i];
            second[i] = arr[i + half];
        }

        Arrays.sort(first);
        Arrays.sort(second);

        int count = 0;
        int j = 0;

        for (int i = 0; i < half; i++) {
            while (j < half && (long) first[i] >= 5L * second[j]) {
                j++;
            }

            count += j;
        }

        return count;
    }
}