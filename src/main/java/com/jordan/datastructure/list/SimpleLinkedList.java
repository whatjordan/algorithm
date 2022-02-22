package com.jordan.datastructure.list;

public class SimpleLinkedList<T> extends AbstractLinkedList<T> {
    protected LinkedNode header;

    public SimpleLinkedList() {
        this.header = new LinkedNode(null, null);
    }

    public void add(T ele){
        insert(size(), ele);
    }

    @Override
    public void clear() {
        LinkedNode cur = header;
        while (cur != null) {
            cur.value = null;
            LinkedNode tmpCur = cur;
            cur = cur.next;
            tmpCur.next = null;
        }
    }

    @Override
    public void insert(int index, T ele) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        LinkedNode linkedNode = new LinkedNode(null, ele);
        LinkedNode previous = header;
        for (int i = 0; i < index; i++) {
            previous = previous.next;
        }
        linkedNode.next = previous.next;
        previous.next = linkedNode;
    }

    @Override
    public void delete(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        LinkedNode previous = header;
        for (int i = 0; i < index; i++) {
            previous = previous.next;
        }
        previous.next = previous.next.next;
    }

    @Override
    public int size() {
        int count = 0;
        LinkedNode cur = header;
        while (cur.next != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    @Override
    public T get(int index) {
        int size = size();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        LinkedNode<T> cur = header;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.value;
    }
}
