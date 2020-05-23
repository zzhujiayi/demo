package com.example.demo.problemset.calc;

import java.util.HashSet;

public class Application {
    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
    }

    public int numberOfSteps(int num) {
        int step = 0;
        while (num != 0) {
            if (num % 2 != 0) {
                num -= 1;
            } else {
                num /= 2;
            }

            step++;
        }

        return step;
    }

    public static int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }

        int[] ans = new int[n];
        ans[0] = 1;
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        int n1, n2, n3;
        for (int i = 1; i < n; i++) {
            n1 = ans[i2] * 2;
            n2 = ans[i3] * 3;
            n3 = ans[i5] * 5;
            ans[i] = Math.min(Math.min(n1, n2), n3);
            if (ans[i] == n1) {
                i2++;
            } else if (ans[i] == n2) {
                i3++;
            } else {
                i5++;
            }
        }

        return ans[n - 1];
    }
}
