package com.jordan.datastructure.sort;

import java.lang.reflect.Array;
import java.util.Comparator;

public class RecursiveMergingSort<T> extends Sort<T> {


    @Override
    public T[] sort(T[] array, Comparator<T> comparator) {
        if (array.length <= 1) {
            return array;
        }
        mergeSort(array, array, comparator, 0, array.length - 1);
        return array;
    }

    public void mergeSort(T[] sourceArray, T[] dividedArray, Comparator<T> comparator, int start, int end) {
        if (start == end) {
            dividedArray[start] = sourceArray[start];
        } else {
            T[] dividedArrayTmp = (T[]) Array.newInstance(sourceArray.getClass().getComponentType(), sourceArray.length);
            int mid = (start + end) / 2;
            mergeSort(sourceArray, dividedArrayTmp, comparator, start, mid); //
            mergeSort(sourceArray, dividedArrayTmp, comparator, mid + 1, end); //
            merge(dividedArrayTmp, dividedArray, comparator, start, mid, end);
        }
    }

    public void merge(T[] sourceArray, T[] dividedArray, Comparator<T> comparator, int start, int mid, int end) {
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
        if(start <= mid){
            for(; remainedIndex <= mid - start; remainedIndex++){
                dividedArray[dividedIndex + remainedIndex] = sourceArray[start + remainedIndex];
            }
        }
        if(dividedMid <= end){
            for(; remainedIndex <= end - dividedMid; remainedIndex++){
                dividedArray[dividedIndex + remainedIndex] = sourceArray[dividedMid + remainedIndex];
            }
        }
    }
}

