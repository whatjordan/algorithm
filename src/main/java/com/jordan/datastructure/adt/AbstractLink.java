package com.jordan.datastructure.adt;

public class AbstractLink {

    public static class Node {
        public Node next;
        public Object value;

        public Node(Node next, Object value) {
            this.next = next;
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setValue(Object value) {
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
