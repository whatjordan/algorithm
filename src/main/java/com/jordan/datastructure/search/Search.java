package com.jordan.datastructure.search;

import com.jordan.datastructure.sort.FixedArrayBubbleSort;

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class Search {
    public static int sequentialSearchWithSentinel(Object[] array, Object key) {
        if (array == null || array.length == 0) {
            return -1;
        }
        Object sentinel = array[array.length - 1];
        int i = 0;
        while (!sentinel.equals(array[i])) {
            if (array[i].equals(key)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static <T extends Comparable> int binarySearch(T[] array, T key) {
        if (array == null || array.length == 0) {
            return -1;
        }
        T[] sortedArray = FixedArrayBubbleSort.sort(array, (t1, t2) -> t1.compareTo(t2));
        int high = array.length -1;
        int low = 0;
        while (high >= low) {
            int middle = (high + low) / 2;
            if (sortedArray[middle].compareTo(key) == 0) {
                return middle;
            }
            if (sortedArray[middle].compareTo(key) > 0) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }
}
