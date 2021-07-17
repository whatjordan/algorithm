package com.jordan.datastructure.sort;

import java.util.Comparator;

public class StraightInsertionSort<T> extends Sort<T> {
    @Override
    public T[] sort(T[] array, Comparator<T> comparator) {
        if (array.length <= 1) {
            return array;
        }
        for (int i = 1; i < array.length; i++) {
            if (comparator.compare(array[i - 1], array[i]) > 0) {
                T temp = array[i];
                int j = i - 1;
                for (; j >= 0 && comparator.compare(array[j], temp) > 0; j--) {
                    // move all temp's left elements that are greater than the tmp one right step.
                    array[j + 1] = array[j];
                }
                // insert the temp to the position whose left element is just smaller than the temp.
                array[j + 1] = temp;
            }
        }
        return array;
    }
}
