package com.jordan.datastructure.queue;

import com.jordan.datastructure.adt.QueueADT;

public class ArrayQueue implements QueueADT {

    private int maxSize;
    private Object[] arr;
    private int frontIndex;
    // point to the next empty place
    private int rearIndex;


    public ArrayQueue(int capacity) {
        // +1 for distinguishing if the array is empty.
        this.maxSize = capacity + 1;
        arr = new Object[this.maxSize];
    }

    @Override
    public Object getHead() {
        return arr[frontIndex];
    }

    @Override
    public void enQueue(Object ele) {
        //Check if a queue is full: (rearIndex+1) % maxQueue == frontIndex
        if ((rearIndex + 1) % maxSize == frontIndex) {
            throw new IndexOutOfBoundsException();
        }
        arr[rearIndex] = ele;
        rearIndex = (rearIndex + 1) % maxSize;
    }

    @Override
    public Object deQueue() {
        if (rearIndex == frontIndex) {
            throw new IndexOutOfBoundsException();
        }
        Object result = arr[frontIndex];
        frontIndex = (frontIndex + 1) % maxSize;
        return result;
    }

    @Override
    public int size() {
        return (rearIndex - frontIndex + maxSize) % maxSize;
    }

    @Override
    public void clear() {
        while (frontIndex != rearIndex) {
            arr[frontIndex] = null;
            frontIndex = (frontIndex + 1) % maxSize;
        }
    }
}
