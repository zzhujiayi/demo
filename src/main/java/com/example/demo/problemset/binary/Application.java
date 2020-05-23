package com.example.demo.problemset.binary;

public class Application {
    public static void main(String[] args) {
        System.out.println(hasAlternatingBits(4));
    }

    public static boolean hasAlternatingBits(int n) {
        n = n ^ (n >> 1);
        return (n & ((long) n + 1)) == 0;
    }
}
