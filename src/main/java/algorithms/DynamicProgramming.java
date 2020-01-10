package algorithms;

import javafx.util.Pair;

public class DynamicProgramming {

    Pair<Integer, Integer> getLongestCommonSubsequence(String a, String b) {
        Integer length = 0, startingIndexOfFirstString = 0;
        int n = a.length(), m = b.length();
        int dp[][] = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    if (dp[i + 1][j + 1] > length) {
                        length = dp[i + 1][j + 1];
                        startingIndexOfFirstString = i;
                    }
                }
            }
        }
        return new Pair<>(length, startingIndexOfFirstString - length + 1);
    }
}
