package com.jordan.datastructure.adt;

public class AbstractLink {

    protected static class LinkedNode<T> {
        public LinkedNode next;
        public T value;

        public LinkedNode(LinkedNode next, T value) {
            this.next = next;
            this.value = value;
        }

        public void setNext(LinkedNode next) {
            this.next = next;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public LinkedNode getNext() {
            return next;
        }

        public T getValue() {
            return value;
        }
    }

    protected static class DoublyLinkedNode<T> {
        public DoublyLinkedNode next;
        public DoublyLinkedNode previous;
        public T value;

        public DoublyLinkedNode(DoublyLinkedNode next, DoublyLinkedNode previous, T value) {
            this.next = next;
            this.previous = previous;
            this.value = value;
        }

        public DoublyLinkedNode getNext() {
            return next;
        }

        public void setNext(DoublyLinkedNode next) {
            this.next = next;
        }

        public DoublyLinkedNode getPrevious() {
            return previous;
        }

        public void setPrevious(DoublyLinkedNode previous) {
            this.previous = previous;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }
}
