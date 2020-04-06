package com.example.demo.sort;

public class heapSort {
    public static void main(String[] args) {
        int[] array = {4, 6, 28, 5, 4, 9, 11, 64, 6, 45, 8, 98, 32, 45, 63};
        sort(array);

        for (int i = array.length - 1; i >= 0; i--) {
            System.out.println(array[i]);
        }
    }

    static void sort(int[] array) {
        for (int i = array.length / 2; i >= 0; i--) {
            downAjust(array, i, array.length);
        }

        int t;
        for (int i = array.length - 1; i > 0; i--) {
            t = array[i];
            array[i] = array[0];
            array[0] = t;

            downAjust(array, 0, i);
        }
    }

    static void downAjust(int[] array, int parentIndex, int length) {
        int parentValue = array[parentIndex];
        int childIndex = parentIndex * 2 + 1;
        while (childIndex < length) {
            if (childIndex + 1 < length && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }

            if (parentValue > array[childIndex]) {
                break;
            }

            array[parentIndex] = array[childIndex];

            parentIndex = childIndex;
            childIndex = childIndex * 2 + 1;
        }

        array[parentIndex] = parentValue;
    }
}
