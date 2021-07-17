package com.jordan.datastructure.sort;

import java.util.Comparator;


public class SimpleSelectSort<T> extends Sort<T> {

    public T[] sort(T[] array, Comparator<T> comparator) {
        if (array.length <= 1) {
            return array;
        }
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            int j;
            for (j = i + 1; j < array.length; j++) {
                if (comparator.compare(array[minIndex], array[j]) > 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(array, i, minIndex);
            }
        }
        return array;
    }

}


