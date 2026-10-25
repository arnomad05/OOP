package ru.nsu.aimykhelov.task121;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


/**
 * Тесты равенства, хеш-кода и строкового представления графов.
 */
class GraphEqualityTest {

    private void fill(Graph<String> graph) {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
    }

    @Test
    void graphsOfDifferentRepresentationsShouldBeEqual() {
        AdjacencyListGraph<String> list = new AdjacencyListGraph<>();
        AdjacencyMatrixGraph<String> matrix = new AdjacencyMatrixGraph<>();
        IncidenceMatrixGraph<String> incidence = new IncidenceMatrixGraph<>();
        fill(list);
        fill(matrix);
        fill(incidence);

        assertEquals(list, matrix);
        assertEquals(list, incidence);
        assertEquals(matrix, incidence);
        assertEquals(list.hashCode(), matrix.hashCode());
    }

    @Test
    void differentGraphsShouldNotBeEqual() {
        AdjacencyListGraph<String> g1 = new AdjacencyListGraph<>();
        AdjacencyListGraph<String> g2 = new AdjacencyListGraph<>();
        fill(g1);
        g2.addVertex("A");
        g2.addVertex("B");
        g2.addVertex("C");
        g2.addEdge("A", "C");
        g2.addEdge("C", "B");

        assertNotEquals(g1, g2);
    }

    @Test
    void graphShouldNotEqualNonGraph() {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        fill(graph);
        assertFalse(graph.equals("not a graph"));
        assertFalse(graph.equals(null));
    }

    @Test
    void graphShouldEqualItself() {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        fill(graph);
        assertTrue(graph.equals(graph));
    }

    @Test
    void graphsWithDifferentVertexCountsShouldNotBeEqual() {
        AdjacencyListGraph<String> g1 = new AdjacencyListGraph<>();
        AdjacencyListGraph<String> g2 = new AdjacencyListGraph<>();
        g1.addVertex("A");
        g2.addVertex("A");
        g2.addVertex("B");
        assertNotEquals(g1, g2);
    }

    @Test
    void graphsWithDifferentEdgeCountsShouldNotBeEqual() {
        AdjacencyListGraph<String> g1 = new AdjacencyListGraph<>();
        AdjacencyListGraph<String> g2 = new AdjacencyListGraph<>();
        g1.addVertex("A");
        g1.addVertex("B");
        g2.addVertex("A");
        g2.addVertex("B");
        g2.addEdge("A", "B");
        assertNotEquals(g1, g2);
    }

    @Test
    void toStringShouldContainClassNameVerticesAndEdges() {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        String s = graph.toString();
        assertTrue(s.contains("AdjacencyListGraph"));
        assertTrue(s.contains("A"));
        assertTrue(s.contains("B"));
        assertTrue(s.contains("->"));
    }

    @Test
    void toStringOfEmptyGraphShouldBeWellFormed() {
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>();
        String s = graph.toString();
        assertTrue(s.contains("AdjacencyMatrixGraph"));
        assertTrue(s.contains("[]"));
    }
}