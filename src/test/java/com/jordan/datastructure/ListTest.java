package com.jordan.datastructure;


import com.jordan.datastructure.list.CircularLinkedList;
import com.jordan.datastructure.list.LinkedList;
import com.jordan.datastructure.list.SimpleLinkedList;
import com.jordan.datastructure.list.StaticList;
import org.junit.Assert;
import org.junit.Test;

public class ListTest {
    @Test
    public void testCircularLinkedList() {
        CircularLinkedList circularLinkedList1 = new CircularLinkedList();
        circularLinkedList1.add(1);
        circularLinkedList1.add(2);
        CircularLinkedList circularLinkedList2 = new CircularLinkedList();
        circularLinkedList2.add(3);
        circularLinkedList2.add(4);
        circularLinkedList1.merge(circularLinkedList2);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4}, circularLinkedList1.getArray());
        circularLinkedList1.delete(0);
        Assert.assertArrayEquals(new Integer[]{2, 3, 4}, circularLinkedList1.getArray());
        circularLinkedList1.insert(3, 1);
        Assert.assertArrayEquals(new Integer[]{2, 3, 4, 1}, circularLinkedList1.getArray());
        circularLinkedList1.clear();
        Assert.assertEquals(0, circularLinkedList1.size());
    }


    @Test
    public void testStaticList() {
        StaticList staticList = new StaticList();
        staticList.add("A");
        staticList.add("B");
        staticList.add("C");
        Assert.assertTrue(staticList.contains("A"));
        staticList.delete(2);
        Assert.assertFalse(staticList.contains("C"));
        Assert.assertEquals(2, staticList.size());
        staticList.insert(0, "x");
        staticList.insert(0, "y");
        Assert.assertArrayEquals(new String[]{"y", "x", "A", "B"}, staticList.getArray());
    }

    @Test
    public void testSimpleLinkedList() {
        SimpleLinkedList simpleLinkedList = new SimpleLinkedList();
        simpleLinkedList.add(1);
        simpleLinkedList.add(2);
        simpleLinkedList.add(3);
        simpleLinkedList.insert(3, 4);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4}, simpleLinkedList.getArray());
        simpleLinkedList.clear();
        Assert.assertEquals(0, simpleLinkedList.size());
        simpleLinkedList.add(5);
        Assert.assertEquals(5, simpleLinkedList.get(0));
    }

    @Test
    public void testLinkedList() {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.insert(3, 4);
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 4}, list.getArray());
        list.clear();
        Assert.assertEquals(0, list.size());
        list.add(5);
        Assert.assertEquals(5, list.get(0));
    }

}
