package com.jordan.datastructure.sort;

import com.jordan.datastructure.sort.Sort;

import java.util.Comparator;

public class ShellSort<T> extends Sort<T> {
    //Marcin Ciura's gap sequence.
    private static int[] CIURA_GAPS = new int[]{1750, 701, 301, 132, 57, 23, 10, 4, 1};

    @Override
    public T[] sort(T[] array, Comparator<T> comparator) {
        if (array.length <= 1) {
            return array;
        }
        for (int gap : CIURA_GAPS) {
            for (int i = gap; i < array.length; i++) {
                if (comparator.compare(array[i - gap], array[i]) > 0) {
                    T temp = array[i];
                    int j = i - gap;
                    for (; j >= 0 && comparator.compare(array[j], temp) > 0; j -= gap) {
                        // move index j's element to the next position in the group divided by the gap.
                        array[j + gap] = array[j];
                    }
                    // insert the temp to the correct position.
                    array[j + gap] = temp;
                }
            }
        }
        int indexOfGaps = 0;
        do {
            int gap = CIURA_GAPS[indexOfGaps];
            for (int i = gap; i < array.length; i++) {
                if (comparator.compare(array[i], array[i]) > 0) {
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
            indexOfGaps++;
        } while (indexOfGaps < CIURA_GAPS.length);

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
