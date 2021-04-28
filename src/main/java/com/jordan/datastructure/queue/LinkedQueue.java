package com.jordan.datastructure.queue;

import com.jordan.datastructure.adt.AbstractLink;
import com.jordan.datastructure.adt.QueueADT;

public class LinkedQueue extends AbstractLink implements QueueADT {

    private Node rear;
    private Node front;

    public LinkedQueue() {
        rear = new Node(null, null);
        front = new Node(null, null);
    }

    @Override
    public Object getHead() {
        return front.next;
    }

    @Override
    public void enQueue(Object ele) {
        Node node = new Node(null, ele);
        if (rear.next != null) {
            rear.next.next = node;
        }
        if (front.next == null) {
            front.next = node;
        }
        rear.next = node;
    }

    @Override
    public Object deQueue() {
        if (front.next == null) {
            throw new IndexOutOfBoundsException();
        }
        Node cur = front.next;
        front.next = cur.next;
        if (cur == rear.next) {
            rear.next = front.next;
        }
        return cur.value;
    }

    @Override
    public int size() {
        int count = 0;
        Node cur = front;
        while (cur.next != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    @Override
    public void clear() {
        Node cur = front;
        while (cur != null) {
            cur.value = null;
            Node tmp = cur;
            cur = cur.next;
            tmp.next = null;
        }
        rear.next = null;
    }
}
