package com.jordan.datastructure;

import com.jordan.datastructure.search.Search;
import org.junit.Assert;
import org.junit.Test;

public class SearchTest {
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
        array = new Integer[]{1, 2, 3, 5};
        index = Search.binarySearch(array, 1);
        Assert.assertEquals(0, index);
    }
}
