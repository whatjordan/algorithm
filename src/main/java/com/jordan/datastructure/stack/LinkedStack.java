package com.jordan.datastructure.stack;

import com.jordan.datastructure.adt.AbstractLink;
import com.jordan.datastructure.adt.StackADT;

public class LinkedStack extends AbstractLink implements StackADT {

    private LinkedNode top;

    private int size = 0;

    public LinkedStack() {
        top = new LinkedNode(null, null);
    }

    @Override
    public void push(Object value) {
        LinkedNode linkedNode = new LinkedNode(null, value);
        if (top.next != null) {
            linkedNode.next = top.next;
        }
        top.next = linkedNode;
        size++;
    }

    @Override
    public Object pop() {
        LinkedNode cur = top.next;
        if (cur == null) {
            throw new IndexOutOfBoundsException();
        }
        top.next = cur.next;
        size--;
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
        return size;
    }

    @Override
    public void clear() {
        LinkedNode cur = top;
        while (cur != null) {
            cur.value = null;
            LinkedNode tmp = cur;
            cur = cur.next;
            tmp.next = null;
        }
        size = 0;
    }


}
