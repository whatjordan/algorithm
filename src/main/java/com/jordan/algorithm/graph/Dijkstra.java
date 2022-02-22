package com.jordan.algorithm.graph;

import java.util.*;

public class Dijkstra {

    /**
     * find predecessors and distance by source
     *
     * @param source
     * @return
     */
    public static <T> Map.Entry<Map<Graph.Node<T>, Double>, Map<Graph.Node<T>, Graph.Node<T>>> findShortestPath(Graph<T> graph, Graph.Node<T> source) {
        Map<Graph.Node<T>, Graph.Node<T>> predecessors = new HashMap<>();
        Map<Graph.Node<T>, Double> distance = new HashMap<>();
        List<Graph.Node<T>> unHandledList = new ArrayList<>(graph.getNodes());
        for (Graph.Node<T> node : unHandledList) {
            predecessors.put(node, null);
            if (source.equals(node)) {
                distance.put(node, 0.0);
            } else {
                distance.put(node, Double.POSITIVE_INFINITY);
            }
        }

        while (unHandledList.size() > 0) {
            //Find the shortest distance in unHandledList
            Graph.Node<T> shortestDistanceNode = unHandledList.stream().min(Comparator.comparing(distance::get)).orElseThrow(NoSuchElementException::new);
            //Relax the shortest Node's outDegree
            for (Graph.Edge<T> edge : shortestDistanceNode.outDegree) {
                Double weight = edge.weight;
                if (weight < 0.0) {
                    throw new IllegalArgumentException("The weight shouldn't be less than 0!");
                }
                Graph.Node<T> to = edge.to;
                if (distance.get(shortestDistanceNode) + weight < distance.get(to)) {
                    distance.put(to, distance.get(shortestDistanceNode) + weight);
                    predecessors.put(to, shortestDistanceNode);
                }
            }
            unHandledList.remove(shortestDistanceNode);
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
