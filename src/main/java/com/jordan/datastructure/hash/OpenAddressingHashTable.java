package com.jordan.datastructure.hash;

public class OpenAddressingHashTable<T> implements HashTable<T> {

    private Object[] elements;
    private int size;

    public OpenAddressingHashTable(int size) {
        elements = new Object[size];
        this.size = size;
    }

    public OpenAddressingHashTable() {
        this(10);
    }


}
