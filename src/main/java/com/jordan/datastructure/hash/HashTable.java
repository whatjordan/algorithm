package com.jordan.datastructure.hash;

public interface HashTable<T> {

    default int hash(T element) {
        return element.hashCode() % 31;
    }


}
