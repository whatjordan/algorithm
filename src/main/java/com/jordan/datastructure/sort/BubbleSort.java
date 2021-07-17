package com.jordan.datastructure.sort;

import java.util.Comparator;

public class BubbleSort<T> extends Sort<T> {

    public T[] sort(T[] array, Comparator<T> comparator) {
        if (array.length <= 1) {
            return array;
        }

        for (int i = 0; i < array.length - 1; i++) {
            boolean hasSwap = false;
            //From bottom to top, let the smallest element float to the surface by swapping.
            for (int j = array.length - 2; j >= i; j--) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    hasSwap = true;
                    swap(array, j, j + 1);
                }
            }
            if (!hasSwap) {
                return array;
            }
        }
        return array;
    }

}
