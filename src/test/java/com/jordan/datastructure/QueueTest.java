package com.jordan.datastructure;

import com.jordan.datastructure.adt.QueueADT;
import com.jordan.datastructure.queue.ArrayQueue;
import com.jordan.datastructure.queue.LinkedQueue;
import org.junit.Assert;
import org.junit.Test;

public class QueueTest {
    @Test(expected =  IndexOutOfBoundsException.class)
    public void testArrayQueue() {
        QueueADT queueADT = new ArrayQueue(3);
        queueADT.enQueue(1);
        queueADT.enQueue(2);
        queueADT.enQueue(3);
        Assert.assertEquals(3, queueADT.size());
        queueADT.clear();
        Assert.assertNull(queueADT.getHead());
        Assert.assertEquals(0, queueADT.size());
        queueADT.enQueue(100);
        queueADT.enQueue(200);
        Assert.assertEquals(100, queueADT.deQueue());
        Assert.assertEquals(200, queueADT.getHead());
        Assert.assertEquals(1, queueADT.size());
        queueADT.enQueue(300);
        queueADT.enQueue(400);
        queueADT.enQueue(500);
    }

    @Test
    public void testLinkedQueue() {
        QueueADT queueADT = new LinkedQueue();
        queueADT.enQueue(1);
        queueADT.enQueue(2);
        queueADT.enQueue(3);
        Assert.assertEquals(queueADT.size(), 3);
        Assert.assertEquals(1, queueADT.deQueue());
        queueADT.clear();
        Assert.assertNull(queueADT.getHead());
        Assert.assertEquals(queueADT.size(), 0);
    }
}
