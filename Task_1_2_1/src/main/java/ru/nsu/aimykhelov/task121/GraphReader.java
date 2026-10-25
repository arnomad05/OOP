package ru.nsu.aimykhelov.task121;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Утилита чтения графа из файла фиксированного формата.
 * Формат: первая строка — {@code vertexCount edgeCount}, далее строки рёбер
 * {@code from to}. Пустые строки пропускаются без учёта в счётчиках.
 */
public final class GraphReader {

    private GraphReader() {
    }

    /**
     * Читает граф из файла.
     *
     * @param graph целевой граф
     * @param path  путь к файлу
     * @param <V>   тип вершин
     * @throws IOException при ошибке чтения
     */
    @SuppressWarnings("unchecked")
    public static <V> void read(Graph<V> graph, String path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path))) {
            String header = reader.readLine();
            if (header == null) {
                return;
            }
            String[] parts = header.trim().split("\\s+");
            if (parts.length != 2) {
                throw new IOException("Invalid header: " + header);
            }
            int vertexCount = Integer.parseInt(parts[0]);
            int edgeCount = Integer.parseInt(parts[1]);

            for (int i = 0; i < vertexCount; ) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                if (line.isBlank()) {
                    continue;
                }
                graph.addVertex((V) line.trim());
                i++;
            }

            for (int i = 0; i < edgeCount; ) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                if (line.isBlank()) {
                    continue;
                }
                String[] edgeParts = line.trim().split("\\s+");
                if (edgeParts.length >= 2) {
                    graph.addEdge((V) edgeParts[0], (V) edgeParts[1]);
                    i++;
                }
            }
        }
    }
}
