package ru.nsu.aimykhelov.task121;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тесты для {@link AdjacencyMatrixGraph}.
 */
class AdjacencyMatrixGraphTest {

    private AdjacencyMatrixGraph<String> graph;

    @BeforeEach
    void setUp() {
        graph = new AdjacencyMatrixGraph<>();
    }

    @Test
    void addVertexShouldAddNewVertex() {
        assertTrue(graph.addVertex("A"));
        assertTrue(graph.containsVertex("A"));
    }

    @Test
    void addVertexShouldRejectDuplicate() {
        graph.addVertex("A");
        assertFalse(graph.addVertex("A"));
    }

    @Test
    void removeVertexShouldRemoveIncidentEdges() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("C", "B");
        graph.addEdge("B", "A");

        assertTrue(graph.removeVertex("B"));
        assertFalse(graph.containsVertex("B"));
        assertFalse(graph.containsEdge("A", "B"));
        assertFalse(graph.containsEdge("C", "B"));
        assertEquals(0, graph.edgeCount());
    }

    @Test
    void removeUnknownVertexShouldReturnFalse() {
        assertFalse(graph.removeVertex("X"));
    }

    @Test
    void addEdgeShouldRequireBothVertices() {
        graph.addVertex("A");
        assertFalse(graph.addEdge("A", "B"));
    }

    @Test
    void addEdgeShouldRejectDuplicate() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        assertFalse(graph.addEdge("A", "B"));
    }

    @Test
    void addEdgeShouldStoreInMatrix() {
        graph.addVertex("A");
        graph.addVertex("B");
        assertTrue(graph.addEdge("A", "B"));
        assertTrue(graph.containsEdge("A", "B"));
        assertEquals(Set.of("B"), graph.getNeighbors("A"));
    }

    @Test
    void removeEdgeShouldWork() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        assertTrue(graph.removeEdge("A", "B"));
        assertFalse(graph.containsEdge("A", "B"));
    }

    @Test
    void removeEdgeShouldReturnFalseForUnknownVertex() {
        assertFalse(graph.removeEdge("X", "Y"));
    }

    @Test
    void removeEdgeShouldReturnFalseWhenMissing() {
        graph.addVertex("A");
        graph.addVertex("B");
        assertFalse(graph.removeEdge("A", "B"));
    }

    @Test
    void getNeighborsShouldReturnNullForUnknownVertex() {
        assertNull(graph.getNeighbors("Z"));
    }

    @Test
    void getNeighborsShouldReturnEmptyForIsolatedVertex() {
        graph.addVertex("A");
        assertEquals(Set.of(), graph.getNeighbors("A"));
    }

    @Test
    void countsShouldBeConsistent() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        assertEquals(3, graph.vertexCount());
        assertEquals(2, graph.edgeCount());
    }

    @Test
    void containsEdgeShouldReturnFalseForUnknownVertex() {
        assertFalse(graph.containsEdge("X", "Y"));
    }

    @Test
    void vertexSetInternalShouldReturnAllVertices() {
        graph.addVertex("A");
        graph.addVertex("B");
        assertEquals(Set.of("A", "B"), graph.vertexSetInternal());
    }

    @Test
    void getVerticesShouldReturnAllVertices() {
        graph.addVertex("A");
        assertEquals(Set.of("A"), graph.getVertices());
    }
}