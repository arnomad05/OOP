package ru.nsu.aimykhelov.Task_1_1_1;

import java.util.Arrays;

public final class Main {

    public static void main(String[] args) {
        int[] input = {5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(input));

        Sort.sort(input);

        System.out.println(Arrays.toString(input));
    }
}
