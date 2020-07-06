package com.example.demo.problemset.dp;

import com.example.demo.problemset.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class DP {
    public static void main(String[] args) {
        generateTrees(3);
    }

    /**
     * 95. 不同的二叉搜索树 II
     *
     * @param n
     * @return
     */
    public static List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return new ArrayList<>();
        }

        return generateTreesCore(1, n);
    }

    private static List<TreeNode> generateTreesCore(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = generateTreesCore(start, i - 1);
            List<TreeNode> rights = generateTreesCore(i + 1, end);

            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;

                    result.add(node);
                }
            }

        }

        return result;
    }

    /**
     * 96. 不同的二叉搜索树
     *
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 1;
        ans[1] = 1;
        for (int i = 2; i <= n; i++) {
            int total = 0;
            for (int j = 1; j <= i; j++) {
                total += ans[j - 1] * ans[i - j];
            }

            ans[i] = total;
        }

        return ans[n];
    }

    /**
     * 64. 最小路径和
     *
     * @param grid
     * @return
     */
    public static int minPathSum(int[][] grid) {
        int h = grid.length;
        int w = grid[0].length;
        int r;
        int b;
        for (int i = h - 1; i >= 0; i--) {
            for (int j = w - 1; j >= 0; j--) {
                if (j + 1 == w && i + 1 == h) {
                    continue;
                }

                if (j + 1 == w) {
                    r = Integer.MAX_VALUE;
                } else {
                    r = grid[i][j + 1];
                }

                if (i + 1 == h) {
                    b = Integer.MAX_VALUE;
                } else {
                    b = grid[i + 1][j];
                }

                grid[i][j] = grid[i][j] + Math.min(r, b);
            }
        }

        return grid[0][0];
    }

    /**
     * @param w 工人总数量
     * @param n 金矿数量
     * @param p 金矿开采需要的人数
     * @param g 金矿含金量
     * @return
     */
    static int getBest(int w, int n, int[] p, int[] g) {
        if (w == 0 || n == 0) {
            return 0;
        }

        if (w < p[n - 1]) {
            int b = getBest(w, n - 1, p, g);
            System.out.println("w<p[n-1]  --" + b);
            return b;
        }

        int a = getBest(w, n - 1, p, g);
        System.out.println("getBest(w, n - 1, p, g)--" + a);
        int b = getBest(w - p[n - 1], n - 1, p, g) + g[n - 1];
        System.out.println("getBest(w - p[n - 1], n - 1, p, g) + g[n - 1]   --" + a);

        return Math.max(a, b);
    }

    static int getBest_Sec(int w, int n, int[] p, int[] g) {
        int[][] c = new int[p.length + 1][w + 1];
        for (int i = 1; i <= p.length; i++) {
            for (int j = 1; j <= w; j++) {
                if (p[i - 1] > j) {
                    c[i][j] = c[i - 1][j];
                } else {
                    c[i][j] = Math.max(c[i - 1][j], c[i - 1][j - p[i - 1]] + g[i - 1]);
                }

                System.out.print(c[i][j]);
                System.out.print("\t");
            }

            System.out.println();
        }


        return c[p.length][w];
    }

    static int climbStairs(int n) {
        if (n < 4) {
            return n;
        }

        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 2;
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        return arr[n];
    }

    public static int minCostClimbingStairs(int[] cost) {
        if (cost.length < 2) {
            return 0;
        }

        if (cost.length == 2) {
            return Math.min(cost[0], cost[1]);
        }

        int[] minCost = new int[cost.length];
        minCost[0] = cost[0];
        minCost[1] = cost[1];

        for (int i = 2; i < cost.length; i++) {
            minCost[i] = Math.min(cost[i] + minCost[i - 1], cost[i] + minCost[i - 2]);
        }

        return Math.min(minCost[cost.length - 1], minCost[cost.length - 2]);
        //return minCostClimbingStairsCore(cost, cost.length);
    }

    public static int minCostClimbingStairsCore(int[] cost, int n) {
        if (n < 0) {
            return 0;
        }

        return Math.min((n - 1 >= 0 ? cost[n - 1] : 0) + minCostClimbingStairsCore(cost, n - 1), (n - 2 >= 0 ? cost[n - 2] : 0) + minCostClimbingStairsCore(cost, n - 2));
    }


    public static int[] replaceElements(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }

        if (arr.length == 1) {
            arr[0] = -1;
            return arr;
        }

        if (arr.length == 2) {
            arr[0] = arr[1];
            arr[1] = -1;
            return arr;
        }

        int maxValue = 0;
        int maxIndex = -1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (i >= maxIndex) {
                maxIndex = i + 1;
                maxValue = arr[maxIndex];

                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] > arr[maxIndex]) {
                        maxIndex = j;
                        maxValue = arr[maxIndex];
                    }
                }
            }

            arr[i] = maxValue;
        }

        arr[arr.length - 1] = -1;
        return arr;
    }

    public static int arrayPairSum(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        boolean nextAdd = true;
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nextAdd) {
                total += nums[i];
                nextAdd = false;
            } else {
                nextAdd = true;
            }
        }

        return total;
    }

    public static void quickSort(int[] nums, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return;
        }

        int pivotIndex = partition(nums, startIndex, endIndex);
        quickSort(nums, 0, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, endIndex);
    }

    public static int partition(int[] nums, int startIndex, int endIndex) {
        int pivot = nums[startIndex];
        int mark = startIndex;
        int t;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (pivot > nums[i]) {
                mark += 1;
                t = nums[i];
                nums[i] = nums[mark];
                nums[mark] = t;

            }

        }

        t = nums[mark];
        nums[mark] = nums[startIndex];
        nums[startIndex] = t;
        return mark;
    }

}
