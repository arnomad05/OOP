package ru.nsu.aimykhelov.task121;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Утилита сохранения графа в файл фиксированного формата.
 */
public final class GraphWriter {

    private GraphWriter() {
    }

    /**
     * Записывает граф в файл.
     *
     * @param graph целевой граф
     * @param path  путь к файлу
     * @param <V>   тип вершин
     * @throws IOException при ошибке записи
     */
    public static <V> void write(Graph<V> graph, String path) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(path))) {
            writer.write(graph.vertexCount() + " " + graph.edgeCount());
            writer.newLine();
            for (V vertex : graph.getVertices()) {
                writer.write(String.valueOf(vertex));
                writer.newLine();
            }
            List<V[]> edges = graph.getEdges();
            for (V[] edge : edges) {
                writer.write(edge[0] + " " + edge[1]);
                writer.newLine();
            }
        }
    }
}
