package com.jordan.datastructure.list;

public class CircularLinkedList<T> extends AbstractLinkedList<T> {

    private LinkedNode<T> rear;

    public CircularLinkedList() {
        rear = new LinkedNode(null, null);
    }

    public void add(Object value) {
        insert(size(), value);
    }

    @Override
    public T get(int index) {
        int size = size();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        LinkedNode<T> cur = rear.next; // get last
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.value;
    }

    @Override
    public void clear() {
        LinkedNode first = getFirst();
        LinkedNode linkedNodeCur = getFirst();
        do {
            LinkedNode previous = linkedNodeCur;
            linkedNodeCur = linkedNodeCur.getNext();
            previous.setNext(null);
            previous.setValue(null);
            if (linkedNodeCur != first) {
                break;
            }
        } while (true);
        rear.setNext(null);
        rear = new LinkedNode(null, null);
    }

    @Override
    public void insert(int index, Object ele) {
        int size = size();
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        LinkedNode linkedNode = new LinkedNode(null, ele);
        if (rear.next == null) {
            linkedNode.next = linkedNode;
            rear.next = linkedNode;
        }
        LinkedNode previous = rear;
        for (int i = 0; i <= index; i++) {
            previous = previous.next;
        }
        linkedNode.next = previous.next; //
        previous.next = linkedNode;
        if(size == index){
            rear.next = linkedNode;
        }
    }

    @Override
    public void delete(int index) {
        int size = size();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        LinkedNode prior = getLast(); // find the prior of target
        for (int i = 0; i < index; i++) {
            prior = prior.next;
        }
        LinkedNode target = prior.next;
        target.value = null;
        if (prior == target) {
            rear.next = null;
        } else {
            if (target == rear.next) {
                rear.next = prior;
            }
            prior.next = target.next;
        }
    }

    @Override
    public int size() {
        if (rear.next == null) {
            return 0;
        }
        int count = 0;
        LinkedNode first = getFirst();
        LinkedNode cur = getFirst();
        do {
            count++;
            cur = cur.next;
        } while (first != cur);
        return count;
    }

    public LinkedNode getFirst() {
        if (rear.next == null) {
            return null;
        }
        return rear.next.next;
    }

    public LinkedNode getLast() {
        return rear.next;
    }

    private LinkedNode getRear() {
        return rear;
    }

    public void merge(CircularLinkedList linkedList) {
        if (linkedList.getLast() == null || getLast() == null) {
            return;
        }
        LinkedNode selfFirst = getFirst();
        LinkedNode selfLast = getLast();
        LinkedNode targetFirst = linkedList.getFirst();
        LinkedNode targetLast = linkedList.getLast();
        selfLast.next = targetFirst;
        targetLast.next = selfFirst;
        rear = linkedList.getRear();
    }

    private void print(CircularLinkedList circularLinkedList) {
        LinkedNode first = circularLinkedList.getFirst();
        LinkedNode linkedNodeCur = circularLinkedList.getFirst();
        do {
            if (linkedNodeCur == null) {
                return;
            }
            System.out.println(linkedNodeCur.getValue());
            linkedNodeCur = linkedNodeCur.getNext();
        } while (linkedNodeCur != first);
    }

}
