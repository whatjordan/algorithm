package com.jordan.algorithm.string;

public class LongestCommonSubSequence {

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

    public static String findLCS(String s1, String s2) {
        int[][] table = computeLcsTransformTable(s1, s2);
        // Start from the right bottom of LCS length table
        return assembleLCS(s1, s2, table, s1.length(), s2.length());
    }

    private static String assembleLCS(String s1, String s2, int[][] table, int s1TableIndex, int s2TableIndex) {
        //Because of String index is from 0, and the table record an empty string in index 0.
        //So, when we check the string, we need to minus 1; Otherwise, we need to add an empty char in the head of the string.
        int s1StringIndex = s1TableIndex - 1;
        int s2StringIndex = s2TableIndex - 1;
        if (s1TableIndex == 0 || s2TableIndex == 0 || table[s1TableIndex][s2TableIndex] == 0) {
            return "";
        }

        // If table[s1TableIndex][s2TableIndex] != 0, it means that we still need to find the LCS suffix
        // and append it to the returned string.
        if (s1.charAt(s1StringIndex) == s2.charAt(s2StringIndex)) {
            // move to one left and one up index of table.
            return assembleLCS(s1, s2, table, s1TableIndex - 1, s2TableIndex - 1) + s1.charAt(s1StringIndex);
        } else {
            // find the longer length of LCS table and move cur to the position (one left or one up index).
            if (table[s1TableIndex - 1][s2TableIndex] > table[s1TableIndex][s2TableIndex - 1]) {
                return assembleLCS(s1, s2, table, s1TableIndex - 1, s2TableIndex);
            } else {
                return assembleLCS(s1, s2, table, s1TableIndex, s2TableIndex - 1);
            }
        }
    }
}
