package com.example.demo.problemset.sort;

import com.example.demo.problemset.linkedlist.ListNode;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        String s = "abpcplea";
        List<String> d = new ArrayList<>();
        d.add("ale");
        d.add("apple");
        d.add("monkey");
        d.add("plea");
        findLongestWord(s, d);
    }

    /**
     * @param s
     * @param d
     * @return
     */
    public static String findLongestWord(String s, List<String> d) {
        char[] chars = s.toCharArray();
        String ans = "";
        char[] items;
        int i;
        int j;
        for (String str : d) {
            if (str.length() < ans.length()) {
                continue;
            }

            items = str.toCharArray();
            i = 0;
            j = 0;
            while (i < chars.length && j < items.length) {
                if (chars[i] == items[j]) {
                    j++;
                }

                i++;
            }

            if (j == items.length && (ans.length() == 0 || str.length() > ans.length() || ans.compareTo(str) > 0)) {
                ans = str;
            }
        }

        return ans;
    }

    /**
     * 274. H 指数
     *
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int ans = 0;
        while (ans < citations.length && citations[citations.length - ans - 1] > ans) {
            ans++;
        }

        return ans;
    }

    /**
     * 179. 最大数
     *
     * @param nums
     * @return
     */
    public static String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strs, (a, b) -> {
            String s1 = a + b;
            String s2 = b + a;
            return s2.compareTo(s1);
        });

        if (strs[0].equals("0")) {
            return "0";
        }

        StringBuilder ans = new StringBuilder();
        for (String s : strs) {
            ans.append(s);
        }

        return ans.toString();
    }

    /**
     * @param head
     * @return
     */
    public static ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode next;
        ListNode dummy0 = dummy;

        while (head != null) {
            next = head.next;
            if (head.val < dummy0.val) {
                dummy0 = dummy;
            }

            while (dummy0.next != null && dummy0.next.val < head.val) {
                dummy0 = dummy0.next;
            }

            head.next = dummy0.next;
            dummy0.next = head;

            head = next;
        }

        return dummy.next;
    }

    private static void insertionSortList_core(ListNode ans, ListNode node) {
        while (ans.next != null && ans.next.val < node.val) {
            ans = ans.next;
        }

        node.next = ans.next;
        ans.next = node;
    }

    /**
     * 75. 颜色分类
     *
     * @param nums
     */
    public static void sortColors(int[] nums) {
        int p0 = 0;
        int p2 = nums.length - 1;
        int cur = 0;
        while (cur <= p2) {
            if (nums[cur] == 0) {
                nums[cur] = nums[p0];
                nums[p0] = 0;
                p0++;
                cur++;
            } else if (nums[cur] == 2) {
                nums[cur] = nums[p2];
                nums[p2] = 2;
                p2--;
            } else {
                cur++;
            }
        }
    }

    /**
     * 1370. 上升下降字符串
     *
     * @param s
     * @return
     */
    public static String sortString(String s) {
        char[] chars = s.toCharArray();
        char[] ans = new char[s.length()];

        int[] map = new int[26];
        for (int i = 0; i < chars.length; i++) {
            map[chars[i] - 97]++;
        }


        int idx = 0;
        while (idx < chars.length) {
            for (int i = 0; i < 26; i++) {
                if (map[i] > 0) {
                    ans[idx++] = (char) (i + 97);
                    map[i]--;
                }
            }

            for (int i = 25; i >= 0; i--) {
                if (map[i] > 0) {
                    ans[idx++] = (char) (i + 97);
                    map[i]--;
                }
            }
        }

        return new String(ans);
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
