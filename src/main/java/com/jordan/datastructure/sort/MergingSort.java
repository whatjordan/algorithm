package com.jordan.datastructure.sort;

import java.util.Comparator;

public abstract class MergingSort<T> extends Sort<T> {
    protected void merge(T[] sourceArray, T[] dividedArray, Comparator<T> comparator, int start, int mid, int end) {
        int dividedIndex = start;
        int dividedMid = mid + 1;
        int remainedIndex = 0;
        for (; start <= mid && dividedMid <= end; dividedIndex++) {
            if (comparator.compare(sourceArray[start], sourceArray[dividedMid]) < 0) {
                dividedArray[dividedIndex] = sourceArray[start++];
            } else {
                dividedArray[dividedIndex] = sourceArray[dividedMid++];
            }
        }
        if (start <= mid) {
            for (; remainedIndex <= mid - start; remainedIndex++) {
                dividedArray[dividedIndex + remainedIndex] = sourceArray[start + remainedIndex];
            }
        }
        if (dividedMid <= end) {
            for (; remainedIndex <= end - dividedMid; remainedIndex++) {
                dividedArray[dividedIndex + remainedIndex] = sourceArray[dividedMid + remainedIndex];
            }
        }
    }
}
