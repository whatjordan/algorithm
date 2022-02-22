package com.jordan.datastructure.queue;

import com.jordan.datastructure.adt.AbstractLink;
import com.jordan.datastructure.adt.QueueADT;

public class LinkedQueue extends AbstractLink implements QueueADT {

    private LinkedNode rear;
    private LinkedNode front;

    public LinkedQueue() {
        rear = new LinkedNode(null, null);
        front = new LinkedNode(null, null);
    }

    @Override
    public Object getHead() {
        return front.next;
    }

    @Override
    public void enQueue(Object ele) {
        LinkedNode linkedNode = new LinkedNode(null, ele);
        if (rear.next != null) {
            rear.next.next = linkedNode;
        }
        if (front.next == null) {
            front.next = linkedNode;
        }
        rear.next = linkedNode;
    }

    @Override
    public Object deQueue() {
        if (front.next == null) {
            throw new IndexOutOfBoundsException();
        }
        LinkedNode cur = front.next;
        front.next = cur.next;
        if (cur == rear.next) {
            rear.next = front.next;
        }
        return cur.value;
    }

    @Override
    public int size() {
        int count = 0;
        LinkedNode cur = front;
        while (cur.next != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    @Override
    public void clear() {
        LinkedNode cur = front;
        while (cur != null) {
            cur.value = null;
            LinkedNode tmp = cur;
            cur = cur.next;
            tmp.next = null;
        }
        rear.next = null;
    }
}
