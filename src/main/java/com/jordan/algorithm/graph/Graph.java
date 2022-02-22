package com.jordan.algorithm.graph;

import java.util.*;

public class Graph<T> {

    private Set<Node<T>> nodes = new HashSet<>();

    private List<Node<T>> topologySortedNodes = null;

    public Graph(){
    }

    public Graph(Node<T>... nodes){
       addNode(nodes);
    }

    public Set<Node<T>> getNodes() {
        return nodes;
    }

    public List<Node<T>> getTopologySortedNodes() {
        return topologySortedNodes;
    }

    public void setTopologySortedNodes(List<Node<T>> topologySortedNodes) {
        this.topologySortedNodes = topologySortedNodes;
    }

    public void addNode(Node<T>... nodes) {
        if (nodes != null) {
            Collections.addAll(this.nodes, nodes);
        }
    }

    public static class Node<T> {
        public T value;
        public final HashSet<Edge<T>> inDegree = new HashSet<>();
        public final HashSet<Edge<T>> outDegree = new HashSet<>();

        public Node(T value) {
            this.value = value;
        }

        public Node<T> addEdge(Node<T> node) {
            return addEdge(node, 0.0);
        }

        public Node<T> addEdge(Node<T> node, Double weight) {
            Edge<T> edge = new Edge<>(this, node, weight);
            outDegree.add(edge);
            node.inDegree.add(edge);
            return this;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public static class Edge<T> {
        public Node<T> from;
        public Node<T> to;
        public Double weight;

        public Edge(Node<T> from, Node<T> to, Double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?> edge = (Edge<?>) o;
            return Objects.equals(from, edge.from) &&
                    Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
}
