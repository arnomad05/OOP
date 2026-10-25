package ru.nsu.aimykhelov.task121;

/**
 * Перечисление доступных алгоритмов топологической сортировки.
 */

public enum TopologicalSortAlgorithm {

    /**
     * Алгоритм Кана (на основе степеней входа).
     */
    KAHN,

    /**
     * Поиск в глубину с пост-обработкой (DFS-based).
     */
    DFS
}

