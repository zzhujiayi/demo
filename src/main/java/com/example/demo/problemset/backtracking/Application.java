package com.example.demo.problemset.backtracking;

import reactor.core.Fuseable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 回溯算法
 */
public class Application {
    public static void main(String[] args) {
        System.out.println(countArrangement(4));
    }

    /**
     * 526. 优美的排列
     *
     * @param N
     * @return
     */
    public static int countArrangement(int N) {
        countArrangement_ans = 0;
        boolean[] used = new boolean[N];
        countArrangement_dfs(N, 1, used);
        return countArrangement_ans;
    }

    private static int countArrangement_ans;

    private static void countArrangement_dfs(int n, int pos, boolean[] used) {
        if (pos > n) {
            countArrangement_ans++;
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (used[i - 1]) {
                continue;
            }

            if (pos % i == 0 || i % pos == 0) {
                used[i - 1] = true;
                countArrangement_dfs(n, pos + 1, used);
                used[i - 1] = false;
            }
        }
    }

    /**
     * 90. 子集 II
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        subsetsWithDup_dfs(nums, 0, used, new ArrayList<>(), ans);
        return ans;
    }

    private void subsetsWithDup_dfs(int[] nums, int first, boolean[] used, List<Integer> items, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(items));
        for (int i = first; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            used[i] = true;
            items.add(nums[i]);
            subsetsWithDup_dfs(nums, i + 1, used, items, ans);
            items.remove(items.size() - 1);
            used[i] = false;
        }
    }

    /**
     * 60. 第k个排列
     *
     * @param n
     * @param k
     * @return
     */
    public static String getPermutation(int n, int k) {
        getPermutation_k = k;
        boolean[] used = new boolean[n];
        getPermutation_dfs(used, n, new ArrayList<>());
        return getPermutation_ans;
    }

    private static int getPermutation_k;
    private static String getPermutation_ans = "";

    private static void getPermutation_dfs(boolean[] used, int n, List<Integer> items) {
        if (getPermutation_k < 0) {
            return;
        }

        if (items.size() == n) {
            getPermutation_k--;

            if (getPermutation_k == 0) {
                StringBuilder sb = new StringBuilder();
                for (Integer i : items) {
                    sb.append(i);
                }

                getPermutation_ans = sb.toString();
            }

            return;
        }

        for (int i = 1; i <= n; i++) {
            if (used[i - 1]) {
                continue;
            }

            used[i - 1] = true;
            items.add(i);
            getPermutation_dfs(used, n, items);
            items.remove(items.size() - 1);
            used[i - 1] = false;
        }
    }

    /**
     * 216. 组合总和 III
     *
     * @param k 个数
     * @param n 相加之和为n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        combinationSum3_dfs(k, n, 1, ans, new ArrayList<>());
        return ans;
    }

    private void combinationSum3_dfs(int k, int n, int first, List<List<Integer>> ans, ArrayList<Integer> items) {
        if (k == 0 && n == 0) {
            ans.add(new ArrayList<>(items));
        }

        for (int i = first; i < 10; i++) {
            if (n - i < 0) {
                break;
            }

            items.add(i);
            combinationSum3_dfs(k - 1, n - i, i + 1, ans, items);
            items.remove(items.size() - 1);
        }
    }

    /**
     * 22. 括号生成
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        char[] items = new char[n * 2];
        for (int i = 0; i < n * 2; i++) {
            items[i] = ')';
        }

        generateParenthesis_dfs(n, 0, 0, ans, items);
        return ans;
    }

    private static void generateParenthesis_dfs(int n, int begin, int len, List<String> ans, char[] items) {
        if (n == 0) {
            ans.add(new String(items));
            return;
        }

        int nextLen = len + 2;
        for (int i = begin; i <= len; i++) {
            items[i] = '(';
            generateParenthesis_dfs(n - 1, i + 1, nextLen, ans, items);
            items[i] = ')';
        }
    }

    /**
     * 1079. 活字印刷
     *
     * @param tiles
     * @return
     */
    public static int numTilePossibilities(String tiles) {
        boolean[] used = new boolean[tiles.length()];
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars);
        numTilePossibilities_dfs(chars, used);
        return numTiles - 1;
    }

    private static int numTiles;

    private static void numTilePossibilities_dfs(char[] tiles, boolean[] used) {
        numTiles++;
        for (int i = 0; i < tiles.length; i++) {
            if (used[i]) {
                continue;
            }

            if (i > 0 && tiles[i] == tiles[i - 1] && !used[i - 1]) {
                continue;
            }

            used[i] = true;
            numTilePossibilities_dfs(tiles, used);
            used[i] = false;
        }
    }

    /**
     * 89. 格雷编码
     *
     * @param n
     * @return
     */
    public static List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<Integer>() {{
            add(0);
        }};

        int head = 1;
        for (int i = 0; i < n; i++) {
            for (int j = ans.size() - 1; j >= 0; j--) {
                ans.add(head + ans.get(j));
            }

            head <<= 1;
        }

        return ans;
    }

    /**
     * 39. 组合总和
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        int[] used = new int[candidates.length];
        combinationSum_dfs(candidates, target, 0, new LinkedList<>(), ans, used);
        return ans;
    }

    private static void combinationSum_dfs(int[] candidates, int target, int begin, LinkedList<Integer> items, List<List<Integer>> ans, int[] used) {
        if (target == 0) {
            ans.add(new ArrayList<>(items));
        }

        for (int i = begin; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                break;
            }

            items.add(candidates[i]);
            combinationSum_dfs(candidates, target - candidates[i], i, items, ans, used);
            items.removeLast();
        }
    }

    /**
     * 47. 全排列 II
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        permuteUnique_dfs(nums, nums.length, new LinkedList<>(), ans, used);
        return ans;
    }

    private static void permuteUnique_dfs(int[] nums, int len, LinkedList<Integer> items, List<List<Integer>> ans, boolean[] used) {
        if (items.size() == len) {
            ans.add(new ArrayList<>(items));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            items.add(nums[i]);
            used[i] = true;
            permuteUnique_dfs(nums, len, items, ans, used);
            used[i] = false;
            items.removeLast();
        }
    }

    /**
     * 40.组合总和 II
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);

        combinationSum2_dfs(ans, new LinkedList<>(), target, 0, candidates);

        return ans;
    }

    private static void combinationSum2_dfs(List<List<Integer>> ans, LinkedList<Integer> items, int residue, int first, int[] candidates) {
        if (residue == 0) {
            ans.add(new ArrayList<>(items));
        }

        for (int i = first; i < candidates.length; i++) {
            if (residue - candidates[i] < 0) {
                break;
            }

            if (i > first && candidates[i] == candidates[i - 1]) {
                continue;
            }

            items.add(candidates[i]);
            combinationSum2_dfs(ans, items, residue - candidates[i], i + 1, candidates);
            items.removeLast();
        }
    }

    /**
     * 77.组合
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        combine_dfs(n, 1, k, new ArrayList<>(), ans);
        return ans;
    }

    private void combine_dfs(int n, int first, int k, List<Integer> items, List<List<Integer>> ans) {
        if (items.size() == k) {
            ans.add(new ArrayList<>(items));
        }

        for (int i = first; i <= n; i++) {
            items.add(i);

            combine_dfs(n, i + 1, k, items, ans);

            items.remove(items.size() - 1);
        }
    }

    /**
     * 78. 子集
     * nums = [1,2,3]
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length + 1; i++) {
            subsets_dfs(nums, 0, i, new ArrayList<>(), ans);
        }

        return ans;
    }

    private static void subsets_dfs(int[] nums, int first, int len, List<Integer> items, List<List<Integer>> ans) {
        if (len == items.size()) {
            ans.add(new ArrayList<>(items));
        }

        for (int i = first; i < nums.length; i++) {
            items.add(nums[i]);

            subsets_dfs(nums, i + 1, len, items, ans);

            items.remove(items.size() - 1);
        }
    }

    /**
     * 17. 电话号码的字母组合
     *
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) {
            return ans;
        }

        String[] combos = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        char[] chars = new char[digits.length()];
        letterCombinations_dfs(combos, digits, 0, digits.length(), chars, ans);
        return ans;
    }

    private static void letterCombinations_dfs(String[] combos, String digits, int idx, int len, char[] chars, List<String> ans) {
        if (idx >= len) {
            ans.add(new String(chars));
            return;
        }

        char[] array = combos[digits.charAt(idx) - 48].toCharArray();
        int next = idx + 1;
        for (int i = 0; i < array.length; i++) {
            chars[idx] = array[i];
            letterCombinations_dfs(combos, digits, next, len, chars, ans);
        }
    }
}
