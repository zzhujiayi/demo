package com.example.demo.problemset.binary;

public class Application {
    public static void main(String[] args) {

        isPowerOfFour(-2147483648);
    }

    /**
     * 342. 4的幂
     *
     * @param n
     * @return
     */
    public static boolean isPowerOfFour(int n) {
        return n > 0 && (n & n - 1) != 0 && (n & 0b10101010_10101010_10101010_10101010) == 0;
    }

    public static int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }

        if (k <= (1 << (n - 2))) {
            return kthGrammar(n - 1, k);
        }

        return kthGrammar(n - 1, k - (1 << (n - 2))) ^ 1;
    }

    /**
     * 461. 汉明距离
     *
     * @param x
     * @param y
     * @return
     */
    public static int hammingDistance(int x, int y) {
        int dif = x ^ y;
        int ans = 0;
        while (dif > 0) {
            ans += dif & 1;
            dif >>= 1;
        }

        return ans;
    }

    public boolean isPerfectSquare(int num) {
        if (num < 2) {
            return true;
        }

//        int x = num / 2;
//        while ((x * x) > num) {
//            x = (x + (num / x)) / 2;
//        }
//
//        return x * x == num;

        int step = 1;
        while (num > 0) {
            num -= step;
            step += 2;
        }

        return num == 0;
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
