package com.jordan.datastructure.adt;

public interface QueueADT <T>{

    T getHead();

    void enQueue(T ele);

    T deQueue();

    int size();

    void clear();

}
