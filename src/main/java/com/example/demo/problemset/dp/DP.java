package com.example.demo.problemset.dp;

import com.example.demo.problemset.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 动态规划
 */
public class DP {
    public static void main(String[] args) {


        DP dp = new DP();
        System.out.println(dp.getRow(4));
    }

    /**
     * 119. 杨辉三角 II
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        int mid = (int) (Math.ceil((rowIndex + 1) * 1.0 / 2));
        ans.add(1);
        if (rowIndex >= 2) {
            ans.add(rowIndex);
        }

        for (int i = 2; i < mid; i++) {
            int n = 1;
            int c = rowIndex - i;
            while (c >= 0) {
                n += i;
                c--;
            }

            ans.add(n);
        }

        for (int i = ans.size() - 1; i >= 0; i--) {
            ans.add(ans.get(i));
        }

        return ans;
    }

    /**
     * 152. 乘积最大子数组
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int ans = nums[0];
        int max = ans;
        int min = ans;
        for (int i = 1; i < nums.length; i++) {
            int mm = Math.max(max * nums[i], Math.max(nums[i], min * nums[i]));
            int mi = Math.min(min * nums[i], Math.min(nums[i], max * nums[i]));

            max = mm;
            min = mi;

            ans = Math.max(ans, max);
        }

        return ans;
    }

    /**
     * 139. 单词拆分
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int max = 0;
        HashSet<String> hashSet = new HashSet<>();
        for (String s1 : wordDict) {
            max = Math.max(max, s1.length());
            hashSet.add(s1);
        }

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = (Math.max(0, i - max)); j < i; j++) {
                if (dp[j] && hashSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public int fib(int n) {
        int a = 0;
        int b = 1;
        int sum;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }

        return a;
    }

    /**
     * 45. 跳跃游戏 II
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int maxPosition = 0;
        int ans = 0;
        int end = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + i > maxPosition) {
                maxPosition = nums[i] + i;
            }

            if (i == end) {
                //更新边界
                end = maxPosition;
                ans++;
            }
        }

        return ans;
    }

    /**
     * 799. 香槟塔
     *
     * @param poured      倾倒香槟总杯数
     * @param query_row   行数
     * @param query_glass 杯子的位置数
     * @return
     */
    public static double champagneTower(int poured, int query_row, int query_glass) {
        double[][] towers = new double[101][101];
        towers[0][0] = poured;
        for (int i = 0; i <= query_row; i++) {
            for (int j = 0; j <= i; j++) {
                if (towers[i][j] <= 1) {
                    continue;
                }

                double overflow = towers[i][j] - 1;
                double half = overflow / 2;
                towers[i + 1][j] += half;
                towers[i + 1][j + 1] += half;
            }
        }

        return Math.min(1, towers[query_row][query_glass]);
    }

    private static void champagneTowerCore(double[][] towers, int i, int j, double add) {
        if (towers.length <= i) {
            return;
        }

        towers[i][j] += add;
        double size = towers[i][j];
        if (size <= 1) {
            return;
        }

        double overflow = size - 1;
        towers[i][j] = 1;
        double half = overflow / 2;
        champagneTowerCore(towers, i + 1, j, half);
        champagneTowerCore(towers, i + 1, j + 1, half);
    }


    /**
     * 343. 整数拆分
     *
     * @param n
     * @return
     */
    public static int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            int max = 0;
            for (int j = i - 2; j > 0; j--) {
                max = Math.max(Math.max(max, dp[j] * (i - j)), j * (i - j));
            }

            dp[i] = max;
        }
        return dp[n];
    }

    /**
     * 1005. K 次取反后最大化的数组和
     *
     * @param A
     * @param K
     * @return
     */
    public static int largestSumAfterKNegations(int[] A, int K) {
        int[] map = new int[201];
        int sum = 0;
        for (int n : A) {
            map[n + 100]++;
            sum += n;
        }

        int pre = 0;
        for (int i = 0; i < map.length && K > 0; i++) {
            for (int j = map[i]; j > 0 && K > 0; j--) {
                if (i < 100) {
                    pre = (100 - i) * 2;
                    sum += pre;
                } else {
                    if (K % 2 == 0) {
                        return sum;
                    }

                    if (pre > 0 && pre < (i - 100) * 2) {
                        sum -= pre;
                    } else {
                        sum -= (i - 100) * 2;
                    }

                    pre = 0;
                }

                K--;
            }
        }

        return sum;
    }

    /**
     * 416. 分割等和子集
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        for (int n : nums) {

        }
        return false;
    }

    /**
     * 309. 最佳买卖股票时机含冷冻期
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0];
        //0 已买入股票的最大收益，买入
        //1 处于冷冻期的最大收益，卖出/或冷冻期
        //2 不处于冷冻期的最大收益，可买入
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][2] - prices[i], dp[i - 1][0]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1]);
        }

        return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]);
    }

    /**
     * 413. 等差数列划分
     *
     * @param A
     * @return
     */
    public static int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) {
            return 0;
        }

        int[] dp = new int[A.length];
        int n = 0;
        for (int i = 2; i < dp.length; i++) {
            n += (i - 1);
            dp[i] = n;
        }

        int i = 1, j = 2, count = 2, ans = 0;
        int preDiff = A[1] - A[0];
        while (j < A.length) {
            if (A[j] - A[i] != preDiff) {
                ans += dp[count - 1];
                preDiff = A[j] - A[i];
                count = 1;
            }

            i++;
            j++;
            count++;
        }

        ans += dp[count - 1];
        return ans;
    }

    /**
     * 91. 解码方法
     *
     * @param s
     * @return
     */
    public static int numDecodings(String s) {
        char[] chars = s.toCharArray();
        int[] ans = new int[chars.length];
        ans[0] = 1;
        if (chars[0] == '1' || (chars[0] == '2' && chars[1] <= '6')) {
            ans[1] = 2;
        } else {
            ans[1] = 1;
        }

        for (int i = 1; i < chars.length; i++) {
            if (chars[i - 1] == '1' || (chars[i - 1] == '2' && chars[i] <= '6')) {
                if (i - 2 > 0) {
                    ans[i] = ans[i - 2] * 2;
                } else {
                    ans[i] = ans[i - 1] + 1;
                }
            } else {
                ans[i] = ans[i - 1];
            }
        }

        return ans[ans.length - 1];
    }

    private static int numDecodingsCore(char[] chars, int start) {
        if (start >= chars.length) {
            return 0;
        }

        int ans = 1;
        ans += numDecodingsCore(chars, start + 1);
        if (start + 1 < chars.length) {
            int t = (chars[start] - 48) * 10 + chars[start + 1] - 48;
            if (t < 27) {
                ans++;
                ans += numDecodingsCore(chars, start + 2);
            }
        }

        return ans;
    }

    /**
     * 120. 三角形最小路径和
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) {
            return 0;
        }

        List<Integer> list;
        List<Integer> nextLevel;
        for (int i = triangle.size() - 2; i >= 0; i--) {
            list = triangle.get(i);
            nextLevel = triangle.get(i + 1);
            for (int j = 0; j < list.size(); j++) {
                list.set(j, list.get(j) + Math.min(nextLevel.get(j), nextLevel.get(j + 1)));
            }
        }

        return triangle.get(0).get(0);
    }

    /**
     * 63. 不同路径 II
     *
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] tables = new int[obstacleGrid.length][obstacleGrid[0].length];
        int n = 1;
        for (int i = obstacleGrid.length - 2; i >= 0; i--) {
            n -= obstacleGrid[i][obstacleGrid[i].length - 1];
            tables[i][obstacleGrid[i].length - 1] = Math.max(0, n);
        }

        n = 1;
        for (int i = obstacleGrid[0].length - 2; i >= 0; i--) {
            n -= obstacleGrid[obstacleGrid.length - 1][i];
            tables[obstacleGrid.length - 1][i] = Math.max(0, n);
        }

        for (int i = obstacleGrid.length - 2; i >= 0; i--) {
            for (int j = obstacleGrid[i].length - 2; j >= 0; j--) {
                if (obstacleGrid[i][j] != 1) {
                    tables[i][j] = tables[i][j + 1] + tables[i + 1][j];
                }
            }
        }
        return tables[0][0];
    }

    /**
     * 279. 完全平方数
     *
     * @param n
     * @return
     */
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int[] sqrts = new int[((int) Math.sqrt(n)) + 1];
        for (int i = 1; i < sqrts.length; i++) {
            sqrts[i] = i * i;
        }

        for (int i = 1; i <= n; i++) {
            ;
            for (int j = 1; j < sqrts.length; j++) {
                if (i < sqrts[j]) {
                    break;
                }

                dp[i] = Math.min(dp[i], dp[i - sqrts[j]] + 1);
            }
        }

        return dp[n];
    }

    /**
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        //[10,9,2,5,3,7,101,18]
        int[] ans = new int[nums.length];
        int cur;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            cur = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    cur = Math.max(ans[j], cur);
                }
            }

            ans[i] = cur + 1;
            max = Math.max(ans[i], max);
        }

        return max;
    }

    /**
     * 338. 比特位计数
     *
     * @param num
     * @return
     */
    public static int[] countBits(int num) {
        int[] ans = new int[num + 1];
        int temp = 0;
        int next = 1;
        for (int i = 1; i <= num; i++) {
            if (i >= next) {
                temp = next;
                next <<= 1;
            }

            if (temp == i) {
                ans[i] = 1;
                continue;
            }

            ans[i] = ans[i - temp] + 1;
        }

        return ans;
    }

    /**
     * 213. 打家劫舍 II
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        return Math.max(robCore(nums, 0, nums.length - 1), robCore(nums, 1, nums.length));
    }

    private static int robCore(int[] nums, int start, int end) {
        int cur = 0;
        int pre = 0;
        int temp;
        //pre cur
        //0,  0,  1,2,10,2,3,10
        for (int i = start; i < end; i++) {
            temp = cur;
            cur = Math.max(pre + nums[i], cur);
            pre = temp;
        }

        return cur;
    }

    /**
     * 264. 丑数 II
     *
     * @param n
     * @return
     */
    public static int nthUglyNumber(int n) {
        int[] ans = new int[n];
        ans[0] = 1;
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        int ugly;
        for (int i = 1; i < n; i++) {
            ugly = Math.min(Math.min(ans[i2] * 2, ans[i3] * 3), ans[i5] * 5);
            ans[i] = ugly;
            if (ans[i2] * 2 == ugly) {
                i2++;
            }
            if (ans[i3] * 3 == ugly) {
                i3++;
            }
            if (ans[i5] * 5 == ugly) {
                i5++;
            }
        }

        return ans[n - 1];
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
     * 62. 不同路径
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        int[][] tables = new int[m][n];

        for (int i = 0; i < m; i++) {
            tables[i][n - 1] = 1;
        }

        for (int i = 0; i < n; i++) {
            tables[m - 1][i] = 1;
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                tables[j][i] = tables[j + 1][i] + tables[j][i + 1];
            }
        }

        return tables[0][0];
    }

    /**
     * 1025. 除数博弈
     *
     * @param N
     * @return
     */
    public boolean divisorGame(int N) {
        return N % 2 == 0;
    }

    /**
     * 303. 区域和检索
     */
    public static class NumArray {
        private int[] sums;

        public NumArray(int[] nums) {
            sums = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                sums[i] = nums[i];
                if (i > 0) {
                    sums[i] += sums[i - 1];
                }
            }
        }

        public int sumRange(int i, int j) {
            if (i > j || i < 0 || j >= sums.length) {
                return 0;
            }

            return i > 0 ? sums[j] - sums[i - 1] : sums[j];
        }
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
