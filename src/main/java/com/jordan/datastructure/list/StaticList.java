package com.jordan.datastructure.list;

import com.jordan.datastructure.adt.ListADT;

public class StaticList<T> implements ListADT<T> {
    private int length;

    /**
     * arr[0] is the head, and it's cur point to the next available node.
     * If a node's cur point to 0, that node is the end of a link.
     * <p>
     * arr[maxSize -1] is the rear, and it's cur point to the begin of a link.
     */
    private Node<T>[] arr;

    public StaticList() {
        this(1000);
    }

    public StaticList(int initSize) {
        if (initSize <= 0) {
            throw new IllegalArgumentException();
        }
        initSize = initSize + 2;
        this.length = initSize;
        arr = new Node[length];
        Node header = new Node(1, null);
        arr[0] = header;
        Node tail = new Node(0, null);
        arr[length - 1] = tail;
    }

    public void add(Object value) {
        insert(size() , value);
    }

    @Override
    public void clear() {
        int size = size();
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        arr = new Node[length];
    }

    public boolean contains(Object value) {
        int cur = arr[length - 1].next;
        do {
            if (cur == 0) {
                return false;
            }
            if (value.equals(arr[cur].value)) {
                return true;
            } else {
                cur = arr[cur].next;
            }
        } while (true);
    }

    @Override
    public void insert(int index, Object ele) {
        if (index >= length - 1) {
            //TODO resize arr
            throw new IndexOutOfBoundsException();
        } else if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        int availableIndex = allocate();

        int cur = length - 1;

        for (int i = 0; i < index; i++) { // find the previous one by loop times
            cur = arr[cur].next;
        }
        Node insertion = new Node(arr[cur].next, ele); // assign previous cur to insertion cur
        arr[cur].next = availableIndex; // assign insertion index to previous cur
        arr[availableIndex] = insertion;
    }

    @Override
    public T get(int index) {
        int size = size();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int cur = arr[length - 1].next;
        for (int i = 0; i < index; i++) { // find the previous one by loop times
            cur = arr[cur].next;
        }
        return arr[cur].value;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void delete(int index) {
        int size = size();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int cur = length - 1;
        for (int i = 0; i < index; i++) { // find the previous one by loop times
            cur = arr[cur].next;
        }
        int target = arr[cur].next;
        arr[cur].next = arr[target].next;
        free(target);

    }

    @Override
    public int size() {
        int count = 0;
        int index = arr[length - 1].next;
        while (index != 0) {
            count++;
            index = arr[index].next;
        }
        return count;
    }

    private int allocate() {
        int cur = arr[0].next;
        if (arr[cur] != null) {
            arr[0].next = arr[cur].next;
        } else {
            arr[0].next = cur + 1;
        }
        return cur;
    }

    private void free(int cur) {
        arr[cur].next = arr[0].next;
        arr[cur].value = null;
        arr[0].next = cur;
    }

    private int getLength() {
        return length;
    }

    private static class Node<T> {
        private int next;
        private T value;

        public Node(int cur, T value) {
            this.next = cur;
            this.value = value;
        }
    }
}
