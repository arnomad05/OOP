package ru.nsu.aimykhelov.task121;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Реализация графа на основе списка смежности.
 * Каждой вершине сопоставляется множество её соседей (куда ведут рёбра).
 *
 * @param <V> тип данных, хранимых в вершинах графа
 */
public class AdjacencyListGraph<V> extends AbstractGraph<V> {

    private final Map<V, Set<V>> adjacency = new LinkedHashMap<>();

    /**
     * Добавляет вершину.
     *
     * @param value значение новой вершины
     * @return {@code true}, если вершина была добавлена
     */
    @Override
    public boolean addVertex(V value) {
        if (adjacency.containsKey(value)) {
            return false;
        }
        adjacency.put(value, new HashSet<>());
        return true;
    }

    /**
     * Удаляет вершину и все инцидентные рёбра.
     *
     * @param value значение удаляемой вершины
     * @return {@code true}, если вершина была удалена
     */
    @Override
    public boolean removeVertex(V value) {
        if (!adjacency.containsKey(value)) {
            return false;
        }
        adjacency.remove(value);
        for (Set<V> neighbors : adjacency.values()) {
            neighbors.remove(value);
        }
        return true;
    }

    /**
     * Добавляет ребро.
     *
     * @param from начальная вершина
     * @param to   конечная вершина
     * @return {@code true}, если ребро добавлено
     */
    @Override
    public boolean addEdge(V from, V to) {
        Set<V> neighbors = adjacency.get(from);
        if (neighbors == null || !adjacency.containsKey(to)) {
            return false;
        }
        return neighbors.add(to);
    }

    /**
     * Удаляет ребро.
     *
     * @param from начальная вершина
     * @param to   конечная вершина
     * @return {@code true}, если ребро удалено
     */
    @Override
    public boolean removeEdge(V from, V to) {
        Set<V> neighbors = adjacency.get(from);
        return neighbors != null && neighbors.remove(to);
    }

    /**
     * Возвращает соседей вершины.
     *
     * @param value значение вершины
     * @return множество соседей
     */
    @Override
    public Set<V> getNeighbors(V value) {
        Set<V> neighbors = adjacency.get(value);
        return neighbors == null ? null : new HashSet<>(neighbors);
    }

    /**
     * Возвращает количество вершин.
     *
     * @return количество вершин
     */
    @Override
    public int vertexCount() {
        return adjacency.size();
    }

    /**
     * Возвращает количество рёбер.
     *
     * @return количество рёбер
     */
    @Override
    public int edgeCount() {
        int count = 0;
        for (Set<V> neighbors : adjacency.values()) {
            count += neighbors.size();
        }
        return count;
    }

    /**
     * Возвращает множество вершин.
     *
     * @return множество вершин
     */
    @Override
    public Set<V> getVertices() {
        return new HashSet<>(adjacency.keySet());
    }

    /**
     * Проверяет наличие вершины.
     *
     * @param value значение вершины
     * @return {@code true}, если вершина существует
     */
    @Override
    public boolean containsVertex(V value) {
        return adjacency.containsKey(value);
    }

    /**
     * Проверяет наличие ребра.
     *
     * @param from начальная вершина
     * @param to   конечная вершина
     * @return {@code true}, если ребро существует
     */
    @Override
    public boolean containsEdge(V from, V to) {
        Set<V> neighbors = adjacency.get(from);
        return neighbors != null && neighbors.contains(to);
    }

    /**
     * Возвращает внутреннее множество вершин.
     *
     * @return множество вершин
     */
    @Override
    protected Set<V> vertexSetInternal() {
        return new HashSet<>(adjacency.keySet());
    }
}
