package com.jordan.algorithm.graph;

import org.junit.Test;

import java.util.Map;

public class GraphTest {

    /**
     * a (8)>  b (2)> d (0)> e
     * a (5)>  c (1)> d
     * x (1)>  c
     */
    @Test
    public void testDAGShortestPath() {
        Graph.Node<String> nodeA = new Graph.Node<>("a");
        Graph.Node<String> nodeB = new Graph.Node<>("b");
        Graph.Node<String> nodeC = new Graph.Node<>("c");
        Graph.Node<String> nodeD = new Graph.Node<>("d");
        Graph.Node<String> nodeE = new Graph.Node<>("e");
        Graph.Node<String> nodeX = new Graph.Node<>("x");
        nodeA.addEdge(nodeB, 8.0).addEdge(nodeC, 5.0);
        nodeB.addEdge(nodeD, 2.0);
        nodeC.addEdge(nodeD, 1.0);
        nodeD.addEdge(nodeE, 0.0);
        nodeX.addEdge(nodeC, 1.0);
        Graph<String> graph = new Graph<>();
        graph.addNode(nodeA, nodeB, nodeC, nodeD, nodeE, nodeX);
        System.out.println("source: " + nodeA);
        Map.Entry<Map<Graph.Node<String>, Double>, Map<Graph.Node<String>, Graph.Node<String>>> entry = DAG.findShortestPath(graph, nodeA);
        System.out.println(entry.getKey());
        System.out.println(entry.getValue());
        System.out.println("destination: " + nodeE);
        System.out.println("shortest path: ");
        System.out.println(DAG.findShortestPathNodes(graph, nodeA, nodeE));
    }

    /**
     * a (8)>  b (2)> d (0)> e
     *       (3)  *
     *        *  (1)
     * a (5)>  c (6)> d
     * x (1)>  c
     */
    @Test
    public void testDijkstraShortestPath() {
        Graph.Node<String> nodeA = new Graph.Node<>("a");
        Graph.Node<String> nodeB = new Graph.Node<>("b");
        Graph.Node<String> nodeC = new Graph.Node<>("c");
        Graph.Node<String> nodeD = new Graph.Node<>("d");
        Graph.Node<String> nodeE = new Graph.Node<>("e");
        Graph.Node<String> nodeX = new Graph.Node<>("x");
        nodeA.addEdge(nodeB, 8.0).addEdge(nodeC, 5.0);
        nodeB.addEdge(nodeD, 2.0);
        nodeB.addEdge(nodeC, 3.0);
        nodeC.addEdge(nodeD, 6.0);
        nodeC.addEdge(nodeB, 1.0);
        nodeD.addEdge(nodeE, 0.0);
        nodeX.addEdge(nodeC, 1.0);
        Graph<String> graph = new Graph<>();
        graph.addNode(nodeA, nodeB, nodeC, nodeD, nodeE, nodeX);
        System.out.println("source: " + nodeA);
        Map.Entry<Map<Graph.Node<String>, Double>, Map<Graph.Node<String>, Graph.Node<String>>> entry = Dijkstra.findShortestPath(graph, nodeA);
        System.out.println(entry.getKey());
        System.out.println(entry.getValue());
        System.out.println("destination: " + nodeE);
        System.out.println("shortest path: ");
        System.out.println(Dijkstra.findShortestPathNodes(graph, nodeA, nodeE));
    }

    /**
     * a (8)>  b (2)> d (0)> e
     *       (-10)
     *        *
     * a (5)>  c (6)> d
     * x (1)>  c
     */
    @Test
    public void testBellmanFordShortestPath() {
        Graph.Node<String> nodeA = new Graph.Node<>("a");
        Graph.Node<String> nodeB = new Graph.Node<>("b");
        Graph.Node<String> nodeC = new Graph.Node<>("c");
        Graph.Node<String> nodeD = new Graph.Node<>("d");
        Graph.Node<String> nodeE = new Graph.Node<>("e");
        Graph.Node<String> nodeX = new Graph.Node<>("x");
        nodeA.addEdge(nodeB, 8.0).addEdge(nodeC, 5.0);
        nodeB.addEdge(nodeD, 2.0);
        nodeB.addEdge(nodeC, -10.0);
        nodeC.addEdge(nodeD, 6.0);
        nodeD.addEdge(nodeE, 0.0);
        nodeX.addEdge(nodeC, 1.0);
        Graph<String> graph = new Graph<>();
        graph.addNode(nodeA, nodeB, nodeC, nodeD, nodeE, nodeX);
        System.out.println("source: " + nodeA);
        Map.Entry<Map<Graph.Node<String>, Double>, Map<Graph.Node<String>, Graph.Node<String>>> entry = BellmanFord.findShortestPath(graph, nodeA);
        System.out.println(entry.getKey());
        System.out.println(entry.getValue());
        System.out.println("destination: " + nodeE);
        System.out.println("shortest path: ");
        System.out.println(BellmanFord.findShortestPathNodes(graph, nodeA, nodeE));
    }

    /**
     * a (8)>  b (2)> d (0)> e
     *       (-10) *
     *        *   (1)
     * a (5)>  c (6)> d
     * x (1)>  c
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBellmanFordWithNegativeCycle() {
        Graph.Node<String> nodeA = new Graph.Node<>("a");
        Graph.Node<String> nodeB = new Graph.Node<>("b");
        Graph.Node<String> nodeC = new Graph.Node<>("c");
        Graph.Node<String> nodeD = new Graph.Node<>("d");
        Graph.Node<String> nodeE = new Graph.Node<>("e");
        Graph.Node<String> nodeX = new Graph.Node<>("x");
        nodeA.addEdge(nodeB, 8.0).addEdge(nodeC, 5.0);
        nodeB.addEdge(nodeD, 2.0);
        nodeB.addEdge(nodeC, -10.0);
        nodeC.addEdge(nodeD, 6.0);
        nodeC.addEdge(nodeB, 1.0);
        nodeD.addEdge(nodeE, 0.0);
        nodeX.addEdge(nodeC, 1.0);
        Graph<String> graph = new Graph<>();
        graph.addNode(nodeA, nodeB, nodeC, nodeD, nodeE, nodeX);
        System.out.println("source: " + nodeA);
        Map.Entry<Map<Graph.Node<String>, Double>, Map<Graph.Node<String>, Graph.Node<String>>> entry = BellmanFord.findShortestPath(graph, nodeA);
        System.out.println(entry.getKey());
        System.out.println(entry.getValue());
        System.out.println("destination: " + nodeE);
        System.out.println("negative cycle: ");
        System.out.println(BellmanFord.findNegativeCycle(graph, nodeA));
        System.out.println(BellmanFord.findShortestPathNodes(graph, nodeA, nodeE));
    }

    /**
     * a (8)>  b (2)> d (0)> e
     *       (-10)
     *        *
     * a (5)>  c (6)> d
     * x (1)>  c
     */
    @Test
    public void testFloydWarShallShortestPaths() {
        Graph.Node<String> nodeA = new Graph.Node<>("a");
        Graph.Node<String> nodeB = new Graph.Node<>("b");
        Graph.Node<String> nodeC = new Graph.Node<>("c");
        Graph.Node<String> nodeD = new Graph.Node<>("d");
        Graph.Node<String> nodeE = new Graph.Node<>("e");
        Graph.Node<String> nodeX = new Graph.Node<>("x");
        nodeA.addEdge(nodeB, 8.0).addEdge(nodeC, 5.0);
        nodeB.addEdge(nodeD, 2.0);
        nodeB.addEdge(nodeC, -10.0);
        nodeC.addEdge(nodeD, 6.0);
        nodeD.addEdge(nodeE, 0.0);
        nodeX.addEdge(nodeC, 1.0);
        Graph<String> graph = new Graph<>();
        graph.addNode(nodeA, nodeB, nodeC, nodeD, nodeE, nodeX);
        Map.Entry<Map<Graph.Node<String>, Map<Graph.Node<String>, Double>>, Map<Graph.Node<String>, Map<Graph.Node<String>, Graph.Node<String>>>> entry
                = FloydWarShall.findShortestPaths(graph);
        System.out.println(entry.getKey());
        System.out.println(entry.getValue());
        System.out.println("source: " + nodeA);
        System.out.println("predecessors:" + entry.getValue().get(nodeA));
        System.out.println("source: " + nodeX);
        System.out.println("predecessors:" + entry.getValue().get(nodeX));
    }
}
