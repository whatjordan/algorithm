package com.jordan.datastructure.list;

public class CircularLinkedList extends AbstractLinkedList {

    private Node rear;

    public CircularLinkedList() {
        rear = new Node(null, null);
    }

    public void add(Object value) {
        insert(size(), value);
    }

    @Override
    public Object get(int index) {
        int size = size();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node cur = rear.next; // get last
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.value;
    }

    @Override
    public void clear() {
        Node first = getFirst();
        Node nodeCur = getFirst();
        do {
            Node previous = nodeCur;
            nodeCur = nodeCur.getNext();
            previous.setNext(null);
            previous.setValue(null);
            if (nodeCur != first) {
                break;
            }
        } while (true);
        rear.setNext(null);
        rear = new Node(null, null);
    }

    @Override
    public void insert(int index, Object ele) {
        int size = size();
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = new Node(null, ele);
        if (rear.next == null) {
            node.next = node;
            rear.next = node;
        }
        Node previous = rear;
        for (int i = 0; i <= index; i++) {
            previous = previous.next;
        }
        node.next = previous.next; //
        previous.next = node;
        if(size == index){
            rear.next = node;
        }
    }

    @Override
    public void delete(int index) {
        int size = size();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node prior = getLast(); // find the prior of target
        for (int i = 0; i < index; i++) {
            prior = prior.next;
        }
        Node target = prior.next;
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
        Node first = getFirst();
        Node cur = getFirst();
        do {
            count++;
            cur = cur.next;
        } while (first != cur);
        return count;
    }

    public Node getFirst() {
        if (rear.next == null) {
            return null;
        }
        return rear.next.next;
    }

    public Node getLast() {
        return rear.next;
    }

    private Node getRear() {
        return rear;
    }

    public void merge(CircularLinkedList linkedList) {
        if (linkedList.getLast() == null || getLast() == null) {
            return;
        }
        Node selfFirst = getFirst();
        Node selfLast = getLast();
        Node targetFirst = linkedList.getFirst();
        Node targetLast = linkedList.getLast();
        selfLast.next = targetFirst;
        targetLast.next = selfFirst;
        rear = linkedList.getRear();
    }

}
