package ru.nsu.aimykhelov.task121;

import java.util.List;
import java.util.Set;

/**
 * Интерфейс графа, определяющий базовые операции над вершинами и рёбрами,
 * а также операции сравнения, вывода в строку и сохранения/загрузки.
 *
 * @param <V> тип данных, хранимых в вершинах графа
 */
public interface Graph<V> {

    /**
     * Добавляет вершину с указанным значением в граф.
     *
     * @param value значение новой вершины
     * @return {@code true}, если вершина была добавлена, иначе {@code false}
     */
    boolean addVertex(V value);

    /**
     * Удаляет вершину с указанным значением и все инцидентные ей рёбра.
     *
     * @param value значение удаляемой вершины
     * @return {@code true}, если вершина была удалена, иначе {@code false}
     */
    boolean removeVertex(V value);

    /**
     * Добавляет направленное ребро из вершины {@code from} в вершину {@code to}.
     *
     * @param from значение начальной вершины
     * @param to   значение конечной вершины
     * @return {@code true}, если ребро было добавлено, иначе {@code false}
     */
    boolean addEdge(V from, V to);

    /**
     * Удаляет направленное ребро из вершины {@code from} в вершину {@code to}.
     *
     * @param from значение начальной вершины
     * @param to   значение конечной вершины
     * @return {@code true}, если ребро было удалено, иначе {@code false}
     */
    boolean removeEdge(V from, V to);

    /**
     * Возвращает множество всех соседей указанной вершины (вершин, в которые
     * идут рёбра из данной).
     *
     * @param value значение вершины
     * @return множество значений соседних вершин или {@code null}, если вершины нет
     */
    Set<V> getNeighbors(V value);

    /**
     * Возвращает количество вершин в графе.
     *
     * @return количество вершин
     */
    int vertexCount();

    /**
     * Возвращает количество рёбер в графе.
     *
     * @return количество рёбер
     */
    int edgeCount();

    /**
     * Возвращает множество всех вершин графа.
     *
     * @return множество вершин графа
     */
    Set<V> getVertices();

    /**
     * Возвращает список всех рёбер графа в виде пар {@code [from, to]}.
     *
     * @return список рёбер
     */
    List<V[]> getEdges();

    /**
     * Проверяет наличие вершины с указанным значением.
     *
     * @param value значение вершины
     * @return {@code true}, если вершина существует
     */
    boolean containsVertex(V value);

    /**
     * Проверяет наличие направленного ребра между указанными вершинами.
     *
     * @param from значение начальной вершины
     * @param to   значение конечной вершины
     * @return {@code true}, если ребро существует
     */
    boolean containsEdge(V from, V to);

    /**
     * Выполняет топологическую сортировку вершин графа с использованием
     * указанного алгоритма.
     *
     * @param algorithm алгоритм топологической сортировки
     * @return список вершин в топологическом порядке
     * @throws IllegalStateException если граф содержит цикл
     */
    List<V> topologicalSort(TopologicalSortAlgorithm algorithm);

    /**
     * Загружает граф из файла фиксированного формата.
     *
     * @param path путь к файлу
     * @throws java.io.IOException при ошибке чтения
     */
    void readFromFile(String path) throws java.io.IOException;

    /**
     * Сохраняет граф в файл фиксированного формата.
     *
     * @param path путь к файлу
     * @throws java.io.IOException при ошибке записи
     */
    void writeToFile(String path) throws java.io.IOException;
}
