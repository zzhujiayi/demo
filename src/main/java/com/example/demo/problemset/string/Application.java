package com.example.demo.problemset.string;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        String first = "a";
        String second = "a";
        System.out.println(oneEditAway(first, second));
    }

    /**
     * 面试题 01.05. 一次编辑
     *
     * @param first
     * @param second
     * @return
     */
    public static boolean oneEditAway(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }

        int max = Math.max(first.length(), second.length());
        int match = 0;
        for (int i = 0; i < Math.min(first.length(), second.length()); i++) {
            if (first.charAt(i) == second.charAt(i)) {
                match++;
            } else {
                break;
            }
        }

        int i = first.length() - 1;
        int j = second.length() - 1;
        int skipFront = match;
        while (i >= skipFront && j >= skipFront) {
            if (first.charAt(i) == second.charAt(j)) {
                match++;
            } else {
                break;
            }

            i--;
            j--;
        }

        return match == max || match == (max - 1);
    }

    /**
     * 1566. 重复至少 K 次且长度为 M 的模式
     *
     * @param arr
     * @param m
     * @param k
     * @return
     */
    public static boolean containsPattern(int[] arr, int m, int k) {
        int len = m * k;
        if (len > arr.length) {
            return false;
        }

        int offset;
        for (int i = 0; i + len <= arr.length; i++) {
            for (offset = 0; offset < len; offset++) {
                if (arr[offset + i] != arr[i + offset * m]) {
                    break;
                }
            }

            if (offset == len) {
                return true;
            }
        }

        return false;
    }

    /**
     * 696. 计数二进制子串
     *
     * @param s
     * @return
     */
    public static int countBinarySubstrings(String s) {
        int i = 0;
        int len = s.length();
        int ans = 0;
        int pre = 0;
        while (i < len) {
            char c = s.charAt(i);
            int count = 0;
            while (i < len && c == s.charAt(i)) {
                count++;
                i++;
            }

            ans += Math.min(pre, count);
            pre = count;
        }

        return ans;
    }

    /**
     * 1002. 查找常用字符
     *
     * @param A
     * @return
     */
    public static List<String> commonChars(String[] A) {
        int[] ans = null;
        for (String s : A) {
            int[] map = new int[26];
            for (char c : s.toCharArray()) {
                map[c - 97]++;
            }

            if (ans == null) {
                ans = map;
            } else {
                for (int i = 0; i < map.length; i++) {
                    ans[i] = Math.min(ans[i], map[i]);
                }
            }
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] > 0) {
                while (ans[i] > 0) {
                    list.add(String.valueOf((char) (i + 97)));
                    ans[i]--;
                }
            }
        }

        return list;
    }

    /**
     * 1419. 数青蛙
     *
     * @param croakOfFrogs
     * @return
     */
    public static int minNumberOfFrogs(String croakOfFrogs) {
        if (croakOfFrogs.length() % 5 != 0) {
            return -1;
        }

        int ans = -1;
        int c = 0;
        int r = 0;
        int o = 0;
        int a = 0;
        int k = 0;
        for (char cc : croakOfFrogs.toCharArray()) {
            if (cc == 'c') c++;
            if (cc == 'r') r++;
            if (cc == 'o') o++;
            if (cc == 'a') a++;
            if (cc == 'k') {
                ans = Math.max(c, ans);
                c--;
                r--;
                o--;
                a--;
            }

            if (c < r || r < o || o < a || a < k) {
                return -1;
            }
        }

        return ans;
    }

    /**
     * 443. 压缩字符串
     *
     * @param chars
     * @return
     */
    public static int compress(char[] chars) {
        if (chars.length == 0) {
            return 0;
        }

        char pre = (char) 30;
        int cnt = 0;
        int idx = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == pre) {
                cnt++;
            } else {
                if (cnt > 1) {
                    for (char c : ("" + cnt).toCharArray()) {
                        chars[idx++] = c;
                    }
                }

                chars[idx++] = chars[i];
                pre = chars[i];
                cnt = 1;
            }
        }

        if (cnt > 1) {
            for (char c : ("" + cnt).toCharArray()) {
                chars[idx++] = c;
            }
        }

        return idx;
    }

    public static boolean validPalindrome(String s) {
        return validPalindromeCore(s.toCharArray(), 0, s.length() - 1, false);
    }

    private static boolean validPalindromeCore(char[] chars, int start, int end, boolean flag) {
        while (start < end) {
            if (chars[start] != chars[end]) {
                return !flag && (validPalindromeCore(chars, start + 1, end, true) || validPalindromeCore(chars, start, end - 1, true));
            }

            start++;
            end--;
        }

        return true;
    }


    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            if (!Character.isLetterOrDigit(chars[i])) {
                i++;
                continue;
            }

            if (!Character.isLetterOrDigit(chars[j])) {
                j--;
                continue;
            }

            if (Character.toLowerCase(chars[i]) != Character.toLowerCase(chars[j])) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    public static String[] findOcurrences(String text, String first, String second) {
        String[] textes = text.split(" ");
        ArrayList<String> ans = new ArrayList<>();
        int status = 0;

        for (String s : textes) {
            if (status == 2) {
                ans.add(s);
            }

            if (s.equals(first)) {
                status = 1;
            } else if (status == 1 && s.equals(second)) {
                status = 2;
            } else {
                status = 0;
            }
        }

        return ans.toArray(new String[0]);
    }

    public String toLowerCase(String str) {
        char[] chars = str.toCharArray();
        char[] ans = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 65 && chars[i] <= 90) {
                ans[i] = (char) (chars[i] - 32);
            } else {
                ans[i] = chars[i];
            }
        }

        return new String(ans);
    }

    public static List<String> letterCasePermutation(String S) {
        List<String> ans = new LinkedList<>();
        letterCasePermutationCore(S.toCharArray(), ans, 0);
        return ans;
    }

    public static List<String> letterCasePermutationCore(char[] chars, List<String> ans, int idx) {
        ans.add(new String(chars));
        char c;
        for (int i = idx; i < chars.length; i++) {
            c = chars[i];
            if (Character.isUpperCase(c)) {
                char[] copy = Arrays.copyOf(chars, chars.length);
                copy[i] = Character.toLowerCase(c);

                letterCasePermutationCore(copy, ans, i + 1);
            } else if (Character.isLetter(c)) {
                char[] copy = Arrays.copyOf(chars, chars.length);
                copy[i] = Character.toUpperCase(c);

                letterCasePermutationCore(copy, ans, i + 1);
            }
        }

        return ans;
    }

    public char findTheDifference(String s, String t) {
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        int n = 0;
        for (char c : chars1) {
            n ^= c;
        }

        for (char c : chars2) {
            n ^= c;
        }

        return (char) n;
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

    /**
     * 找出字符串中出现次数最多的字母，次数相同的情况下返回ascii code更小的，没有字母的话返回null(忽略大小写，统一当成小写处理).
     *
     * <p>
     * 提示:
     * 1. 字符串长度在1000以内，且只包含ascii字符
     * </p>
     *
     * @param string 只包含ascii字符的字符串.
     * @return 出现次数最多的字母.
     */
    public static Character mostFrequentLetter(String string) {
        // TODO: 实现这个方法
        //ascii 从0到127
        //a-z 97~122
        int[] count = new int[128];
        char[] charArr = string.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            count[charArr[i]]++;
        }

        int preCount = 0;
        Character output = null;
        for (int i = 97; i < 123; i++) {
            if (count[i] > preCount) {
                output = (char) i;
                preCount = count[i];
            }
        }

        return output;
    }
}
