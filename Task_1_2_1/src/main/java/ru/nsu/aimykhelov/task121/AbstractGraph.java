package ru.nsu.aimykhelov.task121;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Абстрактная базовая реализация интерфейса {@link Graph}.
 * Содержит общую логику, не зависящую от способа представления графа.
 *
 * @param <V> тип данных, хранимых в вершинах графа
 */
public abstract class AbstractGraph<V> implements Graph<V> {

    /**
     * Загружает граф из файла фиксированного формата.
     * Формат: первая строка — количество вершин и рёбер через пробел,
     * далее строки рёбер в виде {@code from to}.
     *
     * @param path путь к файлу
     * @throws IOException при ошибке чтения
     */
    @Override
    public void readFromFile(String path) throws IOException {
        GraphReader.read(this, path);
    }

    /**
     * Сохраняет граф в файл фиксированного формата.
     *
     * @param path путь к файлу
     * @throws IOException при ошибке записи
     */
    @Override
    public void writeToFile(String path) throws IOException {
        GraphWriter.write(this, path);
    }

    /**
     * Выполняет топологическую сортировку графа.
     *
     * @param algorithm алгоритм сортировки
     * @return список вершин в топологическом порядке
     * @throws IllegalStateException если граф содержит цикл
     */
    @Override
    public List<V> topologicalSort(TopologicalSortAlgorithm algorithm) {
        return TopologicalSorter.sort(this, algorithm);
    }

    /**
     * Возвращает список всех рёбер графа.
     *
     * @return список пар {@code [from, to]}
     */
    @Override
    public List<V[]> getEdges() {
        List<V[]> edges = new ArrayList<>();
        for (V from : getVertices()) {
            for (V to : getNeighbors(from)) {
                @SuppressWarnings("unchecked")
                V[] edge = (V[]) java.lang.reflect.Array.newInstance(from.getClass(), 2);
                edge[0] = from;
                edge[1] = to;
                edges.add(edge);
            }
        }
        return edges;
    }

    /**
     * Сравнивает граф с другим объектом на равенство.
     * Графы равны, если совпадают множества вершин и рёбер.
     *
     * @param obj объект для сравнения
     * @return {@code true}, если графы эквивалентны
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Graph<?>)) {
            return false;
        }
        Graph<V> other = (Graph<V>) obj;
        if (!getVertices().equals(other.getVertices())) {
            return false;
        }
        if (edgeCount() != other.edgeCount()) {
            return false;
        }
        for (V from : getVertices()) {
            Set<V> mine = getNeighbors(from);
            Set<V> theirs = other.getNeighbors(from);
            if (mine == null ? theirs != null : !mine.equals(theirs)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Возвращает хеш-код графа, вычисленный по множеству вершин и рёбер.
     *
     * @return хеш-код
     */
    @Override
    public int hashCode() {
        return Objects.hash(getVertices(), edgeCount());
    }

    /**
     * Возвращает строковое представление графа.
     *
     * @return строка с описанием вершин и рёбер
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append("{");
        sb.append("vertices=").append(getVertices());
        sb.append(", edges=");
        List<V[]> edges = getEdges();
        sb.append("[");
        for (int i = 0; i < edges.size(); i++) {
            sb.append("(").append(edges.get(i)[0]).append("->").append(edges.get(i)[1]).append(")");
            if (i < edges.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]}");
        return sb.toString();
    }

    /**
     * Вспомогательный метод: возвращает множество вершин как защитную копию.
     *
     * @return множество вершин
     */
    protected abstract Set<V> vertexSetInternal();
}
