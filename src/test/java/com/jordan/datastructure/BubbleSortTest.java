package com.jordan.datastructure;

import org.junit.Assert;
import org.junit.Test;

import com.jordan.datastructure.sort.FixedArrayBubbleSort;

public class BubbleSortTest {
    private Integer[] array = new Integer[]{2, 4, 1, 3};

    @Test
    public void testSort() {
        Integer[] result = FixedArrayBubbleSort
                .sort(array, Integer::compareTo);
        Assert.assertArrayEquals(result, new Integer[]{1, 2, 3, 4});
    }

    @Test
    public void testInsert() {
        FixedArrayBubbleSort<Integer> bubbleSort = new FixedArrayBubbleSort<>(array);
        bubbleSort.insert(0, 5);
        Assert.assertArrayEquals(bubbleSort.getArray(), new Integer[]{5, 2, 4, 1, 3});
    }

    @Test
    public void testRemove() {
        FixedArrayBubbleSort<Integer> bubbleSort = new FixedArrayBubbleSort<>(array);
        bubbleSort.delete(0);
        Assert.assertArrayEquals(bubbleSort.getArray(), new Integer[]{4, 1, 3});
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testException() {
        FixedArrayBubbleSort<Integer> bubbleSort = new FixedArrayBubbleSort<>(array);
        bubbleSort.delete(-1);
    }

}
