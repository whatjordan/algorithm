package com.jordan.datastructure.list;

import com.jordan.datastructure.adt.ListADT;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FixedArrayList<T> implements Iterable<T>, ListADT<T> {
    protected T[] array = null;
    private Class<?> clazz;
    private int DEFAULT_CAPACITY = 50;
    private int size;

    public FixedArrayList(Class<?> clazz, int capacity) {
        if (null == clazz) {
            throw new IllegalArgumentException("Clazz can't not be null");
        }
        this.clazz = clazz;
        this.size = 0;
        array = (T[]) Array.newInstance(clazz, capacity);
    }

    public FixedArrayList(T[] array) {
        if (null == array) {
            throw new IllegalArgumentException("Initial array can't not be null");
        }
        this.clazz = array.getClass().getComponentType();
        this.size = array.length;
        this.array = copyArray(array, array.length);
    }

    private void ensureCapacity(int capacity) {
        if (capacity <= size) {
            return;
        }
        this.array = copyArray(this.array, capacity);
    }

    private void trimToSize() {
        ensureCapacity(size);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        this.size = 0;
        this.array = (T[]) Array.newInstance(clazz, DEFAULT_CAPACITY);
    }

    public void add(T ele) {
        insert(size, ele);
    }

    @Override
    public void insert(int index, T ele) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (size == array.length) {
            ensureCapacity(array.length * 2 + 1);
        }
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = ele;
        size++;
    }

    @Override
    public void delete(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public void replace(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
    }

    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] != null && array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T[] getArray() {
        @SuppressWarnings("unchecked")
        T[] copy = (T[]) Array.newInstance(clazz, size);
        for (int i = 0; i < size; i++) {
            copy[i] = array[i];
        }
        return copy;
    }


    private T[] copyArray(T[] array, int newCapacity) {
        @SuppressWarnings("unchecked")
        T[] copy = (T[]) Array.newInstance(clazz, newCapacity);
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        return copy;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayAdtIterator();
    }

    private class ArrayAdtIterator implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return get(index++);
        }

        @Override
        public void remove() {
            delete(--index);
        }
    }

}
