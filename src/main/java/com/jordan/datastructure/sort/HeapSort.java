package com.jordan.datastructure.sort;

import com.jordan.datastructure.list.FixedArrayList;

import java.util.Comparator;

public class HeapSort<T> extends Sort<T> {
    @Override
    public T[] sort(T[] array, Comparator<T> comparator) {
        if (array.length <= 1) {
            return array;
        }
        //For the binary heap sort, the first index should be 1.
        int originalArrayLength = array.length;
        FixedArrayList<T> tmpFixedArrayList = new FixedArrayList<>(array);
        tmpFixedArrayList.insert(0, null);
        T[] tmpArray = tmpFixedArrayList.getArray();
        for (int i = originalArrayLength / 2; i > 0; i--) {
            doMaxHeap(tmpArray, i, originalArrayLength, comparator);
        }
        //After adjusting the array to be a max heap, the root should be the largest value.
        for (int i = originalArrayLength; i > 1; i--) {
            //Swap the root and the bottom, then doMaxHeap for adjusting the sub array to be a max heap
            swap(tmpArray, 1, i);
            doMaxHeap(tmpArray, 1, i - 1, comparator);
        }
        tmpFixedArrayList = new FixedArrayList<>(tmpArray);
        tmpFixedArrayList.delete(0);
        return tmpFixedArrayList.getArray();
    }

    private void doMaxHeap(T[] array, int rootIndex, int length, Comparator<T> comparator) {
        T temp = array[rootIndex];
        for (int childIndex = 2 * rootIndex; childIndex <= length; childIndex *= 2) {
            // First, find the left child index.
            if (childIndex < length && comparator.compare(array[childIndex], array[childIndex + 1]) < 0) {
                childIndex++; // if left child is larger than the right child, use the right child.
            }
            if (comparator.compare(temp, array[childIndex]) >= 0) {
                // If the root is larger than the child, then the partial order is correct, skip the process.
                break;
            }
            // The root is smaller than child, assign the child value to root position.
            array[rootIndex] = array[childIndex];
            // Go down to the child, and get ready for another loop
            rootIndex = childIndex;
        }
        // The rootIndex should be the empty position after finishing the loop,
        // and it should be assigned to the temp value for switching the largest value.
        array[rootIndex] = temp;
    }
}
