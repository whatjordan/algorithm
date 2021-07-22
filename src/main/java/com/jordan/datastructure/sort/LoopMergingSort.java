package com.jordan.datastructure.sort;

import com.jordan.datastructure.list.FixedArrayList;

import java.lang.reflect.Array;
import java.util.Comparator;

public class LoopMergingSort<T> extends MergingSort<T> {
    @Override
    public T[] sort(T[] array, Comparator<T> comparator) {
        int groupingIndex = 1;
        int originalLength = array.length;
        //For the binary loop merging sort, the first index should be 1.
        FixedArrayList<T> tmpFixedArrayList = new FixedArrayList<>(array);
        tmpFixedArrayList.insert(0, null);
        array = tmpFixedArrayList.getArray();
        T[] tmpArray = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);
        while (groupingIndex < originalLength) {
            mergePartially(array, tmpArray, comparator, groupingIndex, originalLength);
            groupingIndex *= 2;
            mergePartially(tmpArray, array, comparator, groupingIndex, originalLength);
            groupingIndex *= 2;
        }
        tmpFixedArrayList = new FixedArrayList<>(array);
        tmpFixedArrayList.delete(0);
        return tmpFixedArrayList.getArray();
    }

    private void mergePartially(T[] sourceArray, T[] tmpArray, Comparator<T> comparator, int groupingIndex, int length) {
        int i = 1;
        while (i <= length - 2 * groupingIndex + 1) {
            merge(sourceArray, tmpArray, comparator, i, i + groupingIndex - 1, i + 2 * groupingIndex - 1);
            i = i + 2 * groupingIndex;
        }
        if (i < length - groupingIndex + 1) {
            merge(sourceArray, tmpArray, comparator, i, i + groupingIndex - 1, length);
        } else {
            for (int j = i; j <= length; j++) {
                tmpArray[j] = sourceArray[j];
            }
        }
    }

}
