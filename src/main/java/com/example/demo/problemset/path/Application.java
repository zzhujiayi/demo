package com.example.demo.problemset.path;

import java.util.HashMap;

public class Application {
    public static void main(String[] args) {

    }

    public int majorityElement(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n > max) {
                max = n;
            } else if (min > n) {
                min = n;
            }
        }

        int length = max - min;
        int[] mmap = new int[length];

        int m = nums.length / 2;
        int t;
        for (int n : nums) {
            mmap[n - length]++;
            if (mmap[n - length] > m) {
                return n;
            }
        }

        return 0;
    }
}
