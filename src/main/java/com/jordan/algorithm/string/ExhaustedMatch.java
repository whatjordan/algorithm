package com.jordan.algorithm.string;

public class ExhaustedMatch {

    public static int indexOf(String str, String pattern) {
        if (str == null || pattern == null || str.length() < pattern.length()) {
            return -1;
        }
        int i = 0;
        // i-j is for backing the index i to the begin matching place
        while (str.length() - i >= pattern.length()) {
            for (int j = 0; j < pattern.length(); j++) {
                if (str.charAt(i) == pattern.charAt(j)) {
                    if (j == pattern.length() - 1) {
                        return i - j;
                    }
                    i++;
                } else {
                    i = i - j + 1;
                    break;
                }
            }
        }
        return -1;
    }


}
