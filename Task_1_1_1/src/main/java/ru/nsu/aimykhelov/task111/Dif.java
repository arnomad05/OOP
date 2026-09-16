package ru.nsu.aimykhelov.task111;

import java.util.Arrays;
import java.util.Random;

/**
 * Бенчмарк: эмпирическая проверка сложности {@link Sort}.
 */

public final class Dif {

    private Dif() {
    }

    /**
    * Замеряет время сортировки на массивах разного размера
     * и выводит отношение t / (n log2 n).
     */

    public static void main(String[] args) {
        int[] sizes = {10_000, 50_000, 100_000, 500_000, 1_000_000, 2_000_000};
        Random rnd = new Random(1);

        System.out.printf("%12s %15s %18s%n",
                "n", "t_med (ms)", "t / (n log2 n) [ns]");
        System.out.println("-".repeat(48));

        for (int n : sizes) {
            int[] base = new int[n];
            for (int i = 0; i < n; i++) {
                base[i] = rnd.nextInt();
            }

            warmUp(base);

            int runs = 7;
            long[] times = new long[runs];
            for (int r = 0; r < runs; r++) {
                int[] copy = base.clone();
                long start = System.nanoTime();
                Sort.sort(copy);
                times[r] = System.nanoTime() - start;

                if (!isSorted(copy)) {
                    throw new AssertionError("not sorted at n=" + n);
                }
            }
            Arrays.sort(times);
            long medianNs = times[runs / 2];

            double ms = medianNs / 1_000_000.0;
            double logFactor = n * (Math.log(n) / Math.log(2));
            double nsPerUnit = medianNs / logFactor;

            System.out.printf("%12d %15.2f %18.3f%n", n, ms, nsPerUnit);
        }
    }

    private static void warmUp(int[] arr) {
        for (int i = 0; i < 3; i++) {
            int[] copy = arr.clone();
            Sort.sort(copy);
        }
    }

    private static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] > a[i]) {
                return false;
            }
        }
        return true;
    }
}