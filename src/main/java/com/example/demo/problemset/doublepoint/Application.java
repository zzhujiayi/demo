package com.example.demo.problemset.doublepoint;

/**
 * 双指针
 */
public class Application {
    public static void main(String[] args) {

    }

    /**
     * 392. 判断子序列
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isSubsequence(String s, String t) {

        char[] s_chars = s.toCharArray();
        char[] t_chars = t.toCharArray();

        int i = 0;
        int j = 0;
        while (i < s_chars.length && j < t_chars.length) {
            if (s_chars[i] == t_chars[j]) {
                i++;
            }

            j++;
        }

        return i >= s_chars.length;
    }
}
