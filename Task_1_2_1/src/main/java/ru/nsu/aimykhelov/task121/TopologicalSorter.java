package ru.nsu.aimykhelov.task121;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Утилита топологической сортировки графа.
 * Содержит реализации алгоритмов Кана и DFS.
 */
public final class TopologicalSorter {

    private TopologicalSorter() {
    }

    /**
     * Выполняет топологическую сортировку выбранным алгоритмом.
     *
     * @param graph     граф
     * @param algorithm алгоритм
     * @param <V>       тип вершин
     * @return список вершин в топологическом порядке
     * @throws IllegalStateException если граф содержит цикл
     */
    public static <V> List<V> sort(Graph<V> graph, TopologicalSortAlgorithm algorithm) {
        return switch (algorithm) {
            case KAHN -> kahn(graph);
            case DFS -> dfs(graph);
        };
    }

    /**
     * Топологическая сортировка алгоритмом Кана.
     *
     * @param graph граф
     * @param <V>   тип вершин
     * @return список вершин в топологическом порядке
     * @throws IllegalStateException если граф содержит цикл
     */
    public static <V> List<V> kahn(Graph<V> graph) {
        Map<V, Integer> inDegree = new HashMap<>();
        for (V vertex : graph.getVertices()) {
            inDegree.put(vertex, 0);
        }
        for (V vertex : graph.getVertices()) {
            for (V neighbor : graph.getNeighbors(vertex)) {
                inDegree.merge(neighbor, 1, Integer::sum);
            }
        }
        Deque<V> queue = new ArrayDeque<>();
        for (Map.Entry<V, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }
        List<V> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            V current = queue.poll();
            result.add(current);
            for (V neighbor : graph.getNeighbors(current)) {
                int degree = inDegree.merge(neighbor, -1, Integer::sum);
                if (degree == 0) {
                    queue.add(neighbor);
                }
            }
        }
        if (result.size() != graph.vertexCount()) {
            throw new IllegalStateException("Graph contains a cycle");
        }
        return result;
    }

    /**
     * Топологическая сортировка на основе поиска в глубину.
     *
     * @param graph граф
     * @param <V>   тип вершин
     * @return список вершин в топологическом порядке
     * @throws IllegalStateException если граф содержит цикл
     */
    public static <V> List<V> dfs(Graph<V> graph) {
        Set<V> visited = new HashSet<>();
        Set<V> inStack = new LinkedHashSet<>();
        List<V> result = new ArrayList<>();
        for (V vertex : graph.getVertices()) {
            if (!visited.contains(vertex)) {
                dfsVisit(graph, vertex, visited, inStack, result);
            }
        }
        List<V> reversed = new ArrayList<>(result);
        java.util.Collections.reverse(reversed);
        return reversed;
    }

    /**
     * Рекурсивный обход в глубину.
     *
     * @param graph   граф
     * @param vertex  текущая вершина
     * @param visited множество посещённых
     * @param inStack множество в текущем стеке (для обнаружения циклов)
     * @param result  результирующий список
     * @param <V>     тип вершин
     */
    private static <V> void dfsVisit(Graph<V> graph, V vertex, Set<V> visited,
                                     Set<V> inStack, List<V> result) {
        visited.add(vertex);
        inStack.add(vertex);
        for (V neighbor : graph.getNeighbors(vertex)) {
            if (inStack.contains(neighbor)) {
                throw new IllegalStateException("Graph contains a cycle");
            }
            if (!visited.contains(neighbor)) {
                dfsVisit(graph, neighbor, visited, inStack, result);
            }
        }
        inStack.remove(vertex);
        result.add(vertex);
    }
}
