package com.jordan.algorithm.string;

public class LevenshteinDistance {
    public static int[][] computeLcsTransformTable(String s1, String s2) {
        int[][] table = new int[s1.length() + 1][s2.length() + 1];
        //0 index means empty sub-sequence string
        for (int i = 0; i <= s1.length(); i++) {
            table[i][0] = 0;
        }
        for (int j = 0; j <= s2.length(); j++) {
            table[0][j] = 0;
        }
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Math.max(table[i - 1][j], table[i][j - 1]);
                }
            }
        }
        return table;
    }
}
