package com.example.demo.problemset.binary;

public class Application {
    public static void main(String[] args) {
        System.out.println(hasAlternatingBits(4));
    }

    public int findComplement(int num) {
        int mask = 1;
        int ans = 0;
        for (int i = 1; i < 32 && mask <= num; i++) {
            if ((mask & num) == 0) {
                ans += mask;
            }

            mask <<= 1;
        }

        return ans;
    }

    public static boolean hasAlternatingBits(int n) {
        n = n ^ (n >> 1);
        return (n & ((long) n + 1)) == 0;
    }
}
