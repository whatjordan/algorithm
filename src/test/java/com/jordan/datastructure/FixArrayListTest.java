package com.jordan.datastructure;

import com.jordan.datastructure.list.FixedArrayList;
import org.junit.Assert;
import org.junit.Test;

public class FixArrayListTest {
    private Integer[] array = new Integer[]{2, 4, 1, 3};


    @Test
    public void testInsert() {
        FixedArrayList<Integer> fixedArrayList = new FixedArrayList<>(array);
        fixedArrayList.insert(0, 5);
        Assert.assertArrayEquals(fixedArrayList.getArray(), new Integer[]{5, 2, 4, 1, 3});
    }

    @Test
    public void testRemove() {
        FixedArrayList<Integer> fixedArrayList = new FixedArrayList<>(array);
        fixedArrayList.delete(0);
        Assert.assertArrayEquals(fixedArrayList.getArray(), new Integer[]{4, 1, 3});
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testException() {
        FixedArrayList<Integer> fixedArrayList = new FixedArrayList<>(array);
        fixedArrayList.delete(-1);
    }

}
