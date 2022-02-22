package com.jordan.datastructure;

import com.jordan.datastructure.tree.BinarySearchTree;
import com.jordan.datastructure.search.Search;
import org.junit.Assert;
import org.junit.Test;

public class SearchTest {

    @Test
    public void testSentinelLinearSearch() {
        final Integer[] elements = new Integer[]{1, 5, 3};
        Assert.assertEquals(2, Search.sentinelLinearSearch(elements, 3));
        Assert.assertEquals(0, Search.sentinelLinearSearch(elements, 1));
        Assert.assertEquals(-1, Search.sentinelLinearSearch(elements, 0));
    }

    @Test
    public void testSequentialSearchWithSentinel() {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5};
        Object index = Search.sequentialSearchWithSentinel(array, 3);
        Assert.assertEquals(2, index);
    }

    @Test
    public void testBinarySearch() {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5};
        Object index = Search.binarySearch(array, 3);
        Assert.assertEquals(2, index);
        array = new Integer[]{1, 2, 3, 5};
        index = Search.binarySearch(array, 4);
        Assert.assertEquals(-1, index);
        array = new Integer[]{1, 6, 3, 5};
        index = Search.binarySearch(array, 6);
        Assert.assertEquals(1, index);
    }

    @Test
    public void testInterpolationSearch() {
        Number[] array = new Number[]{1, 2, 3, 3.3, 3.1, 5};
        Object index = Search.interpolationSearch(array, 3.1);
        Assert.assertEquals(4, index);
        array = new Integer[]{1, 2, 3, 5};
        index = Search.interpolationSearch(array, 4);
        Assert.assertEquals(-1, index);
        array = new Integer[]{1, 2, 3, 5};
        index = Search.interpolationSearch(array, 1);
        Assert.assertEquals(0, index);
    }

    @Test
    public void testFibonacciSearch() {
        Double[] array = new Double[]{1.0, 2.0, 3.0, 3.3, 3.1, 5.0};
        Object index = Search.fibonacciSearch(array, 3.1);
        Assert.assertEquals(4, index);
        index = Search.fibonacciSearch(array, 5.0);
        Assert.assertEquals(5, index);
        index = Search.fibonacciSearch(array, 55.0);
        Assert.assertEquals(-1, index);
    }

    @Test
    public void testBinarySearchTree() {
        Double[] array = new Double[]{1.0, 2.0, 3.0, 3.3, 3.1, 5.0};
        BinarySearchTree<Double> tree = new BinarySearchTree<>();
        for (Double ele : array) {
            tree.insert(ele);
        }
        Assert.assertTrue(tree.contains(3.1));
        Assert.assertFalse(tree.contains(2.8));
        Assert.assertEquals(5.0, tree.max(), 0.1);
        Assert.assertEquals(1.0, tree.min(), 0.1);
        tree.delete(1.0);
        Assert.assertEquals(2.0, tree.min(), 0.1);
        tree.delete(2.0);
        tree.delete(3.0);
        tree.delete(3.1);
        tree.delete(3.3);
        Assert.assertEquals(tree.min(), tree.max(), 0.1);
        tree.insert(4.0);
        tree.delete(5.0);
        Assert.assertEquals(4.0, tree.max(), 0.1);
    }
}
