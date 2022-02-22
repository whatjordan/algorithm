package com.jordan.algorithm.graph;

import java.util.*;

public class FloydWarShall {

    /**
     * find predecessors and distance by source
     */
    public static <T> Map.Entry<Map<Graph.Node<T>, Map<Graph.Node<T>, Double>>, Map<Graph.Node<T>, Map<Graph.Node<T>, Graph.Node<T>>>> findShortestPaths(Graph<T> graph) {
        Map<Graph.Node<T>, Map<Graph.Node<T>, Graph.Node<T>>> predecessors = new HashMap<>();
        Map<Graph.Node<T>, Map<Graph.Node<T>, Double>> distance = new HashMap<>();
        Set<Graph.Node<T>> nodes = graph.getNodes();
        // init
        for (Graph.Node<T> src : nodes) {
            Map<Graph.Node<T>, Graph.Node<T>> destPredecessorsList = new HashMap<>();
            Map<Graph.Node<T>, Double> destDistanceList = new HashMap<>();
            predecessors.put(src, destPredecessorsList);
            distance.put(src, destDistanceList);
            for (Graph.Node<T> dest : nodes) {
                destPredecessorsList.put(dest, null);
                if (dest.equals(src)) {
                    destDistanceList.put(dest, 0.0);
                } else {
                    destDistanceList.put(dest, Double.POSITIVE_INFINITY);
                }
            }
            for (Graph.Edge<T> edge : src.outDegree) {
                destPredecessorsList.put(edge.to, src);
                destDistanceList.put(edge.to, edge.weight);
            }
        }
        // Iterate medium node for checking if we choose this src -> medium -> dest path can shorten the distance.
        for (Graph.Node<T> medium : nodes) {
            for (Graph.Node<T> src : nodes) {
                for (Graph.Node<T> dest : nodes) {
                    if (distance.get(src).get(dest) > distance.get(src).get(medium) + distance.get(medium).get(dest)) {
                        distance.get(src).put(dest, distance.get(src).get(medium) + distance.get(medium).get(dest));
                        predecessors.get(src).put(dest, medium);
                    }
                }
            }
        }
        return new AbstractMap.SimpleEntry<>(distance, predecessors);
    }

//    public static <T> List<Graph.Node<T>> findShortestPathNodes(Graph<T> graph, Graph.Node<T> source, Graph.Node<T> destination) {
//        List<Graph.Node<T>> result = new ArrayList<>();
//        Map.Entry<Map<Graph.Node<T>, Double>, Map<Graph.Node<T>, Graph.Node<T>>> shortestPath = findShortestPath(graph, source);
//        Map<Graph.Node<T>, Graph.Node<T>> predecessors = shortestPath.getValue();
//        Graph.Node<T> currentPredecessor = predecessors.get(destination);
//        result.add(destination);
//        while (currentPredecessor != null) {
//            result.add(currentPredecessor);
//            currentPredecessor = predecessors.get(currentPredecessor);
//        }
//        Collections.reverse(result);
//        return result;
//    }
}
