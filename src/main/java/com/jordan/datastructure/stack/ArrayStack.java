package com.jordan.datastructure.stack;

import com.jordan.datastructure.adt.StackADT;

public class ArrayStack implements StackADT {

    private int topIndex = -1;

    private int length;

    private Object[] arr;

    public ArrayStack(int length) {
        if (length == 0) {
            throw new IllegalArgumentException("length can't be 0");
        }
        this.arr = new Object[length];
        this.length = length;
    }

    @Override
    public void push(Object value) {
        if (topIndex < length - 1) {
            topIndex += 1;
            arr[topIndex] = value;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean isEmpty() {
        return topIndex == -1;
    }

    @Override
    public Object pop() {
        if (topIndex > -1) {
            Object obj = arr[topIndex];
            topIndex -= 1;
            return obj;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Object peek() {
        if (topIndex > -1) {
            return arr[topIndex];
        }
        return null;
    }

    @Override
    public int size() {
        int count = 0;
        for (int i = 0; i <= topIndex; i++) {
            count++;
        }
        return count;
    }

    @Override
    public void clear() {
        while (topIndex > -1) {
            arr[topIndex] = null;
            topIndex -= 1;
        }
    }
}
