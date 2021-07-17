package com.jordan.datastructure.adt;

public class AbstractLink {

    public static class Node<T> {
        public Node next;
        public T value;

        public Node(Node next, T value) {
            this.next = next;
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public Object getValue() {
            return value;
        }
    }
}
