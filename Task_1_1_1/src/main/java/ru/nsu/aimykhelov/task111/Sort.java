package ru.nsu.aimykhelov.task111;

/**
 * Пирамидальная сортировка (heapsort).
 * Сортирует массив по возрастанию, O(n log n) во всех случаях, O(1) по памяти.
 */
public final class Sort {

    private Sort() {
    }

    /**
     * Сортирует массив {@code arr} по возрастанию (in-place).
     *
     * @param arr массив; не должен быть {@code null}
     * @throws NullPointerException если {@code arr == null}
     */
    public static void sort(int[] arr) {
        if (arr == null) {
            throw new NullPointerException("arr");
        }
        int n = arr.length;
        if (n < 2) {
            return;
        }

        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            siftDown(arr, i, 0);
        }
    }

    private static void siftDown(int[] arr, int size, int i) {
        while (true) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && arr[left] > arr[largest]) {
                largest = left;
            }
            if (right < size && arr[right] > arr[largest]) {
                largest = right;
            }
            if (largest == i) {
                return;
            }
            swap(arr, i, largest);
            i = largest;
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
