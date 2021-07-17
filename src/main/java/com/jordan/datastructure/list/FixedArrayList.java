package com.jordan.datastructure.list;

import com.jordan.datastructure.adt.ListADT;

import java.lang.reflect.Array;
import java.util.Iterator;

public class FixedArrayList<T> implements Iterable<T>, ListADT<T> {
    protected T[] array = null;
    private Class<?> clazz;
    private int length;

    public FixedArrayList(Class<?> clazz, int length) {
        if (null == clazz) {
            throw new IllegalArgumentException("Clazz can't not be null");
        }
        this.clazz = clazz;
        this.length = length;
        array = (T[]) Array.newInstance(clazz, array.length);
    }

    public FixedArrayList(T[] array) {
        if (null == array) {
            throw new IllegalArgumentException("Initial array can't not be null");
        }
        this.array = array;
        this.length = array.length;
        clazz = array.getClass().getComponentType();
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; i++) {
            array[i] = null;
        }
        array = (T[]) Array.newInstance(clazz, array.length);
    }

    /**
     * @param index
     * @param ele
     */
    @Override
    public void insert(int index, T ele) {
        if (index >= array.length || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        @SuppressWarnings("unchecked") final T[] newArray = (T[]) Array.newInstance(clazz, array.length + 1);
        for (int i = 0; i < index; i++) {
            newArray[i] = array[i];
        }
        newArray[index] = ele;
        for (int i = index; i < array.length; i++) {
            newArray[i + 1] = array[i];
        }
        this.array = newArray;
    }

    @Override
    public void delete(int index) {
        if (index >= array.length || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) Array.newInstance(clazz, array.length - 1);
        for (int i = 0; i < index; i++) {
            newArray[i] = array[i];
        }

        for (int i = index + 1; i < array.length; i++) {
            newArray[i - 1] = array[i];
        }
        this.array = newArray;

    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public void replace(int index, T element) {
        if (index >= array.length || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
    }

    public boolean contains(T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T[] getArray() {
        T[] copy = (T[]) Array.newInstance(clazz, array.length);
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }


    @Override
    public Iterator<T> iterator() {
        return new arrayADTIterator();
    }

    private class arrayADTIterator implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < length;
        }

        @Override
        public T next() {
            return get(index++);
        }

    }

}
