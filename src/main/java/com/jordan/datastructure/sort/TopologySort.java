package com.jordan.datastructure.sort;

import com.jordan.algorithm.graph.Graph;

import java.util.*;

public class TopologySort {
    public static <T> List<Graph.Node<T>> sort(Graph<T> graph) {
        Set<Graph.Node<T>> nodes = graph.getNodes();
        if (graph.getTopologySortedNodes() != null) {
            return graph.getTopologySortedNodes();
        }
        List<Graph.Node<T>> sortedList = new LinkedList<>();
        HashSet<Graph.Node<T>> zeroInDegreeSet = new HashSet<>();
        nodes.forEach(node -> {
            if (node.inDegree.size() == 0) {
                zeroInDegreeSet.add(node);
            }
        });
        while (!zeroInDegreeSet.isEmpty()) {
            Graph.Node<T> zeroInDegreeNode = zeroInDegreeSet.iterator().next();
            zeroInDegreeSet.remove(zeroInDegreeNode);
            sortedList.add(zeroInDegreeNode);
            for (Iterator<Graph.Edge<T>> iterator = zeroInDegreeNode.outDegree.iterator(); iterator.hasNext(); ) {
                Graph.Edge<T> outDegreeEdge = iterator.next();
                Graph.Node<T> outDegreeNode = outDegreeEdge.to;
                // It's not necessary to remove outDegree
                // iterator.remove();
                outDegreeNode.inDegree.remove(outDegreeEdge);
                if (outDegreeNode.inDegree.isEmpty()) {
                    zeroInDegreeSet.add(outDegreeNode);
                }
            }
        }
        for (Graph.Node<T> node : nodes) {
            if (!node.inDegree.isEmpty()) {
                throw new RuntimeException("This graph is not Directed Acyclic Graph! Can't execute topology sort.");
            }
        }
        graph.setTopologySortedNodes(sortedList);
        return sortedList;
    }
}
