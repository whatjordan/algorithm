package com.jordan.datastructure;

import com.jordan.datastructure.sort.Sort;
import com.jordan.datastructure.sort.SortFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.jordan.datastructure.sort.SortFactory.SortType.*;


public class SortTest {
    private Integer[] array = new Integer[]{2, 4, 1, 2, 3};

    @Before
    public void before() {
        array = new Integer[]{2, 4, 1, 2, 3};
    }

    @Test
    public void testBubbleSort() {
        Sort<Integer> bubbleSort = SortFactory.getSort(BUBBLE);
        Integer[] result = bubbleSort.sort(array, Integer::compareTo);
        Assert.assertArrayEquals(result, new Integer[]{1, 2, 2, 3, 4});
    }

    @Test
    public void testSimpleSelectSort() {
        Sort<Integer> simpleSelectSort = SortFactory.getSort(SIMPLE_SELECT);
        Integer[] result = simpleSelectSort.sort(array, Integer::compareTo);
        Assert.assertArrayEquals(result, new Integer[]{1, 2, 2, 3, 4});
    }

    @Test
    public void testStraightInsertionSort() {
        Sort<Integer> straightInsertionSort = SortFactory.getSort(STRAIGHT_INSERTION);
        Integer[] result = straightInsertionSort.sort(array, Integer::compareTo);
        Assert.assertArrayEquals(result, new Integer[]{1, 2, 2, 3, 4});
    }

    @Test
    public void testShellSort() {
        Sort<Integer> shellSort = SortFactory.getSort(SHELL);
        Integer[] result = shellSort.sort(array, Integer::compareTo);
        Assert.assertArrayEquals(result, new Integer[]{1, 2, 2, 3, 4});
    }

    @Test
    public void testHeapSort() {
        Sort<Integer> heapSort = SortFactory.getSort(HEAP);
        Integer[] result = heapSort.sort(array, Integer::compareTo);
        Assert.assertArrayEquals(result, new Integer[]{1, 2, 2, 3, 4});
    }

    @Test
    public void testRecursiveMergingSort() {
        Sort<Integer> recursiveMergingSort = SortFactory.getSort(RECURSIVE_MERGING);
        Integer[] result = recursiveMergingSort.sort(array, Integer::compareTo);
        Assert.assertArrayEquals(result, new Integer[]{1, 2, 2, 3, 4});
    }

    @Test
    public void testLoopMergingSort() {
        Sort<Integer> loopMergingSort = SortFactory.getSort(LOOP_MERGING);
        Integer[] result = loopMergingSort.sort(array, Integer::compareTo);
        Assert.assertArrayEquals(result, new Integer[]{1, 2, 2, 3, 4});
    }

    @Test
    public void testQuickSort() {
        Sort<Integer> quickSort = SortFactory.getSort(QUICK);
        Integer[] result = quickSort.sort(array, Integer::compareTo);
        Assert.assertArrayEquals(result, new Integer[]{1, 2, 2, 3, 4});
        Integer[] testLargerArray = new Integer[100];
        Integer[] verifiedLagerArray = new Integer[100];
        for (int i = 0; i < 100; i++) {
            testLargerArray[99 - i] = i;
            verifiedLagerArray[i] = i;
        }
        result = quickSort.sort(testLargerArray, Integer::compareTo);
        Assert.assertArrayEquals(result, verifiedLagerArray);
    }
}
