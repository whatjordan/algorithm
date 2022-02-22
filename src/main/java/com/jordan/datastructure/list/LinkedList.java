package com.jordan.datastructure.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

/*
 DoublyLinkedList
*/
public class LinkedList<T> extends AbstractLinkedList<T> implements Iterable<T> {
    private DoublyLinkedNode<T> header;

    private DoublyLinkedNode<T> tail;

    private int size;

    private int modCount = 0;

    @Override
    public void clear() {
        DoublyLinkedNode next;
        for (DoublyLinkedNode cur = header; cur != null; cur = next) {
            next = cur.next;
            cur.value = null;
            cur.next = null;
            cur.previous = null;
        }
        tail = header = null;
        size = 0;
        modCount++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insert(int index, T ele) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        if (index == this.size) {
            add(ele);
        } else {
            DoublyLinkedNode nextNode = getDoublyLinkedNode(index);
            addBefore(nextNode, ele);
        }
    }

    public void add(T element) {
        DoublyLinkedNode newNode = new DoublyLinkedNode<>(null, tail, element);
        DoublyLinkedNode l = tail;
        tail = newNode;
        if (l == null) {
            header = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    private void addBefore(DoublyLinkedNode<T> target, T ele) {
        DoublyLinkedNode newNode = new DoublyLinkedNode<>(target, target.previous, ele);
        target.previous.next = newNode;
        target.previous = newNode;
        size++;
        modCount++;
    }

    @Override
    public void delete(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        remove(getDoublyLinkedNode(index));
    }

    private void remove(DoublyLinkedNode<T> ele) {
        ele.previous.next = ele.next;
        ele.next.previous = ele.previous;
        size--;
        modCount++;
    }

    private DoublyLinkedNode<T> getDoublyLinkedNode(int index) {
        int size = size();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        DoublyLinkedNode<T> cur = null;
        if (index < size / 2) {
            cur = header.next;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
        } else {
            cur = tail;
            for (int i = size; i > index; i--) {
                cur = cur.previous;
            }
        }
        return cur;
    }

    @Override
    public T get(int index) {
        return Optional.ofNullable(getDoublyLinkedNode(index)).map(n -> n.value).orElse(null);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private DoublyLinkedNode<T> cur = header;
        private boolean okToRemove = false;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cur != tail;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            cur = cur.next;
            okToRemove = true;
            return cur.value;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            LinkedList.this.remove(cur);
            okToRemove = false;
            expectedModCount++;
        }
    }

}
