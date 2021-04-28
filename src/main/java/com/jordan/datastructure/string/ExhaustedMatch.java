package com.jordan.datastructure.string;

public class ExhaustedMatch {

    public static int indexOf(String str, String pattern) {
        if (str == null || pattern == null || str.length() < pattern.length()) {
            return -1;
        }
        int i = 0;
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
