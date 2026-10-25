package ru.nsu.aimykhelov.task121;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Реализация графа на основе матрицы инцидентности.
 * Каждое ребро идентифицируется парой (from, to), а матрица хранит
 * для каждой вершины набор рёбер, в которых она участвует.
 *
 * @param <V> тип данных, хранимых в вершинах графа
 */
public class IncidenceMatrixGraph<V> extends AbstractGraph<V> {

    private final Map<V, Map<String, Integer>> rows = new LinkedHashMap<>();
    private final List<V[]> edges = new ArrayList<>();

    /**
     * Возвращает уникальный ключ ребра.
     *
     * @param from начальная вершина
     * @param to   конечная вершина
     * @return строковый ключ
     */
    private String edgeKey(V from, V to) {
        return from + "->" + to;
    }

    /**
     * Возвращает индекс ребра или -1, если ребра нет.
     *
     * @param from начальная вершина
     * @param to   конечная вершина
     * @return индекс ребра
     */
    private int indexOfEdge(V from, V to) {
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i)[0].equals(from) && edges.get(i)[1].equals(to)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Добавляет вершину.
     *
     * @param value значение новой вершины
     * @return {@code true}, если вершина была добавлена
     */
    @Override
    public boolean addVertex(V value) {
        if (rows.containsKey(value)) {
            return false;
        }
        rows.put(value, new LinkedHashMap<>());
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
        if (!rows.containsKey(value)) {
            return false;
        }
        List<V[]> remaining = new ArrayList<>();
        for (V[] edge : edges) {
            if (!edge[0].equals(value) && !edge[1].equals(value)) {
                remaining.add(edge);
            }
        }
        edges.clear();
        edges.addAll(remaining);
        rows.remove(value);
        rebuildRows();
        return true;
    }

    /**
     * Перестраивает строки матрицы инцидентности после изменения рёбер.
     */
    private void rebuildRows() {
        for (V vertex : rows.keySet()) {
            rows.get(vertex).clear();
        }
        for (int i = 0; i < edges.size(); i++) {
            V from = edges.get(i)[0];
            V to = edges.get(i)[1];
            rows.get(from).put(edgeKey(from, to), 1);
            if (rows.containsKey(to)) {
                rows.get(to).put(edgeKey(from, to), -1);
            }
        }
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
        if (!rows.containsKey(from) || !rows.containsKey(to)) {
            return false;
        }
        if (indexOfEdge(from, to) != -1) {
            return false;
        }
        @SuppressWarnings("unchecked")
        V[] edge = (V[]) new Object[]{from, to};
        edges.add(edge);
        rows.get(from).put(edgeKey(from, to), 1);
        rows.get(to).put(edgeKey(from, to), -1);
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
        int index = indexOfEdge(from, to);
        if (index == -1) {
            return false;
        }
        edges.remove(index);
        rebuildRows();
        return true;
    }

    /**
     * Возвращает соседей вершины.
     *
     * @param value значение вершины
     * @return множество соседей
     */
    @Override
    public Set<V> getNeighbors(V value) {
        if (!rows.containsKey(value)) {
            return null;
        }
        Set<V> result = new HashSet<>();
        for (V[] edge : edges) {
            if (edge[0].equals(value)) {
                result.add(edge[1]);
            }
        }
        return result;
    }

    /**
     * Возвращает количество вершин.
     *
     * @return количество вершин
     */
    @Override
    public int vertexCount() {
        return rows.size();
    }

    /**
     * Возвращает количество рёбер.
     *
     * @return количество рёбер
     */
    @Override
    public int edgeCount() {
        return edges.size();
    }

    /**
     * Возвращает множество вершин.
     *
     * @return множество вершин
     */
    @Override
    public Set<V> getVertices() {
        return new HashSet<>(rows.keySet());
    }

    /**
     * Проверяет наличие вершины.
     *
     * @param value значение вершины
     * @return {@code true}, если вершина существует
     */
    @Override
    public boolean containsVertex(V value) {
        return rows.containsKey(value);
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
        return indexOfEdge(from, to) != -1;
    }

    /**
     * Возвращает внутреннее множество вершин.
     *
     * @return множество вершин
     */
    @Override
    protected Set<V> vertexSetInternal() {
        return new HashSet<>(rows.keySet());
    }
}
