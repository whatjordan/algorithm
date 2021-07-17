package com.jordan.datastructure.hash;

import com.jordan.datastructure.list.LinkedList;

public class OpenAddressingLinkedHashTable<T, V> {

    private LinkedList<Entry<T, V>>[] buckets;
    private int capacity;

    //TODO auto scaling by load factor
    public OpenAddressingLinkedHashTable(int capacity) {
        buckets = new LinkedList[capacity];
        this.capacity = capacity;
    }

    public OpenAddressingLinkedHashTable() {
        this(12);
    }

    private int getAddress(T key) {
        return key.hashCode() % capacity;
    }

    public void put(T key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }
        int address = getAddress(key);
        if (buckets[address] == null) {
            buckets[address] = new LinkedList<>();
            buckets[address].add(new Entry<>(key, value));
        } else {
            for (int i = 0; i < buckets[address].size(); i++) {
                if (buckets[address].get(i).equals(key)) {
                    return;
                }
            }
            buckets[address].add(new Entry<>(key, value));
        }
    }

    public boolean containsKey(T key) {
        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }
        int address = getAddress(key);
        if (buckets[address] != null) {
            for (int i = 0; i < buckets[address].size(); i++) {
                if (buckets[address].get(i).getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                for (int linkIndex = 0; linkIndex < buckets[i].size(); linkIndex++) {
                    if (buckets[i].get(linkIndex).getValue().equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int size() {
        int count = 0;
        for (int i = 0; i < buckets.length; i++) {
            if(buckets[i] != null){
                count += buckets[i].size();
            }
        }
        return count;
    }

    public static class Entry<T, V> {
        private T key;
        private V value;

        Entry(T key, V value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
