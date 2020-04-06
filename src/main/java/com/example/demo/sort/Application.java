package com.example.demo.sort;

import java.util.HashMap;
import java.util.HashSet;

public class Application {
    public static void main(String[] args) {
        char[][] board = new char[9][9];
        board[0][0] = '5';
        board[0][1] = '3';
        board[0][4] = '7';
        board[1][0] = '6';
        board[1][3] = '1';
        board[1][4] = '9';
        board[1][5] = '5';
        board[2][1] = '9';
        board[2][2] = '8';
        board[2][7] = '6';
        board[3][0] = '8';
        board[3][4] = '6';
        board[3][8] = '3';
        board[4][0] = '4';
        board[4][3] = '8';
        board[4][5] = '3';
        board[4][8] = '1';
        board[5][0] = '7';
        board[5][4] = '2';
        board[5][8] = '6';
        board[6][1] = '6';
        board[6][6] = '2';
        board[6][7] = '8';
        board[7][3] = '4';
        board[7][4] = '1';
        board[7][5] = '9';
        board[7][8] = '5';
        board[8][4] = '8';
        board[8][7] = '7';
        board[8][8] = '9';

        isValidSudoku(board);
    }

    public static boolean isValidSudoku(char[][] board) {
        HashMap<Integer, Integer>[] rows = new HashMap[9];
        HashMap<Integer, Integer>[] cols = new HashMap[9];
        HashMap<Integer, Integer>[] subs = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<>();
            cols[i] = new HashMap<>();
            subs[i] = new HashMap<>();
        }

        for (int i = 0; i < 9; i++) {
            HashMap<Integer, Integer> row = rows[i];
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }

                int num = c;
                HashMap<Integer, Integer> col = cols[j];
                int subIndex = i / 3 * 3 + j / 3;
                HashMap<Integer, Integer> sub = subs[subIndex];
                row.put(num, row.getOrDefault(num, 0) + 1);
                col.put(num, col.getOrDefault(num, 0) + 1);
                sub.put(num, sub.getOrDefault(num, 0) + 1);

                if (row.get(num) > 1 || col.get(num) > 1 || sub.get(num) > 1) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int lengthOfLongestSubstring(String s) {
        int length = s.length();
        int maxLength = 0;
        int[] map = new int[128];
        for (int i = 0, j = 0; j < length; j++) {
            char c = s.charAt(j);
            if (map[c] > 0) {
                i = Math.max(i, map[c]);
            }

            maxLength = Math.max(maxLength, j - i + 1);
            map[c] = j + 1;
        }

        return maxLength;
    }

    public static String frequencySort(String s) {
        int[] map = new int[128];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map[c]++;
        }

        StringBuilder result = new StringBuilder();
        while (true) {
            int maxCount = 0;
            int c = 0;
            for (int i = 0; i < map.length; i++) {
                if (map[i] > maxCount) {
                    c = i;
                    maxCount = map[i];
                }
            }

            if (c == 0) {
                break;
            }

            map[c] = 0;
            while (maxCount > 0) {
                result.append((char) c);
                maxCount--;
            }
        }

        return result.toString();
    }

    public static int firstUniqChar(String s) {
        //a 97, z 122
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c - 97]++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 97] == 1) {
                return i;
            }
        }

        return -1;
    }

    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                return true;
            }

            map.put(i, 1);
        }

        return false;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }

        HashSet<Integer> set2 = new HashSet<>();
        for (int i : nums2) {
            set2.add(i);
        }

        set1.retainAll(set2);
        int[] result = new int[set1.size()];
        int i = 0;
        for (int n : set1) {
            result[i++] = n;
        }

        return result;
    }

    public static int[] sortArrayByParityII(int[] A) {
        int i = 0;
        int j = 1;
        int tmp;
        while (i < A.length) {
            if (A[i] % 2 != 0) {
                while (A[j] % 2 != 0) {
                    j += 2;
                }

                tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }

            i += 2;
        }

        return A;
    }

    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] count = new int[1001];
        for (int i : arr1) {
            count[i]++;
        }

        int[] result = new int[arr1.length];
        int j = 0;
        for (int i : arr2) {
            while (count[i] > 0) {
                result[j] = i;
                j++;
                count[i]--;
            }
        }

        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                result[j] = i;
                j++;
                count[i]--;
            }
        }

        return result;
    }
}
