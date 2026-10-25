package ru.nsu.aimykhelov.task121;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тесты для {@link AdjacencyListGraph}.
 */
class AdjacencyListGraphTest {

    private AdjacencyListGraph<String> graph;

    @BeforeEach
    void setUp() {
        graph = new AdjacencyListGraph<>();
    }

    @Test
    void addVertexShouldAddNewVertex() {
        assertTrue(graph.addVertex("A"));
        assertTrue(graph.containsVertex("A"));
        assertEquals(1, graph.vertexCount());
    }

    @Test
    void addVertexShouldRejectDuplicate() {
        graph.addVertex("A");
        assertFalse(graph.addVertex("A"));
        assertEquals(1, graph.vertexCount());
    }

    @Test
    void removeVertexShouldRemoveVertexAndEdges() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("C", "B");

        assertTrue(graph.removeVertex("B"));
        assertFalse(graph.containsVertex("B"));
        assertEquals(0, graph.edgeCount());
        assertEquals(Set.of(), graph.getNeighbors("A"));
    }

    @Test
    void removeNonExistentVertexShouldReturnFalse() {
        assertFalse(graph.removeVertex("X"));
    }

    @Test
    void addEdgeShouldAddEdgeBetweenExistingVertices() {
        graph.addVertex("A");
        graph.addVertex("B");
        assertTrue(graph.addEdge("A", "B"));
        assertTrue(graph.containsEdge("A", "B"));
        assertEquals(1, graph.edgeCount());
    }

    @Test
    void addEdgeShouldFailIfVertexMissing() {
        graph.addVertex("A");
        assertFalse(graph.addEdge("A", "B"));
        assertFalse(graph.addEdge("B", "A"));
    }

    @Test
    void addEdgeShouldRejectDuplicate() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        assertFalse(graph.addEdge("A", "B"));
        assertEquals(1, graph.edgeCount());
    }

    @Test
    void removeEdgeShouldRemoveExistingEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        assertTrue(graph.removeEdge("A", "B"));
        assertFalse(graph.containsEdge("A", "B"));
    }

    @Test
    void removeEdgeShouldReturnFalseWhenMissing() {
        graph.addVertex("A");
        graph.addVertex("B");
        assertFalse(graph.removeEdge("A", "B"));
        assertFalse(graph.removeEdge("X", "Y"));
    }

    @Test
    void getNeighborsShouldReturnEmptySetForIsolatedVertex() {
        graph.addVertex("A");
        assertEquals(Set.of(), graph.getNeighbors("A"));
    }

    @Test
    void getNeighborsShouldReturnNullForUnknownVertex() {
        assertNull(graph.getNeighbors("Z"));
    }

    @Test
    void getNeighborsShouldReturnCopyNotLiveView() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        Set<String> neighbors = graph.getNeighbors("A");
        neighbors.add("C");
        assertEquals(Set.of("B"), graph.getNeighbors("A"));
    }

    @Test
    void getVerticesShouldReturnAllVertices() {
        graph.addVertex("A");
        graph.addVertex("B");
        assertEquals(Set.of("A", "B"), graph.getVertices());
    }

    @Test
    void containsEdgeShouldReturnFalseForUnknownVertex() {
        assertFalse(graph.containsEdge("X", "Y"));
    }

    @Test
    void vertexSetInternalShouldReturnVertices() {
        graph.addVertex("A");
        assertEquals(Set.of("A"), graph.vertexSetInternal());
    }
}