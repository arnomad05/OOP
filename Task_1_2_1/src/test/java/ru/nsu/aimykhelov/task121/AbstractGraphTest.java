package ru.nsu.aimykhelov.task121;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;


/**
 * Тесты общих методов {@link AbstractGraph}, покрывающие {@code getEdges}
 * и защиту от живых ссылок.
 */
class AbstractGraphTest {

    @Test
    void getEdgesShouldReturnAllEdges() {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");

        List<String[]> edges = graph.getEdges();
        assertEquals(2, edges.size());
        boolean abFound = edges.stream().anyMatch(e -> e[0].equals("A") && e[1].equals("B"));
        boolean bcFound = edges.stream().anyMatch(e -> e[0].equals("B") && e[1].equals("C"));
        assertTrue(abFound);
        assertTrue(bcFound);
    }

    @Test
    void getEdgesOfEmptyGraphShouldBeEmpty() {
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>();
        assertTrue(graph.getEdges().isEmpty());
    }

    @Test
    void topologicalSortShouldWorkThroughGraphInterface() {
        IncidenceMatrixGraph<String> graph = new IncidenceMatrixGraph<>();
        graph.addVertex("X");
        graph.addVertex("Y");
        graph.addEdge("X", "Y");
        List<String> sorted = graph.topologicalSort(TopologicalSortAlgorithm.KAHN);
        assertEquals(List.of("X", "Y"), sorted);
    }
}