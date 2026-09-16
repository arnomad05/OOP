package ru.nsu.aimykhelov.task111;

import java.util.Arrays;

/**
 * Демонстрация работы {@link Sort} на примере из задания.
 */
public final class Main {

    private Main() {
    }

    /**
     * Запускает демонстрацию.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        int[] input = {5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(input));

        Sort.sort(input);

        System.out.println(Arrays.toString(input));
    }
}
