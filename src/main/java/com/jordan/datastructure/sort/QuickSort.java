package com.jordan.datastructure.sort;

import java.util.Comparator;

public class QuickSort<T> extends Sort<T> {

    // If the array size is small (7 or 50), Straight insertion performance will better than Quick sort.
    private final int STRAIGHT_INSERTION_THRESHOLD = 7;

    @Override
    public T[] sort(T[] array, Comparator<T> comparator) {
        if (array.length <= 1) {
            return array;
        }
        return betterRecursiveSort(array, comparator, 0, array.length - 1);
    }

    private T[] recursiveSort(T[] array, Comparator<T> comparator, int start, int end) {
        if (start < end) {
            int pivot = getPivotAndBetterPartiallySort(array, comparator, start, end);
            recursiveSort(array, comparator, start, pivot - 1);
            recursiveSort(array, comparator, pivot + 1, end);
        }
        return array;
    }

    private T[] betterRecursiveSort(T[] array, Comparator<T> comparator, int start, int end) {
        // Combine two sorts will improve the performance.
        if(end - start < STRAIGHT_INSERTION_THRESHOLD){
            Sort<T> StraightInsertionSort = SortFactory.getSort(SortFactory.SortType.STRAIGHT_INSERTION);
            return StraightInsertionSort.sort(array, comparator);
        }
        while (start < end) {
            int pivot = getPivotAndBetterPartiallySort(array, comparator, start, end);
            betterRecursiveSort(array, comparator, start, pivot - 1);
            // By setting the start index to pivot + 1, and iterating the pivot. The 'betterRecursiveSort' method
            // are the same of recursively sorting the right part of the array.
            start = pivot + 1;
        }
        return array;
    }

    private int getPivotAndBetterPartiallySort(T[] array, Comparator<T> comparator, int start, int end) {
        // Find the median-of-three, and to be the pivotKey, balance the partition action.
        int mid = start + (end - start) / 2;
        if (comparator.compare(array[start], array[end]) > 0) {
            swap(array, start, end);
        }
        if (comparator.compare(array[mid], array[end]) > 0) {
            swap(array, mid, end);
        }
        if (comparator.compare(array[mid], array[start]) > 0) {
            swap(array, mid, start);
        }
        //
        T pivotKey = array[start];
        // Instead of swapping, Use replacement action.
        while (start < end) {
            while (start < end && comparator.compare(array[end], pivotKey) >= 0) {
                end--;
            }
            array[start] = array[end];
            while (start < end && comparator.compare(array[start], pivotKey) <= 0) {
                start++;
            }
            array[end] = array[start];
        }
        array[start] = pivotKey;
        return start;
    }

    private int getPivotAndPartiallySort(T[] array, Comparator<T> comparator, int start, int end) {
        T pivotKey = array[start];
        while (start < end) {
            while (start < end && comparator.compare(array[end], pivotKey) >= 0) {
                end--;
            }
            swap(array, start, end);
            while (start < end && comparator.compare(array[start], pivotKey) <= 0) {
                start++;
            }
            swap(array, start, end);
        }
        array[start] = pivotKey;
        return start;
    }
}
