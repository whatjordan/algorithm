package com.jordan.datastructure.sort;

public class CountSort {

    public Integer[] sort(Integer[] array) {
        if (array.length <= 1) {
            return array;
        }
        Integer max = null;
        Integer min = null;
        for (Integer ele : array) {
            if (max == null || ele > max) {
                max = ele;
            }
            if (min == null || ele < min) {
                min = ele;
            }
        }
        int[] countArray = new int[max - min + 1];
        for (Integer ele : array) {
            countArray[ele - min] += 1;
        }

        int[] accumulationArray = countArray;
        for (int i = 1; i < accumulationArray.length; i++) {
            accumulationArray[i] = accumulationArray[i - 1] + accumulationArray[i];
        }
        // Example:
        // array {3, 4, 1} min:1 max:4
        // count        [0] [1] [2] [3]
        //               1   0   1   1
        // accumulation [0] [1] [2] [3]
        //               1   1   2   3
        // result:      [0] [1] [2]
        // steps:        1
        //                       4
        //                   3

        Integer[] result = new Integer[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            Integer value = array[i];
            //Because the orderedIndex is starting from 0, the accumulation element should subtracted 1 first, then it will be the corresponding position.
            Integer orderedIndex = accumulationArray[value - min] -= 1;
            result[orderedIndex] = value;
        }
        return result;
    }
}
