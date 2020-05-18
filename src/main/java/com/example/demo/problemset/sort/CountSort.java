package com.example.demo.problemset.sort;

public class CountSort {
    public static void main(String[] args) {
        int[] arr = {95, 86, 90, 87, 94, 92, 93};
        countSort(arr);
    }

    static void countSort(int[] array) {
        int max = array[0];
        int min = max;
        for (int i = 0; i < array.length; i++) {
            max = Math.max(max, array[i]);
            min = Math.min(min, array[i]);
        }

        int[] countArray = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            countArray[array[i] - min]++;
        }

        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }

        int[] result = new int[array.length];
        int idx;
        for (int i = array.length - 1; i >= 0; i--) {
            idx = array[i] - min;
            result[countArray[idx]-1] = array[i];
            countArray[idx]--;
        }

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
