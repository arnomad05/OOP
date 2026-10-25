package ru.nsu.aimykhelov.task121;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Тесты для {@link GraphReader} и {@link GraphWriter}.
 */
class GraphReaderWriterTest {

    @Test
    void writeThenReadShouldRestoreGraph(@TempDir Path dir) throws IOException {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");

        Path file = dir.resolve("graph.txt");
        graph.writeToFile(file.toString());

        AdjacencyListGraph<String> loaded = new AdjacencyListGraph<>();
        loaded.readFromFile(file.toString());

        assertEquals(graph.getVertices(), loaded.getVertices());
        assertEquals(graph.getNeighbors("A"), loaded.getNeighbors("A"));
        assertEquals(graph.getNeighbors("B"), loaded.getNeighbors("B"));
        assertEquals(graph.edgeCount(), loaded.edgeCount());
    }

    @Test
    void readShouldWorkForAllRepresentations(@TempDir Path dir) throws IOException {
        Path file = dir.resolve("g.txt");
        Files.writeString(file, "3 2\nA\nB\nC\nA B\nB C\n");

        AdjacencyListGraph<String> list = new AdjacencyListGraph<>();
        list.readFromFile(file.toString());
        AdjacencyMatrixGraph<String> matrix = new AdjacencyMatrixGraph<>();
        matrix.readFromFile(file.toString());
        IncidenceMatrixGraph<String> incidence = new IncidenceMatrixGraph<>();
        incidence.readFromFile(file.toString());

        assertEquals(3, list.vertexCount());
        assertEquals(2, list.edgeCount());
        assertEquals(list.getVertices(), matrix.getVertices());
        assertEquals(list.getVertices(), incidence.getVertices());
        assertEquals(Set.of("B"), list.getNeighbors("A"));
    }

    @Test
    void readEmptyFileShouldNotFail(@TempDir Path dir) throws IOException {
        Path file = dir.resolve("empty.txt");
        Files.writeString(file, "");
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        graph.readFromFile(file.toString());
        assertEquals(0, graph.vertexCount());
    }

    @Test
    void readFileWithBlankLinesShouldSkipThem(@TempDir Path dir) throws IOException {
        Path file = dir.resolve("g.txt");
        Files.writeString(file, "2 1\nA\nB\n\nA B\n");
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        graph.readFromFile(file.toString());
        assertEquals(2, graph.vertexCount());
        assertTrue(graph.containsEdge("A", "B"));
    }
}