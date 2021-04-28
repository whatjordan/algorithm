package com.jordan.datastructure.list;

public class LinkedList extends AbstractLinkedList {
    protected Node header;

    public LinkedList() {
        this.header = new Node(null, null);
    }

    public void add(Object ele){
        insert(size(), ele);
    }

    @Override
    public void clear() {
        Node cur = header;
        while (cur != null) {
            cur.value = null;
            Node tmpCur = cur;
            cur = cur.next;
            tmpCur.next = null;
        }
    }

    @Override
    public void insert(int index, Object ele) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        Node node = new Node(null, ele);
        Node previous = header;
        for (int i = 0; i < index; i++) {
            previous = previous.next;
        }
        node.next = previous.next;
        previous.next = node;
    }

    @Override
    public void delete(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node previous = header;
        for (int i = 0; i < index; i++) {
            previous = previous.next;
        }
        previous.next = previous.next.next;
    }

    @Override
    public int size() {
        int count = 0;
        Node cur = header;
        while (cur.next != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    @Override
    public Object get(int index) {
        int size = size();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node cur = header;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.value;
    }
}
