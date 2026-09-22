import java.util.*;

class Solution {
    public String findLongestWord(String s, List<String> d) {

        int n = s.length();

        // nextPos[c][i] = next index of character c from i
        int[][] nextPos = new int[26][n + 1];

        for (int c = 0; c < 26; c++) {
            Arrays.fill(nextPos[c], -1);
        }

        // Build next occurrence table
        for (int i = n - 1; i >= 0; i--) {

            for (int c = 0; c < 26; c++) {
                nextPos[c][i] = nextPos[c][i + 1];
            }

            int ch = s.charAt(i) - 'a';
            nextPos[ch][i] = i;
        }

        String ans = "";

        for (String word : d) {

            int pos = 0;
            boolean valid = true;

            for (int j = 0; j < word.length(); j++) {

                int ch = word.charAt(j) - 'a';

                if (pos > n || nextPos[ch][pos] == -1) {
                    valid = false;
                    break;
                }

                pos = nextPos[ch][pos] + 1;
            }

            if (valid) {

                if (word.length() > ans.length() ||
                   (word.length() == ans.length() &&
                    word.compareTo(ans) < 0)) {

                    ans = word;
                }
            }
        }

        return ans;
    }
}