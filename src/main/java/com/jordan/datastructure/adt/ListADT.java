package com.jordan.datastructure.adt;

public interface ListADT<T> {
    void clear();
    void insert(int index, T ele);
    void delete(int index);
    int size();
    T get(int index);
    boolean isEmpty();
    default Object[] getArray(){
        int size = size();
        Object[] copy = new Object[size];
        for(int i = 0; i < size; i++){
            copy[i] = get(i);
        }
        return copy;
    }
}
