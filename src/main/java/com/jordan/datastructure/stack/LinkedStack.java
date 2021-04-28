package com.jordan.datastructure.stack;

import com.jordan.datastructure.adt.AbstractLink;
import com.jordan.datastructure.adt.StackADT;

public class LinkedStack extends AbstractLink implements StackADT {

    private Node top;

    public LinkedStack() {
        top = new Node(null, null);
    }

    @Override
    public void push(Object value) {
        Node node = new Node(null, value);
        if (top.next != null) {
            node.next = top.next;
        }
        top.next = node;
    }

    @Override
    public Object pop() {
        Node cur = top.next;
        if (cur == null) {
            throw new IndexOutOfBoundsException();
        }
        top.next = cur.next;
        return cur.value;
    }

    @Override
    public Object peek() {
        if (top.next != null) {
            return top.next.value;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        int count = 0;
        Node cur = top.next;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    @Override
    public void clear() {
        Node cur = top;
        while (cur != null) {
            cur.value = null;
            Node tmp = cur;
            cur = cur.next;
            tmp.next = null;
        }
    }


}
