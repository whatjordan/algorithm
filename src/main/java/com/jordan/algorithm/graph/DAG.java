package com.jordan.algorithm.graph;

import com.jordan.datastructure.sort.TopologySort;

import java.util.*;

public class DAG {

    /**
     * find predecessors and distance by source
     *
     * @param source
     * @return
     */
    public static <T> Map.Entry<Map<Graph.Node<T>, Double>, Map<Graph.Node<T>, Graph.Node<T>>> findShortestPath(Graph<T> graph, Graph.Node<T> source) {
        List<Graph.Node<T>> sortedNodes = TopologySort.sort(graph);
        Map<Graph.Node<T>, Graph.Node<T>> predecessors = new HashMap<>();
        Map<Graph.Node<T>, Double> distance = new HashMap<>();
        for (Graph.Node<T> node : sortedNodes) {
            predecessors.put(node, null);
            if (source.equals(node)) {
                distance.put(node, 0.0);
            } else {
                distance.put(node, Double.POSITIVE_INFINITY);
            }
        }
        //Relax all outDegree by topology sort order
        for (int i = 0; i < sortedNodes.size(); i++) {
            Graph.Node<T> node = sortedNodes.get(i);
            for (Graph.Edge<T> edge : node.outDegree) {
                Double weight = edge.weight;
                if (weight < 0.0) {
                    throw new IllegalArgumentException("The weight shouldn't less than 0");
                }
                Graph.Node<T> to = edge.to;
                if (distance.get(node) + weight < distance.get(to)) {
                    distance.put(to, distance.get(node) + weight);
                    predecessors.put(to, node);
                }
            }
        }
        return new AbstractMap.SimpleEntry<>(distance, predecessors);
    }

    public static <T> List<Graph.Node<T>> findShortestPathNodes(Graph<T> graph, Graph.Node<T> source, Graph.Node<T> destination) {
        List<Graph.Node<T>> result = new ArrayList<>();
        Map.Entry<Map<Graph.Node<T>, Double>, Map<Graph.Node<T>, Graph.Node<T>>> shortestPath = findShortestPath(graph, source);
        Map<Graph.Node<T>, Graph.Node<T>> predecessors = shortestPath.getValue();
        Graph.Node<T> currentPredecessor = predecessors.get(destination);
        result.add(destination);
        while (currentPredecessor != null) {
            result.add(currentPredecessor);
            currentPredecessor = predecessors.get(currentPredecessor);
        }
        Collections.reverse(result);
        return result;
    }
}
