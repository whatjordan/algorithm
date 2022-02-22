package com.jordan.datastructure.sort;

public class RadixSort {

    /**
     * Only accept ASCII String
     *
     * @param array
     * @return
     */
    public String[] sort(String[] array) {
        if (array.length <= 1) {
            return array;
        }
        int maxElementLength = 0;
        for (String ele : array) {
            if (ele.length() > maxElementLength) {
                maxElementLength = ele.length();
            }
        }
        int charIndex = 0;
        String[][] bucket = new String[127][127];
        int[] bucketCounts = new int[127];
        while (charIndex < maxElementLength) {
            for (String ele : array) {
                int order = 0;
                if (ele.length() > charIndex) {
                    char c = ele.charAt(charIndex);
                    order = c % 128;
                }
                bucket[order][bucketCounts[order]] = ele;
                bucketCounts[order]++;
            }
            int arrayIndex = 0;
            for (int i = 0; i < 127; i++) {
                if (bucketCounts[i] != 0) {
                    for (int j = 0; j < bucketCounts[i]; j++) {
                        array[arrayIndex] = bucket[i][j];
                        arrayIndex++;
                    }
                }
                bucketCounts[i] = 0;
            }
            charIndex++;
        }
        return array;
    }
}
