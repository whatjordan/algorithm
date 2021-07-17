package com.jordan.datastructure.sort;

import com.jordan.datastructure.list.FixedArrayList;

import java.util.Comparator;

public abstract class Sort<T> {
    public static <T> void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static <T> void swap(FixedArrayList<T> array, int index1, int index2) {
        T temp = array.get(index1);
        array.replace(index1, array.get(index2));
        array.replace(index2, temp);
    }

    public FixedArrayList<T> sort(FixedArrayList<T> array, Comparator<T> comparator) {
        return new FixedArrayList<>(sort(array.getArray(), comparator));
    }

    public abstract T[] sort(T[] array, Comparator<T> comparator);
}
