package com.jordan.algorithm.graph;

import java.util.*;

public class BellmanFord {

    /**
     * find predecessors and distance by source
     *
     * @param source
     * @return
     */
    public static <T> Map.Entry<Map<Graph.Node<T>, Double>, Map<Graph.Node<T>, Graph.Node<T>>> findShortestPath(Graph<T> graph, Graph.Node<T> source) {
        Map<Graph.Node<T>, Graph.Node<T>> predecessors = new HashMap<>();
        Map<Graph.Node<T>, Double> distance = new HashMap<>();
        Set<Graph.Node<T>> nodes = graph.getNodes();
        for (Graph.Node<T> node : nodes) {
            predecessors.put(node, null);
            if (source.equals(node)) {
                distance.put(node, 0.0);
            } else {
                distance.put(node, Double.POSITIVE_INFINITY);
            }
        }

        for (int i = 1; i < nodes.size() - 1; i++) {
            for (Graph.Node<T> node : nodes) {
                for (Graph.Edge<T> edge : node.outDegree) {
                    Double weight = edge.weight;
                    Graph.Node<T> to = edge.to;
                    if (distance.get(node) + weight < distance.get(to)) {
                        distance.put(to, distance.get(node) + weight);
                        predecessors.put(to, node);
                    }
                }
            }
        }
        return new AbstractMap.SimpleEntry<>(distance, predecessors);
    }

    public static <T> List<Graph.Node<T>> findShortestPathNodes(Graph<T> graph, Graph.Node<T> source, Graph.Node<T> destination) {
        List<Graph.Node<T>> result = new ArrayList<>();
        Map.Entry<Map<Graph.Node<T>, Double>, Map<Graph.Node<T>, Graph.Node<T>>> shortestPath = findShortestPath(graph, source);
        if (findNegativeCycle(graph, shortestPath).size() != 0) {
            throw new IllegalArgumentException("Contains an negative cycle! " +
                    "If a graph contains an negative cycle that is reachable from source, there is no cheapest path.");
        }
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

    public static <T> List<Graph.Node<T>> findNegativeCycle(Graph<T> graph, Map.Entry<Map<Graph.Node<T>, Double>, Map<Graph.Node<T>, Graph.Node<T>>> shortestPath) {
        List<Graph.Node<T>> result = new ArrayList<>();
        Map<Graph.Node<T>, Double> distance = shortestPath.getKey();
        Map<Graph.Node<T>, Graph.Node<T>> predecessors = shortestPath.getValue();
        Set<Graph.Node<T>> nodes = graph.getNodes();
        for (Graph.Node<T> node : nodes) {
            for (Graph.Edge<T> edge : node.outDegree) {
                Double weight = edge.weight;
                Graph.Node<T> to = edge.to;
                // Contains an negative cycle
                if (distance.get(node) + weight < distance.get(to)) {
                    Graph.Node<T> begin = predecessors.get(node);
                    Graph.Node<T> cur = predecessors.get(begin);
                    result.add(begin);
                    while (!begin.equals(cur)) {
                        result.add(cur);
                        cur = predecessors.get(cur);
                    }
                    break;
                }
            }
        }
        return result;
    }

    public static <T> List<Graph.Node<T>> findNegativeCycle(Graph<T> graph, Graph.Node<T> source) {
        Map.Entry<Map<Graph.Node<T>, Double>, Map<Graph.Node<T>, Graph.Node<T>>> shortestPath = findShortestPath(graph, source);
        return findNegativeCycle(graph, shortestPath);
    }

}
