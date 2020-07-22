package com.example.demo.problemset.greedy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 贪心算法
 */
public class Application {
    public static void main(String[] args) {
        int[] bills = {5, 5, 5, 10, 20};
        lemonadeChange(bills);
        ReentrantLock
    }

    public int count(String filename, String str) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        StringBuilder sb = new StringBuilder();
        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            int index;
            while ((index = sb.indexOf(str)) != -1) {
                sb.delete(0, index + str.length());
                count++;
            }

            if (sb.length() > str.length()) {
                sb.delete(0, sb.length() - str.length() - 1);
            }
        }

        return count;
    }

    /**
     * 134. 加油站
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            int j = i;
            while (j != i) {

                j++;
                if (j > gas.length) {
                    j %= gas.length;
                }
            }
        }

        return 0;
    }

    /**
     * 944. 删列造序
     *
     * @param A
     * @return
     */
    public int minDeletionSize(String[] A) {
        if (A.length == 0) {
            return 0;
        }

        int d = 0;
        int colLen = A[0].length();
        int rLen;
        for (int c = 0; c < colLen; c++) {
            rLen = A.length - 1;
            for (int r = 0; r < rLen; r++) {
                if (A[r].charAt(c) > A[r + 1].charAt(c)) {
                    d++;
                    break;
                }
            }
        }

        return d;
    }

    /**
     * 860. 柠檬水找零
     *
     * @param bills
     * @return
     */
    public static boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int n : bills) {
            if (n == 5) {
                five++;
            } else if (n == 10) {
                if (five == 0) {
                    return false;
                }

                five--;
                ten++;
            } else {
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else if (ten == 0 && five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 455. 分发饼干
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                i++;
            }

            j++;
        }

        return i;
    }
}
