package com.example.demo.problemset.string;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        String[] ans = findOcurrences("obo jvezipre obo jnvavldde jvezipre jvezipre jnvavldde jvezipre jvezipre jvezipre y jnvavldde jnvavldde obo jnvavldde jnvavldde obo jnvavldde jnvavldde jvezipre",
                "jnvavldde", "y");
        for (String s : ans) {
            System.out.println(s);
        }
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

    public static int compress(char[] chars) {
        int ans = 0;
        int cnt = 0;
        char pre = '\0';
        int preIndex = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == pre) {
                cnt++;
            } else if (cnt > 0) {
                ans += Math.min(2, cnt);
                chars[preIndex] = pre;
                if (cnt > 1) {
                    chars[preIndex + 1] = (char) (cnt + 48);
                }

                preIndex = i;
                cnt = 1;
                pre = chars[i];
            }
        }

        if (cnt != 0) {
            ans += Math.min(2, cnt);
            chars[preIndex] = pre;
            if (cnt > 1) {
                chars[preIndex + 1] = (char) (cnt + 48);
            }
        }

        return ans;
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
