package com.example.demo.problemset.array;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        int[] nums = {2,1, 0, -1};
        System.out.println(maximumProduct(nums));
    }

    public static int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[0] * nums[1] * nums[nums.length - 1], nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3]);
    }

    public static int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i] + Math.max(i - 2 >= 0 ? ans[i - 2] : 0, i - 3 >= 0 ? ans[i - 3] : 0);
        }

        return Math.max(ans[ans.length - 1], ans[ans.length - 2]);

        //return Math.max(coreRob(nums, 0, nums.length - 1), coreRob(nums, 0, nums.length - 2));
    }

    private static int coreRob(int[] nums, int start, int end) {
        if (start > end) {
            return 0;
        }

        return Math.max(nums[end] + coreRob(nums, start, end - 2), nums[end] + coreRob(nums, start, end - 3));
    }

    public static int[] productExceptSelf(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }

        int[] left = new int[nums.length];
        left[0] = 1;
        int[] right = new int[nums.length];
        right[nums.length - 1] = 1;
        for (int i = 1; i < nums.length; i++) {
            left[i] = nums[i - 1] * left[i - 1];
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = nums[i + 1] * right[i + 1];
        }

        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = left[i] * right[i];
        }

        return ans;
    }

    public static int findKthLargest(int[] nums, int k) {
        for (int i = nums.length / 2; i >= 0; i--) {
            downAjust(nums, i, nums.length);
        }

        int last = nums.length - 1;
        while (k > 1) {
            nums[0] = nums[last];
            k--;
            last--;
            downAjust(nums, 0, last);
        }

        return nums[0];
    }

    //3,2,3,1,2,4,5,5,6
    //3,2,1,5,6,4
    private static void downAjust(int[] nums, int parentIndex, int length) {
        int parentVal = nums[parentIndex];
        int childIndex = parentIndex * 2 + 1;

        while (childIndex < length) {
            if ((childIndex + 1) < length && nums[childIndex] < nums[childIndex + 1]) {
                childIndex++;
            }

            if (nums[childIndex] <= parentVal) {
                break;
            }

            nums[parentIndex] = nums[childIndex];
            parentIndex = childIndex;
            childIndex = parentIndex * 2 + 1;
        }

        nums[parentIndex] = parentVal;
    }

    public static int[] singleNumbers(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (map.containsKey(n)) {
                map.remove(n);
            } else {
                map.put(n, 1);
            }
        }

        int[] ans = new int[2];
        Iterator<Integer> ite = map.keySet().iterator();
        ans[0] = ite.next();
        ans[1] = ite.next();
        return ans;
    }

    public static int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        for (int k = 0; k < n; k++) {
            res[k] = new int[n];
        }

        int m = n - 1;
        int _n = m;
        int i = 0;
        int j = 0;
        int turns = 0;

        int num = 1;
        while (turns <= m / 2 && turns <= _n / 2) {
            res[i][j] = num++;
            if (i == turns && j < _n - turns) {
                j++;
            } else if (j == _n - turns && i < m - turns) {
                i++;
            } else if (i == m - turns && i != turns && j > turns) {
                j--;
            } else if (j == turns && i > (turns + 1) && j != _n - turns) {
                i--;
            } else {
                turns++;
                j++;
            }
        }

        return res;
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }

        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        int i = 0;
        int j = 0;
        int turns = 0;

        while (turns <= m / 2 && turns <= n / 2) {
            ans.add(matrix[i][j]);
            System.out.println(matrix[i][j]);
            if (i == turns && j < n - turns) {
                j++;
            } else if (j == n - turns && i < m - turns) {
                i++;
            } else if (i == m - turns && i != turns && j > turns) {
                j--;
            } else if (j == turns && i > (turns + 1) && j != n - turns) {
                i--;
            } else {
                turns++;
                j++;
            }
        }

        return ans;
    }
}
