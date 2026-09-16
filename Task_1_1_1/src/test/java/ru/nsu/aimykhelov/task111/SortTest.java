package ru.nsu.aimykhelov.task111;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Юнит-тесты для {@link Sort}.
 */

class SortTest {

    @Test
    void taskExample() {
        int[] arr = {5, 4, 3, 2, 1};
        Sort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void alreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        Sort.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void reverseSorted() {
        int[] arr = {9, 7, 5, 3, 1};
        Sort.sort(arr);
        assertArrayEquals(new int[]{1, 3, 5, 7, 9}, arr);
    }

    @Test
    void allEqual() {
        int[] arr = {4, 4, 4, 4, 4};
        Sort.sort(arr);
        assertArrayEquals(new int[]{4, 4, 4, 4, 4}, arr);
    }

    @Test
    void emptyArray() {
        int[] arr = {};
        Sort.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void singleElement() {
        int[] arr = {42};
        Sort.sort(arr);
        assertArrayEquals(new int[]{42}, arr);
    }


}
