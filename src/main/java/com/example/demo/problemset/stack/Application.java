package com.example.demo.problemset.stack;

import java.util.Stack;

public class Application {
    public static void main(String[] args) {
        System.out.println(reverseWords("Let's take LeetCode contest"));
    }

    public static String reverseWords(String s) {
        if (s.length() == 0) {
            return "";
        }

        int i = 0;
        int j = -1;
        StringBuilder sb = new StringBuilder();
        while (i <= s.length()) {
            if (i==s.length()|| s.charAt(i) == 32) {
                for (int k = i - 1; k > j; k--) {
                    sb.append(s.charAt(k));
                }

                if(i!=s.length()){
                    sb.append(' ');
                }

                j = i;
            }

            i++;
        }

        return sb.toString();
    }
}
