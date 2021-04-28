package com.jordan.datastructure.sort;

import java.util.Comparator;

import com.jordan.datastructure.list.FixedArrayList;

public class FixedArrayBubbleSort<T> extends FixedArrayList<T> {

    public FixedArrayBubbleSort(T[] array) {
        super(array);
    }

    public static <T> T[] sort(T[] array, Comparator<T> comparator) {

        if (array.length < 1) {
            return array;
        }

        for (int i = array.length - 1; i > 0; i--) {
            boolean hasSwap = false;
            for (int j = 0; j < i; j++) {
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

    public static <T> FixedArrayList<T> sort(FixedArrayList<T> array, Comparator<T> comparator) {

        if (array.size() < 1) {
            return array;
        }

        for (int i = array.size() - 1; i > 0; i--) {
            boolean hasSwap = false;
            for (int j = 0; j < i; j++) {
                if (comparator.compare(array.get(j), array.get(j + 1)) > 0) {
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

    private static <T> void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private static <T> void swap(FixedArrayList<T> array, int index1, int index2) {
        T temp = array.get(index1);
        array.replace(index1, array.get(index2));
        array.replace(index2, temp);
    }

}
