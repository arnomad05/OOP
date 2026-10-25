package ru.nsu.aimykhelov.task121;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Реализация графа на основе матрицы смежности.
 * Матрица хранится в виде {@code Map<V, Map<V, Boolean>>}, что позволяет
 * работать с произвольным типом вершин без привязки к индексам.
 *
 * @param <V> тип данных, хранимых в вершинах графа
 */
public class AdjacencyMatrixGraph<V> extends AbstractGraph<V> {

    private final Map<V, Map<V, Boolean>> matrix = new LinkedHashMap<>();

    /**
     * Добавляет вершину.
     *
     * @param value значение новой вершины
     * @return {@code true}, если вершина была добавлена
     */
    @Override
    public boolean addVertex(V value) {
        if (matrix.containsKey(value)) {
            return false;
        }
        matrix.put(value, new LinkedHashMap<>());
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
        if (!matrix.containsKey(value)) {
            return false;
        }
        matrix.remove(value);
        for (Map<V, Boolean> row : matrix.values()) {
            row.remove(value);
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
        if (!matrix.containsKey(from) || !matrix.containsKey(to)) {
            return false;
        }
        if (matrix.get(from).containsKey(to)) {
            return false;
        }
        matrix.get(from).put(to, Boolean.TRUE);
        return true;
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
        if (!matrix.containsKey(from)) {
            return false;
        }
        return matrix.get(from).remove(to) != null;
    }

    /**
     * Возвращает соседей вершины.
     *
     * @param value значение вершины
     * @return множество соседей
     */
    @Override
    public Set<V> getNeighbors(V value) {
        Map<V, Boolean> row = matrix.get(value);
        if (row == null) {
            return null;
        }
        return new HashSet<>(row.keySet());
    }

    /**
     * Возвращает количество вершин.
     *
     * @return количество вершин
     */
    @Override
    public int vertexCount() {
        return matrix.size();
    }

    /**
     * Возвращает количество рёбер.
     *
     * @return количество рёбер
     */
    @Override
    public int edgeCount() {
        int count = 0;
        for (Map<V, Boolean> row : matrix.values()) {
            count += row.size();
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
        return new HashSet<>(matrix.keySet());
    }

    /**
     * Проверяет наличие вершины.
     *
     * @param value значение вершины
     * @return {@code true}, если вершина существует
     */
    @Override
    public boolean containsVertex(V value) {
        return matrix.containsKey(value);
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
        Map<V, Boolean> row = matrix.get(from);
        return row != null && row.containsKey(to);
    }

    /**
     * Возвращает внутреннее множество вершин.
     *
     * @return множество вершин
     */
    @Override
    protected Set<V> vertexSetInternal() {
        return new HashSet<>(matrix.keySet());
    }
}
