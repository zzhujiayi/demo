package com.example.demo.problemset.string;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        System.out.println(wordPattern("aba", "dog cat cat"));

    }

    public static boolean wordPattern(String pattern, String str) {
        String[] strings = str.split(" ");
        char[] chars = pattern.toCharArray();
        if (strings.length != chars.length) {
            return false;
        }

        int i = 0;
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        while (i < chars.length) {
            if (!map1.getOrDefault(chars[i], 0).equals(map2.getOrDefault(strings[i], 0))) {
                return false;
            }

            map1.put(chars[i], i);
            map2.put(strings[i], i);

            i++;
        }

        return true;
    }

    public static int repeatedNTimes(int[] A) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }

        int times = A.length / 2;
        for (Integer key : map.keySet()) {
            if (map.get(key) == times) {
                return key;
            }
        }

        return -1;
    }

    public static boolean checkValidString(String s) {
        if (s == null) {
            return false;
        }

        if (s.length() == 0) {
            return true;
        }

        char[] chars = s.toCharArray();
        int left = 0;
        int common = 0;
        int leftCommon = 0;
        for (char c : chars) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                leftCommon += common;
                common = 0;
                if (left == 0 && leftCommon == 0) {
                    return false;
                } else if (left > 0) {
                    left--;
                } else {
                    leftCommon--;
                }
            } else {
                common++;
            }
        }

        return common >= left;
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0;
        int end = 0;
        char[] charArr = s.toCharArray();

        for (int k = 0; k < charArr.length; k++) {
            int i = k;
            int j = k;
            while (j < charArr.length && charArr[i] == charArr[j]) {
                j++;
            }

            j--;
            int temp = j;
            while (i >= 0 && j < charArr.length && charArr[i] == charArr[j]) {
                i--;
                j++;
            }

            int len = j - i - 1;
            int preLen = end - start;
            if (len > preLen) {
                start = i + 1;
                end = j - 1;
            }

            if ((charArr.length - temp) * 2 <= (end - start)) {
                break;
            }

            k = temp;
        }

        return s.substring(start, end + 1);
    }

    public static void reverseString(char[] s) {
        if (s.length == 0) {
            return;
        }

        char t;
        int i = 0;
        int j = s.length - 1;
        while (j > i) {
            t = s[i];
            s[i] = s[j];
            s[j] = t;

            i++;
            j--;
        }
    }
}
