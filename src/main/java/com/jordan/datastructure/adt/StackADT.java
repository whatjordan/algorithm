package com.jordan.datastructure.adt;

public interface StackADT<T> {

    void push(T value);

    T pop();

    T peek();

    boolean isEmpty();

    int size();

    void clear();
}
